package dados;

public class Usuario {
    private String usuario;
    private String email;
    private String senha;
    private Email emails = new Email[];
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
