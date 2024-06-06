package Part3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

    Transaction transferObject;
    StringBuilder sbAllData;
    LinkedList<Account> globalAccounts;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton showAllData;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JLabel showAll;
    private JLabel messageLabel; 
    private JTextField accDeposit;
    private JTextField depositInput;
    private JTextField accWithdraw;
    private JTextField withdrawInput;
    private JTextField acc1Transfer;
    private JTextField acc2Transfer;
    private JTextField transferAmount;

    /**
     * Create the frame.
     */
    public GUI(LinkedList<Account> accounts) {

        super();
        super.setTitle("Banking System");
        getContentPane().setLayout(null);

        this.globalAccounts = accounts;
        this.sbAllData = new StringBuilder();

        // Populate sbAllData with account data
        for (Account account : globalAccounts) {
            sbAllData.append(account.toString()).append("\n");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 407);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(203, 182, 196));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        showAllData = new JButton("Show All");
        showAllData.setFont(new Font("Tahoma", Font.BOLD, 10));
        showAllData.setBackground(new Color(200, 200, 200));
        showAllData.setBounds(206, 189, 85, 21);
        contentPane.add(showAllData);

        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        depositButton.setBackground(new Color(200, 200, 200));
        depositButton.setBounds(10, 238, 85, 21);
        contentPane.add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 10));
        withdrawButton.setBackground(new Color(200, 200, 200));
        withdrawButton.setBounds(10, 269, 85, 21);
        contentPane.add(withdrawButton);

        transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        transferButton.setBackground(new Color(200, 200, 200));
        transferButton.setBounds(10, 321, 85, 21);
        contentPane.add(transferButton);

        showAll = new JLabel("");
        showAll.setBounds(68, 34, 411, 143);
        contentPane.add(showAll);

        messageLabel = new JLabel(""); // Initialize message label
        messageLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        messageLabel.setForeground(Color.RED); // Set color to red for visibility
        messageLabel.setBounds(68, 5, 411, 20);
        contentPane.add(messageLabel);

        accDeposit = new JTextField();
        accDeposit.setBackground(new Color(200, 200, 200));
        accDeposit.setBounds(142, 242, 96, 19);
        contentPane.add(accDeposit);
        accDeposit.setColumns(10);

        depositInput = new JTextField();
        depositInput.setBackground(new Color(200, 200, 200));
        depositInput.setBounds(280, 242, 96, 19);
        contentPane.add(depositInput);
        depositInput.setColumns(10);

        accWithdraw = new JTextField();
        accWithdraw.setBackground(new Color(200, 200, 200));
        accWithdraw.setBounds(142, 271, 96, 19);
        contentPane.add(accWithdraw);
        accWithdraw.setColumns(10);

        withdrawInput = new JTextField();
        withdrawInput.setBackground(new Color(200, 200, 200));
        withdrawInput.setBounds(280, 271, 96, 19);
        contentPane.add(withdrawInput);
        withdrawInput.setColumns(10);

        acc1Transfer = new JTextField();
        acc1Transfer.setBackground(new Color(200, 200, 200));
        acc1Transfer.setBounds(142, 323, 96, 19);
        contentPane.add(acc1Transfer);
        acc1Transfer.setColumns(10);

        acc2Transfer = new JTextField();
        acc2Transfer.setBackground(new Color(200, 200, 200));
        acc2Transfer.setBounds(280, 323, 96, 19);
        contentPane.add(acc2Transfer);
        acc2Transfer.setColumns(10);

        transferAmount = new JTextField();
        transferAmount.setBackground(new Color(200, 200, 200));
        transferAmount.setBounds(422, 323, 96, 19);
        contentPane.add(transferAmount);
        transferAmount.setColumns(10);

        JLabel lblNewLabel = new JLabel("Sender Acc Num");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblNewLabel.setBounds(142, 300, 96, 13);
        contentPane.add(lblNewLabel);

        JLabel lblReciver = new JLabel("Reciver Acc Num");
        lblReciver.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblReciver.setBounds(280, 300, 96, 13);
        contentPane.add(lblReciver);

        JLabel lblAmount_2 = new JLabel("Acc Num");
        lblAmount_2.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblAmount_2.setBounds(161, 219, 45, 13);
        contentPane.add(lblAmount_2);

        JLabel lblAmount_1 = new JLabel("Amount");
        lblAmount_1.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblAmount_1.setBounds(299, 219, 45, 13);
        contentPane.add(lblAmount_1);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblAmount.setBounds(434, 300, 45, 13);
        contentPane.add(lblAmount);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(422, 256, 85, 21);
        contentPane.add(btnNewButton);

        // Create and add the action listener
        HandlerClass handler = new HandlerClass();
        showAllData.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
    }

    private class HandlerClass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showAllData) {
                messageLabel.setText(""); // Clear any previous message
                updateAccountData();
            } else if (e.getSource() == depositButton) {
                int accountNumber = Integer.parseInt(accDeposit.getText());
                int amount = Integer.parseInt(depositInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == accountNumber) {
                        account.deposit(amount);
                        messageLabel.setText("Deposit successful!");
                        updateAccountData();
                        break;
                    }
                }
            } else if (e.getSource() == withdrawButton) {
                int accountNumber = Integer.parseInt(accWithdraw.getText());
                int amount = Integer.parseInt(withdrawInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == accountNumber) {
                        if (account.getBalance() >= amount) { // Check for sufficient balance
                            account.withdraw(amount);
                            messageLabel.setText("Withdrawal successful!");
                        } else {
                            messageLabel.setText("Insufficient funds for withdrawal.");
                        }
                        updateAccountData();
                        break;
                    }
                }
            } else if (e.getSource() == transferButton) {
                int fromAccountNumber = Integer.parseInt(acc1Transfer.getText());
                int toAccountNumber = Integer.parseInt(acc2Transfer.getText());
                int amount = Integer.parseInt(transferAmount.getText());
                transferObject = new Transaction();

                Account fromAccount = null;
                Account toAccount = null;

                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == fromAccountNumber) {
                        fromAccount = account;
                    }
                    if (account.getAccountNum() == toAccountNumber) {
                        toAccount = account;
                    }
                }
                if (fromAccount != null && toAccount != null) {
                    if (fromAccount.getBalance() >= amount) {
                        transferObject.transfer(fromAccount, toAccount, amount);
                        messageLabel.setText("Transfer successful!");
                    } else {
                        messageLabel.setText("Insufficient funds for transfer.");
                    }
                    updateAccountData();
                } else {
                    messageLabel.setText("Invalid account number(s) for transfer.");
                }
            }
        }

        private void updateAccountData() {
            sbAllData = new StringBuilder();
            for (Account account : globalAccounts) {
                sbAllData.append(" Name: ").append(account.getFirstName()).append(" ").append(account.getLastName())
                        .append(", Account Number: ").append(account.getAccountNum()).append(", Balance: ")
                        .append(account.getBalance()).append("\n");
            }
            showAll.setText("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
        }
    }
}
