package negocio;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import exceptions.*;
import dados.*;
import persistencia.*;

public class Sistema {
    private UsuarioDAO usuarioDAO;
    private EmailDAO emailDAO;

    public Sistema(String senha) throws ClassNotFoundException, SQLException, SelectException {
        Conexao.setSenha(senha);
        usuarioDAO = UsuarioDAO.getInstance();
        emailDAO = EmailDAO.getInstance();
    }

    public boolean inserirUsuario(Usuario usuario) throws InsertException, SelectException, ClassNotFoundException {
        if (usuarioDAO.insert(usuario))
            return true;
        return false;
    }
    public List<Usuario> getUsuarios() throws SelectException {
        return usuarioDAO.selectAll();
    }
    public List<Email> getEmails() throws SelectException {
        return emailDAO.selectAll();
    }
    public List<Email> getEmails(Usuario u) throws SelectException {
        List<Email> emails = new LinkedList<>();
        for (Email e : emailDAO.selectAll())
            if (e.getIdUsuario() == u.getId())
                emails.add(e);
        return emails;
    }
    public Usuario buscarUsuario(String email) {
        try {
            for (Usuario u : usuarioDAO.selectAll()) {
                if (u.getEnderecoEmail().equals(email))
                    return usuarioDAO.select(u.getId());
            }
        } catch (SelectException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }
    public boolean loginUsuario(String email, String senha) {
        Usuario idUsuario = buscarUsuario(email);
        if (idUsuario != null 
        && idUsuario.getEnderecoEmail().equals(email) 
        && idUsuario.getSenha().equals(senha))
            return true;
        return false;
    }
}
