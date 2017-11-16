import javax.swing.JFrame;

public class MoneyProject {

    /**
     * @param args
     */
    public static void main(String[] args) {
        WalletGUI gui = new WalletGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

}