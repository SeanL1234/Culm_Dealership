/*
Do not compile, bugs ahead need full dealership
*/
import javax.swing.*;
import java.awt.*;

public class DealershipGUI extends JFrame {

    private DealershipSystem dealership;

    private JTextArea txtOutput;

    private JButton btnInventory;
    private JButton btnCustomers;
    private JButton btnTransactions;
    private JButton btnReports;

    public DealershipGUI() {

        dealership = new DealershipSystem();

        setTitle("ICS4U Vehicle Dealership");
        setSize(1000,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title =
            new JLabel(
                "Vehicle Dealership Management System",
                SwingConstants.CENTER);

        title.setFont(
            new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);

        add(new JScrollPane(txtOutput),
            BorderLayout.CENTER);

        JPanel menuPanel =
            new JPanel(new GridLayout(1,4));

        btnInventory =
            new JButton("Inventory");

        btnCustomers =
            new JButton("Customers");

        btnTransactions =
            new JButton("Transactions");

        btnReports =
            new JButton("Reports");

        menuPanel.add(btnInventory);
        menuPanel.add(btnCustomers);
        menuPanel.add(btnTransactions);
        menuPanel.add(btnReports);

        add(menuPanel, BorderLayout.SOUTH);

        btnInventory.addActionListener(e ->
            openInventoryMenu());

        btnCustomers.addActionListener(e ->
            openCustomerMenu());

        btnTransactions.addActionListener(e ->
            openTransactionMenu());

        btnReports.addActionListener(e ->
            openReportMenu());

        setVisible(true);
    }

    private void openInventoryMenu() {

        String[] options = {
            "Display Inventory",
            "Search VIN",
            "Sort By Year",
            "Sort By Manufacturer"
        };

        String choice =
            (String) JOptionPane.showInputDialog(
                this,
                "Choose Action",
                "Inventory",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice){

            case "Display Inventory":

                txtOutput.setText(
                    dealership.displayInventory());

                break;

            case "Search VIN":

                String vin =
                    JOptionPane.showInputDialog(
                        "Enter VIN");

                Vehicle v =
                    dealership.searchVehicleByVIN(vin);

                if(v != null)
                    txtOutput.setText(v.toString());
                else
                    txtOutput.setText(
                        "Vehicle not found.");

                break;
        }
    }

    private void openCustomerMenu() {

        txtOutput.setText(
            "Customer functions here");
    }

    private void openTransactionMenu() {

        txtOutput.setText(
            "Transaction functions here");
    }

    private void openReportMenu() {

        txtOutput.setText(
            "Report functions here");
    }

    public static void main(String[] args) {

        new DealershipGUI();
    }
}

