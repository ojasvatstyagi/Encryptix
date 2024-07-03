import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATMGUI implements ActionListener {

    // Creating Object of UIManager class to manage the look and feel
    UIManager UI = new UIManager();

    // Creating objects of JFrame and JPanel
    private JFrame frame;
    private JPanel panel1;

    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;
    JButton checkBalanceButton, depositButton, withdrawButton, exitButton;
    private JLabel imageLabel, textLabel1;

    public ATMGUI(BankAccount account) {
        this.account = account;

        // Managing look and feel of JOptionPane
        UI.put("OptionPane.background", new Color(73, 88, 103));
        UI.put("Panel.background", new Color(73, 88, 103));
        UI.put("OptionPane.messageForeground", Color.white);
        UI.put("OptionPane.messageFont", new Font("Comic Sans MS", Font.BOLD, 13));

        // Setting properties of JFrame
        frame = new JFrame();
        frame.setTitle("ATM");
        frame.getContentPane().setLayout(null);

        // Setting Properties of JPanel
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(206, 121, 107));
        panel1.setBounds(0, 0, 350, 500);

        // Setting properties of the components
        textLabel1 = new JLabel();
        textLabel1.setText("Number");
        textLabel1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        textLabel1.setForeground(Color.WHITE);
        textLabel1.setBounds(78, 20, 300, 100);

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", account.getBalance()));
        balanceLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(390, 150, 250, 35);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        checkBalanceButton.setBounds(390, 200, 200, 35);
        checkBalanceButton.setFocusable(false);
        checkBalanceButton.setBackground(new Color(193, 140, 93));
        checkBalanceButton.setForeground(Color.white);

        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        depositButton.setBounds(390, 250, 200, 35);
        depositButton.setFocusable(false);
        depositButton.setBackground(new Color(193, 140, 93));
        depositButton.setForeground(Color.white);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        withdrawButton.setBounds(390, 300, 200, 35);
        withdrawButton.setFocusable(false);
        withdrawButton.setBackground(new Color(193, 140, 93));
        withdrawButton.setForeground(Color.white);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        exitButton.setBounds(390, 350, 200, 35);
        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(193, 140, 93));
        exitButton.setForeground(Color.white);

        ImageIcon imageIcon = new ImageIcon("atm.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(50, 150, icon.getIconWidth(), icon.getIconHeight());

        // Adding components to the container
        panel1.add(textLabel1);
        panel1.add(imageLabel);
        frame.add(panel1);
        frame.add(balanceLabel);
        frame.add(checkBalanceButton);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(exitButton);

        // Setting properties of JFrame
        frame.getContentPane().setBackground(new Color(236, 200, 175));
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!");
                System.exit(0);
            }
        });
    }

    private void checkBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }

    private void deposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                account.deposit(amount);
                checkBalance();
                JOptionPane.showMessageDialog(null, String.format("$%.2f has been deposited. Your new balance is: $%.2f", amount, account.getBalance()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number.");
            }
        }
    }

    private void withdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                if (account.withdraw(amount)) {
                    checkBalance();
                    JOptionPane.showMessageDialog(null, String.format("$%.2f has been withdrawn. Your new balance is: $%.2f", amount, account.getBalance()));
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance or invalid amount.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number.");
            }
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount("Ojas Tyagi", 500);
        new ATMGUI(userAccount);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
