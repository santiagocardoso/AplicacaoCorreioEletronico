package dados;

public class Email {
    private int id;
    private String remetente;
    private String destinatario;
    private String corpo;
    private String data;
    private String hora;
    private int idUsuario;
    private int idDestinatario;

    public Email() {}
    public Email(int id, String remetente, String destinatario, String corpo, String data, String hora, 
                 int idUsuario, int idDestinatario) {
        this.id = id;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
        this.data = data;
        this.hora = hora;
        this.idUsuario = idUsuario;
        this.idDestinatario = idDestinatario;
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
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdDestinatario() {
        return idDestinatario;
    }
    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public String toString() {
        return "[" + this.id + "] " + this.remetente + " | Email: " + this.corpo + " | Data: " + this.data + " - " + this.hora;
    }
    public boolean equals(Object o) {
        Email e;
        if (!(o instanceof Email))
            return false;
        e = (Email) o;
        if (this.id == e.getId())
            return true;
        return false;
    }
}
