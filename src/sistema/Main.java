package sistema;

import dados.*;
import negocio.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Sistema sistema = new Sistema();

    public static void cadastrarUsuario() {
        char opcao = 's';
        while (opcao != 'n') {
            Usuario u = new Usuario();

            System.out.println("Digite o nome:");
            String usuario = scan.nextLine();
            u.setUsuario(usuario);
            System.out.println("Digite o endereço de email:");
            String enderecoEmail = scan.nextLine();
            u.setEnderecoEmail(enderecoEmail);
            System.out.println("Digite a senha:");
            String senha = scan.nextLine();
            u.setSenha(senha);

            sistema.cadastrarUsuario(u);

            System.out.println("Deseja cadastrar mais um usuário? (s/n)");
            opcao = scan.nextLine().charAt(0);
        }
    }
    public static void novoEmail() {
        Email e = new Email();

        System.out.println("Remetente:");
        String remetente = scan.nextLine();
        e.setRemetente(remetente);
        System.out.println("Destinatário:");
        String destinatario = scan.nextLine();
        e.setDestinatario(destinatario);
        System.out.println("Mensagem:");
        String mensagem = scan.nextLine();
        e.setCorpo(mensagem);
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        e.setData(data);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        e.setHora(hora);

        sistema.enviarEmail(e);
    }
    public static void mostrarEmails(Usuario usuario) {
        for (Email e : usuario.getEmails())
            System.out.println(e.toString());
    }
    public static void responderEmail(Usuario usuario) {
        System.out.println("Selecione o email que deseja responder:");
        mostrarEmails(usuario);
        int opcao = Integer.parseInt(scan.nextLine());
        Email original = usuario.getEmails().get(opcao);
        Email enviar = new Email(original);

        enviar.setDestinatario(enviar.getRemetente());
        enviar.setRemetente(usuario.getEnderecoEmail());
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        enviar.setData(data);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        enviar.setHora(hora);

        System.out.println("Mensagem:");
        enviar.setCorpo(scan.nextLine());

        sistema.enviarEmail(enviar);
    }
    public static void removerEmail(Usuario usuario) {
        System.out.println("Selecione a ID do email que deseja remover:");
        mostrarEmails(usuario);
        int opcao = Integer.parseInt(scan.nextLine());
        usuario.removerEmail(usuario.getEmails().get(opcao));
    }
    public static void descriptarEmails(Usuario usuario) {
        mostrarEmails(usuario);
        int opcao = Integer.parseInt(scan.nextLine());
        sistema.descriptarEmail(usuario, usuario.getEmails().get(opcao));
    }
    public static void mostrarUsuarios() {
        int i = 0;
        for (Usuario u : sistema.getUsuarios()) {
            System.out.println("[" + i + "] " + u.toString());
            i++;
        }
    }
    public static void login() {
        System.out.println("Digite seu email:");
        String email = scan.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scan.nextLine();

        if (sistema.loginUsuario(email, senha)) {
            Usuario usuario = sistema.buscarUsuario(email);
            int opcao = -1;
            while (opcao != 0) {
            userMenu();
            opcao = Integer.parseInt(scan.nextLine());
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    novoEmail();
                    break;
                case 2:
                    mostrarEmails(usuario);
                    break;
                case 3:
                    descriptarEmails(usuario);
                    break;
                case 4:
                    responderEmail(usuario);
                    break;
                case 5:
                    removerEmail(usuario);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        }
        else {
            System.out.println("Endereço de email ou senha errados!");
            return;
        }
    }
    public static void userMenu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Selecione uma opção:");
        System.out.println("[0] Sair");
        System.out.println("[1] Enviar email");
        System.out.println("[2] Mostrar emails");
        System.out.println("[3] Descriptar emails");
        System.out.println("[4] Responder email");
        System.out.println("[5] Remover email");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
    }
    public static void menu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Selecione uma opção:");
        System.out.println("[0] Sair");
        System.out.println("[1] Cadastrar usuario");
        System.out.println("[2] Mostrar usuarios");
        System.out.println("[3] Efetuar login");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
    }

    public static void main(String[] args) {
        // test
        Usuario u1 = new Usuario("Santiago", "santcar7@gmail.com", "123");
        Usuario u2 = new Usuario("Jaiza", "jaiza@gmail.com", "321");
        sistema.cadastrarUsuario(u1);
        sistema.cadastrarUsuario(u2);
        Email email1 = new Email("santcar7@gmail.com", "jaiza@gmail.com", "isso eh um email de teste!", "03/04/2023", "00:01");
        u2.adicionarEmail(email1);
        Email email2 = new Email("jaiza@gmail.com", "santcar7@gmail.com", "a b c d e f g h i j k l m n o p q r s t u v w x y z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z", "03/04/2023", "00:02");
        u1.adicionarEmail(email2);

        int opcao = -1;
        while (opcao != 0) {
            menu();
            opcao = Integer.parseInt(scan.nextLine());
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    mostrarUsuarios();
                    break;
                case 3:
                    login();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
