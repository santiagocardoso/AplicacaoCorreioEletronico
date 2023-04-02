package dados;

public class Email {
    private Usuario remetente;
    private Usuario destinatario;
    private String corpo;
    private String data;
    private String hora;

    public Usuario getRemetente() {
        return remetente;
    }
    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }
    public Usuario getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
    public String getCorpo() {
        return corpo;
    }
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    public String toString() {
        return "a";
    }
    public boolean equals(Object o) {
        Email e;
        if (!(o instanceof Email))
            return false;
        e = (Email) o;
        if (this.remetente.equals(e.getRemetente()) 
        & this.destinatario.equals(e.getDestinatario()) 
        & this.corpo.equals(e.getCorpo()) 
        & this.data.equals(e.getData()) 
        & this.hora.equals(e.getHora()))
            return true;
        return false;
    }
}
