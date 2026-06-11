/**
 * DealershipSystem.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 9, 2026
 * 
 * Description: The GUI of the dealership system, 
 * providing an interface for users to interact with the system's functionalities
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
    private JButton btnDeals;

    public DealershipGUI() {

        dealership = new DealershipSystem(
                "test_customers.txt",
                "test_inventory.txt",
                "test_transactions.txt");

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

                // Show any messages produced during system initialization
                if (dealership != null) {
                        String msgs = dealership.getMessages();
                        if (msgs != null && !msgs.isEmpty()) {
                                txtOutput.setText(msgs);
                                dealership.clearMessages();
                        }
                }

        JPanel menuPanel =
                new JPanel(new GridLayout(1,5));

        btnInventory = new JButton("Inventory");
        btnCustomers = new JButton("Customers");
        btnTransactions = new JButton("Transactions");
        btnReports = new JButton("Reports");
        btnFiles = new JButton("Files");
        btnDeals = new JButton("Deals");

        menuPanel.add(btnInventory);
        menuPanel.add(btnCustomers);
        menuPanel.add(btnTransactions);
        menuPanel.add(btnReports);
        menuPanel.add(btnFiles);
        menuPanel.add(btnDeals);

        add(menuPanel, BorderLayout.SOUTH);

        btnInventory.addActionListener(e -> openInventoryMenu());
        btnCustomers.addActionListener(e -> openCustomerMenu());
        btnTransactions.addActionListener(e -> openTransactionMenu());
        btnReports.addActionListener(e -> openReportMenu());
        btnFiles.addActionListener(e -> openFileMenu());
        btnDeals.addActionListener(e -> openDealsMenu());

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
            txtOutput.setText(
                dealership.displayInventory()
            );
                
            
                break;

            case "Search VIN":

                String vin =
                        JOptionPane.showInputDialog(
                                "Enter VIN:");

                Vehicle v =
                        dealership.searchVehicleByVIN(vin);

                                if (v == null) {
                                        JOptionPane.showMessageDialog(this, "Vehicle not found.", "Search VIN", JOptionPane.WARNING_MESSAGE);
                                        txtOutput.setText("Vehicle not found.");
                                } else {
                                        JOptionPane.showMessageDialog(this, v.toString(), "Search VIN - Vehicle", JOptionPane.INFORMATION_MESSAGE);
                                        txtOutput.setText(v.toString());
                                }

                break;

            case "Sort By Year":

                dealership.sortVehicleByYear();

                txtOutput.setText(
                        "Inventory sorted by year.");

                break;
        
                        case "Search Spec":
                                String[] specTypes = {"Gas","Electric","Hybrid"};
                                String chosen = (String) JOptionPane.showInputDialog(this, "Choose spec type", "Spec Type", JOptionPane.PLAIN_MESSAGE, null, specTypes, specTypes[0]);
                                if (chosen == null) break;
                                try {
                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                        int warranty = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance (String):");
                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                        Spec expectation = null;
                                        if (chosen.equals("Gas")) {
                                                String engineType = JOptionPane.showInputDialog(this, "Engine Type (String):");
                                                int fuelCap = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Capacity (int):"));
                                                int fuelEff = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                expectation = new GasSpec(mileage, age, warranty, lastMaintenance, baseMaintenanceFee, engineType, fuelCap, fuelEff);
                                        } else if (chosen.equals("Electric")) {
                                                double batteryHealthPerc = Double.parseDouble(JOptionPane.showInputDialog(this, "Battery Health Percentage (0-100):"));
                                                int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                expectation = new ElectricSpec(mileage, age, warranty, lastMaintenance, baseMaintenanceFee, batteryHealthPerc/100.0, chargingTime);
                                        } else { // Hybrid
                                                int powerReturn = Integer.parseInt(JOptionPane.showInputDialog(this, "Power Return Rate (int):"));
                                                int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                int fuelEff = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                expectation = new HybridSpec(mileage, age, warranty, lastMaintenance, baseMaintenanceFee, powerReturn, chargingTime, fuelEff);
                                        }
                                        double percentMatch = Double.parseDouble(JOptionPane.showInputDialog(this, "Percent Match (0-100):"));
                                        Vehicle[] matches = dealership.searchVehicleBySpec(expectation, percentMatch);
                                        if (matches == null || matches.length == 0) {
                                                txtOutput.setText("No matching vehicles found.");
                                        } else {
                                                String out = "";
                                                for (Vehicle mv : matches) out += mv.toString() + "\n\n";
                                                txtOutput.setText(out);
                                        }
                                } catch (NumberFormatException ex) {
                                        txtOutput.setText("Input error: " + ex.getMessage());
                                }
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

                        if (removed) {
                                JOptionPane.showMessageDialog(this, "Vehicle removed.", "Remove Vehicle", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                                JOptionPane.showMessageDialog(this, "Vehicle not found.", "Remove Vehicle", JOptionPane.WARNING_MESSAGE);
                        }

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
                "Display Accounts",
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
                txtOutput.setText(
                    dealership.displayAllCustomerInfo()
                );
                break;

            case "Display Loyal Customers":
                txtOutput.setText(
                    dealership.displayAllLoyalCustomerInfo()
                );
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

                        case "Display Accounts":

                                String acctId = JOptionPane.showInputDialog("Customer ID:");
                                if (acctId == null) {
                                        txtOutput.setText("Display accounts cancelled.");
                                        break;
                                }
                                                Customer foundCust = dealership.searchCustomerByID(acctId);
                                                if (foundCust == null) {
                                                        txtOutput.setText("Customer not found.");
                                                        break;
                                                }
                                                StringBuilder accOut = new StringBuilder();
                                                accOut.append(foundCust.getEssentialInfo()).append("\n");
                                                Account[] accts = foundCust.getCustomerAccount();
                                // Seller
                                accOut.append("Seller Account:\n");
                                if (accts == null || accts.length < 1 || accts[0] == null) {
                                        accOut.append("null\n\n");
                                } else {
                                        accOut.append(accts[0].toString()).append("\n\n");
                                }
                                // Buyer
                                accOut.append("Buyer Account:\n");
                                if (accts == null || accts.length < 2 || accts[1] == null) {
                                        accOut.append("null\n\n");
                                } else {
                                        accOut.append(accts[1].toString()).append("\n\n");
                                }
                                // Trade-in
                                accOut.append("Trade-In Account:\n");
                                if (accts == null || accts.length < 3 || accts[2] == null) {
                                        accOut.append("null\n\n");
                                } else {
                                        accOut.append(accts[2].toString()).append("\n\n");
                                }

                                txtOutput.setText(accOut.toString());

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

                                String customerID = JOptionPane.showInputDialog("ID");
                                String accountType = JOptionPane.showInputDialog("Seller / Buyer / TradeIn");

                                if (customerID == null || accountType == null) {
                                        txtOutput.setText("Account creation cancelled.");
                                        break;
                                }

                                String t = accountType.trim().toLowerCase();
                                Account acc = null;
                                Customer cust = dealership.searchCustomerByID(customerID);
                                if(cust == null) {
                                        JOptionPane.showMessageDialog(this, "Customer does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
                                        break;
                                }
                                try {
                                        if (t.equals("seller")) {
                                                boolean org = JOptionPane.showInputDialog(this, "Organization? (Y/N)").equalsIgnoreCase("Y");
                                                boolean fam = JOptionPane.showInputDialog(this, "Family? (Y/N)").equalsIgnoreCase("Y");
                                                int offered = Integer.parseInt(JOptionPane.showInputDialog(this, "Offered price (int):"));
                                                int rating = Integer.parseInt(JOptionPane.showInputDialog(this, "Rating (int):"));
                                                String brandName = JOptionPane.showInputDialog(this, "Brand Name");
                                                String modelName = JOptionPane.showInputDialog(this, "Model Name");
                                                int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Year Made (int):"));
                                                int basePrice = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Price (int):"));
                                                String trim = JOptionPane.showInputDialog(this, "Trim");
                                                int maxSpeed = Integer.parseInt(JOptionPane.showInputDialog(this, "Max Speed (int):"));
                                                String type = JOptionPane.showInputDialog(this, "Type Vehicle");
                                                String wheel = JOptionPane.showInputDialog(this, "Wheel");
                                                String trans = JOptionPane.showInputDialog(this, "Transmission");
                                                int safe = Integer.parseInt(JOptionPane.showInputDialog(this, "Safety Rating (int):"));
                                                int seats = Integer.parseInt(JOptionPane.showInputDialog(this, "Number of Seats (int):"));
                                                String color = JOptionPane.showInputDialog(this, "Color");
                                                int tow = Integer.parseInt(JOptionPane.showInputDialog(this, "Tow (int):"));
                                                String maintenance = JOptionPane.showInputDialog(this, "Maintenance Period:");
                                                int carRange = Integer.parseInt(JOptionPane.showInputDialog(this, "Range (int):"));
                                                String vin = JOptionPane.showInputDialog(this, "VIN");
                                                Vehicle owned = null;
                                                String typeCar = JOptionPane.showInputDialog(this, "Type of Car? (Gas, Hybrid, Electric): ");
                                                // if (vin != null && !vin.equalsIgnoreCase("none")) owned = dealership.searchVehicleByVIN(vin);
                                                if(typeCar.equals("Gas")) {
                                                        int maxHorsePower = Integer.parseInt(JOptionPane.showInputDialog(this, "Max Horsepower (int):"));
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        String engineType = JOptionPane.showInputDialog(this, "Engine Type");
                                                        int cap = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Cap (int):"));
                                                        int eff = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                        owned = new GasVehicle(modelName, brandName, type, year, basePrice, safe, vin, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, carRange, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                                                } else if(typeCar.equals("Electric")) {
                                                        boolean hasAutoPilot = JOptionPane.showInputDialog(this, "Has autopilot? (Y/N)").equalsIgnoreCase("Y");
                                                        boolean hasModes = JOptionPane.showInputDialog(this, "Has modes? (Y/N)").equalsIgnoreCase("Y");
                                                        String chargerType = JOptionPane.showInputDialog(this, "Charger Type");
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        double batteryHealthPercentage = Double.parseDouble(JOptionPane.showInputDialog(this, "Battery Health Percentage (0-100):")) / 100.0;
                                                        int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                        // use previously-entered VIN and carRange variables
                                                        owned = new ElectricVehicle(modelName, brandName, type, year, basePrice, safe, vin, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, carRange, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                                                } else {
                                                        boolean isRechargeable = JOptionPane.showInputDialog(this, "Is Rechargeable? (Y/N)").equalsIgnoreCase("Y");
                                                        boolean hasModes = JOptionPane.showInputDialog(this, "Has modes? (Y/N)").equalsIgnoreCase("Y");
                                                        boolean hasPlugIn = JOptionPane.showInputDialog(this, "Has Plug-in? (Y/N)").equalsIgnoreCase("Y");
                                                        String chargerType = JOptionPane.showInputDialog(this, "Charger Type");
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        int powerReturnRate = Integer.parseInt(JOptionPane.showInputDialog(this, "Power Return Rate (int):"));
                                                        int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                        int fuelEfficiency = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                        owned = new HybridVehicle(modelName, brandName, type, year, basePrice, safe, vin, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, carRange, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                                                }
                                                double range = Double.parseDouble(JOptionPane.showInputDialog(this, "Range of accept (double):"));
                                                acc = new SellerAccount(org, fam, offered, rating, owned, range);
                                        } else if (t.equals("buyer")) {
                                                boolean org = JOptionPane.showInputDialog(this, "Organization? (Y/N)").equalsIgnoreCase("Y");
                                                boolean fam = JOptionPane.showInputDialog(this, "Family? (Y/N)").equalsIgnoreCase("Y");
                                                int budget = Integer.parseInt(JOptionPane.showInputDialog(this, "Budget (int):"));
                                                String typeCar = JOptionPane.showInputDialog(this, "Preferred car type (e.g., Gas/Electric/Hybrid):");
                                                double percentMatch = Double.parseDouble(JOptionPane.showInputDialog(this, "Percent match (double 0-100):"));
                                                double rangeOfAccept = Double.parseDouble(JOptionPane.showInputDialog(this, "Range of accept (double):"));
                                                Spec expectation = null;
                                                if(typeCar.equals("Gas")) {
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        String engineType = JOptionPane.showInputDialog(this, "Engine Type");
                                                        int cap = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Cap (int):"));
                                                        int eff = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                        expectation = new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff);
                                                } else if(typeCar.equals("Electric")) {
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        double batteryHealthPercentage = Double.parseDouble(JOptionPane.showInputDialog(this, "Battery Health Percentage (0-100):")) / 100.0;
                                                        int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                        expectation = new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime);
                                                } else {
                                                        int mileage = Integer.parseInt(JOptionPane.showInputDialog(this, "Mileage (int):"));
                                                        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age (int):"));
                                                        int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));
                                                        String lastMaintenance = JOptionPane.showInputDialog(this, "Last Maintenance");
                                                        int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));
                                                        int powerReturnRate = Integer.parseInt(JOptionPane.showInputDialog(this, "Power Return Rate (int):"));
                                                        int chargingTime = Integer.parseInt(JOptionPane.showInputDialog(this, "Charging Time (int):"));
                                                        int fuelEfficiency = Integer.parseInt(JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));
                                                        expectation = new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency);
                                                }
                                                // BuyerAccount constructor: (boolean isOrg, boolean isFamily, int budget, String typeCar, Spec expectation, double percentMatch, double rangeOfAccept)
                                                acc = new BuyerAccount(org, fam, budget, typeCar, expectation, percentMatch, rangeOfAccept);
                                        } else if (t.equals("tradein") || t.equals("trade-in") || t.equals("trade")) {
                                                boolean org = JOptionPane.showInputDialog(this, "Organization? (Y/N)").equalsIgnoreCase("Y");
                                                boolean fam = JOptionPane.showInputDialog(this, "Family? (Y/N)").equalsIgnoreCase("Y");
                                                int rating = Integer.parseInt(JOptionPane.showInputDialog(this, "Rating (int):"));
                                                String vin = JOptionPane.showInputDialog(this, "If vehicle for trading exists in inventory, enter VIN, otherwise enter 'none':");
                                                Vehicle tradeVeh = null;
                                                if (vin != null && !vin.equalsIgnoreCase("none")) tradeVeh = dealership.searchVehicleByVIN(vin);
                                                double range = Double.parseDouble(JOptionPane.showInputDialog(this, "Range of accept (double):"));
                                                acc = new TradeInAccount(org, fam, null, rating, tradeVeh, 1.0, range);
                                        } else {
                                                txtOutput.setText("Unknown account type: " + accountType);
                                                break;
                                        }

                                        boolean ok = dealership.createAccountForCustomer(cust.getName(), customerID, accountType, acc);
                                        txtOutput.setText(ok ? "Account created." : "Account creation failed.");
                                } catch (NumberFormatException ex) {
                                        txtOutput.setText("Input error: " + ex.getMessage());
                                }

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
                txtOutput.setText(
                    dealership.displayTransactionHistory()
                );
                break;

            case "Search By Customer ID":

                String id =
                        JOptionPane.showInputDialog(
                                "Customer ID");

                Transaction[] t =
                        dealership.searchCustomerByID(id).getCustomerTransactionHistory();

                
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
                "Append Customers",
                "Append Inventory",
                "Append Transactions",
                "Overwrite Customers",
                "Overwrite Inventory",
                "Overwrite Transactions",
                "Save Customers",
                "Save Inventory",
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

                        case "Append Customers": {
                                String fn = JOptionPane.showInputDialog(this, "File name to append customers from:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Append customers cancelled.");
                                        break;
                                }
                                dealership.loadCustomers(fn, true);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Append Inventory": {
                                String fn = JOptionPane.showInputDialog(this, "File name to append inventory from:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Append inventory cancelled.");
                                        break;
                                }
                                dealership.loadInventory(fn, true);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Append Transactions": {
                                String fn = JOptionPane.showInputDialog(this, "File name to append transactions from:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Append transactions cancelled.");
                                        break;
                                }
                                dealership.loadTransactions(fn, true);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Overwrite Customers": {
                                String fn = JOptionPane.showInputDialog(this, "File name to overwrite customers with:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Overwrite customers cancelled.");
                                        break;
                                }
                                dealership.loadCustomers(fn, false);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Overwrite Inventory": {
                                String fn = JOptionPane.showInputDialog(this, "File name to overwrite inventory with:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Overwrite inventory cancelled.");
                                        break;
                                }
                                dealership.loadInventory(fn, false);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Overwrite Transactions": {
                                String fn = JOptionPane.showInputDialog(this, "File name to overwrite transactions with:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Overwrite transactions cancelled.");
                                        break;
                                }
                                dealership.loadTransactions(fn, false);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Save Customers": {
                                String fn = JOptionPane.showInputDialog(this, "File name to save customers to:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Save customers cancelled.");
                                        break;
                                }
                                dealership.saveCustomers(fn);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Save Inventory": {
                                String fn = JOptionPane.showInputDialog(this, "File name to save inventory to:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Save inventory cancelled.");
                                        break;
                                }
                                dealership.saveInventory(fn);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }

                        case "Save Transactions": {
                                String fn = JOptionPane.showInputDialog(this, "File name to save transactions to:");
                                if (fn == null || fn.isEmpty()) {
                                        txtOutput.setText("Save transactions cancelled.");
                                        break;
                                }
                                dealership.saveTransactionHistory(fn);
                                txtOutput.setText(dealership.getMessages());
                                dealership.clearMessages();
                                break;
                        }
                }
    }

    private void openDealsMenu() {

        String[] options = {
                "Sell Vehicle",
                "Buy Vehicle",
                "Trade Vehicle"
        };

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Deal Operations",
                "Deals",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if(choice == null)
            return;

        switch(choice) {

                case "Sell Vehicle":
                        String sellID = JOptionPane.showInputDialog("ID");    
                        Customer sellCus = dealership.searchCustomerByID(sellID);
                        if(sellCus == null) {
                                JOptionPane.showMessageDialog(this, "Customer not found! Ensure you entered the correct id, or go add a new customer!", "Error!", JOptionPane.WARNING_MESSAGE);
                        } else {
                                SellerAccount sellAcc = (SellerAccount)sellCus.getSellerAccount();
                                if(sellAcc == null) {
                                        JOptionPane.showMessageDialog(this, "You do not have an account! Go create one!", "Error!", JOptionPane.WARNING_MESSAGE);
                                } else {
                                        if(dealership.validateAccount("Seller", sellID)) {
                                                if(dealership.withinDealerRange(sellAcc.getOwnedVehicle(), sellAcc)) {
                                                     int dealPrice = dealership.createDealPrice("seller", sellCus.isLoyal(), sellAcc, null);
                                                        if(sellAcc.sellVehicle(dealPrice)) {
                                                                JOptionPane.showMessageDialog(this, "Transaction complete!\nVehicle sold for: $" + dealPrice, "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                        Transaction transaction = new Transaction(sellCus.getName(), sellID, dealPrice, false, false, true, false, 6, 11, 2026, sellAcc.getOwnedVehicle(), null);
                                                        dealership.updateTransactionHistory(transaction);
                                                        sellCus.updateTransactionHistory(transaction);   
                                                } else {
                                                        JOptionPane.showMessageDialog(this, "Deal rejected! You're offer price is too high compared to the base price!", "Rejected", JOptionPane.INFORMATION_MESSAGE);
                                                }
                                        } else {
                                                JOptionPane.showMessageDialog(this, "Your seller account is invalid! Check your rating, or make sure your vehicle information is correct!", "Invalid!", JOptionPane.WARNING_MESSAGE);
                                        }
                                }
                        }
                        break;

                case "Buy Vehicle":
                        String buyID = JOptionPane.showInputDialog("ID");    
                        Customer buyCus = dealership.searchCustomerByID(buyID);
                        if(buyCus == null) {
                                JOptionPane.showMessageDialog(this, "Customer not found! Ensure you entered the correct id, or go add a new customer!", "Error!", JOptionPane.WARNING_MESSAGE);
                        } else {
                                BuyerAccount buyAcc = (BuyerAccount)buyCus.getBuyerAccount();
                                if(buyAcc == null) {
                                        JOptionPane.showMessageDialog(this, "You do not have an account! Go create one!", "Error!", JOptionPane.WARNING_MESSAGE);
                                } else {
                                        if(dealership.validateAccount("Buyer", buyID)) {
                                                Vehicle[] vehicles = dealership.showAllApplicableForCustomer(buyAcc);
                                                if(vehicles != null) {
                                                        if(vehicles.length != 0) {
                                                                for(int i = 0; i < vehicles.length; i++) {
                                                                       JOptionPane.showMessageDialog(this, "" + vehicles[i], "Option " + (i + 1), JOptionPane.OK_OPTION); 
                                                                }
                                                        }
                                                }
                                                int option = Integer.parseInt(JOptionPane.showInputDialog("Choice: ")); 
                                                if(option-1 < 0 || option-1 >= vehicles.length) {
                                                        JOptionPane.showMessageDialog(this, "Error! Not a valid option!", "Invalid!", JOptionPane.ERROR_MESSAGE);
                                                        break;
                                                }
                                                Vehicle buying = vehicles[option-1];
                                                int dealPrice = dealership.createDealPrice("buyer", buyCus.isLoyal(), buyAcc, buying);
                                                if(buyAcc.buyVehicle(dealPrice)) {
                                                        JOptionPane.showMessageDialog(this, "Transaction complete!\nVehicle sold for: $" + dealPrice, "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                } 
                                                Transaction transaction = new Transaction(buyCus.getName(), buyID, dealPrice, false, true, false, false, 6, 11, 2026, null, buying);
                                                dealership.updateTransactionHistory(transaction);
                                                buyCus.updateTransactionHistory(transaction);
                                                buyAcc.updateBudget(buyAcc.getBudget() - dealPrice);
                                        } else {
                                                JOptionPane.showMessageDialog(this, "Your Buyer account is invalid! Check your rating, or make sure your vehicle information is correct!", "Invalid!", JOptionPane.WARNING_MESSAGE);
                                        }
                                }
                        }
                        break;
                
                case "Trade Vehicle":
                        String tradeID = JOptionPane.showInputDialog("ID");    
                        Customer tradeCus = dealership.searchCustomerByID(tradeID);
                        if(tradeCus == null) {
                                JOptionPane.showMessageDialog(this, "Customer not found! Ensure you entered the correct id, or go add a new customer!", "Error!", JOptionPane.WARNING_MESSAGE);
                        } else {
                                TradeInAccount tradeAcc = (TradeInAccount)tradeCus.getTradeInAccount();
                                if(tradeAcc == null) {
                                        JOptionPane.showMessageDialog(this, "You do not have an account! Go create one!", "Error!", JOptionPane.WARNING_MESSAGE);
                                } else {
                                        if(dealership.validateAccount("Trade", tradeID)) {
                                                Vehicle[] vehicles = dealership.showAllApplicableForCustomer(tradeAcc);
                                                if(vehicles != null) {
                                                        if(vehicles.length != 0) {
                                                                for(int i = 0; i < vehicles.length; i++) {
                                                                       JOptionPane.showMessageDialog(this, "" + vehicles[i], "Option " + (i + 1), JOptionPane.OK_OPTION); 
                                                                }
                                                        }
                                                }
                                                int option = Integer.parseInt(JOptionPane.showInputDialog("Choice: ")); 
                                                if(option-1 < 0 || option-1 >= vehicles.length) {
                                                        JOptionPane.showMessageDialog(this, "Error! Not a valid option!", "Invalid!", JOptionPane.ERROR_MESSAGE);
                                                        break;
                                                }
                                                Vehicle tradingFor = vehicles[option-1];
                                                if(tradeAcc.tradeVehicle(tradingFor)) {
                                                        JOptionPane.showMessageDialog(this, "Transaction complete!\nVehicle traded!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                                } 
                                                Transaction transaction = new Transaction(tradeCus.getName(), tradeID, -1, true, false, false, false, 6, 11, 2026, tradeAcc.getVehicleForTrading(), tradingFor);
                                                dealership.updateTransactionHistory(transaction);
                                                tradeCus.updateTransactionHistory(transaction);
                                        } else {
                                                JOptionPane.showMessageDialog(this, "Your TradeIn account is invalid! Check your rating, or make sure your vehicle information is correct!", "Invalid!", JOptionPane.WARNING_MESSAGE);
                                        }
                                }
                        }
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
