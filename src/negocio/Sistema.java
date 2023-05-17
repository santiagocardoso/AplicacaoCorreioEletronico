package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.*;

public class Sistema {
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void limparValores() {
		this.usuarios.clear();
	}
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
            if (u.getEnderecoEmail().equals(email))
                return u;
        return null;
    }
    public boolean loginUsuario(String enderecoEmail, String senha) {
        Usuario idUsuario = buscarUsuario(enderecoEmail);
        if (idUsuario.getEnderecoEmail().equals(enderecoEmail) 
        && idUsuario.getSenha().equals(senha) 
        && idUsuario != null)
            return true;
        return false;
    }
    public boolean enviarEmail(Email email) {
        Usuario destinatario = buscarUsuario(email.getDestinatario());
        if (destinatario.getEnderecoEmail().equals(email.getDestinatario()) 
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
