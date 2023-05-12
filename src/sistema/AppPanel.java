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

    //***********************painelEntrada**********************/
    private JPanel painelEntrada = new JPanel();

    JLabel welcomeCaixaTexto = new JLabel("Bem vindo!");

    private JButton botaoCadastro = new JButton("CADASTRO");
    private JButton botaoMostrar = new JButton("MOSTRAR USUARIOS");
    private JButton botaoLogin = new JButton("LOGIN");
    //************************************************************/

    //***********************painelLogin***********************/
    private JPanel painelLogin = new JPanel();

    JLabel infoCaixaTexto = new JLabel("LOGIN");

    JLabel loginCaixaTexto = new JLabel("Email:");
    private JTextField userLoginCaixaTexto = new JTextField("user@email.com");

    JLabel senhaCaixaTexto = new JLabel("Senha:");
    private JTextField userSenhaCaixaTexto = new JTextField("password");

    private JButton botaoEntrar = new JButton("ENTRAR");
    //************************************************************/
    
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

        botaoCadastro.setBounds(MEIO - 100, 150, 200, 25);
        botaoCadastro.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoCadastro);

        botaoCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        botaoMostrar.setBounds(MEIO - 100, 200, 200, 25);
        botaoMostrar.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoMostrar);

        botaoMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        botaoLogin.setBounds(MEIO - 100, 250, 200, 25);
        botaoLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        painelEntrada.add(botaoLogin);

        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
    //************************************************************/

    //***********************painelLogin***********************/
        painelLogin.setBounds(0, 0, LARGURA, ALTURA);
        painelLogin.setLayout(null);
        this.add(painelLogin);

        infoCaixaTexto.setBounds(MEIO - 50, 0, 100, 100);
        infoCaixaTexto.setFont(new Font("Arial", Font.BOLD, 30));
        painelLogin.add(infoCaixaTexto);

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
    //************************************************************/

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
