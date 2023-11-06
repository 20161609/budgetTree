import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel jPanel;
    private JButton manageTreeButton;
    private JButton manageTransactionsButton;

    public App() {
        manageTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will create a new window for the ManageTree form
                ManageTree manageTree = new ManageTree(); // Assume ManageTree is a JFrame.
                manageTree.pack();
                manageTree.setLocationRelativeTo(null); // to center the window
                manageTree.setVisible(true);
            }
        });

        manageTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will create a new window for the ManageTransactions form
                ManageTransactions manageTransactions = new ManageTransactions(); // Assume ManageTransactions is a JFrame.
                manageTransactions.pack();
                manageTransactions.setLocationRelativeTo(null); // to center the window
                manageTransactions.setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
