package negocio;

import java.sql.*;
import java.util.List;

import exceptions.*;
import dados.*;
import persistencia.*;

public class SistemaBD {
    private UsuarioDAO usuarioDAO;

    public SistemaBD(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        usuarioDAO = UsuarioDAO.getInstance();
    }

    public void inserirUsuario(Usuario usuario) throws InsertException, SelectException {
        usuarioDAO.insert(usuario);
    }
    public List<Usuario> selectAll() throws SelectException {
        return usuarioDAO.selectAll();
    }
    public Usuario buscarUsuario(int id) {
        try {
            return usuarioDAO.select(id);
        } catch (SelectException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }
    public boolean loginUsuario(int id, String senha) {
        Usuario idUsuario = buscarUsuario(id);
        if (idUsuario != null 
        && idUsuario.getId() == id 
        && idUsuario.getSenha().equals(senha))
            return true;
        return false;
    }
    public boolean enviarEmail(Email email) {
        Usuario destinatario = buscarUsuario(email.getIdDestinatario());
        if (destinatario.getId() == email.getIdDestinatario() 
        && destinatario != null) {
            destinatario.adicionarEmail(email);
            return true;
        }
        return false;
    }
    public void descriptarEmail(Usuario usuario, Email email) {
        usuario.cifraCesar(email, false);
    }
}
