package sistema;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AppPanel extends JPanel implements Runnable {
    static final int LARGURA = 900;
    static final int ALTURA = 500;
    static final int MEIO = 900 / 2;
    static final Dimension TELA = new Dimension(LARGURA, ALTURA);

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
    private JTextField userLoginCaixaTexto = new JTextField("user@email.com");

    JLabel senhaCaixaTexto = new JLabel("Senha:");
    private JTextField userSenhaCaixaTexto = new JTextField("password");

    private JButton botaoEntrar = new JButton("ENTRAR");
    private JButton botaoLoginReturn = new JButton("<--");

    //***painelCadastro***/
    private JPanel painelCadastro = new JPanel();

    JLabel infoCadastroCaixaTexto = new JLabel("CADASTRO");

    JLabel cadastroNomeCaixaTexto = new JLabel("Nome:");
    private JTextField userCadastroNomeCaixaTexto = new JTextField("Nome Sobrenome");

    JLabel cadastroLoginCaixaTexto = new JLabel("Email:");
    private JTextField userCadastroLoginCaixaTexto = new JTextField("user@email.com");

    JLabel cadastroSenhaCaixaTexto = new JLabel("Senha:");
    private JTextField userCadastroSenhaCaixaTexto = new JTextField("password");

    private JButton botaoCadastrar = new JButton("CADASTRAR");
    private JButton botaoCadastroReturn = new JButton("<--");

    //***painelMostrar***/
    private JPanel painelMostrar = new JPanel();

    JLabel infoMostrarCaixaTexto = new JLabel("MOSTRAR USUÁRIOS");

    private JScrollPane painelScrollMostrarUsuarios = new JScrollPane();

    private JTable tabelaUsuarios;

    private JButton botaoMostrarReturn = new JButton("<--");
    
    AppPanel() {
        this.setLayout(null);
        this.setFocusable(true);
        this.setPreferredSize(TELA);

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
            }
        });
    //************************************************************/

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
                source.removeFocusListener(this);
            }
        });

        senhaCaixaTexto.setBounds(MEIO - 100, 200, 100, 100);
        senhaCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelLogin.add(senhaCaixaTexto);
        userSenhaCaixaTexto.setBounds(MEIO - 100, 275, 200, 20);
        painelLogin.add(userSenhaCaixaTexto);

        userSenhaCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        botaoEntrar.setBounds(MEIO - 50, 350, 100, 25);
        botaoEntrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelLogin.add(botaoEntrar);

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                userLoginCaixaTexto.setText("");
                userSenhaCaixaTexto.setText("");
            }
        });

        botaoLoginReturn.setBounds(10, 10, 75, 25);
        botaoLoginReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelLogin.add(botaoLoginReturn);

        botaoLoginReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
                painelLogin.setBounds(0, 0, 0, 0);
            }
        });
    //************************************************************/

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
                source.removeFocusListener(this);
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
                source.removeFocusListener(this);
            }
        });

        cadastroSenhaCaixaTexto.setBounds(MEIO - 100, 275, 100, 100);
        cadastroSenhaCaixaTexto.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(cadastroSenhaCaixaTexto);
        userCadastroSenhaCaixaTexto.setBounds(MEIO - 100, 350, 200, 20);
        painelCadastro.add(userCadastroSenhaCaixaTexto);

        userCadastroSenhaCaixaTexto.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        botaoCadastrar.setBounds(MEIO - 100, 425, 200, 25);
        botaoCadastrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelCadastro.add(botaoCadastrar);

        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                userCadastroNomeCaixaTexto.setText("");
                userCadastroLoginCaixaTexto.setText("");
                userCadastroSenhaCaixaTexto.setText("");
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

        infoMostrarCaixaTexto.setBounds(MEIO - 200 + 40, 0, 400, 100);
        infoMostrarCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelMostrar.add(infoMostrarCaixaTexto);

        painelScrollMostrarUsuarios.setBounds(MEIO - 200, 90, 400, 385);
        painelMostrar.add(painelScrollMostrarUsuarios);

        botaoMostrarReturn.setBounds(10, 10, 75, 25);
        botaoMostrarReturn.setFont(new Font("Arial", Font.BOLD, 15));
        painelMostrar.add(botaoMostrarReturn);

        botaoMostrarReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                painelEntrada.setBounds(0, 0, LARGURA, ALTURA);
                painelMostrar.setBounds(0, 0, 0, 0);
            }
        });
    //***********************************************************/

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
