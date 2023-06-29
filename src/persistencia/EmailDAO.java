package persistencia;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import dados.*;
import exceptions.*;

public class EmailDAO {
    private static EmailDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;

    public static EmailDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null)
            instance = new EmailDAO();
        return instance;
    }

    private EmailDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('seq_id_email')");
        insert = conexao.prepareStatement("insert into email values(?,?,?,?,?,?,?,?)");
        selectAll = conexao.prepareStatement("select * from email");
        delete = conexao.prepareStatement("delete from email where id = ?");
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
            insert.setString(4, cifraCesar(email, true));
            insert.setString(5, email.getData());
            insert.setString(6, email.getHora());
            insert.setInt(7, email.getIdUsuario());
            insert.setInt(8, email.getIdDestinatario());
            insert.executeUpdate();
        }
        catch (SQLException e) {
            throw new InsertException("Erro ao inserir email!");
        }
    }
    public List<Email> selectAll() throws SelectException {
        List<Email> emails = new LinkedList<>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String remetente = rs.getString(2);
                String destinatario = rs.getString(3);
                String corpo = descriptar(rs.getString(4), (id + (((int) rs.getString(5).charAt(0) - '0') + ((int) rs.getString(5).charAt(1) - '0'))));
                String data = rs.getString(5);
                String hora = rs.getString(6);
                int idUsuario = rs.getInt(7);
                int idDestinatario = rs.getInt(8);
                
                Email email = new Email(id, remetente, destinatario, corpo, data, hora, idUsuario, idDestinatario);
                emails.add(email);
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar emails!");
        }
        return emails;
    }
    public void delete(Email email) throws DeleteException {
        try {
            delete.setInt(1, email.getId());
            delete.executeUpdate();
        }
        catch (SQLException e) {
            throw new DeleteException("Erro ao deletar email!");
        }
    }

    public String encriptar(String mensagem, int cifra) {
        String encriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                encriptado += mensagem.charAt(i);
            else {
                if (!(Character.isUpperCase(mensagem.charAt(i)))) {
                    if (mensagem.charAt(i) + cifra > 122)
                        encriptado += (char)(mensagem.charAt(i) + cifra - 26);
                    else
                        encriptado += (char)(mensagem.charAt(i) + cifra);
                }
                else {
                    if (mensagem.charAt(i) + cifra > 90)
                        encriptado += (char)(mensagem.charAt(i) + cifra - 26);
                    else
                        encriptado += (char)(mensagem.charAt(i) + cifra);    
                }
            }
        }
        return encriptado;
    }
    public String descriptar(String mensagem, int cifra) {
        String descriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                descriptado += mensagem.charAt(i);
            else {
                if (!(Character.isUpperCase(mensagem.charAt(i)))) {
                    if (mensagem.charAt(i) - cifra < 97)
                        descriptado += (char)(mensagem.charAt(i) - cifra + 26);
                    else
                        descriptado += (char)(mensagem.charAt(i) - cifra);
                }
                else {
                    if (mensagem.charAt(i) - cifra < 65)
                        descriptado += (char)(mensagem.charAt(i) - cifra + 26);
                    else
                        descriptado += (char)(mensagem.charAt(i) - cifra);    
                }
            }
        }
        return descriptado;
    }
    // a b c d e f g h i j k l m n o p q r s t u v w x y z
    // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
    public String cifraCesar(Email email, boolean modo) {
        int id = email.getId();
        int dia = ((int) email.getData().charAt(0) - '0') + ((int) email.getData().charAt(1) - '0');
        int cifra = id + dia;

        if (modo)
            return (encriptar(email.getCorpo(), cifra));
        else
            return (descriptar(email.getCorpo(), cifra));
    }
}
