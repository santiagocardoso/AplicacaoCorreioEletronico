package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String usuario;
    private String enderecoEmail;
    private String senha;
    private List<Email> emails = new ArrayList<Email>();
    private int quantEmails = 0;

    public Usuario() {

    }
    public Usuario(String usuario, String enderencoEmail, String senha) {
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
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getQuantEmails() {
        return quantEmails;
    }
    public List<Email> getEmails() {
        return emails;
    }

    public String encriptar(String mensagem, int cifra) {
        String encriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                encriptado += mensagem.charAt(i);
            else if (mensagem.charAt(i) + cifra > 122)
                encriptado += (char)(mensagem.charAt(i) + cifra - 26);
            else
                encriptado += (char)(mensagem.charAt(i) + cifra);
        }
        return encriptado;
    }
    public String descriptar(String mensagem, int cifra) {
        String descriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                descriptado += mensagem.charAt(i);
            else if (mensagem.charAt(i) - cifra < 97)
                descriptado += (char)(mensagem.charAt(i) - cifra + 26);
            else
                descriptado += (char)(mensagem.charAt(i) - cifra);
        }
        return descriptado;
    }
    public void cifraCesar(Email email, boolean modo) {
        int id = emails.indexOf(email);
        int dia = ((int) email.getData().charAt(0) - '0') + ((int) email.getData().charAt(1) - '0');

        if (modo) {
            int cifra = id + dia + 1;
            email.setCorpo(encriptar(email.getCorpo(), cifra));
        }
        else {
            int cifra = id + dia;
            email.setCorpo(descriptar(email.getCorpo(), cifra));
        }
    }
    public boolean adicionarEmail(Email email) {
        // cifraCesar(email, true); // armazena a mensagem com a cifra de Cesar
        if (!(emails.contains(email))) {
            emails.add(email);
            quantEmails++;
            return true;
        }
        else
            return false;
    }
    public boolean removerEmail(Email email) {
        if (emails.contains(email)) {
            emails.remove(email);
            quantEmails--;
            return true;
        }
        else
            return false;
    }
    public Email buscarEmail(Email email) {
        for (Email e : emails) {
            if (email.equals(e))
                return e;
        }
        return null;
    }
    public String toString() {
        return this.usuario + " | " + this.enderecoEmail;
    }
    public boolean equals(Object o) {
        Usuario u;
        if (!(o instanceof Usuario))
            return false;
        u = (Usuario) o;
        if (this.enderecoEmail.equals(u.getEnderecoEmail()))
            return true;
        return false;
    }
}
