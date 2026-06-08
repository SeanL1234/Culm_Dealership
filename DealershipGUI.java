/*
Try Compile this
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
    private JButton btnFiles;

    public DealershipGUI() {

        dealership = new DealershipSystem(
                "customers.txt",
                "inventory.txt",
                "transactions.txt");

        setTitle("Vehicle Dealership Management System");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "Vehicle Dealership Management System",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);

        add(new JScrollPane(txtOutput),
                BorderLayout.CENTER);

        JPanel menuPanel =
                new JPanel(new GridLayout(1,5));

        btnInventory = new JButton("Inventory");
        btnCustomers = new JButton("Customers");
        btnTransactions = new JButton("Transactions");
        btnReports = new JButton("Reports");
        btnFiles = new JButton("Files");

        menuPanel.add(btnInventory);
        menuPanel.add(btnCustomers);
        menuPanel.add(btnTransactions);
        menuPanel.add(btnReports);
        menuPanel.add(btnFiles);

        add(menuPanel, BorderLayout.SOUTH);

        btnInventory.addActionListener(e -> openInventoryMenu());
        btnCustomers.addActionListener(e -> openCustomerMenu());
        btnTransactions.addActionListener(e -> openTransactionMenu());
        btnReports.addActionListener(e -> openReportMenu());
        btnFiles.addActionListener(e -> openFileMenu());

        setVisible(true);
    }

    /* ==================================================
                       INVENTORY
       ================================================== */

    private void openInventoryMenu() {

        String[] options = {
                "Display Inventory",
                "Search VIN",
                "Search Spec",
                "Sort By Year",
                "Sort By Manufacturer",
                "Remove Vehicle"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Choose Inventory Action",
                "Inventory",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

            case "Display Inventory":

                dealership.displayInventory();
                break;

            case "Search VIN":

                String vin =
                        JOptionPane.showInputDialog(
                                "Enter VIN:");

                Vehicle v =
                        dealership.searchVehicleByVIN(vin);

                txtOutput.setText(
                        v == null ?
                                "Vehicle not found." :
                                v.toString());

                break;

            case "Sort By Year":

                dealership.sortVehicleByYear();

                txtOutput.setText(
                        "Inventory sorted by year.");

                break;

            case "Sort By Manufacturer":

                dealership.sortVehicleByManufacturer();

                txtOutput.setText(
                        "Inventory sorted by manufacturer.");

                break;

            case "Remove Vehicle":

                String removeVIN =
                        JOptionPane.showInputDialog(
                                "VIN to remove:");

                boolean removed =
                        dealership.removeVehicle(removeVIN);

                txtOutput.setText(
                        removed ?
                                "Vehicle removed." :
                                "Vehicle not found.");

                break;
        }
    }

    /* ==================================================
                      CUSTOMERS
       ================================================== */

    private void openCustomerMenu() {

        String[] options = {
                "Display All Customers",
                "Display Loyal Customers",
                "Search Customer ID",
                "Search Last Name",
                "Add Customer",
                "Create Account",
                "Update Loyalty",
                "Sort By LN + ID",
                "Sort By LN + Loyalty + ID"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Customer Menu",
                "Customers",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

            case "Display All Customers":

                dealership.displayAllCustomerInfo();

                break;

            case "Display Loyal Customers":

                dealership.displayAllLoyalCustomerInfo();

                break;

            case "Search Customer ID":

                String id =
                        JOptionPane.showInputDialog(
                                "Customer ID:");

                Customer c =
                        dealership.searchCustomerByID(id);

                txtOutput.setText(
                        c == null ?
                                "Customer not found." :
                                c.toString());

                break;

            case "Search Last Name":

                String ln =
                        JOptionPane.showInputDialog(
                                "Last Name:");

                Customer[] customers =
                        dealership.searchCustomerByLastName(ln);

                StringBuilder sb =
                        new StringBuilder();

                for(Customer cust : customers)
                    sb.append(cust)
                            .append("\n\n");

                txtOutput.setText(sb.toString());

                break;

            case "Add Customer":

                String name =
                        JOptionPane.showInputDialog(
                                "Customer Name:");

                dealership.addCustomer(name);

                txtOutput.setText(
                        "Customer added.");

                break;

            case "Create Account":

                String customerName =
                        JOptionPane.showInputDialog("Name");

                String customerID =
                        JOptionPane.showInputDialog("ID");

                String accountType =
                        JOptionPane.showInputDialog(
                                "Seller / Buyer / TradeIn");

                dealership.createAccountForCustomer(
                        customerName,
                        customerID,
                        accountType);

                txtOutput.setText(
                        "Account creation attempted.");

                break;

            case "Update Loyalty":

                String n =
                        JOptionPane.showInputDialog("Name");

                String i =
                        JOptionPane.showInputDialog("ID");

                int loyalty =
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        "New Loyalty Points"));

                dealership.updateCustomerLoyalty(
                        n,
                        i,
                        loyalty);

                txtOutput.setText(
                        "Loyalty updated.");

                break;

            case "Sort By LN + ID":

                dealership.sortCustomersByLNID();

                txtOutput.setText(
                        "Customers sorted.");

                break;

            case "Sort By LN + Loyalty + ID":

                dealership.sortCustomerLNLPID();

                txtOutput.setText(
                        "Customers sorted.");

                break;
        }
    }

    /* ==================================================
                     TRANSACTIONS
       ================================================== */

    private void openTransactionMenu() {

        String[] options = {
                "Display Transactions",
                "Search By Customer ID",
                "Search By Date",
                "Search By Month",
                "Search By Year"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Transaction Menu",
                "Transactions",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

            case "Display Transactions":

                dealership.displayTransactionHistory();

                break;

            case "Search By Customer ID":

                String id =
                        JOptionPane.showInputDialog(
                                "Customer ID");

                Transaction[] t =
                        dealership.searchTransactionByID(id);

                displayTransactions(t);

                break;

            case "Search By Date":

                int month =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Month"));

                int day =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Day"));

                int year =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Year"));

                displayTransactions(
                        dealership.searchTransactionsByDate(
                                month,
                                day,
                                year));

                break;

            case "Search By Month":

                int m =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Month"));

                int y =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Year"));

                displayTransactions(
                        dealership.searchTransactionsByMonth(
                                m,
                                y));

                break;

            case "Search By Year":

                int yr =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Year"));

                displayTransactions(
                        dealership.searchTransactionsByYear(
                                yr));

                break;
        }
    }

    /* ==================================================
                         REPORTS
       ================================================== */

    private void openReportMenu() {

        String[] options = {
                "Monthly Salary",
                "Yearly Salary",
                "Sort Transactions By Date"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Reports",
                "Reports",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

            case "Monthly Salary":

                int month =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Month"));

                int year =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Year"));

                double monthly =
                        dealership.getMonthlySalary(
                                month,
                                year);

                txtOutput.setText(
                        "Monthly Profit: $" + monthly);

                break;

            case "Yearly Salary":

                int yr =
                        Integer.parseInt(
                                JOptionPane.showInputDialog("Year"));

                double yearly =
                        dealership.getYearlySalary(yr);

                txtOutput.setText(
                        "Yearly Profit: $" + yearly);

                break;

            case "Sort Transactions By Date":

                dealership.sortTransactionsByDate();

                txtOutput.setText(
                        "Transactions sorted.");

                break;
        }
    }

    /* ==================================================
                      FILE OPERATIONS
       ================================================== */

    private void openFileMenu() {

        String[] options = {
                "Load Inventory",
                "Save Inventory",
                "Load Transactions",
                "Save Transactions"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "File Operations",
                "Files",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

            case "Load Inventory":

                dealership.loadInventory(
                        "inventory.txt");

                txtOutput.setText(
                        "Inventory loaded.");

                break;

            case "Save Inventory":

                dealership.saveInventory(
                        "inventory.txt");

                txtOutput.setText(
                        "Inventory saved.");

                break;

            case "Load Transactions":

                dealership.loadTransactionHistory(
                        "transactions.txt");

                txtOutput.setText(
                        "Transactions loaded.");

                break;

            case "Save Transactions":

                dealership.saveTransactionHistory(
                        "transactions.txt");

                txtOutput.setText(
                        "Transactions saved.");

                break;
        }
    }

    /* ==================================================
                       HELPERS
       ================================================== */

    private void displayTransactions(
            Transaction[] transactions) {

        StringBuilder sb =
                new StringBuilder();

        if(transactions == null ||
                transactions.length == 0) {

            sb.append("No transactions found.");
        }
        else {

            for(Transaction t : transactions) {

                sb.append(t)
                        .append("\n\n");
            }
        }

        txtOutput.setText(sb.toString());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                DealershipGUI::new);
    }
}
