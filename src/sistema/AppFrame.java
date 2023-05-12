package sistema;

import javax.swing.*;

public class AppFrame extends JFrame {
    AppPanel painel;

    AppFrame() {
        painel = new AppPanel();
        this.add(painel);
        this.setTitle("Correio Eletrônico");
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
