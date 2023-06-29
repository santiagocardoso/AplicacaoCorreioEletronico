package persistencia;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import dados.*;
import exceptions.*;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;
    private static List<Email> emails = new LinkedList<>();

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    private PreparedStatement insert;

    public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null)
            instance = new UsuarioDAO();
        return instance;
    }

    private UsuarioDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('seq_id_usuario')");
        insert = conexao.prepareStatement("insert into usuario values(?,?,?,?)");
        select = conexao.prepareStatement("select * from usuario where id = ?");
        selectAll = conexao.prepareStatement("select * from usuario");
    }

    public int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar novo id da tabela usuario!");
        }
        return 0;
    }
    public void adicionarEmail(Email email) {
        emails.add(email);
    }
    public List<Email> getEmails() {
        return emails;
    }
    public boolean insert(Usuario usuario) throws InsertException, SelectException {
        try {
            usuario.setId(selectNewId());
            insert.setInt(1, usuario.getId());
            insert.setString(2, usuario.getUsuario());
            insert.setString(3, usuario.getEnderecoEmail());
            insert.setString(4, usuario.getSenha());
            insert.executeQuery();
            return true;
        }
        catch (SQLException e) {
            throw new InsertException("Erro ao inserir usuário!");
        }
    }
    public Usuario select(int usuario) throws SelectException {
        try {
            select.setInt(1, usuario);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String login = rs.getString(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);

                return new Usuario(id, login, email, senha);
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar usuário!");
        }
        return null;
    }
    public List<Usuario> selectAll() throws SelectException {
        List<Usuario> usuarios = new LinkedList<>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String login = rs.getString(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);

                usuarios.add(new Usuario(id, login, email, senha));
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar usuarios!");
        }
        return usuarios;
    }
}
