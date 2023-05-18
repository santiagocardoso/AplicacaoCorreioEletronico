package dados;

public class Email {
    private String remetente;
    private String destinatario;
    private String corpo;
    private String data;
    private String hora;
    private int id;

    public Email() {

    }
    public Email(String remetente, String destinatario, String corpo, String data, String hora) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.data = data;
        this.hora = hora;
    }
    public Email(Email copia) {
        this.remetente = copia.remetente;
        this.destinatario = copia.destinatario;
        this.corpo = copia.corpo;
        this.data = copia.data;
        this.hora = copia.hora;
        this.id = copia.id;
    }

    public String getRemetente() {
        return remetente;
    }
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "[" + this.id + "] " + this.remetente + " | Email: " + this.corpo + " | Data: " + this.data + " - " + this.hora;
    }
    public boolean equals(Object o) {
        Email e;
        if (!(o instanceof Email))
            return false;
        e = (Email) o;
        if (this.remetente.equals(e.getRemetente()) 
        && this.destinatario.equals(e.getDestinatario()) 
        && this.corpo.equals(e.getCorpo()) 
        && this.data.equals(e.getData()) 
        && this.hora.equals(e.getHora()) 
        && this.id == e.getId())
            return true;
        return false;
    }
}
