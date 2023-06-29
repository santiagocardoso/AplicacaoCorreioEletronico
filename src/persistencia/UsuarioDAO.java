package persistencia;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import dados.*;
import exceptions.*;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;
    private static EmailDAO emailDAO = null;
    private static ArrayList<Email> emails = new ArrayList<>();

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
        selectNewId = conexao.prepareStatement("select nextval('id_usuario')");
        insert = conexao.prepareStatement("insert into usuario values(?,?,?,?)");
        select = conexao.prepareStatement("select * from usuario where id_usuario = ?");
        selectAll = conexao.prepareStatement("select * from usuario");

        emailDAO = EmailDAO.getInstance();
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
    public ArrayList<Email> getEmails() {
        return emails;
    }
    public void insert(Usuario usuario) throws InsertException, SelectException {
        try {
            usuario.setId(selectNewId());
            insert.setInt(1, usuario.getId());
            insert.setString(2, usuario.getUsuario());
            insert.setString(3, usuario.getEnderecoEmail());
            insert.setString(4, usuario.getSenha());
            insert.executeQuery();

            for (Email email : usuario.getEmails()) {
                cifraCesar(email, true);
                adicionarEmail(email);  
            }  
        }
        catch (SQLException e) {
            throw new InsertException("Erro ao inserir usuario!");
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
                List<Email> emails = emailDAO.selectAll(id);

                return new Usuario(id, login, email, senha, emails);
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar usuario!");
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
                List<Email> emails = emailDAO.selectAll(id);

                usuarios.add(new Usuario(id, login, email, senha, emails));
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar usuarios!");
        }
        return usuarios;
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
    public void cifraCesar(Email email, boolean modo) {
        int id = email.getId();
        int dia = ((int) email.getData().charAt(0) - '0') + ((int) email.getData().charAt(1) - '0');

        if (modo) {
            int cifra = id + dia;
            email.setCorpo(encriptar(email.getCorpo(), cifra));
        }
        else {
            int cifra = id + dia;
            email.setCorpo(descriptar(email.getCorpo(), cifra));
        }
    }
}
