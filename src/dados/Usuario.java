package dados;

public class Usuario {
    private int id;
    private String usuario;
    private String enderecoEmail;
    private String senha;

    public Usuario() {}
    public Usuario(int id, String usuario, String enderencoEmail, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.enderecoEmail = enderencoEmail;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getEnderecoEmail() {
        return enderecoEmail;
    }
    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String string) {
        this.senha = string;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}