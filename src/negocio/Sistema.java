package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.*;

public class Sistema {
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void cadastrarUsuario(Usuario usuario) {
        if (!(usuarios.contains(usuario)))
            usuarios.add(usuario);
        else
            return;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;            
    }
    public Usuario buscarUsuario(String email) {
        for (Usuario u : usuarios)
            if (u.getEnderecoEmail() == email)
                return u;
        return null;
    }
    public boolean loginUsuario(String enderecoEmail, String senha) {
        Usuario idUsuario = buscarUsuario(enderecoEmail);
        if (idUsuario.getEnderecoEmail() == enderecoEmail 
        && idUsuario.getSenha() == senha 
        && idUsuario != null)
            return true;
        return false;
    }
    public boolean enviarEmail(Email email) {
        Usuario destinatario = buscarUsuario(email.getDestinatario().getEnderecoEmail());
        if (destinatario.getEnderecoEmail() == email.getDestinatario().getEnderecoEmail() && destinatario != null) {
            email.getDestinatario().adicionarEmail(email);
            return true;
        }
        return false;
    }
}
