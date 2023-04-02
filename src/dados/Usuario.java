package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String usuario;
    private String email;
    private String senha;
    private List<Email> emails = new ArrayList<Email>();
    private int quantEmails = 0;

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getQuantEmails() {
        return quantEmails;
    }
    public List<Email> getEmails() {
        return emails;
    }
    public void adicionarEmail(Email email) {
        if (!(emails.contains(email))) {
            emails.add(email);
            quantEmails++;
        }
        else
            return;
    }
    public void removerEmail(Email email) {
        if (emails.contains(email)) {
            emails.remove(email);
            quantEmails--;
        }
        else
            return;
    }
    public String toString() {
        return this.usuario + " | " + this.email;
    }
    public boolean equals(Object o) {
        Usuario u;
        if (!(o instanceof Usuario))
            return false;
        u = (Usuario) o;
        if (this.usuario.equals(u.getUsuario()) 
        && this.email.equals(u.getEmail()))
            return true;
        return false;
    }
}
