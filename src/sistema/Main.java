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
        int i = 0;
        for (Email e : usuario.getEmails())  {
            System.out.println("[" + i + "] " + e.toString());
            i++;
        }
    }
    public static void removerEmail(Usuario usuario) {
        System.out.println("Selecione o email que deseja remover:");
        mostrarEmails(usuario);
        int opcao = Integer.parseInt(scan.nextLine());
        usuario.removerEmail(opcao);
    }
    public static void login() {
        System.out.println("Digite seu email:");
        String email = scan.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scan.nextLine();

        if (!(sistema.loginUsuario(email, senha))) {
            System.out.println("Endereço de email ou senha errados!");
            return;
        }
        else {
            Usuario usuario = sistema.buscarUsuario(email);
            int opcao = -1;
            while (opcao != 0) {
            menu();
            opcao = Integer.parseInt(scan.nextLine());
            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                case 1:
                    novoEmail();
                    break;
                case 2:
                    removerEmail(usuario);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        }
    }
    public static void userMenu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Selecione uma opção:");
        System.out.println("[0] Sair");
        System.out.println("[1] Enviar email");
        System.out.println("[2] Remover email");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
    }
    public static void menu() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Selecione uma opção:");
        System.out.println("[0] Sair");
        System.out.println("[1] Cadastrar usuario");
        System.out.println("[2] Efetuar login");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
    }

    public static void main(String[] args) {
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
                    login();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
