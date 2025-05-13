package company.Javass.CodeSoft.ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private BankAccount account;
    private JFrame frame;

    public ATMGUI(BankAccount account) {
        this.account = account;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.setSize(300, 250);

        JLabel welcomeLabel = new JLabel("Welcome! Your balance: ₹ " + account.getBalance(), SwingConstants.CENTER);
        frame.add(welcomeLabel);

        JButton checkBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");

        frame.add(checkBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);

        // Action: Check Balance
        checkBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        String.format("Your current balance is ₹ %.2f", account.getBalance()),
                        "Balance", JOptionPane.INFORMATION_MESSAGE)
        );

        // Action: Deposit
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                try {
                    double amt = Double.parseDouble(input);
                    if (account.deposit(amt)) {
                        JOptionPane.showMessageDialog(frame,
                                String.format("Successfully deposited ₹ %.2f", amt),
                                "Deposit", JOptionPane.INFORMATION_MESSAGE);
                        welcomeLabel.setText("Welcome! Your balance: ₹ " + account.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Invalid amount. Please enter a positive value.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a numeric value.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action: Withdraw
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                try {
                    double amt = Double.parseDouble(input);
                    if (account.withdraw(amt)) {
                        JOptionPane.showMessageDialog(frame,
                                String.format("Please collect ₹ %.2f", amt),
                                "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
                        welcomeLabel.setText("Welcome! Your balance: ₹ " + account.getBalance());
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Insufficient funds or invalid amount.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a numeric value.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMGUI(new BankAccount(1000.00)));
    }
}
