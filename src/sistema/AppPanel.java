package sistema;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import negocio.*;
import dados.*;
import exceptions.*;

public class AppPanel extends JPanel implements Runnable {
    static final int LARGURA = 900;
    static final int ALTURA = 500;
    static final int MEIO = 900 / 2;
    static final Dimension TELA = new Dimension(LARGURA, ALTURA);

    private Sistema sistema;

    Usuario userLogin = new Usuario();

    Thread appThread;
    
    //***painelEntrada***/
    private JPanel painelEntrada = new JPanel();

    JLabel welcomeCaixaTexto = new JLabel("Bem vindo!");

    private JButton botaoCadastro = new JButton("CADASTRO");
    private JButton botaoMostrar = new JButton("MOSTRAR USUARIOS");
    private JButton botaoLogin = new JButton("LOGIN");

    //***painelLogin***/
    private JPanel painelLogin = new JPanel();

    JLabel infoLoginCaixaTexto = new JLabel("LOGIN");

    JLabel loginCaixaTexto = new JLabel("Email:");
    private JTextField userLoginCaixaTexto = new JTextField("usuario@email.com");

    JLabel senhaCaixaTexto = new JLabel("Senha:");
    JTextField passwordFieldLogin = new JPasswordField("Senha:");

    private JButton botaoEntrar = new JButton("ENTRAR");
    JLabel loginFracassado = new JLabel("Login fracassado!");
    private JButton botaoLoginReturn = new JButton("<--");

    //***painelCadastro***/
    private JPanel painelCadastro = new JPanel();

    JLabel infoCadastroCaixaTexto = new JLabel("CADASTRO");

    JLabel cadastroNomeCaixaTexto = new JLabel("Nome:");
    private JTextField userCadastroNomeCaixaTexto = new JTextField("Nome Sobrenome");

    JLabel cadastroLoginCaixaTexto = new JLabel("Email:");
    private JTextField userCadastroLoginCaixaTexto = new JTextField("usuario@email.com");

    JLabel cadastroSenhaCaixaTexto = new JLabel("Senha:");
    JTextField passwordFieldCadastro = new JPasswordField("Senha:");

    private JButton botaoCadastrar = new JButton("CADASTRAR");

    JLabel cadastroRealizado = new JLabel("Cadastro realizado!");
    JLabel cadastroFracassado = new JLabel("Cadastro fracassado!");

    private JButton botaoCadastroReturn = new JButton("<--");

    //***painelMostrar***/
    private JPanel painelMostrar = new JPanel();

    JLabel infoMostrarCaixaTexto = new JLabel("USUÁRIOS ATIVOS");

    private JScrollPane painelScrollTabelaUsuarios = new JScrollPane();
    private JTable tabelaUsuarios;
    private TabelaUsuarios usuarios;

    private JButton botaoMostrarReturn = new JButton("<--");

    //***painelUsuario***/
    private JPanel painelUsuario = new JPanel();

    JLabel infoUsuarioCaixaTexto = new JLabel("[POO-Mail]");

    private JScrollPane painelScrollUsuarioEmails = new JScrollPane();
    private JTable tabelaEmails;
    private TabelaEmails emails;

    private JButton botaoNovoEmail = new JButton("Enviar email");

    private JTextField responderEmailCaixaTexto = new JTextField("ID Email");
    private JButton botaoResponderEmail = new JButton("Responder email");

    private JTextField removerEmailCaixaTexto = new JTextField("ID Email");
    private JButton botaoRemoverEmail = new JButton("Deletar email");

    private JButton botaoUsuarioReturn = new JButton("Logout");
    
    //***painelEmail***/
    private JPanel painelEmail = new JPanel();

    JLabel infoEmailCaixaTexto = new JLabel("Nova Mensagem");

    private JTextField emailDestinatarioCaixaTexto = new JTextField("usuario@email.com");
    private JTextField emailCorpoCaixaTexto = new JTextField("Corpo texto");

    private JButton botaoEnviarEmail = new JButton("Enviar Email");

    private JButton botaoEmailReturn = new JButton("Cancelar");

    AppPanel() {
        this.setLayout(null);
        this.setFocusable(true);
        this.setPreferredSize(TELA);
        try {
            sistema = new Sistema("postgres");
        } catch (ClassNotFoundException | SQLException | SelectException e) {
            e.printStackTrace();
        }
        try {
            usuarios = new TabelaUsuarios();
        } catch (ClassNotFoundException | SQLException | SelectException e) {
            e.printStackTrace();
        }

    //***********************painelEntrada*********************/
        painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
        painelEntrada.setLayout(null);
        this.add(painelEntrada);

        welcomeCaixaTexto.setBounds(MEIO - 80, 50, 300, 100);
        welcomeCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelEntrada.add(welcomeCaixaTexto);

        botaoCadastro.setBounds(MEIO - 100, 175, 200, 25);
        botaoCadastro.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoCadastro);

        botaoCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, 0, 0);
                painelCadastro.setBounds(0, 0, LARGURA, ALTURA);
                userCadastroNomeCaixaTexto.setText("Nome Sobrenome");
                userCadastroLoginCaixaTexto.setText("usuario@email.com");
                passwordFieldCadastro.setText("senha123");
            }
        });

        botaoMostrar.setBounds(MEIO - 100, 225, 200, 25);
        botaoMostrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoMostrar);

        botaoMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, 0, 0);
                painelMostrar.setBounds(0, 0, LARGURA, ALTURA);
            }
        });

        botaoLogin.setBounds(MEIO - 100, 275, 200, 25);
        botaoLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoLogin);

        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, 0, 0);
                painelLogin.setBounds(0, 0, LARGURA, ALTURA);
                userLoginCaixaTexto.setText("usuario@email.com");
                passwordFieldLogin.setText("senha123");
            }
        });
    //*********************************************************/

    //***********************painelLogin***********************/
        painelLogin.setLayout(null);
        this.add(painelLogin);

        infoLoginCaixaTexto.setBounds(MEIO - 50, 0, 100, 100);
        infoLoginCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelLogin.add(infoLoginCaixaTexto);

        loginCaixaTexto.setBounds(MEIO - 100, 100, 100, 100);
        loginCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelLogin.add(loginCaixaTexto);
        userLoginCaixaTexto.setBounds(MEIO - 100, 175, 200, 20);
        painelLogin.add(userLoginCaixaTexto);

        userLoginCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        senhaCaixaTexto.setBounds(MEIO - 100, 200, 100, 100);
        senhaCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelLogin.add(senhaCaixaTexto);
        passwordFieldLogin.setBounds(MEIO - 100, 275, 200, 20);
        painelLogin.add(passwordFieldLogin);

        passwordFieldLogin.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        botaoEntrar.setBounds(MEIO - 50, 350, 100, 25);
        botaoEntrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelLogin.add(botaoEntrar);

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String email = userLoginCaixaTexto.getText();
                String senha = passwordFieldLogin.getText();
                if (sistema.loginUsuario(email, senha)) {
                    userLogin = sistema.buscarUsuario(email);
                    emails = new TabelaEmails(userLogin);
                    tabelaEmails = new JTable(emails);
                    painelScrollUsuarioEmails.setViewportView(tabelaEmails);

                    painelEntrada.setBounds(0, 0, 0, 0);
                    painelLogin.setBounds(0, 0, 0, 0);
                    painelUsuario.setBounds(0, 0, LARGURA, ALTURA);
                } 
                else {
                    loginFracassado.setFont(new Font("Arial", Font.BOLD, 20));
                    loginFracassado.setForeground(Color.red);
                    painelLogin.add(loginFracassado);
                    loginFracassado.setBounds(MEIO - 90, 400, 200, 20);
                }
            }
        });

        botaoLoginReturn.setBounds(10, 10, 75, 25);
        botaoLoginReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelLogin.add(botaoLoginReturn);

        botaoLoginReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                loginFracassado.setBounds(MEIO - 90, 400, 0, 0);
                painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
                painelLogin.setBounds(0, 0, 0, 0);
            }
        });
    //***********************************************************/

    //***********************painelCadastro**********************/
        painelCadastro.setLayout(null);
        this.add(painelCadastro);

        infoCadastroCaixaTexto.setBounds(MEIO - 100 + 15, 0, 200, 100);
        infoCadastroCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelCadastro.add(infoCadastroCaixaTexto);

        cadastroNomeCaixaTexto.setBounds(MEIO - 100, 75, 100, 100);
        cadastroNomeCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(cadastroNomeCaixaTexto);
        userCadastroNomeCaixaTexto.setBounds(MEIO - 100, 150, 200, 20);
        painelCadastro.add(userCadastroNomeCaixaTexto);

        userCadastroNomeCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        cadastroLoginCaixaTexto.setBounds(MEIO - 100, 175, 100, 100);
        cadastroLoginCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(cadastroLoginCaixaTexto);
        userCadastroLoginCaixaTexto.setBounds(MEIO - 100, 250, 200, 20);
        painelCadastro.add(userCadastroLoginCaixaTexto);

        userCadastroLoginCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        cadastroSenhaCaixaTexto.setBounds(MEIO - 100, 275, 100, 100);
        cadastroSenhaCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(cadastroSenhaCaixaTexto);
        passwordFieldCadastro.setBounds(MEIO - 100, 350, 200, 20);
        passwordFieldCadastro.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(passwordFieldCadastro);

        passwordFieldCadastro.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        botaoCadastrar.setBounds(MEIO - 100, 425, 200, 25);
        botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(botaoCadastrar);

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Usuario u = new Usuario();

                u.setEnderecoEmail((userCadastroLoginCaixaTexto.getText()));
                u.setSenha(passwordFieldCadastro.getText());
                u.setUsuario(userCadastroNomeCaixaTexto.getText());

                cadastroRealizado.setFont(new Font("Arial", Font.BOLD, 20));
                cadastroRealizado.setForeground(Color.green);
                painelCadastro.add(cadastroRealizado);

                cadastroFracassado.setFont(new Font("Arial", Font.BOLD, 20));
                cadastroFracassado.setForeground(Color.red);
                painelCadastro.add(cadastroFracassado);

                try {
                    if (sistema.inserirUsuario(u)) {
                        usuarios.adicionarValor();
                        cadastroFracassado.setBounds(MEIO - 90, 0, 0, 0);
                        cadastroRealizado.setBounds(MEIO - 90, 465, 200, 20);
                    }
                    else {
                        cadastroRealizado.setBounds(MEIO - 90, 0, 0, 0);
                        cadastroFracassado.setBounds(MEIO - 100, 465, 210, 20);
                    }
                } catch (InsertException | SelectException | ClassNotFoundException e) {
                    System.err.println("Erro na inserção do usuário!");
                }
                
                userCadastroNomeCaixaTexto.setText("Nome Sobrenome");
                userCadastroLoginCaixaTexto.setText("usuario@email.com");
                passwordFieldCadastro.setText("senha123");
            }
        });

        botaoCadastroReturn.setBounds(10, 10, 75, 25);
        botaoCadastroReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelCadastro.add(botaoCadastroReturn);

        botaoCadastroReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
                painelCadastro.setBounds(0, 0, 0, 0);
            }
        });
    //***********************************************************/

    //***********************painelMostrar***********************/
        painelMostrar.setLayout(null);
        this.add(painelMostrar);

        infoMostrarCaixaTexto.setBounds(MEIO - 200 + 50, 0, 400, 100);
        infoMostrarCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelMostrar.add(infoMostrarCaixaTexto);

        painelScrollTabelaUsuarios.setBounds(MEIO - 200, 90, 400, 385);
        painelMostrar.add(painelScrollTabelaUsuarios);

        tabelaUsuarios = new JTable(usuarios);
        painelScrollTabelaUsuarios.setViewportView(tabelaUsuarios);

        botaoMostrarReturn.setBounds(10, 10, 75, 25);
        botaoMostrarReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelMostrar.add(botaoMostrarReturn);

        botaoMostrarReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
                painelMostrar.setBounds(0, 0, 0, 0);
            }
        });
    //*********************************************************/

    //***********************painelUsuario*********************/
        painelUsuario.setLayout(null);
        this.add(painelUsuario);

        infoUsuarioCaixaTexto.setBounds(15, 0, 100, 50);
        infoUsuarioCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelUsuario.add(infoUsuarioCaixaTexto);

        painelScrollUsuarioEmails.setBounds(15, 70, 600, 415);
        painelUsuario.add(painelScrollUsuarioEmails);

        botaoNovoEmail.setBounds(650, 150, 216, 25);
        botaoNovoEmail.setFont(new Font("Arial", Font.PLAIN, 17));
        painelUsuario.add(botaoNovoEmail);

        botaoNovoEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelUsuario.setBounds(0, 0, 0, 0);
                painelEmail.setBounds(0, 0, LARGURA, ALTURA);
                emailDestinatarioCaixaTexto.setText("usuario@email.com");
                emailCorpoCaixaTexto.setText("Corpo Texto");
            }
        });

        responderEmailCaixaTexto.setBounds(650, 250, 60, 25);
        painelUsuario.add(responderEmailCaixaTexto);

        responderEmailCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        botaoResponderEmail.setBounds(710, 250, 156, 25);
        botaoResponderEmail.setFont(new Font("Arial", Font.PLAIN, 17));
        painelUsuario.add(botaoResponderEmail);

        botaoResponderEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a1rg0) {
                painelUsuario.setBounds(0, 0, 0, 0);
                painelEmail.setBounds(0, 0, LARGURA, ALTURA);
                try {
                    for (Email e : sistema.getEmails()) {
                        if (e.getId() == (Integer.parseInt(responderEmailCaixaTexto.getText())))
                            emailDestinatarioCaixaTexto.setText(e.getRemetente());
                    }
                    emailCorpoCaixaTexto.setText("Corpo Texto");;
                    responderEmailCaixaTexto.setText("ID Email");
                } catch (NumberFormatException | SelectException e) {
                    e.printStackTrace();
                }
            }
        });

        removerEmailCaixaTexto.setBounds(650, 350, 60, 25);
        painelUsuario.add(removerEmailCaixaTexto);

        removerEmailCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        botaoRemoverEmail.setBounds(710, 350, 156, 25);
        botaoRemoverEmail.setFont(new Font("Arial", Font.PLAIN, 17));
        painelUsuario.add(botaoRemoverEmail);

        botaoRemoverEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    for (Email e : sistema.getEmails()) {
                        if (e.getId() == Integer.parseInt(removerEmailCaixaTexto.getText())) {
                            sistema.deleteEmail(e);
                            emails.atualizar();
                            removerEmailCaixaTexto.setText("ID Email");
                            break;
                        }
                    }
                } catch (NumberFormatException | DeleteException | SelectException e) {
                    e.printStackTrace();
                }
            }
        });

        botaoUsuarioReturn.setBounds(790, 10, 100, 25);
        botaoUsuarioReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelUsuario.add(botaoUsuarioReturn);

        botaoUsuarioReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                loginFracassado.setBounds(MEIO - 90, 400, 0, 0);
                painelUsuario.setBounds(0, 0, 0, 0);
                painelLogin.setBounds(0, 0, LARGURA, ALTURA);
                userLoginCaixaTexto.setText("usuario@email.com");
                passwordFieldLogin.setText("senha123");
            }
        });
    //*********************************************************/

    //************************painelEmail**********************/
        painelEmail.setLayout(null);
        this.add(painelEmail);

        infoEmailCaixaTexto.setBounds(10, 10, 200, 100);
        infoEmailCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 16));
        painelEmail.add(infoEmailCaixaTexto);

        emailDestinatarioCaixaTexto.setBounds(10, 100, 200, 20);
        painelEmail.add(emailDestinatarioCaixaTexto);

        emailDestinatarioCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        emailCorpoCaixaTexto.setBounds(10, 150, 880, 305);
        painelEmail.add(emailCorpoCaixaTexto);

        emailCorpoCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
            }
        });

        botaoEnviarEmail.setBounds(734, 465, 156, 25);
        botaoEnviarEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        painelEmail.add(botaoEnviarEmail);

        botaoEnviarEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Email email = new Email();

                email.setRemetente(userLogin.getEnderecoEmail());
                email.setDestinatario(emailDestinatarioCaixaTexto.getText());
                email.setCorpo(emailCorpoCaixaTexto.getText());
                Date dataHoraAtual = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                email.setData(data);
                String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
                email.setHora(hora);
                email.setIdUsuario(userLogin.getId());

                try {
                    for (Usuario u : sistema.getUsuarios()) {
                        if (u != null && email != null && u.getEnderecoEmail().equals(email.getDestinatario()))
                            email.setIdDestinatario(u.getId());
                    }
                } catch (SelectException e) {
                    System.err.println("Não foi possível encontrar o usuário!");
                }

                try {
                    sistema.inserirEmail(email);
                } catch (InsertException | SelectException | ClassNotFoundException e) {
                    System.err.println("Não foi possível criar o email!");
                }

                emailDestinatarioCaixaTexto.setText("usuario@email.com");
                emailCorpoCaixaTexto.setText("Corpo Texto");
            }
        });

        botaoEmailReturn.setBounds(10, 10, 100, 25);
        botaoEmailReturn.setFont(new Font("Arial", Font.PLAIN, 16));
        painelEmail.add(botaoEmailReturn);

        botaoEmailReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEmail.setBounds(0, 0, 0, 0);
                painelUsuario.setBounds(0, 0, LARGURA, ALTURA);
                responderEmailCaixaTexto.setText("ID Email");
            }
        });
    //*********************************************************/

        appThread = new Thread(this);
        appThread.start();
    }

    @Override
    public void run() {
        final int DELAY = 100;
        while(true){
            try{
                Thread.sleep(DELAY);
            } catch(Exception e){
                e.printStackTrace();                    
            } 
        }
    }
}
