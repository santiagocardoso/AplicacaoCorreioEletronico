package negocio;

import java.sql.*;
import java.util.List;

import excepctions.*;
import dados.*;
import persistencia.*;

public class SistemaTeste {
    private UsuarioDAO usuarioDAO;

    public SistemaTeste(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public void inserirUsuario(Usuario usuario) throws InsertException, SelectException {
        usuarioDAO.insert(usuario);
    }
    public List<Usuario> selectAll() throws SelectException {
        return usuarioDAO.selectAll();
    }
}
