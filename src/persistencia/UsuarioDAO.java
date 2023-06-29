package persistencia;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import dados.Email;
import dados.Usuario;
import excepctions.DeleteException;
import excepctions.InsertException;
import excepctions.SelectException;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;
    private static EmailDAO emailDAO = null;

    private PreparedStatement selectNewId;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;

    public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null)
            instance = new UsuarioDAO();
        return instance;
    }

    private UsuarioDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('id_usuario')");
        insert = conexao.prepareStatement("insert into usuario values(?,?,?,?)");
        delete = conexao.prepareStatement("delete from usuario where id = ?");
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
    public void insert(Usuario usuario) throws InsertException, SelectException {
        try {
            usuario.setId(selectNewId());
            insert.setInt(1, usuario.getId());
            insert.setString(2, usuario.getUsuario());
            insert.setString(3, usuario.getEnderecoEmail());
            insert.setString(4, usuario.getSenha());
            insert.executeQuery();
        }
        catch (SQLException e) {
            throw new InsertException("Erro ao inserir usuario!");
        }
    }
    public List<Usuario> selectAll() throws SelectException {
        List<Usuario> usuarios = new LinkedList<>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String usuario = rs.getString(2);
                String email = rs.getString(3);
                String senha = rs.getString(4);

                usuarios.add(new Usuario(id, usuario, email, senha));
            }
        }
        catch (SQLException e) {
            throw new SelectException("Erro ao buscar usuario!");
        }
        return usuarios;
    }
}
