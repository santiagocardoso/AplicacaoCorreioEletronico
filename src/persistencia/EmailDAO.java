package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dados.*;
import exceptions.*;

public class EmailDAO {
    private static EmailDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement delete;

    public static EmailDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null)
            instance = new EmailDAO();
        return instance;
    }

    private EmailDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_email')");
        insert = conexao.prepareStatement("insert into email values(?,?,?,?,?,?,?)");
        select = conexao.prepareStatement("select * from email where id_usuario = ?");
        delete = conexao.prepareStatement("delete from email where id_usuario = ?");
    }

    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar novo id da tabela email!");
        }
        return 0;
    }
    public void insert(Email email) throws InsertException, SelectException {
        try {
            insert.setInt(1, selectNewId());
            insert.setString(2, email.getRemetente());
            insert.setString(3, email.getDestinatario());
            insert.setString(4, email.getCorpo());
            insert.setString(5, email.getData());
            insert.setString(6, email.getHora());
            insert.setInt(7, email.getIdUsuario());
            insert.executeQuery();
        }
        catch (SQLException e) {
            throw new InsertException("Erro ao inserir email!");
        }
    }
    public List<Email> selectAll(int usuario) throws SelectException {
        List<Email> emails = new ArrayList<>();
        try {
            select.setInt(1, usuario);
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String remetente = rs.getString(2);
                String destinatario = rs.getString(3);
                String corpo = rs.getString(4);
                String data = rs.getString(5);
                String hora = rs.getString(6);
                int idUsuario = rs.getInt(7);
                int idDestinatario = rs.getInt(8);
                
                Email email = new Email(id, remetente, destinatario, corpo, data, hora, idUsuario, idDestinatario);
                emails.add(email);
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar email do usuario!");
        }
        return emails;
    }
    public void delete(Email email) throws DeleteException {
        try {
            delete.setInt(1, email.getIdUsuario());
            delete.executeUpdate();
        }
        catch (SQLException e) {
            throw new DeleteException("Erro ao deletar email!");
        }
    }
}
