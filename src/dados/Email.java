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
        return "[" + this.id + "] " + this.remetente + " | Email: " + cifraCesar(this.id, this.data, this.corpo, false) + " | Data: " + this.data + " - " + this.hora;
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

    public String encriptar(String mensagem, int cifra) {
        String encriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                encriptado += mensagem.charAt(i);
            else {
                if (!(Character.isUpperCase(mensagem.charAt(i)))) {
                    if (mensagem.charAt(i) + cifra > 122)
                        encriptado += (char)(mensagem.charAt(i) + cifra - 26);
                    else
                        encriptado += (char)(mensagem.charAt(i) + cifra);
                }
                else {
                    if (mensagem.charAt(i) + cifra > 90)
                        encriptado += (char)(mensagem.charAt(i) + cifra - 26);
                    else
                        encriptado += (char)(mensagem.charAt(i) + cifra);    
                }
            }
        }
        return encriptado;
    }
    public String descriptar(String mensagem, int cifra) {
        String descriptado = "";
        for (int i = 0; i < mensagem.length(); i++) {
            if (!(Character.isLetter(mensagem.charAt(i))))
                descriptado += mensagem.charAt(i);
            else {
                if (!(Character.isUpperCase(mensagem.charAt(i)))) {
                    if (mensagem.charAt(i) - cifra < 97)
                        descriptado += (char)(mensagem.charAt(i) - cifra + 26);
                    else
                        descriptado += (char)(mensagem.charAt(i) - cifra);
                }
                else {
                    if (mensagem.charAt(i) - cifra < 65)
                        descriptado += (char)(mensagem.charAt(i) - cifra + 26);
                    else
                        descriptado += (char)(mensagem.charAt(i) - cifra);    
                }
            }
        }
        return descriptado;
    }
    // a b c d e f g h i j k l m n o p q r s t u v w x y z
    // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
    public String cifraCesar(int id, String data, String corpo, boolean modo) {
        int dia = ((int) data.charAt(0) - '0') + ((int) data.charAt(1) - '0');
        int cifra = id + dia;

        System.out.println("Cifra " + id);

        if (modo)
            return (encriptar(corpo, cifra));
        else
            return (descriptar(corpo, cifra));
    }
}
