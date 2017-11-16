

    import java.awt.BorderLayout;

    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.JFrame;
    import javax.swing.JMenuBar;
    import javax.swing.JMenu;
    import javax.swing.JMenuItem;
    import javax.swing.JTextArea;
    import javax.swing.JLabel;
    import javax.swing.SwingConstants;
    import java.awt.Dimension;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.Scanner;

    import javax.swing.JFileChooser;

    public class WalletGUI extends JFrame {

        private static final long serialVersionUID = 1L;

        private JPanel jContentPane = null;

        private JMenuBar jJMenuBar = null;

        private JMenu fileMenu = null;

        private JMenuItem openMenuItem = null;

        private JMenuItem exitMenuItem = null;

        private JMenu editMenu = null;

        private JMenuItem addMenuItem = null;

        private JTextArea billTextArea = null;

        private JTextArea coinTextArea = null;

        private JLabel totalMoneyLabel = null;

        private JFileChooser jFileChooser = null;

        private Wallet wallet = null; // @jve:decl-index=0:

        private double sumOfBill = 0;

        private double sumOfCoin = 0;

        /**
         * This is the default constructor
         */
        public WalletGUI() {
            super();
            initialize();
        }

        /**
         * This method initializes this
         *
         * @return void
         */
        private void initialize() {
            this.setSize(560, 420);
            this.setJMenuBar(getJJMenuBar());
            this.setContentPane(getJContentPane());
            this.setTitle("Wallet GUI");
        }

        /**
         * This method initializes jContentPane
         *
         * @return javax.swing.JPanel
         */
        private JPanel getJContentPane() {
            if (jContentPane == null) {
                totalMoneyLabel = new JLabel();
                totalMoneyLabel.setText("");
                totalMoneyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                totalMoneyLabel.setPreferredSize(new Dimension(360, 24));
                totalMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
                jContentPane = new JPanel();
                jContentPane.setLayout(new BorderLayout());
                jContentPane.add(getBillTextArea(), BorderLayout.WEST);
                jContentPane.add(getCoinTextArea(), BorderLayout.EAST);
                jContentPane.add(totalMoneyLabel, BorderLayout.SOUTH);
            }
            return jContentPane;
        }

        /**
         * This method initializes jJMenuBar
         *
         * @return javax.swing.JMenuBar
         */
        private JMenuBar getJJMenuBar() {
            if (jJMenuBar == null) {
                jJMenuBar = new JMenuBar();
                jJMenuBar.add(getFileMenu());
                jJMenuBar.add(getEditMenu());
            }
            return jJMenuBar;
        }

        /**
         * This method initializes fileMenu
         *
         * @return javax.swing.JMenu
         */
        private JMenu getFileMenu() {
            if (fileMenu == null) {
                fileMenu = new JMenu();
                fileMenu.setText("File");
                fileMenu.add(getOpenMenuItem());
                fileMenu.add(getExitMenuItem());
            }
            return fileMenu;
        }

        /**
         * This method initializes openMenuItem
         *
         * @return javax.swing.JMenuItem
         */
        private JMenuItem getOpenMenuItem() {
            if (openMenuItem == null) {
                openMenuItem = new JMenuItem();
                openMenuItem.setText("Open");
                openMenuItem.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        openFile();
                    }
                });
            }
            return openMenuItem;
        }

        /**
         * This method initializes exitMenuItem
         *
         * @return javax.swing.JMenuItem
         */
        private JMenuItem getExitMenuItem() {
            if (exitMenuItem == null) {
                exitMenuItem = new JMenuItem();
                exitMenuItem.setText("Exit");
                exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        System.exit(0);
                    }
                });
            }
            return exitMenuItem;
        }

        /**
         * This method initializes editMenu
         *
         * @return javax.swing.JMenu
         */
        private JMenu getEditMenu() {
            if (editMenu == null) {
                editMenu = new JMenu();
                editMenu.setText("Edit");
                editMenu.add(getAddMenuItem());
            }
            return editMenu;
        }

        /**
         * This method initializes addMenuItem
         *
         * @return javax.swing.JMenuItem
         */
        private JMenuItem getAddMenuItem() {
            if (addMenuItem == null) {
                addMenuItem = new JMenuItem();
                addMenuItem.setText("Add");
                addMenuItem.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        addMoney();
                    }
                });
            }
            return addMenuItem;
        }

        /**
         * This method initializes billTextArea
         *
         * @return javax.swing.JTextArea
         */
        private JTextArea getBillTextArea() {
            if (billTextArea == null) {
                billTextArea = new JTextArea();
                billTextArea.setPreferredSize(new Dimension(
                        this.getWidth() / 2 - 15, this.getHeight() - 50));
                billTextArea.setEditable(false);
            }
            return billTextArea;
        }

        /**
         * This method initializes coinTextArea
         *
         * @return javax.swing.JTextArea
         */
        private JTextArea getCoinTextArea() {
            if (coinTextArea == null) {
                coinTextArea = new JTextArea();
                coinTextArea.setPreferredSize(new Dimension(
                        this.getWidth() / 2 - 15, this.getHeight() - 50));
                coinTextArea.setEditable(false);
            }
            return coinTextArea;
        }

        /**
         * This method initializes jFileChooser
         *
         * @return javax.swing.JFileChooser
         */
        private JFileChooser getJFileChooser() {
            if (jFileChooser == null) {
                jFileChooser = new JFileChooser(".");
            }
            return jFileChooser;
        }

        /**
         * Add money to the wallet by the string
         *
         * @param str
         *            the money string. (It is formated like "B20", "20" and so on)
         */
        private boolean addMoneyByString(String str) {
            try {
                String text = "";
                int index;
                switch (str.charAt(0)) {
                case 'b':
                case 'B':
                    // Add one bill to the
                    int dollars = Integer.parseInt(str.substring(1, str.length())
                            .trim());
                    Bill bill = new Bill(dollars);
                    wallet.addToWallet(bill);
                    // Add the bill into the end of the text aread
                    text = "";
                    sumOfBill += bill.getValue();
                    index = billTextArea.getText().lastIndexOf("\n");
                    if (index >= 0) {
                        text = billTextArea.getText().substring(0, index + 1);
                    }
                    text += (bill.toString());
                    text += ("\n" + "The total amount of the bill is $" + String
                            .format("%.2f", sumOfBill));
                    billTextArea.setText(text);
                    break;
                default: {
                    int value = Integer.parseInt(str.trim());
                    Coin newCoin = null;
                    switch (value) {
                    case 25:
                        // Add one quarter to the wallet
                        newCoin = new Quarter();
                        break;
                    case 10:
                        // Add one Dime to the wallet
                        newCoin = new Dime();
                        break;
                    case 5:
                        // Add one Nickel to the wallet
                        newCoin = new Nickel();
                        break;
                    case 1:
                        // Add one Penny to the wallet
                        newCoin = new Penny();
                        break;
                    default:
                        return false;
                    }
                    wallet.addToWallet(newCoin);
                    // Add the coin into the end of the text aread
                    text = "";
                    sumOfCoin += (newCoin.getValue() / 100.0);
                    index = coinTextArea.getText().lastIndexOf("\n");
                    if (index >= 0) {
                        text = coinTextArea.getText().substring(0, index + 1);
                    }
                    text += (newCoin.toString());
                    text += ("\n" + "The total amount of the coin is $" + String
                            .format("%.2f", sumOfCoin));
                    coinTextArea.setText(text);
                    break;
                }
                }
                totalMoneyLabel.setText(String.format(
                        "The total amount of money in wallet is %.2f", sumOfBill
                                + sumOfCoin));
                return true;
            } catch (Exception e) {
                // The format of the input string is not valid
                return false;
            }
        }

        /**
         * Create a new wallet with the values taken from the given file
         *
         */
        private void openFile() {
            if (getJFileChooser().showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
                // The user didn't click the "OK" button
                return;
            }
            // Get the selected file
            File file = getJFileChooser().getSelectedFile();
            if (file == null)
                return;
            try {
                wallet = new Wallet();
                sumOfBill = sumOfCoin = 0;
                Scanner in = new Scanner(file);
                // Read each line in the file
                while (in.hasNextLine()) {
                    String word = in.nextLine().trim();
                    addMoneyByString(word);
                }

                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /**
         * Add money to the wallet
         *
         */
        private void addMoney() {
            if (wallet == null) {
                wallet = new Wallet();
                sumOfBill = sumOfCoin = 0;
            }
            String line = "";
            do {
                // Get the input from the user
                line = JOptionPane.showInputDialog(this,
                        "Please enter a string for the added money");
                if (line == null)
                    return;
                // Add the money to the wallet
            } while (!addMoneyByString(line.trim()));
        }

    }