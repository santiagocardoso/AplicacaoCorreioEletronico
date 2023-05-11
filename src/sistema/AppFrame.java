package sistema;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppFrame extends JFrame {
    private JPanel painel = new JPanel();
    private JPanel painelEntrada = new JPanel();
    JLabel loginCaixaTexto = new JLabel("Email:");
    JLabel senhaCaixaTexto = new JLabel("Senha:");
    private JTextField userLoginCaixaTexto = new JTextField();
    private JTextField userSenhaCaixaTexto = new JTextField();

    public AppFrame() {
        setTitle("Correio Eletrônico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(100, 100, 500, 600);
        setContentPane(painel);
        painel.setLayout(null);

        painelEntrada.setBounds(15, 80, 100, 100);
        painelEntrada.setLayout(null);
        painel.add(painelEntrada);

        loginCaixaTexto.setBounds(30, 30, 70, 10);
        painelEntrada.add(loginCaixaTexto);

        userLoginCaixaTexto.setBounds(30, 40, 70, 10);
        painelEntrada.add(userLoginCaixaTexto);

        senhaCaixaTexto.setBounds(30, 60, 70, 10);
        painelEntrada.add(senhaCaixaTexto);

        userSenhaCaixaTexto.setBounds(30, 70, 70, 10);
        painelEntrada.add(userSenhaCaixaTexto);
    }
}
