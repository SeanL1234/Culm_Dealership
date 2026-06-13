/**
 * DealershipGUI.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 9, 2026
 *
 * Description:
 * Graphical User Interface (GUI) for the Vehicle Dealership Management System.
 * This class provides users with an interactive Swing-based interface to:
 * - View and manage vehicle inventory
 * - Search for vehicles
 * - Manage customers and transactions
 * - Generate reports
 * - Perform file operations
 * - Access dealership deals and promotions
 *
 * The GUI communicates with the DealershipSystem class, which handles
 * all business logic and data management.
 */

import javax.swing.*;
import java.awt.*;

/**
 * Main GUI window for the Vehicle Dealership Management System.
 */
public class DealershipGUI extends JFrame {

    /** Core dealership system that manages all dealership operations. */
    private DealershipSystem dealership;

    /** Output area used to display inventory, search results, reports, and messages. */
    private JTextArea txtOutput;

    /** Main navigation buttons. */
    private JButton btnInventory;
    private JButton btnCustomers;
    private JButton btnTransactions;
    private JButton btnReports;
    private JButton btnFiles;
    private JButton btnDeals;

    /**
     * Constructs the dealership GUI and initializes all interface components.
     * Loads dealership data from the test files and displays any startup messages.
     */
    public DealershipGUI() {

        // Create dealership system and load data files
        dealership = new DealershipSystem(
                "test_customers.txt",
                "test_inventory.txt",
                "test_transactions.txt");

        // Configure main window properties
        setTitle("Vehicle Dealership Management System");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and configure title label
        JLabel title = new JLabel(
                "Vehicle Dealership Management System",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        // Main text area used for displaying output
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

        // Create navigation menu panel
        JPanel menuPanel =
                new JPanel(new GridLayout(1,5));

        // Initialize navigation buttons
        btnInventory = new JButton("Inventory");
        btnCustomers = new JButton("Customers");
        btnTransactions = new JButton("Transactions");
        btnReports = new JButton("Reports");
        btnFiles = new JButton("Files");
        btnDeals = new JButton("Deals");

        // Add buttons to menu panel
        menuPanel.add(btnInventory);
        menuPanel.add(btnCustomers);
        menuPanel.add(btnTransactions);
        menuPanel.add(btnReports);
        menuPanel.add(btnFiles);
        menuPanel.add(btnDeals);

        add(menuPanel, BorderLayout.SOUTH);

        // Register button event handlers
        btnInventory.addActionListener(e -> openInventoryMenu());
        btnCustomers.addActionListener(e -> openCustomerMenu());
        btnTransactions.addActionListener(e -> openTransactionMenu());
        btnReports.addActionListener(e -> openReportMenu());
        btnFiles.addActionListener(e -> openFileMenu());
        btnDeals.addActionListener(e -> openDealsMenu());

        // Display GUI window
        setVisible(true);
    }

    /* ==================================================
                       INVENTORY MENU
       ==================================================
       Provides inventory-related operations including:
       - Displaying inventory
       - Searching by VIN
       - Searching by specification requirements
       - Sorting inventory
       - Removing vehicles
       ================================================== */

    /**
     * Opens the inventory management menu and processes
     * the selected inventory-related operation.
     */
    private void openInventoryMenu() {

        // Available inventory actions
        String[] options = {
                "Display Inventory",
                "Search VIN",
                "Search Spec",
                "Sort By Year",
                "Sort By Manufacturer",
                "Remove Vehicle",
                "See Depreciated Value",
                "See Cheap Vehicles",
                "Add Vehicle"
        };

        // Display action selection dialog
        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Choose Inventory Action",
                "Inventory",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        // User cancelled dialog
        if(choice == null)
            return;

        switch(choice) {

            case "Display Inventory":

                // Display complete inventory list
                txtOutput.setText(
                    dealership.displayInventory()
                );

                break;

            case "Search VIN":

                // Prompt user for VIN
                String vin =
                        JOptionPane.showInputDialog(
                                "Enter VIN:");

                // Search for matching vehicle
                Vehicle v =
                        dealership.searchVehicleByVIN(vin);

                // Display search results
                if (v == null) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Vehicle not found.",
                                "Search VIN",
                                JOptionPane.WARNING_MESSAGE);

                        txtOutput.setText("Vehicle not found.");
                } else {
                        JOptionPane.showMessageDialog(
                                this,
                                v.toString(),
                                "Search VIN - Vehicle",
                                JOptionPane.INFORMATION_MESSAGE);

                        txtOutput.setText(v.toString());
                }

                break;

            case "Sort By Year":

                // Sort inventory by vehicle year
                dealership.sortVehicleByYear();

                txtOutput.setText(
                        "Inventory sorted by year.\n\n"
                        + dealership.displayInventory());

                break;

            case "Search Spec":

                // Allow user to select specification type
                String[] specTypes = {"Gas","Electric","Hybrid"};

                String chosen = (String) JOptionPane.showInputDialog(
                        this,
                        "Choose spec type",
                        "Spec Type",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        specTypes,
                        specTypes[0]);

                if (chosen == null)
                        break;

                try {

                        // Collect common specification information
                        int mileage = Integer.parseInt(
                                JOptionPane.showInputDialog(this, "Mileage (int):"));

                        int age = Integer.parseInt(
                                JOptionPane.showInputDialog(this, "Age (int):"));

                        int warranty = Integer.parseInt(
                                JOptionPane.showInputDialog(this, "Warranty Expire Year (int):"));

                        String lastMaintenance =
                                JOptionPane.showInputDialog(this, "Last Maintenance (String):");

                        int baseMaintenanceFee = Integer.parseInt(
                                JOptionPane.showInputDialog(this, "Base Maintenance Fee (int):"));

                        Spec expectation = null;

                        // Create appropriate specification object
                        if (chosen.equals("Gas")) {

                                String engineType =
                                        JOptionPane.showInputDialog(this, "Engine Type (String):");

                                int fuelCap = Integer.parseInt(
                                        JOptionPane.showInputDialog(this, "Fuel Capacity (int):"));

                                int fuelEff = Integer.parseInt(
                                        JOptionPane.showInputDialog(this, "Fuel Efficiency (int):"));

                                expectation = new GasSpec(
                                        mileage, age, warranty,
                                        lastMaintenance, baseMaintenanceFee,
                                        engineType, fuelCap, fuelEff);

                        } else if (chosen.equals("Electric")) {

                                double batteryHealthPerc =
                                        Double.parseDouble(
                                                JOptionPane.showInputDialog(
                                                        this,
                                                        "Battery Health Percentage (0-100):"));

                                int chargingTime =
                                        Integer.parseInt(
                                                JOptionPane.showInputDialog(
                                                        this,
                                                        "Charging Time (int):"));

                                expectation = new ElectricSpec(
                                        mileage, age, warranty,
                                        lastMaintenance, baseMaintenanceFee,
                                        batteryHealthPerc / 100.0,
                                        chargingTime);

                        } else { // Hybrid

                                int powerReturn = Integer.parseInt(
                                        JOptionPane.showInputDialog(
                                                this,
                                                "Power Return Rate (int):"));

                                int chargingTime = Integer.parseInt(
                                        JOptionPane.showInputDialog(
                                                this,
                                                "Charging Time (int):"));

                                int fuelEff = Integer.parseInt(
                                        JOptionPane.showInputDialog(
                                                this,
                                                "Fuel Efficiency (int):"));

                                expectation = new HybridSpec(
                                        mileage, age, warranty,
                                        lastMaintenance, baseMaintenanceFee,
                                        powerReturn, chargingTime, fuelEff);
                        }

                        // Determine minimum similarity percentage
                        double percentMatch =
                                Double.parseDouble(
                                        JOptionPane.showInputDialog(
                                                this,
                                                "Percent Match (0-100):"));

                        // Search inventory for matching vehicles
                        Vehicle[] matches =
                                dealership.searchVehicleBySpec(
                                        expectation,
                                        percentMatch);

                        // Display results
                        if (matches == null || matches.length == 0) {
                                txtOutput.setText(
                                        "No matching vehicles found.");
                        } else {

                                String out = "";

                                for (Vehicle mv : matches)
                                        out += mv.toString() + "\n\n";

                                txtOutput.setText(out);
                        }

                } catch (NumberFormatException ex) {

                        // Handle invalid numeric input
                        txtOutput.setText(
                                "Input error: " + ex.getMessage());
                }

                break;

            case "Sort By Manufacturer":

                // Sort inventory alphabetically by manufacturer
                dealership.sortVehicleByManufacturer();

                txtOutput.setText(
                        "Inventory sorted by manufacturer.\n\n"
                        + dealership.displayInventory());

                break;

            case "Remove Vehicle":

                // Prompt for VIN of vehicle to remove
                String removeVIN =
                        JOptionPane.showInputDialog(
                                "VIN to remove:");

                // Attempt removal
                boolean removed =
                        dealership.removeVehicle(removeVIN);

                if (removed) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Vehicle removed.",
                                "Remove Vehicle",
                                JOptionPane.INFORMATION_MESSAGE);
                } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Vehicle not found.",
                                "Remove Vehicle",
                                JOptionPane.WARNING_MESSAGE);
                }

                break;

                case "See Depreciated Value":
                        try {
                                int year = Integer.parseInt(JOptionPane.showInputDialog("Years Passed: "));
                                String VIN = JOptionPane.showInputDialog("Vehicle VIN: ");
                                Vehicle car = dealership.searchVehicleByVIN(VIN);
                                Spec carSpec = dealership.searchVehicleByVIN(VIN).getVehicleSpec();
                                JOptionPane.showMessageDialog(this, "The depreciated value after " + year + "years is:\n$" + carSpec.calculateValueAfterYear(car.getBasePrice(), year));
                        } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(this, "Error! Enter a proper year value!", "Input Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        break;

                case "See Cheap Vehicles":
                        txtOutput.setText(dealership.displayCheapVehicles());
                        break;
                
                case "Add Vehicle":
                try {
                        String brandName = JOptionPane.showInputDialog("Brand name: ");
                        String modelName = JOptionPane.showInputDialog("Model name: ");
                        int year = Integer.parseInt(JOptionPane.showInputDialog("Year: "));
                        int basePrice = Integer.parseInt(JOptionPane.showInputDialog("Base price: "));
                        String trim = JOptionPane.showInputDialog("Trim: ");
                        int maxSpeed = Integer.parseInt(JOptionPane.showInputDialog("Max speed: "));
                        String type = JOptionPane.showInputDialog("Type: ");
                        String wheel = JOptionPane.showInputDialog("Amount of wheels: ");
                        String trans = JOptionPane.showInputDialog("Transmission: ");
                        int safe = Integer.parseInt(JOptionPane.showInputDialog("Safety rating: "));
                        int seats = Integer.parseInt(JOptionPane.showInputDialog("Number of seats: "));
                        String color = JOptionPane.showInputDialog("Color: ");
                        int tow = Integer.parseInt(JOptionPane.showInputDialog("Towing capacity: "));
                        String maintenance = JOptionPane.showInputDialog("Maintenance information: ");
                        int range = Integer.parseInt(JOptionPane.showInputDialog("Range: "));
                        String VIN = JOptionPane.showInputDialog("VIN: ");
                        String typeCar = JOptionPane.showInputDialog("Vehicle type (Gas/Electric): ");
                        Vehicle car = null;

                        if (typeCar.equals("Gas")) {
                                int maxHorsePower = Integer.parseInt(JOptionPane.showInputDialog("Max horsepower: "));
                                int mileage = Integer.parseInt(JOptionPane.showInputDialog("Mileage: "));
                                int age = Integer.parseInt(JOptionPane.showInputDialog("Age: "));
                                int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog("Warranty expiration year: "));
                                String lastMaintenance = JOptionPane.showInputDialog("Last maintenance date: ");
                                int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog("Base maintenance fee: "));
                                String engineType = JOptionPane.showInputDialog("Engine type: ");
                                int cap = Integer.parseInt(JOptionPane.showInputDialog("Capacity: "));
                                int eff = Integer.parseInt(JOptionPane.showInputDialog("Efficiency: "));
                                car = new GasVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                        } else if(typeCar.equals("Electric")) {
                                boolean hasAutoPilot = JOptionPane.showInputDialog("Has autopilot? (Y/N): ").equals("Y");
                                boolean hasModes = JOptionPane.showInputDialog("Has modes? (Y/N): ").equals("Y");
                                String chargerType = JOptionPane.showInputDialog("Charger type: ");
                                int mileage = Integer.parseInt(JOptionPane.showInputDialog("Mileage: "));
                                int age = Integer.parseInt(JOptionPane.showInputDialog("Age: "));
                                int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog("Warranty expiration year: "));
                                String lastMaintenance = JOptionPane.showInputDialog("Last maintenance date: ");
                                int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog("Base maintenance fee: "));
                                double batteryHealthPercentage = Double.parseDouble(JOptionPane.showInputDialog("Battery health percentage: "));
                                int chargingTime = Integer.parseInt(JOptionPane.showInputDialog("Charging time: "));
                                car = new ElectricVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                        } else {
                                boolean isRechargeable = JOptionPane.showInputDialog("Is rechargeable? (Y/N): ").equals("Y");
                                boolean hasModes = JOptionPane.showInputDialog("Has modes? (Y/N): ").equals("Y");
                                boolean hasPlugIn = JOptionPane.showInputDialog("Has plug-in? (Y/N): ").equals("Y");
                                String chargerType = JOptionPane.showInputDialog("Charger type: ");
                                int mileage = Integer.parseInt(JOptionPane.showInputDialog("Mileage: "));
                                int age = Integer.parseInt(JOptionPane.showInputDialog("Age: "));
                                int warrantyExpireYear = Integer.parseInt(JOptionPane.showInputDialog("Warranty expiration year: "));
                                String lastMaintenance = JOptionPane.showInputDialog("Last maintenance date: ");
                                int baseMaintenanceFee = Integer.parseInt(JOptionPane.showInputDialog("Base maintenance fee: "));
                                int powerReturnRate = Integer.parseInt(JOptionPane.showInputDialog("Power return rate: "));
                                int chargingTime = Integer.parseInt(JOptionPane.showInputDialog("Charging time: "));
                                int fuelEfficiency = Integer.parseInt(JOptionPane.showInputDialog("Fuel efficiency: "));
                                car = new HybridVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                        }
                        dealership.addVehicle(car);
                        JOptionPane.showMessageDialog(this, "Vehicle added to inventory.");
                } catch(NumberFormatException nfe) {
                        txtOutput.setText("Number format error!");
                }
                        break;
                }
    }

/* ==================================================
                    CUSTOMER MENU
   ==================================================
   Provides customer management operations including:
   - Viewing customer information
   - Searching customers
   - Managing customer accounts
   - Adding new customers
   - Updating loyalty points
   - Sorting customer records
   ================================================== */

/**
 * Opens the customer management menu and processes
 * the selected customer-related operation.
 *
 * Available operations:
 * - Display all customers
 * - Display loyal customers
 * - Search by customer ID
 * - Search by last name
 * - Display customer accounts
 * - Add customers
 * - Create customer accounts
 * - Update loyalty points
 * - Sort customer records
 */
private void openCustomerMenu() {

    // List of available customer operations
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

    // Display customer menu dialog
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Customer Menu",
            "Customers",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

    // User cancelled menu
    if(choice == null)
        return;

    switch(choice) {
            // Display information for all customers
            case "Display All Customers":
                txtOutput.setText(
                    dealership.displayAllCustomerInfo()
                );
                break;
            
            // Display only customers with loyalty status
            case "Display Loyal Customers":
                txtOutput.setText(
                    dealership.displayAllLoyalCustomerInfo()
                );
                break;

             // Search for a customer using their unique ID
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

                        // Display all accounts associated with a customer
                        // (Seller, Buyer, and Trade-In accounts)
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
            
            // Search for customers by last name
            case "Search Last Name":

                String ln =
                        JOptionPane.showInputDialog(
                                "Last Name:");

                Customer[] customers =
                        dealership.searchCustomerByLastName(ln);
                
                if (customers == null || customers.length == 0) {
                        txtOutput.setText("No customers found with last name: " + ln);
                } else {

                StringBuilder sb =
                        new StringBuilder();

                for(Customer cust : customers)
                    sb.append(cust)
                            .append("\n\n");

                txtOutput.setText(sb.toString());
        }

                break;
            
             // Create and add a new customer to the system
            case "Add Customer":

                String name =
                        JOptionPane.showInputDialog(
                                "Customer Name:");

                dealership.addCustomer(name);

                txtOutput.setText(
                        "Customer added.");

                break;

                        // Create a customer account
                        // Supported account types:
                        // - Seller
                        // - Buyer
                        // - Trade-In
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
            
            // Update a customer's loyalty points
            case "Update Loyalty":
                try {
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
                } catch(NumberFormatException nfe) {
                        txtOutput.setText("Error, number formatting");
                }

                break;

            // Sort customers by
            // Last Name -> ID
            case "Sort By LN + ID":

                dealership.sortCustomersByLNID();

            txtOutput.setText("Customers sorted.\n\n"+dealership.displayAllCustomerInfo());

                break;

            // Sort customers by
            // Last Name -> Loyalty Points -> ID
            case "Sort By LN + Loyalty + ID":

                dealership.sortCustomerLNLPID();

            txtOutput.setText("Customers sorted.\n\n"+dealership.displayAllCustomerInfo());

                break;
        }
    }

/* ==================================================
                  TRANSACTION MENU
   ==================================================
   Provides transaction management operations including:
   - Viewing transaction history
   - Searching transactions by customer
   - Searching transactions by date
   - Searching transactions by month
   - Searching transactions by year
   - Sorting transaction records
   ================================================== */

/**
 * Opens the transaction management menu and processes
 * the selected transaction-related operation.
 *
 * Available operations:
 * - Display all transactions
 * - Search transactions by customer ID
 * - Search transactions by exact date
 * - Search transactions by month
 * - Search transactions by year
 * - Sort transactions chronologically
 */
private void openTransactionMenu() {

    // List of available transaction operations
    String[] options = {
            "Display Transactions",
            "Search By Customer ID",
            "Search By Date",
            "Search By Month",
            "Search By Year",
            "Sort Transactions By Date",
    };

    // Display transaction menu dialog
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Transaction Menu",
            "Transactions",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

    // User cancelled menu
    if(choice == null)
        return;

    switch(choice) {

        case "Display Transactions":

            // Display complete transaction history
            txtOutput.setText(
                dealership.displayTransactionHistory()
            );
            break;

        case "Search By Customer ID":

            // Search transaction history for a specific customer
            String id =
                    JOptionPane.showInputDialog(
                            "Customer ID");

            Customer temp =
                    dealership.searchCustomerByID(id);

            if (temp == null) {

                    txtOutput.setText(
                            "Customer not found.");

            } else {

                    Transaction[] t =
                            temp.getCustomerTransactionHistory();

                    displayTransactions(t);
            }

            break;

        case "Search By Date":

            // Search transactions that occurred
            // on a specific day
            try {
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

            } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
            }

            break;

        case "Search By Month":

            // Search all transactions
            // within a specific month
            try {
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
            } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
            }
            break;

        case "Search By Year":

            // Search all transactions
            // within a specific year
            try {
            int yr =
                    Integer.parseInt(
                            JOptionPane.showInputDialog("Year"));

            displayTransactions(
                    dealership.searchTransactionsByYear(
                            yr));

            } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
            }

            break;

        case "Sort Transactions By Date":

            // Sort transactions in chronological order
            dealership.sortTransactionsByDate();

            txtOutput.setText(
                    "Transactions sorted.\n\n"
                    + dealership.displayTransactionHistory());

            break;
    }
}

/* ==================================================
                     REPORTS MENU
   ==================================================
   Provides dealership reporting operations including:
   - Calculating monthly profit
   - Calculating yearly profit
   - Generating financial summaries
   ================================================== */

/**
 * Opens the reports menu and processes
 * the selected report-generation operation.
 *
 * Available operations:
 * - View monthly profit report
 * - View yearly profit report
 */
private void openReportMenu() {

    // List of available report options
    String[] options = {
            "Monthly Salary",
            "Yearly Salary",
    };

    // Display reports menu dialog
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Reports",
            "Reports",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

    // User cancelled menu
    if(choice == null)
        return;

    switch(choice) {

        case "Monthly Salary":
        try {
            // Generate profit report for a specific month
            int month =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Month (in numbers)"));

            int year =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Year (in numbers)"));

            double monthly =
                    dealership.getMonthlySalary(
                            month,
                            year);

            txtOutput.setText(
                    "Monthly Profit: $" + monthly);

        } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
        }

            break;

        case "Yearly Salary":
        try {
            // Generate profit report for a specific year
            int yr =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    "Year"));

            double yearly =
                    dealership.getYearlySalary(yr);

            txtOutput.setText(
                    "Yearly Profit: $" + yearly);
        } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
        }

            break;
    }
}

/* ==================================================
                 FILE OPERATIONS MENU
   ==================================================
   Provides file management operations including:
   - Loading customer, inventory, and transaction data
   - Appending data to existing records
   - Overwriting existing records
   - Saving dealership data to files
   ================================================== */

/**
 * Opens the file operations menu and processes
 * the selected file-related operation.
 *
 * Available operations:
 * - Append customer data
 * - Append inventory data
 * - Append transaction data
 * - Overwrite customer data
 * - Overwrite inventory data
 * - Overwrite transaction data
 * - Save customer data
 * - Save inventory data
 * - Save transaction data
 */
private void openFileMenu() {

    // List of available file operations
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

    // Display file operations menu dialog
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "File Operations",
            "Files",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

    // User cancelled menu
    if(choice == null)
        return;

    switch(choice) {

        case "Append Customers": {

            // Append customer records from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to append customers from:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Append customers cancelled.");
                    break;
            }

            dealership.loadCustomers(fn, true);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Append Inventory": {

            // Append inventory records from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to append inventory from:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Append inventory cancelled.");
                    break;
            }

            dealership.loadInventory(fn, true);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Append Transactions": {

            // Append transaction records from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to append transactions from:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Append transactions cancelled.");
                    break;
            }

            dealership.loadTransactions(fn, true);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Overwrite Customers": {

            // Replace current customer records
            // with data from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to overwrite customers with:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Overwrite customers cancelled.");
                    break;
            }

            dealership.loadCustomers(fn, false);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Overwrite Inventory": {

            // Replace current inventory records
            // with data from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to overwrite inventory with:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Overwrite inventory cancelled.");
                    break;
            }

            dealership.loadInventory(fn, false);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Overwrite Transactions": {

            // Replace current transaction records
            // with data from a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to overwrite transactions with:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Overwrite transactions cancelled.");
                    break;
            }

            dealership.loadTransactions(fn, false);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Save Customers": {

            // Save customer records to a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to save customers to:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Save customers cancelled.");
                    break;
            }

            dealership.saveCustomers(fn);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Save Inventory": {

            // Save inventory records to a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to save inventory to:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Save inventory cancelled.");
                    break;
            }

            dealership.saveInventory(fn);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }

        case "Save Transactions": {

            // Save transaction history to a file
            String fn = JOptionPane.showInputDialog(
                    this,
                    "File name to save transactions to:");

            if (fn == null || fn.isEmpty()) {
                    txtOutput.setText(
                            "Save transactions cancelled.");
                    break;
            }

            dealership.saveTransactionHistory(fn);

            txtOutput.setText(
                    dealership.getMessages());

            dealership.clearMessages();

            break;
        }
    }
}

/**
 * Opens the deal operations menu and processes
 * the selected vehicle transaction operation.
 *
 * Available operations:
 * - Sell a vehicle to the dealership
 * - Buy a vehicle from dealership inventory
 * - Trade an existing vehicle for another vehicle
 */
private void openDealsMenu() {

    // List of available deal operations
    String[] options = {
            "Sell Vehicle",
            "Buy Vehicle",
            "Trade Vehicle"
    };

    // Display deal operations menu dialog
    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Deal Operations",
            "Deals",
            JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]);

    // User cancelled menu
    if(choice == null)
        return;

    switch(choice) {

        case "Sell Vehicle":

            // Process a vehicle sale from customer
            // to dealership inventory

            String sellID =
                    JOptionPane.showInputDialog("ID");

            Customer sellCus =
                    dealership.searchCustomerByID(sellID);

            if(sellCus == null) {

                    // Customer does not exist
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer not found! Ensure you entered the correct id, or go add a new customer!",
                            "Error!",
                            JOptionPane.WARNING_MESSAGE);

            } else {

                    SellerAccount sellAcc =
                            (SellerAccount)sellCus.getSellerAccount();

                    if(sellAcc == null) {

                            // Customer has no seller account
                            JOptionPane.showMessageDialog(
                                    this,
                                    "You do not have an account! Go create one!",
                                    "Error!",
                                    JOptionPane.WARNING_MESSAGE);

                    } else {

                            // Verify seller account eligibility
                            if(dealership.validateAccount("Seller", sellID)) {

                                    // Check whether offered vehicle
                                    // falls within dealership criteria
                                    if(dealership.withinDealerRange(
                                            sellAcc.getOwnedVehicle(),
                                            sellAcc)) {

                                            int dealPrice =
                                                    dealership.createDealPrice(
                                                            "seller",
                                                            sellCus.isLoyal(),
                                                            sellAcc,
                                                            null);

                                            // Complete vehicle sale
                                            if(sellAcc.sellVehicle(dealPrice)) {

                                                    JOptionPane.showMessageDialog(
                                                            this,
                                                            "Transaction complete!\nVehicle sold for: $" + dealPrice + "\nCustomer Name: " + sellCus.getName() + "\nVehicle Sold: " + sellAcc.getOwnedVehicle(),
                                                            "Success!",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                            }

                                            // Record transaction history
                                            Transaction transaction =
                                                    new Transaction(
                                                            sellCus.getName(),
                                                            sellID,
                                                            dealPrice,
                                                            false,
                                                            false,
                                                            true,
                                                            false,
                                                            6,
                                                            11,
                                                            2026,
                                                            sellAcc.getOwnedVehicle(),
                                                            null);

                                            dealership.updateTransactionHistory(transaction);
                                            sellCus.updateTransactionHistory(transaction);

                                    } else {

                                            // Offer exceeds acceptable range
                                            JOptionPane.showMessageDialog(
                                                    this,
                                                    "Deal rejected! You're offer price is too high compared to the base price!",
                                                    "Rejected",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                    }

                            } else {

                                    // Seller account validation failed
                                    JOptionPane.showMessageDialog(
                                            this,
                                            "Your seller account is invalid! Check your rating, or make sure your vehicle information is correct!",
                                            "Invalid!",
                                            JOptionPane.WARNING_MESSAGE);
                            }
                    }
            }

            break;

        case "Buy Vehicle":
            try {
            // Process vehicle purchase
            // from dealership inventory

            String buyID =
                    JOptionPane.showInputDialog("ID");

            Customer buyCus =
                    dealership.searchCustomerByID(buyID);

            if(buyCus == null) {

                    // Customer does not exist
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer not found! Ensure you entered the correct id, or go add a new customer!",
                            "Error!",
                            JOptionPane.WARNING_MESSAGE);

            } else {

                    BuyerAccount buyAcc =
                            (BuyerAccount)buyCus.getBuyerAccount();

                    if(buyAcc == null) {

                            // Customer has no buyer account
                            JOptionPane.showMessageDialog(
                                    this,
                                    "You do not have an account! Go create one!",
                                    "Error!",
                                    JOptionPane.WARNING_MESSAGE);

                    } else {

                            // Verify buyer account eligibility
                            if(dealership.validateAccount("Buyer", buyID)) {

                                    // Display all vehicles matching
                                    // customer preferences
                                    Vehicle[] vehicles =
                                            dealership.showAllApplicableForCustomer(buyAcc);

                                    if(vehicles != null) {

                                            if(vehicles.length != 0) {

                                                    for(int i = 0; i < vehicles.length; i++) {

                                                            JOptionPane.showMessageDialog(
                                                                    this,
                                                                    "" + vehicles[i],
                                                                    "Option " + (i + 1),
                                                                    JOptionPane.OK_OPTION);
                                                    }
                                            }
                                    }

                                    // Select desired vehicle
                                    int option =
                                            Integer.parseInt(
                                                    JOptionPane.showInputDialog(
                                                            "Choice: "));

                                    if(option - 1 < 0
                                            || option - 1 >= vehicles.length) {

                                            JOptionPane.showMessageDialog(
                                                    this,
                                                    "Error! Not a valid option!",
                                                    "Invalid!",
                                                    JOptionPane.ERROR_MESSAGE);

                                            break;
                                    }

                                    Vehicle buying =
                                            vehicles[option - 1];

                                    int dealPrice =
                                            dealership.createDealPrice(
                                                    "buyer",
                                                    buyCus.isLoyal(),
                                                    buyAcc,
                                                    buying);

                                    // Complete purchase
                                    if(buyAcc.buyVehicle(dealPrice)) {

                                            JOptionPane.showMessageDialog(
                                                    this,
                                                    "Transaction complete!\nVehicle sold for: $" + dealPrice,
                                                    "Success!",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                    }

                                    // Record transaction history
                                    Transaction transaction =
                                            new Transaction(
                                                    buyCus.getName(),
                                                    buyID,
                                                    dealPrice,
                                                    false,
                                                    true,
                                                    false,
                                                    false,
                                                    6,
                                                    11,
                                                    2026,
                                                    null,
                                                    buying);

                                    dealership.updateTransactionHistory(transaction);
                                    buyCus.updateTransactionHistory(transaction);

                                    // Update remaining customer budget
                                    buyAcc.updateBudget(
                                            buyAcc.getBudget() - dealPrice);

                            } else {

                                    // Buyer account validation failed
                                    JOptionPane.showMessageDialog(
                                            this,
                                            "Your Buyer account is invalid! Check your rating, or make sure your vehicle information is correct!",
                                            "Invalid!",
                                            JOptionPane.WARNING_MESSAGE);
                            }
                    }
            }
                }catch (NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
            }
            break;

        case "Trade Vehicle":
            try {
            // Process vehicle trade-in transaction

            String tradeID =
                    JOptionPane.showInputDialog("ID");

            Customer tradeCus =
                    dealership.searchCustomerByID(tradeID);

            if(tradeCus == null) {

                    // Customer does not exist
                    JOptionPane.showMessageDialog(
                            this,
                            "Customer not found! Ensure you entered the correct id, or go add a new customer!",
                            "Error!",
                            JOptionPane.WARNING_MESSAGE);

            } else {

                    TradeInAccount tradeAcc =
                            (TradeInAccount)tradeCus.getTradeInAccount();

                    if(tradeAcc == null) {

                            // Customer has no trade-in account
                            JOptionPane.showMessageDialog(
                                    this,
                                    "You do not have an account! Go create one!",
                                    "Error!",
                                    JOptionPane.WARNING_MESSAGE);

                    } else {

                            // Verify trade-in account eligibility
                            if(dealership.validateAccount("Trade", tradeID)) {

                                    // Display all eligible trade vehicles
                                    Vehicle[] vehicles =
                                            dealership.showAllApplicableForCustomer(tradeAcc);

                                    if(vehicles != null) {

                                            if(vehicles.length != 0) {

                                                    for(int i = 0; i < vehicles.length; i++) {

                                                            JOptionPane.showMessageDialog(
                                                                    this,
                                                                    "" + vehicles[i],
                                                                    "Option " + (i + 1),
                                                                    JOptionPane.OK_OPTION);
                                                    }
                                            }
                                    }

                                    // Select vehicle to trade for
                                    int option =
                                            Integer.parseInt(
                                                    JOptionPane.showInputDialog(
                                                            "Choice: "));

                                    if(option - 1 < 0
                                            || option - 1 >= vehicles.length) {

                                            JOptionPane.showMessageDialog(
                                                    this,
                                                    "Error! Not a valid option!",
                                                    "Invalid!",
                                                    JOptionPane.ERROR_MESSAGE);

                                            break;
                                    }

                                    Vehicle tradingFor =
                                            vehicles[option - 1];

                                    // Complete trade-in transaction
                                    if(tradeAcc.tradeVehicle(tradingFor)) {

                                            JOptionPane.showMessageDialog(
                                                    this,
                                                    "Transaction complete!\nVehicle traded!",
                                                    "Success!",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                    }

                                    // Record transaction history
                                    Transaction transaction =
                                            new Transaction(
                                                    tradeCus.getName(),
                                                    tradeID,
                                                    -1,
                                                    true,
                                                    false,
                                                    false,
                                                    false,
                                                    6,
                                                    11,
                                                    2026,
                                                    tradeAcc.getVehicleForTrading(),
                                                    tradingFor);

                                    dealership.updateTransactionHistory(transaction);
                                    tradeCus.updateTransactionHistory(transaction);

                            } else {

                                    // Trade-in account validation failed
                                    JOptionPane.showMessageDialog(
                                            this,
                                            "Your TradeIn account is invalid! Check your rating, or make sure your vehicle information is correct!",
                                            "Invalid!",
                                            JOptionPane.WARNING_MESSAGE);
                            }
                    }
            }
        } catch(NumberFormatException nfe) {
                txtOutput.setText("error! number formatting");
        }
    }
}

/* ==================================================
                        HELPERS
   ==================================================
   Utility methods used throughout the GUI for:
   - Displaying transaction information
   - Launching the application
   ================================================== */

/**
 * Displays an array of transactions in the output area.
 *
 * If no transactions are found, an appropriate
 * message is displayed instead.
 *
 * @param transactions the array of transactions
 *                     to display
 */
private void displayTransactions(
        Transaction[] transactions) {

    // Build output string for display
    StringBuilder sb =
            new StringBuilder();

    // Check whether any transactions exist
    if(transactions == null ||
            transactions.length == 0) {

        sb.append("No transactions found.");
    }
    else {

        // Display each transaction on a separate line
        for(Transaction t : transactions) {

            sb.append(t)
                    .append("\n\n");
        }
    }

    // Display results in the output area
    txtOutput.setText(sb.toString());
}

/**
 * Program entry point.
 *
 * Launches the Vehicle Dealership Management System
 * GUI on the Swing Event Dispatch Thread (EDT).
 *
 * @param args command-line arguments (unused)
 */
public static void main(String[] args) {

    // Create and display the GUI
    SwingUtilities.invokeLater(
            DealershipGUI::new);
}
}
