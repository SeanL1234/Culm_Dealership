/**
 * DealershipSystem.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 9, 2026
 * 
 * Description: The main class for the dealership system, 
 * responsible for managing customers, vehicles, transactions, and providing core functionalities 
 * such as loading data from files and handling the business logic of the dealership operations.
 */

import java.io.*;

public class DealershipSystem {
    private final int DEAL_RANGE = 1000;
    private Customer[] customers;
    private Vehicle[] vehicles;
    private Transaction[] transactionHistory;
    private int numCustomer;
    private int numCars;
    private int numTransactions;
    // A simple message buffer that GUI can read and display.
    private String messages = "";

    /**
     * Append a message to the internal message buffer. GUI can read this via getMessages().
     */
    public void appendMessage(String msg) {
        if (msg == null) return;
        if (messages == null) messages = "";
        messages += msg + "\n";
    }

    /**
     * Return the current buffered messages. Does not clear them.
     */
    public String getMessages() {
        return messages == null ? "" : messages;
    }

    /**
     * Clear buffered messages.
     */
    public void clearMessages() {
        messages = "";
    }

    /**
     * Construct the system and load data files. By default files are loaded (not appended).
     */
    public DealershipSystem(String customerFileName, String inventoryFileName, String transactionFileName) {
        // Load into fresh arrays by default
        loadCustomers(customerFileName, false);
        loadInventory(inventoryFileName, false);
        loadTransactions(transactionFileName, false);
    }

    /**
     * Load customers from file. If append==true, new customers are appended to existing array.
     */
    public void loadCustomers(String customerFileName, boolean append) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(customerFileName));
            int fileCount = Integer.parseInt(read.readLine());
            int startIndex = 0;
            if (append && customers != null) startIndex = numCustomer;
            Customer[] newArr = new Customer[startIndex + fileCount];
            if (append && customers != null) System.arraycopy(customers, 0, newArr, 0, numCustomer);
            customers = newArr;
            for (int idx = 0; idx < fileCount; idx++) {
                String name = read.readLine();
                String id = read.readLine();
                int loyal = Integer.parseInt(read.readLine());
                read.readLine(); // text indicating a seller account
                String stat = read.readLine();
                SellerAccount sellerAcc = null;
                if(!stat.equals("null")) {
                    boolean org = stat.equals("Y");
                    boolean fam = read.readLine().equals("Y");
                    int offered = Integer.parseInt(read.readLine());
                    int rating = Integer.parseInt(read.readLine());
                    String brandName = read.readLine();
                    String modelName = read.readLine();
                    int year = Integer.parseInt(read.readLine());
                    int basePrice = Integer.parseInt(read.readLine());
                    String trim = read.readLine();
                    int maxSpeed = Integer.parseInt(read.readLine());
                    String type = read.readLine();
                    String wheel = read.readLine();
                    String trans = read.readLine();
                    int safe = Integer.parseInt(read.readLine());
                    int seats = Integer.parseInt(read.readLine());
                    String color = read.readLine();
                    int tow = Integer.parseInt(read.readLine());
                    String maintenance = read.readLine();
                    int range = Integer.parseInt(read.readLine());
                    String VIN = read.readLine();
                    String typeCar = read.readLine();
                    Vehicle car = null;
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        car = new GasVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        car = new ElectricVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else {
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        car = new HybridVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                    double rangeOfAccept = Double.parseDouble(read.readLine());
                    sellerAcc = new SellerAccount(org, fam, offered, rating, car, rangeOfAccept);
                }
                read.readLine(); // text indicating a buyer account
                stat = read.readLine();
                BuyerAccount buyerAcc = null;
                if(!stat.equals("null")) {
                    boolean isOrganization = stat.equals("Y");
                    boolean isFamily = read.readLine().equals("Y");
                    int budget = Integer.parseInt(read.readLine());
                    String typeCar = read.readLine();
                    Spec expectation = null;
                    if(typeCar.equals("Gas")) {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        expectation = new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff);
                    } else if(typeCar.equals("Electric")) {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        expectation = new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime);
                    } else {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        expectation = new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency);
                    }
                    double percentMatch = Double.parseDouble(read.readLine());
                    double rangeOfAccept = Double.parseDouble(read.readLine());
                    buyerAcc = new BuyerAccount(isOrganization, isFamily, budget, typeCar, expectation, percentMatch, rangeOfAccept);
                }
                read.readLine(); // text indicating a trade-in account
                stat = read.readLine();
                TradeInAccount tradeAcc = null;
                if(!stat.equals("null")) {
                    boolean isOrganization = stat.equals("Y");
                    boolean isFamily = read.readLine().equals("Y");
                    String brandName = read.readLine();
                    String modelName = read.readLine();
                    int year = Integer.parseInt(read.readLine());
                    int basePrice = Integer.parseInt(read.readLine());
                    String trim = read.readLine();
                    int maxSpeed = Integer.parseInt(read.readLine());
                    String type = read.readLine();
                    String wheel = read.readLine();
                    String trans = read.readLine();
                    int safe = Integer.parseInt(read.readLine());
                    int seats = Integer.parseInt(read.readLine());
                    String color = read.readLine();
                    int tow = Integer.parseInt(read.readLine());
                    String maintenance = read.readLine();
                    int range = Integer.parseInt(read.readLine());
                    String VIN = read.readLine();
                    String typeCar = read.readLine();
                    Vehicle car = null;
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        car = new GasVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        car = new ElectricVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else {
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        car = new HybridVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                    int rating = Integer.parseInt(read.readLine());
                    Spec expectation = null;
                    String typeSpec = read.readLine();
                    if(typeSpec.equals("Gas")) {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        expectation = new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff);
                    } else if(typeSpec.equals("Electric")) {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        expectation = new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime);
                    } else {
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        expectation = new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency);
                    }
                    double percentMatch = Double.parseDouble(read.readLine());
                    double rangeOfAccept = Double.parseDouble(read.readLine());
                    tradeAcc = new TradeInAccount(isOrganization, isFamily, expectation, rating, car, percentMatch, rangeOfAccept);
                }
                
                int numhistory = Integer.parseInt(read.readLine());
                Transaction[] transactions = new Transaction[numhistory];
                for(int x = 0; x < numhistory; x++) {
                    String thisName = read.readLine();
                    String ID = read.readLine();
                    int finalPrice = Integer.parseInt(read.readLine());
                    boolean isTradeIn = read.readLine().equals("Y");
                    boolean isSeller = read.readLine().equals("Y");
                    boolean isBuyer = read.readLine().equals("Y");
                    boolean isLease = read.readLine().equals("Y");
                    int month = Integer.parseInt(read.readLine());
                    int date = Integer.parseInt(read.readLine());
                    int year = Integer.parseInt(read.readLine());
                    String brandName = read.readLine();
                    String modelName = read.readLine();
                    int carYear = Integer.parseInt(read.readLine());
                    int basePrice = Integer.parseInt(read.readLine());
                    String trim = read.readLine();
                    int maxSpeed = Integer.parseInt(read.readLine());
                    String type = read.readLine();
                    String wheel = read.readLine();
                    String trans = read.readLine();
                    int safe = Integer.parseInt(read.readLine());
                    int seats = Integer.parseInt(read.readLine());
                    String color = read.readLine();
                    int tow = Integer.parseInt(read.readLine());
                    String maintenance = read.readLine();
                    int range = Integer.parseInt(read.readLine());
                    String VIN = read.readLine();
                    String typeCar = read.readLine();
                    Vehicle carObt = null;
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        carObt = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        carObt = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else if(typeCar.equals("Hybrid")){
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        carObt = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                    typeCar = read.readLine();
                    Vehicle carSold = null;
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        carObt = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        carObt = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else if(typeCar.equals("Hybrid")){
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        carObt = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                    transactions[x] = new Transaction(thisName, ID, finalPrice, isTradeIn, isSeller, isBuyer, isLease, month, date, year, carObt, carSold);
                }

                customers[startIndex + idx] = new Customer(loyal, name, id, new Account[] {sellerAcc, buyerAcc, tradeAcc}, transactions);
            }
            numCustomer = startIndex + fileCount;
            read.close();
        } catch(IOException ex) {
            appendMessage("I/O ERROR while loading customers: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Load inventory (vehicles). If append==true, append to existing vehicles array.
     */
    public void loadInventory(String inventoryFileName, boolean append) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(inventoryFileName));
            int fileCount = Integer.parseInt(read.readLine());
            int startIndex = 0;
            if (append && vehicles != null) startIndex = numCars;
            Vehicle[] newArr = new Vehicle[startIndex + fileCount];
            if (append && vehicles != null) System.arraycopy(vehicles, 0, newArr, 0, numCars);
            vehicles = newArr;
            for (int idx = 0; idx < fileCount; idx++) {
                String brandName = read.readLine();
                String modelName = read.readLine();
                int year = Integer.parseInt(read.readLine());
                int basePrice = Integer.parseInt(read.readLine());
                String trim = read.readLine();
                int maxSpeed = Integer.parseInt(read.readLine());
                String type = read.readLine();
                String wheel = read.readLine();
                String trans = read.readLine();
                int safe = Integer.parseInt(read.readLine());
                int seats = Integer.parseInt(read.readLine());
                String color = read.readLine();
                int tow = Integer.parseInt(read.readLine());
                String maintenance = read.readLine();
                int range = Integer.parseInt(read.readLine());
                String VIN = read.readLine();
                String typeCar = read.readLine();
                Vehicle car = null;
                if(typeCar.equals("Gas")) {
                    int maxHorsePower = Integer.parseInt(read.readLine());
                    int mileage = Integer.parseInt(read.readLine());
                    int age = Integer.parseInt(read.readLine());
                    int warrantyExpireYear = Integer.parseInt(read.readLine());
                    String lastMaintenance = read.readLine();
                    int baseMaintenanceFee = Integer.parseInt(read.readLine());
                    String engineType = read.readLine();
                    int cap = Integer.parseInt(read.readLine());
                    int eff = Integer.parseInt(read.readLine());
                    car = new GasVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                } else if(typeCar.equals("Electric")) {
                    boolean hasAutoPilot = read.readLine().equals("Y");
                    boolean hasModes = read.readLine().equals("Y");
                    String chargerType = read.readLine();
                    int mileage = Integer.parseInt(read.readLine());
                    int age = Integer.parseInt(read.readLine());
                    int warrantyExpireYear = Integer.parseInt(read.readLine());
                    String lastMaintenance = read.readLine();
                    int baseMaintenanceFee = Integer.parseInt(read.readLine());
                    double batteryHealthPercentage = Double.parseDouble(read.readLine());
                    int chargingTime = Integer.parseInt(read.readLine());
                    car = new ElectricVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                } else {
                    boolean isRechargeable = read.readLine().equals("Y");
                    boolean hasModes = read.readLine().equals("Y");
                    boolean hasPlugIn = read.readLine().equals("Y");
                    String chargerType = read.readLine();
                    int mileage = Integer.parseInt(read.readLine());
                    int age = Integer.parseInt(read.readLine());
                    int warrantyExpireYear = Integer.parseInt(read.readLine());
                    String lastMaintenance = read.readLine();
                    int baseMaintenanceFee = Integer.parseInt(read.readLine());
                    int powerReturnRate = Integer.parseInt(read.readLine());
                    int chargingTime = Integer.parseInt(read.readLine());
                    int fuelEfficiency = Integer.parseInt(read.readLine());
                    car = new HybridVehicle(modelName, brandName, type, year, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                }
                vehicles[startIndex + idx] = car;
            }
            numCars = startIndex + fileCount;
            read.close();
        } catch(IOException ex) {
            appendMessage("I/O ERROR while loading inventory: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Load transactions. If append==true, append to existing transactionHistory array.
     */
    public void loadTransactions(String transactionFileName, boolean append) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(transactionFileName));
            int fileCount = Integer.parseInt(read.readLine());
            int startIndex = 0;
            if (append && transactionHistory != null) startIndex = numTransactions;
            Transaction[] newArr = new Transaction[startIndex + fileCount];
            if (append && transactionHistory != null) System.arraycopy(transactionHistory, 0, newArr, 0, numTransactions);
            transactionHistory = newArr;
            for (int idx = 0; idx < fileCount; idx++) {
                String thisName = read.readLine();
                String ID = read.readLine();
                int finalPrice = Integer.parseInt(read.readLine());
                boolean isTradeIn = read.readLine().equals("Y");
                boolean isSeller = read.readLine().equals("Y");
                boolean isBuyer = read.readLine().equals("Y");
                boolean isLease = read.readLine().equals("Y");
                int month = Integer.parseInt(read.readLine());
                int date = Integer.parseInt(read.readLine());
                int year = Integer.parseInt(read.readLine());
                Vehicle carObt = null;
                String check = read.readLine();
                if(!check.equals("null")) {
                    String brandName = check;
                    String modelName = read.readLine();
                    int carYear = Integer.parseInt(read.readLine());
                    int basePrice = Integer.parseInt(read.readLine());
                    String trim = read.readLine();
                    int maxSpeed = Integer.parseInt(read.readLine());
                    String type = read.readLine();
                    String wheel = read.readLine();
                    String trans = read.readLine();
                    int safe = Integer.parseInt(read.readLine());
                    int seats = Integer.parseInt(read.readLine());
                    String color = read.readLine();
                    int tow = Integer.parseInt(read.readLine());
                    String maintenance = read.readLine();
                    int range = Integer.parseInt(read.readLine());
                    String VIN = read.readLine();
                    String typeCar = read.readLine();
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        carObt = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        carObt = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else if(typeCar.equals("Hybrid")){
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        carObt = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                }
                Vehicle carSold = null;
                String temp = read.readLine();
                if(!temp.equals("null")) {
                    String brandName = temp;
                    String modelName = read.readLine();
                    int carYear = Integer.parseInt(read.readLine());
                    int basePrice = Integer.parseInt(read.readLine());
                    String trim = read.readLine();
                    int maxSpeed = Integer.parseInt(read.readLine());
                    String type = read.readLine();
                    String wheel = read.readLine();
                    String trans = read.readLine();
                    int safe = Integer.parseInt(read.readLine());
                    int seats = Integer.parseInt(read.readLine());
                    String color = read.readLine();
                    int tow = Integer.parseInt(read.readLine());
                    String maintenance = read.readLine();
                    int range = Integer.parseInt(read.readLine());
                    String VIN = read.readLine();
                    String typeCar = read.readLine();
                    if(typeCar.equals("Gas")) {
                        int maxHorsePower = Integer.parseInt(read.readLine());
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        String engineType = read.readLine();
                        int cap = Integer.parseInt(read.readLine());
                        int eff = Integer.parseInt(read.readLine());
                        carSold = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                    } else if(typeCar.equals("Electric")) {
                        boolean hasAutoPilot = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        double batteryHealthPercentage = Double.parseDouble(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        carSold = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
                    } else if(typeCar.equals("Hybrid")){
                        boolean isRechargeable = read.readLine().equals("Y");
                        boolean hasModes = read.readLine().equals("Y");
                        boolean hasPlugIn = read.readLine().equals("Y");
                        String chargerType = read.readLine();
                        int mileage = Integer.parseInt(read.readLine());
                        int age = Integer.parseInt(read.readLine());
                        int warrantyExpireYear = Integer.parseInt(read.readLine());
                        String lastMaintenance = read.readLine();
                        int baseMaintenanceFee = Integer.parseInt(read.readLine());
                        int powerReturnRate = Integer.parseInt(read.readLine());
                        int chargingTime = Integer.parseInt(read.readLine());
                        int fuelEfficiency = Integer.parseInt(read.readLine());
                        carSold = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                }    
                transactionHistory[startIndex + idx] = new Transaction(thisName, ID, finalPrice, isTradeIn, isSeller, isBuyer, isLease, month, date, year, carObt, carSold);           
            }
            numTransactions = startIndex + fileCount;
            read.close();
        } catch(IOException ex) {
            appendMessage("I/O ERROR while loading transactions: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    public boolean withinDealerRange(Vehicle vehicle, Account acc) {
        if(acc instanceof SellerAccount) {
            return ((SellerAccount)acc).getOfferedPrice() <= vehicle.getBasePrice() + DEAL_RANGE;
        } else {
            return false;
        }
    }

    /**
     * Attempt to accept a deal between the given customer and account using internal validation.
     * If the account validates against the current inventory it will attempt the appropriate
     * buy/sell action and return whether the deal was accepted.
     * @param customer the customer involved in the deal
     * @param acc the account used to perform the deal (SellerAccount or BuyerAccount)
     * @return true if the deal was accepted, false otherwise
     */

    public boolean acceptDeal(Customer customer, Account acc, Vehicle vehicle) {
        if(acc == null) {
            return false;
        } else if(!acc.validate(vehicles)) {
            return false;
        } else {
            return ((TradeInAccount)acc).tradeVehicle(vehicle);
        }
    }

    /**
     * Attempt to accept a trade-in deal for the given customer account against a specific vehicle.
     * This method expects a TradeInAccount and delegates to its trade logic.
     * @param customer the customer performing the trade
     * @param acc the TradeInAccount used for the trade
     * @param vehicle the vehicle to trade for
     * @return true if the trade was accepted, false otherwise
     */

    public Vehicle searchVehicleByVIN(String VIN) {
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i].getVin().equals(VIN)) {
                return vehicles[i];
            }
        }
        return null;
    }
    /**
     * Search the inventory for a vehicle whose spec matches the provided expectation
     * by at least percentMatch.
     * @param expectation the Spec to match
     * @param percentMatch minimum percent match (0-100)
     * @return the first matching Vehicle or null if none found
     */

    public Vehicle[] searchVehicleBySpec(Spec expectation, double percentMatch) {
        Vehicle[] matching = new Vehicle[numVehicleMatch(expectation, percentMatch)];
        int idx = 0;
        for (int i = 0; i < vehicles.length && matching.length != 0; i++) {
            if (vehicles[i].getVehicleSpec().equals(expectation, percentMatch)) {
                matching[idx] = vehicles[i];
                idx++;
            }
        }
        return matching;
    }

    /**
     * Return all vehicles that match the provided Spec by at least percentMatch.
     * @param expectation spec to match
     * @param percentMatch threshold 0-100
     * @return array of matching vehicles (may be empty)
     */
    public int numVehicleMatch(Spec expectation, double percentMatch) {
        int count = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i].getVehicleSpec().equals(expectation, percentMatch)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Find a customer by their ID.
     * @param ID the customer id to search for
     * @return the Customer if found, otherwise null
     */

    public Customer searchCustomerByID(String ID) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].getId().equals(ID)) {
                return customers[i];
            }
        }
        return null;
    }

    /**
     * Return an array of Customers whose last name matches the provided lastName.
     * @param lastName last name to search for
     * @return array of matching Customer objects (possibly empty)
     */

    public Customer[] searchCustomerByLastName(String lastName) {
        int count = searchLNHelper(lastName);
        Customer[] result = new Customer[count];

        if (result.length == 0) return null;
        
        int idx = 0;
        for (int i = 0; i < customers.length; i++) {
            String[] parts = customers[i].getName().split(" ");
            String ln = parts[parts.length - 1];
            if (ln.equalsIgnoreCase(lastName)) {
                result[idx] = customers[i];
                idx++;
            }
        }
        return result;
    }

    /**
     * Helper that counts how many customers have the provided last name.
     * @param lastName the last name to match
     * @return integer count of matching customers
     */

    private int searchLNHelper(String lastName) {
        int counter = 0;
        for (int i = 0; i < customers.length; i++) {
            String[] parts = customers[i].getName().split(" ");
            String ln = parts[parts.length - 1];
            if (ln.equalsIgnoreCase(lastName)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Search transactions by customer ID and return an array of matching Transaction objects.
     * @param ID customer id to filter transactions
     * @return array of transactions for the customer
     */

    public Transaction[] searchTransactionByID(String ID) {
        int count = searchTNID(ID);
        Transaction[] result = new Transaction[count];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getCustomerID().equals(ID)) {
                result[idx] = transactionHistory[i];
                idx++;
            }
        }
        return result;
    }

    /**
     * Counts transactions for a given customer ID.
     * @param ID customer id to count
     * @return number of transactions for the customer
     */

    public int searchTNID(String ID) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getCustomerID().equals(ID)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Return all transactions that reference the given vehicle.
     * @param vehicle vehicle to search transactions for
     * @return array of matching Transaction objects
     */

    public Transaction[] searchTransactionsByVehicleSold(Vehicle vehicle) {
        int count = searchTNVehicleSold(vehicle);
        Transaction[] result = new Transaction[count];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicleSold().equals(vehicle)) {
                result[idx++] = transactionHistory[i];
            }
        }
        return result;
    }

    public Transaction[] searchTransactionsByVehicleObtained(Vehicle vehicle) {
        int count = searchTNVehicleObtained(vehicle);
        Transaction[] result = new Transaction[count];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicleObtained().equals(vehicle)) {
                result[idx++] = transactionHistory[i];
            }
        }
        return result;
    }

    /**
     * Create a suggested deal price for a buyer or seller.
     * If type equals "buyer", uses the provided vehicle's base price.
     * If type equals "seller", uses the seller account's owned vehicle base price.
     * Price is basePrice multiplied by a random factor in [1.0, 1.5].
     * Returns -1 on invalid input.
     * @param type either "buyer" or "seller"
     * @param acc the Account performing the deal (may be SellerAccount/BuyerAccount/TradeInAccount)
     * @param vehicle the vehicle involved (required for buyer, may be null for seller)
     * @return calculated price as double or -1 on error
     */
    public int createDealPrice(String type, boolean isLoyal, Account acc, Vehicle vehicle) {
        String t = type.toLowerCase();
        int base = -1;
        if (t.equals("buyer")) {
            base = vehicle.getBasePrice();
        } else if (t.equals("seller")) {
            base = ((SellerAccount)acc).getOfferedPrice();
        } else {
            return -1;
        }

        if(vehicle == null && base >= ((SellerAccount)acc).getOwnedVehicle().getBasePrice()) {
            return base;
        }
        // multiplier between 1.0 and 1.5
        if(isLoyal) base *= Customer.getLoyalMultiplier();
        double mult = 1.0 + Math.random() * 0.5;
        return (int)(base * mult);
    }

    /**
     * Helper that counts transactions that reference a given vehicle.
     * @param vehicle vehicle to count transactions for
     * @return number of matching transactions
     */

    public int searchTNVehicleSold(Vehicle vehicle) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicleSold().equals(vehicle)) {
                counter++;
            }
        }
        return counter;
    }

    public int searchTNVehicleObtained(Vehicle vehicle) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicleObtained().equals(vehicle)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Sort inventory vehicles by year (newest first) using selection sort.
     */

    public void sortVehicleByYear() {
        for (int i = 0; i < vehicles.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < vehicles.length; j++) {
                if (vehicles[j].getYear() > vehicles[maxIdx].getYear()) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                Vehicle tmp = vehicles[i];
                vehicles[i] = vehicles[maxIdx];
                vehicles[maxIdx] = tmp;
            }
        }
    }

    /**
     * Sort inventory vehicles by manufacturer (alphabetical A–Z) using selection sort.
     */

    public void sortVehicleByManufacturer() {
        for (int i = 0; i < vehicles.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < vehicles.length; j++) {
                String brandJ = vehicles[j].getModelBrand();
                String brandMin = vehicles[minIdx].getModelBrand();
                // null-safe, case-insensitive alphabetical comparison
                if (brandJ == null) continue;
                if (brandMin == null || brandJ.compareToIgnoreCase(brandMin) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Vehicle tmp = vehicles[i];
                vehicles[i] = vehicles[minIdx];
                vehicles[minIdx] = tmp;
            }
        }
    }

    /**
     * Sort customers by last name then numeric part of ID using insertion sort.
     */

    public void sortCustomersByLNID() {
        for (int i = 1; i < customers.length; i++) {
            Customer key = customers[i];
            String[] parts = key.getName().split(" ");
            String keyLast = parts[parts.length - 1];
            String keyId = key.getId();
            int keyNum = 0;
            if (keyId != null && keyId.length() > 2) {
                try {
                    keyNum = Integer.parseInt(keyId.substring(2));
                } catch (NumberFormatException e) {
                    keyNum = 0;
                }
            }
            int j = i - 1;
            while (j >= 0) {
                String[] partsJ = customers[j].getName().split(" ");
                String lastJ = partsJ[partsJ.length - 1];
                boolean shouldShift = false;
                int cmp = lastJ.compareToIgnoreCase(keyLast);
                if (cmp > 0) {
                    shouldShift = true;
                } else if (cmp == 0) {
                    String idJ = customers[j].getId();
                    int numJ = 0;
                    if (idJ != null && idJ.length() > 2) {
                        try {
                            numJ = Integer.parseInt(idJ.substring(2));
                        } catch (NumberFormatException e) {
                            numJ = 0;
                        }
                    }
                    if (numJ > keyNum) {
                        shouldShift = true;
                    }
                }
                if (shouldShift) {
                    customers[j + 1] = customers[j];
                    j--;
                } else {
                    break;
                }
            }
            customers[j + 1] = key;
        }
    }

    /**
     * Sort customers by last name, then loyalty points ascending, then numeric ID part.
     */

    public void sortCustomerLNLPID() {
        for (int i = 1; i < customers.length; i++) {
            Customer key = customers[i];
            String[] parts = key.getName().split(" ");
            String keyLast = parts[parts.length - 1];
            int keyLoyal = key.getLoyaltyPoint();
            String keyId = key.getId();
            int keyNum = 0;
            if (keyId != null && keyId.length() > 2) {
                try {
                    keyNum = Integer.parseInt(keyId.substring(2));
                } catch (NumberFormatException e) {
                    keyNum = 0;
                }
            }
            int j = i - 1;
            while (j >= 0) {
                Customer cur = customers[j];
                String[] partsJ = cur.getName().split(" ");
                String lastJ = partsJ[partsJ.length - 1];
                int loyalJ = cur.getLoyaltyPoint();
                boolean shouldShift = false;
                int cmp = lastJ.compareToIgnoreCase(keyLast);
                if (cmp > 0) {
                    shouldShift = true;
                } else if (cmp == 0) {
                    if (loyalJ > keyLoyal) {
                        shouldShift = true;
                    } else if (loyalJ == keyLoyal) {
                        String idJ = cur.getId();
                        int numJ = 0;
                        if (idJ != null && idJ.length() > 2) {
                            try {
                                numJ = Integer.parseInt(idJ.substring(2));
                            } catch (NumberFormatException e) {
                                numJ = 0;
                            }
                        }
                        if (numJ > keyNum) {
                            shouldShift = true;
                        }
                    }
                }
                if (shouldShift) {
                    customers[j + 1] = customers[j];
                    j--;
                } else {
                    break;
                }
            }
            customers[j + 1] = key;
        }
    }

    /**
     * Sort transaction history by date (newest first) using selection sort.
     */

    public void sortTransactionsByDate() {
        for (int i = 0; i < transactionHistory.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < transactionHistory.length; j++) {
                Transaction a = transactionHistory[j];
                Transaction b = transactionHistory[maxIdx];
                if (a.getYear() > b.getYear() || (a.getYear() == b.getYear() && (a.getMonth() > b.getMonth() || (a.getMonth() == b.getMonth() && a.getDate() > b.getDate())))) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                Transaction tmp = transactionHistory[i];
                transactionHistory[i] = transactionHistory[maxIdx];
                transactionHistory[maxIdx] = tmp;
            }
        }
    }

    /**
     * Return transactions that occurred in the given month and year.
     * @param month month number (1-12)
     * @param year four-digit year
     * @return array of matching Transaction objects
     */

    public Transaction[] searchTransactionsByMonth(int month, int year) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getMonth() == month && transactionHistory[i].getYear() == year) counter++;
        }
        Transaction[] result = new Transaction[counter];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getMonth() == month && transactionHistory[i].getYear() == year) result[idx++] = transactionHistory[i];
        }
        return result;
    }

    /**
     * Return transactions that occurred in the given year.
     * @param year four-digit year
     * @return array of matching Transaction objects
     */

    public Transaction[] searchTransactionsByYear(int year) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getYear() == year) counter++;
        }
        Transaction[] result = new Transaction[counter];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getYear() == year) result[idx++] = transactionHistory[i];
        }
        return result;
    }

    public boolean validateAccount(String type, String id) {
        if(type.equals("Seller")) {
            return searchCustomerByID(id).getSellerAccount().validate(vehicles);
        } else if(type.equals("Buyer")) {
            return searchCustomerByID(id).getBuyerAccount().validate(vehicles);
        } else {
            return searchCustomerByID(id).getTradeInAccount().validate(vehicles);
        }
    }

    /**
     * Return transactions that occurred on the specified date.
     * @param month month number
     * @param date day of month
     * @param year four-digit year
     * @return array of matching Transaction objects
     */

    public Transaction[] searchTransactionsByDate(int month, int date, int year) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getMonth() == month && transactionHistory[i].getDate() == date && transactionHistory[i].getYear() == year) counter++;
        }
        Transaction[] result = new Transaction[counter];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getMonth() == month && transactionHistory[i].getDate() == date && transactionHistory[i].getYear() == year) result[idx++] = transactionHistory[i];
        }
        return result;
    }

    /**
     * Compute total profit (salary) for the given month and year from matching transactions.
     * @param month month number
     * @param year four-digit year
     * @return total profit for that month
     */

    public double getMonthlySalary(int month, int year) {
        Transaction[] trans = searchTransactionsByMonth(month, year);
        double totalProfit = 0.0;
        for (int i = 0; i < trans.length; i++) {
            totalProfit += trans[i].determineProfitMade();
        }
        return totalProfit;
    }

    /**
     * Compute total profit (salary) for the given year from matching transactions.
     * @param year four-digit year
     * @return total profit for that year
     */

    public double getYearlySalary(int year) {
        Transaction[] trans = searchTransactionsByYear(year);
        double totalProfit = 0.0;
        for (int i = 0; i < trans.length; i++) {
            totalProfit += trans[i].determineProfitMade();
        }
        return totalProfit;
    }

    /**
     * Save current inventory to a file using the same format as loadInventory.
     */
    public void saveInventory(String inventoryFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inventoryFileName));
            bw.write(String.valueOf(numCars) + "\n");
            for (int i = 0; i < numCars; i++) {
                Vehicle v = vehicles[i];
                bw.write(v.getModelBrand() + "\n");
                bw.write(v.getModelName() + "\n");
                bw.write(String.valueOf(v.getYear()) + "\n");
                bw.write(String.valueOf(v.getBasePrice()) + "\n");
                bw.write(v.getTrimLevel() + "\n");
                bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                bw.write(v.getTypeVehicle() + "\n");
                bw.write(v.getTypeWheelControl() + "\n");
                bw.write(v.getTransmissionType() + "\n");
                bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                bw.write(String.valueOf(v.getNumSeats()) + "\n");
                bw.write(v.getColor() + "\n");
                bw.write(String.valueOf(v.getTowRating()) + "\n");
                bw.write(v.getMaintenancePeriod() + "\n");
                bw.write(String.valueOf(v.getRange()) + "\n");
                bw.write(v.getVin() + "\n");
                if (v instanceof GasVehicle) {
                    bw.write("Gas" + "\n");
                    GasVehicle gv = (GasVehicle) v;
                    bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                    GasSpec gs = gv.getGasSpec();
                    bw.write(String.valueOf(gs.getMileage()) + "\n");
                    bw.write(String.valueOf(gs.getAge()) + "\n");
                    bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                    bw.write(gs.getLastMaintenance() + "\n");
                    bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                    bw.write(gs.getEngineType() + "\n");
                    bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                    bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                } else if (v instanceof ElectricVehicle) {
                    bw.write("Electric" + "\n");
                    ElectricVehicle ev = (ElectricVehicle) v;
                    bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                    bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                    bw.write(ev.getChargerType() + "\n");
                    ElectricSpec es = ev.getElectricSpec();
                    bw.write(String.valueOf(es.getMileage()) + "\n");
                    bw.write(String.valueOf(es.getAge()) + "\n");
                    bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                    bw.write(es.getLastMaintenance() + "\n");
                    bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                    bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                    bw.write(String.valueOf(es.getChargingTime()) + "\n");
                } else { // Hybrid
                    bw.write("Hybrid" + "\n");
                    HybridVehicle hv = (HybridVehicle) v;
                    bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                    bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                    bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                    bw.write(hv.getChargerType() + "\n");
                    HybridSpec hs = hv.getHybridSpec();
                    bw.write(String.valueOf(hs.getMileage()) + "\n");
                    bw.write(String.valueOf(hs.getAge()) + "\n");
                    bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                    bw.write(hs.getLastMaintenance() + "\n");
                    bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                    bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                    bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                    bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                }
            }
            bw.close();
        } catch (IOException ex) {
            appendMessage("I/O ERROR while saving inventory: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Save transaction history to a file in the same format as loadTransactionHistory.
     */
    public void saveTransactionHistory(String transactionFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFileName));
            bw.write(String.valueOf(numTransactions) + "\n");
            for (int i = 0; i < numTransactions; i++) {
                Transaction t = transactionHistory[i];
                bw.write(t.getCustomerName() + "\n");
                bw.write(t.getCustomerID() + "\n");
                bw.write(String.valueOf(t.getFinalPrice()) + "\n");
                bw.write((t.getIsTradeIn() ? "Y" : "N") + "\n");
                bw.write((t.getIsSold() ? "Y" : "N") + "\n");
                bw.write((t.getIsBought() ? "Y" : "N") + "\n");
                bw.write((t.getIsLease() ? "Y" : "N") + "\n");
                bw.write(String.valueOf(t.getMonth()) + "\n");
                bw.write(String.valueOf(t.getDate()) + "\n");
                bw.write(String.valueOf(t.getYear()) + "\n");
                Vehicle v = t.getVehicleSold();
                if(v != null) {
                    bw.write(v.getModelBrand() + "\n");
                    bw.write(v.getModelName() + "\n");
                    bw.write(String.valueOf(v.getYear()) + "\n");
                    bw.write(String.valueOf(v.getBasePrice()) + "\n");
                    bw.write(v.getTrimLevel() + "\n");
                    bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                    bw.write(v.getTypeVehicle() + "\n");
                    bw.write(v.getTypeWheelControl() + "\n");
                    bw.write(v.getTransmissionType() + "\n");
                    bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                    bw.write(String.valueOf(v.getNumSeats()) + "\n");
                    bw.write(v.getColor() + "\n");
                    bw.write(String.valueOf(v.getTowRating()) + "\n");
                    bw.write(v.getMaintenancePeriod() + "\n");
                    bw.write(String.valueOf(v.getRange()) + "\n");
                    bw.write(v.getVin() + "\n");
                    if (v instanceof GasVehicle) {
                        bw.write("Gas" + "\n");
                        GasVehicle gv = (GasVehicle) v;
                        bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                        GasSpec gs = gv.getGasSpec();
                        bw.write(String.valueOf(gs.getMileage()) + "\n");
                        bw.write(String.valueOf(gs.getAge()) + "\n");
                        bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                        bw.write(gs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                        bw.write(gs.getEngineType() + "\n");
                        bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                        bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                    } else if (v instanceof ElectricVehicle) {
                        bw.write("Electric" + "\n");
                        ElectricVehicle ev = (ElectricVehicle) v;
                        bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                        bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                        bw.write(ev.getChargerType() + "\n");
                        ElectricSpec es = ev.getElectricSpec();
                        bw.write(String.valueOf(es.getMileage()) + "\n");
                        bw.write(String.valueOf(es.getAge()) + "\n");
                        bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                        bw.write(es.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                        bw.write(String.valueOf(es.getChargingTime()) + "\n");
                    } else { // Hybrid
                        bw.write("Hybrid" + "\n");
                        HybridVehicle hv = (HybridVehicle) v;
                        bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                        bw.write(hv.getChargerType() + "\n");
                        HybridSpec hs = hv.getHybridSpec();
                        bw.write(String.valueOf(hs.getMileage()) + "\n");
                        bw.write(String.valueOf(hs.getAge()) + "\n");
                        bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                        bw.write(hs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                        bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                        bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                    }
                } else {
                    bw.write("null\n");
                }
                v = t.getVehicleObtained();
                if(v != null) {
                    bw.write(v.getModelBrand() + "\n");
                    bw.write(v.getModelName() + "\n");
                    bw.write(String.valueOf(v.getYear()) + "\n");
                    bw.write(String.valueOf(v.getBasePrice()) + "\n");
                    bw.write(v.getTrimLevel() + "\n");
                    bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                    bw.write(v.getTypeVehicle() + "\n");
                    bw.write(v.getTypeWheelControl() + "\n");
                    bw.write(v.getTransmissionType() + "\n");
                    bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                    bw.write(String.valueOf(v.getNumSeats()) + "\n");
                    bw.write(v.getColor() + "\n");
                    bw.write(String.valueOf(v.getTowRating()) + "\n");
                    bw.write(v.getMaintenancePeriod() + "\n");
                    bw.write(String.valueOf(v.getRange()) + "\n");
                    bw.write(v.getVin() + "\n");
                    if (v instanceof GasVehicle) {
                        bw.write("Gas" + "\n");
                        GasVehicle gv = (GasVehicle) v;
                        bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                        GasSpec gs = gv.getGasSpec();
                        bw.write(String.valueOf(gs.getMileage()) + "\n");
                        bw.write(String.valueOf(gs.getAge()) + "\n");
                        bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                        bw.write(gs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                        bw.write(gs.getEngineType() + "\n");
                        bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                        bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                    } else if (v instanceof ElectricVehicle) {
                        bw.write("Electric" + "\n");
                        ElectricVehicle ev = (ElectricVehicle) v;
                        bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                        bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                        bw.write(ev.getChargerType() + "\n");
                        ElectricSpec es = ev.getElectricSpec();
                        bw.write(String.valueOf(es.getMileage()) + "\n");
                        bw.write(String.valueOf(es.getAge()) + "\n");
                        bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                        bw.write(es.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                        bw.write(String.valueOf(es.getChargingTime()) + "\n");
                    } else { // Hybrid
                        bw.write("Hybrid" + "\n");
                        HybridVehicle hv = (HybridVehicle) v;
                        bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                        bw.write(hv.getChargerType() + "\n");
                        HybridSpec hs = hv.getHybridSpec();
                        bw.write(String.valueOf(hs.getMileage()) + "\n");
                        bw.write(String.valueOf(hs.getAge()) + "\n");
                        bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                        bw.write(hs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                        bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                        bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                    }
                } else {
                    bw.write("null\n");
                }
            }
            bw.close();
        } catch (IOException ex) {
            appendMessage("I/O ERROR while saving transaction history: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Save customers to a file in the same format expected by loadCustomers.
     */
    public void saveCustomers(String customerFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(customerFileName));
            bw.write(String.valueOf(numCustomer) + "\n");
            for (int i = 0; i < numCustomer; i++) {
                Customer c = customers[i];
                bw.write(c.getName() + "\n");
                bw.write(c.getId() + "\n");
                bw.write(String.valueOf(c.getLoyaltyPoint()) + "\n");
                // Seller marker (legacy) - write a placeholder then write seller block or "null"
                bw.write("seller" + "\n");
                Account sa = c.getSellerAccount();
                if (sa == null) {
                    bw.write("null" + "\n");
                } else {
                    SellerAccount s = (SellerAccount) sa;
                    bw.write((s.isOrganization() ? "Y" : "N") + "\n");
                    bw.write((s.isFamily() ? "Y" : "N") + "\n");
                    bw.write(String.valueOf(s.getOfferedPrice()) + "\n");
                    bw.write(String.valueOf(s.getRating()) + "\n");
                    Vehicle v = s.getOwnedVehicle();
                    bw.write(v.getModelBrand() + "\n");
                    bw.write(v.getModelName() + "\n");
                    bw.write(String.valueOf(v.getYear()) + "\n");
                    bw.write(String.valueOf(v.getBasePrice()) + "\n");
                    bw.write(v.getTrimLevel() + "\n");
                    bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                    bw.write(v.getTypeVehicle() + "\n");
                    bw.write(v.getTypeWheelControl() + "\n");
                    bw.write(v.getTransmissionType() + "\n");
                    bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                    bw.write(String.valueOf(v.getNumSeats()) + "\n");
                    bw.write(v.getColor() + "\n");
                    bw.write(String.valueOf(v.getTowRating()) + "\n");
                    bw.write(v.getMaintenancePeriod() + "\n");
                    bw.write(String.valueOf(v.getRange()) + "\n");
                    bw.write(v.getVin() + "\n");
                    if (v instanceof GasVehicle) {
                        bw.write("Gas" + "\n");
                        GasVehicle gv = (GasVehicle) v;
                        bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                        GasSpec gs = gv.getGasSpec();
                        bw.write(String.valueOf(gs.getMileage()) + "\n");
                        bw.write(String.valueOf(gs.getAge()) + "\n");
                        bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                        bw.write(gs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                        bw.write(gs.getEngineType() + "\n");
                        bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                        bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                    } else if (v instanceof ElectricVehicle) {
                        bw.write("Electric" + "\n");
                        ElectricVehicle ev = (ElectricVehicle) v;
                        bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                        bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                        bw.write(ev.getChargerType() + "\n");
                        ElectricSpec es = ev.getElectricSpec();
                        bw.write(String.valueOf(es.getMileage()) + "\n");
                        bw.write(String.valueOf(es.getAge()) + "\n");
                        bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                        bw.write(es.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                        bw.write(String.valueOf(es.getChargingTime()) + "\n");
                    } else { // Hybrid
                        bw.write("Hybrid" + "\n");
                        HybridVehicle hv = (HybridVehicle) v;
                        bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                        bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                        bw.write(hv.getChargerType() + "\n");
                        HybridSpec hs = hv.getHybridSpec();
                        bw.write(String.valueOf(hs.getMileage()) + "\n");
                        bw.write(String.valueOf(hs.getAge()) + "\n");
                        bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                        bw.write(hs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                        bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                        bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                    }
                    // range of accept for seller
                    bw.write(String.valueOf(s.getRangeOfAccept()) + "\n");
                }

                // buyer marker (legacy)
                bw.write("buyer" + "\n");
                Account ba = c.getBuyerAccount();
                if (ba == null) {
                    bw.write("null" + "\n");
                } else {
                    BuyerAccount b = (BuyerAccount) ba;
                    bw.write((b.isOrganization() ? "Y" : "N") + "\n");
                    bw.write((b.isFamily() ? "Y" : "N") + "\n");
                    bw.write(String.valueOf(b.getBudget()) + "\n");
                    Spec exp = b.getExpectations();
                    if (exp instanceof GasSpec) {
                        bw.write("Gas" + "\n");
                        GasSpec gs = (GasSpec) exp;
                        bw.write(String.valueOf(gs.getMileage()) + "\n");
                        bw.write(String.valueOf(gs.getAge()) + "\n");
                        bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                        bw.write(gs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                        bw.write(gs.getEngineType() + "\n");
                        bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                        bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                    } else if (exp instanceof ElectricSpec) {
                        bw.write("Electric" + "\n");
                        ElectricSpec es = (ElectricSpec) exp;
                        bw.write(String.valueOf(es.getMileage()) + "\n");
                        bw.write(String.valueOf(es.getAge()) + "\n");
                        bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                        bw.write(es.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                        bw.write(String.valueOf(es.getChargingTime()) + "\n");
                    } else if (exp instanceof HybridSpec) {
                        bw.write("Hybrid" + "\n");
                        HybridSpec hs = (HybridSpec) exp;
                        bw.write(String.valueOf(hs.getMileage()) + "\n");
                        bw.write(String.valueOf(hs.getAge()) + "\n");
                        bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                        bw.write(hs.getLastMaintenance() + "\n");
                        bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                        bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                        bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                        bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                    } else {
                        // fallback
                        bw.write("null" + "\n");
                    }
                    bw.write(String.valueOf(b.getPercentMatch()) + "\n");
                    bw.write(String.valueOf(b.getRangeOfAccept()) + "\n");
                }

                // trade-in marker (legacy)
                bw.write("TradeIn" + "\n");
                Account ta = c.getTradeInAccount();
                if (ta == null) {
                    bw.write("null" + "\n");
                } else {
                    TradeInAccount t = (TradeInAccount) ta;
                    bw.write((t.isOrganization() ? "Y" : "N") + "\n");
                    bw.write((t.isFamily() ? "Y" : "N") + "\n");
                    Vehicle tv = t.getVehicleForTrading();
                    if (tv == null) {
                        bw.write("null" + "\n");
                    } else {
                        bw.write(tv.getModelBrand() + "\n");
                        bw.write(tv.getModelName() + "\n");
                        bw.write(String.valueOf(tv.getYear()) + "\n");
                        bw.write(String.valueOf(tv.getBasePrice()) + "\n");
                        bw.write(tv.getTrimLevel() + "\n");
                        bw.write(String.valueOf(tv.getMaxSpeed()) + "\n");
                        bw.write(tv.getTypeVehicle() + "\n");
                        bw.write(tv.getTypeWheelControl() + "\n");
                        bw.write(tv.getTransmissionType() + "\n");
                        bw.write(String.valueOf(tv.getSafetyRating()) + "\n");
                        bw.write(String.valueOf(tv.getNumSeats()) + "\n");
                        bw.write(tv.getColor() + "\n");
                        bw.write(String.valueOf(tv.getTowRating()) + "\n");
                        bw.write(tv.getMaintenancePeriod() + "\n");
                        bw.write(String.valueOf(tv.getRange()) + "\n");
                        bw.write(tv.getVin() + "\n");
                        if (tv instanceof GasVehicle) {
                            bw.write("Gas" + "\n");
                            GasVehicle gv = (GasVehicle) tv;
                            bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                            GasSpec gs = gv.getGasSpec();
                            bw.write(String.valueOf(gs.getMileage()) + "\n");
                            bw.write(String.valueOf(gs.getAge()) + "\n");
                            bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                            bw.write(gs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                            bw.write(gs.getEngineType() + "\n");
                            bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                            bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                        } else if (tv instanceof ElectricVehicle) {
                            bw.write("Electric" + "\n");
                            ElectricVehicle ev = (ElectricVehicle) tv;
                            bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                            bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                            bw.write(ev.getChargerType() + "\n");
                            ElectricSpec es = ev.getElectricSpec();
                            bw.write(String.valueOf(es.getMileage()) + "\n");
                            bw.write(String.valueOf(es.getAge()) + "\n");
                            bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                            bw.write(es.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                            bw.write(String.valueOf(es.getChargingTime()) + "\n");
                        } else { // Hybrid
                            bw.write("Hybrid" + "\n");
                            HybridVehicle hv = (HybridVehicle) tv;
                            bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                            bw.write(hv.getChargerType() + "\n");
                            HybridSpec hs = hv.getHybridSpec();
                            bw.write(String.valueOf(hs.getMileage()) + "\n");
                            bw.write(String.valueOf(hs.getAge()) + "\n");
                            bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                            bw.write(hs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                            bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                            bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                        }
                        bw.write(String.valueOf(t.getRating() + "\n"));
                        Spec expectation = t.getExpectation();
                        if(expectation != null) {
                            if(expectation instanceof GasSpec) {
                                GasSpec gs = (GasSpec)expectation;
                                bw.write(String.valueOf("Gas\n"));
                                bw.write(String.valueOf(gs.getMileage() + "\n"));
                                bw.write(String.valueOf(gs.getAge() + "\n"));
                                bw.write(String.valueOf(gs.getWarrantyExpireYear() + "\n"));
                                bw.write(String.valueOf(gs.getLastMaintenance() + "\n"));
                                bw.write(String.valueOf(gs.getBaseMaintenanceFee() + "\n"));
                                bw.write(String.valueOf(gs.getEngineType() + "\n"));
                                bw.write(String.valueOf(gs.getFuelCapacity() + "\n"));
                                bw.write(String.valueOf(gs.getFuelEfficiency() + "\n"));
                            } else if(expectation instanceof ElectricSpec) {
                                ElectricSpec es = (ElectricSpec)expectation;
                                bw.write(String.valueOf("Electric\n"));
                                bw.write(String.valueOf(es.getMileage() + "\n"));
                                bw.write(String.valueOf(es.getAge() + "\n"));
                                bw.write(String.valueOf(es.getWarrantyExpireYear() + "\n"));
                                bw.write(String.valueOf(es.getLastMaintenance() + "\n"));
                                bw.write(String.valueOf(es.getBaseMaintenanceFee() + "\n"));
                                bw.write(String.valueOf(es.getBatteryHealthPercentage() + "\n"));
                                bw.write(String.valueOf(es.getChargingTime() + "\n"));
                            } else {
                                HybridSpec hs = (HybridSpec)expectation;
                                bw.write(String.valueOf("Hybrid\n"));
                                bw.write(String.valueOf(hs.getMileage() + "\n"));
                                bw.write(String.valueOf(hs.getAge() + "\n"));
                                bw.write(String.valueOf(hs.getWarrantyExpireYear() + "\n"));
                                bw.write(String.valueOf(hs.getLastMaintenance() + "\n"));
                                bw.write(String.valueOf(hs.getBaseMaintenanceFee() + "\n"));
                                bw.write(String.valueOf(hs.getPowerReturnRate() + "\n"));
                                bw.write(String.valueOf(hs.getChargingTime() + "\n"));
                                bw.write(String.valueOf(hs.getFuelEfficiency() + "\n"));                            }
                        } else {
                            bw.write(String.valueOf("null\n"));
                        }
                        bw.write(String.valueOf(t.getPercentMatch() + "\n"));
                        bw.write(String.valueOf(t.getRangeOfAccept() + "\n"));
                        }
                }
                
                // write customer's transaction history
                Transaction[] th = c.getCustomerTransactionHistory();
                bw.write(String.valueOf(th.length) + "\n");
                for (Transaction tr : th) {
                    bw.write(tr.getCustomerName() + "\n");
                    bw.write(tr.getCustomerID() + "\n");
                    bw.write(String.valueOf(tr.getFinalPrice()) + "\n");
                    bw.write((tr.getIsTradeIn() ? "Y" : "N") + "\n");
                    bw.write((tr.getIsSold() ? "Y" : "N") + "\n");
                    bw.write((tr.getIsBought() ? "Y" : "N") + "\n");
                    bw.write((tr.getIsLease() ? "Y" : "N") + "\n");
                    bw.write(String.valueOf(tr.getMonth()) + "\n");
                    bw.write(String.valueOf(tr.getDate()) + "\n");
                    bw.write(String.valueOf(tr.getYear()) + "\n");
                    Vehicle v = tr.getVehicleSold();
                    if(v != null) {
                        bw.write(v.getModelBrand() + "\n");
                        bw.write(v.getModelName() + "\n");
                        bw.write(String.valueOf(v.getYear()) + "\n");
                        bw.write(String.valueOf(v.getBasePrice()) + "\n");
                        bw.write(v.getTrimLevel() + "\n");
                        bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                        bw.write(v.getTypeVehicle() + "\n");
                        bw.write(v.getTypeWheelControl() + "\n");
                        bw.write(v.getTransmissionType() + "\n");
                        bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                        bw.write(String.valueOf(v.getNumSeats()) + "\n");
                        bw.write(v.getColor() + "\n");
                        bw.write(String.valueOf(v.getTowRating()) + "\n");
                        bw.write(v.getMaintenancePeriod() + "\n");
                        bw.write(String.valueOf(v.getRange()) + "\n");
                        bw.write(v.getVin() + "\n");
                        if (v instanceof GasVehicle) {
                            bw.write("Gas" + "\n");
                            GasVehicle gv = (GasVehicle) v;
                            bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                            GasSpec gs = gv.getGasSpec();
                            bw.write(String.valueOf(gs.getMileage()) + "\n");
                            bw.write(String.valueOf(gs.getAge()) + "\n");
                            bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                            bw.write(gs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                            bw.write(gs.getEngineType() + "\n");
                            bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                            bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                        } else if (v instanceof ElectricVehicle) {
                            bw.write("Electric" + "\n");
                            ElectricVehicle ev = (ElectricVehicle) v;
                            bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                            bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                            bw.write(ev.getChargerType() + "\n");
                            ElectricSpec es = ev.getElectricSpec();
                            bw.write(String.valueOf(es.getMileage()) + "\n");
                            bw.write(String.valueOf(es.getAge()) + "\n");
                            bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                            bw.write(es.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                            bw.write(String.valueOf(es.getChargingTime()) + "\n");
                        } else { // Hybrid
                            bw.write("Hybrid" + "\n");
                            HybridVehicle hv = (HybridVehicle) v;
                            bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                            bw.write(hv.getChargerType() + "\n");
                            HybridSpec hs = hv.getHybridSpec();
                            bw.write(String.valueOf(hs.getMileage()) + "\n");
                            bw.write(String.valueOf(hs.getAge()) + "\n");
                            bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                            bw.write(hs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                            bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                            bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                        }
                    } else {
                        bw.write("null\n");
                    }
                    v = tr.getVehicleObtained();
                    if(v != null) {
                        bw.write(v.getModelBrand() + "\n");
                        bw.write(v.getModelName() + "\n");
                        bw.write(String.valueOf(v.getYear()) + "\n");
                        bw.write(String.valueOf(v.getBasePrice()) + "\n");
                        bw.write(v.getTrimLevel() + "\n");
                        bw.write(String.valueOf(v.getMaxSpeed()) + "\n");
                        bw.write(v.getTypeVehicle() + "\n");
                        bw.write(v.getTypeWheelControl() + "\n");
                        bw.write(v.getTransmissionType() + "\n");
                        bw.write(String.valueOf(v.getSafetyRating()) + "\n");
                        bw.write(String.valueOf(v.getNumSeats()) + "\n");
                        bw.write(v.getColor() + "\n");
                        bw.write(String.valueOf(v.getTowRating()) + "\n");
                        bw.write(v.getMaintenancePeriod() + "\n");
                        bw.write(String.valueOf(v.getRange()) + "\n");
                        bw.write(v.getVin() + "\n");
                        if (v instanceof GasVehicle) {
                            bw.write("Gas" + "\n");
                            GasVehicle gv = (GasVehicle) v;
                            bw.write(String.valueOf(gv.getMaxHorsePower()) + "\n");
                            GasSpec gs = gv.getGasSpec();
                            bw.write(String.valueOf(gs.getMileage()) + "\n");
                            bw.write(String.valueOf(gs.getAge()) + "\n");
                            bw.write(String.valueOf(gs.getWarrantyExpireYear()) + "\n");
                            bw.write(gs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + "\n");
                            bw.write(gs.getEngineType() + "\n");
                            bw.write(String.valueOf(gs.getFuelCapacity()) + "\n");
                            bw.write(String.valueOf(gs.getFuelEfficiency()) + "\n");
                        } else if (v instanceof ElectricVehicle) {
                            bw.write("Electric" + "\n");
                            ElectricVehicle ev = (ElectricVehicle) v;
                            bw.write((ev.getHasAutoPilot() ? "Y" : "N") + "\n");
                            bw.write((ev.getHasModes() ? "Y" : "N") + "\n");
                            bw.write(ev.getChargerType() + "\n");
                            ElectricSpec es = ev.getElectricSpec();
                            bw.write(String.valueOf(es.getMileage()) + "\n");
                            bw.write(String.valueOf(es.getAge()) + "\n");
                            bw.write(String.valueOf(es.getWarrantyExpireYear()) + "\n");
                            bw.write(es.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(es.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(es.getBatteryHealthPercentage()) + "\n");
                            bw.write(String.valueOf(es.getChargingTime()) + "\n");
                        } else { // Hybrid
                            bw.write("Hybrid" + "\n");
                            HybridVehicle hv = (HybridVehicle) v;
                            bw.write((hv.getIsRechargeable() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasModes() ? "Y" : "N") + "\n");
                            bw.write((hv.getHasPlugIn() ? "Y" : "N") + "\n");
                            bw.write(hv.getChargerType() + "\n");
                            HybridSpec hs = hv.getHybridSpec();
                            bw.write(String.valueOf(hs.getMileage()) + "\n");
                            bw.write(String.valueOf(hs.getAge()) + "\n");
                            bw.write(String.valueOf(hs.getWarrantyExpireYear()) + "\n");
                            bw.write(hs.getLastMaintenance() + "\n");
                            bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + "\n");
                            bw.write(String.valueOf(hs.getPowerReturnRate()) + "\n");
                            bw.write(String.valueOf(hs.getChargingTime()) + "\n");
                            bw.write(String.valueOf(hs.getFuelEfficiency()) + "\n");
                        }
                    } else {
                        bw.write("null\n");
                    }
                }
            }
            bw.close();
        } catch (IOException ex) {
            appendMessage("I/O ERROR while saving customers: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Adds a new customer object to the customers array, resizing if necessary.
     * @param customer the Customer to add
     * @return true if added, false if parameter was null
     */
    public boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        if (customers == null) {
            customers = new Customer[1];
            customers[0] = customer;
            numCustomer = 1;
            return true;
        }
        if (numCustomer < customers.length) {
            customers[numCustomer] = customer;
            numCustomer++;
            return true;
        } else {
            Customer[] temp = customers;
            customers = new Customer[temp.length + 1];
            for (int i = 0; i < temp.length; i++) customers[i] = temp[i];
            customers[temp.length] = customer;
            numCustomer++;
            return true;
        }
    }

    /**
     * Overloaded addCustomer that creates a simple Customer from a name.
     * ID will be generated as "CU" + (numCustomer+1). Accounts and history are empty.
     */
    public boolean addCustomer(String name) {
        if (name == null || name.isEmpty()) return false;
        // Customer now generates its own id when constructed with name
        Customer c = new Customer(name);
        return addCustomer(c);
    }

    /**
     * Remove a vehicle from inventory by VIN. Shifts remaining vehicles down and decrements numCars.
     * @param VIN vehicle VIN to remove
     * @return true if removed, false if not found
     */
    public boolean removeVehicle(String VIN) {
        if (vehicles == null || VIN == null) return false;
        int idx = -1;
        for (int i = 0; i < numCars; i++) {
            if (vehicles[i] != null && VIN.equals(vehicles[i].getVin())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return false;
        for (int i = idx; i < numCars - 1; i++) vehicles[i] = vehicles[i + 1];
        vehicles[numCars - 1] = null;
        numCars--;
        return true;
    }

    /**
     * Add a new transaction to the transactionHistory array, resizing if needed.
     * @param t the Transaction to add
     * @return true if added, false if parameter was null
     */
    public boolean updateTransactionHistory(Transaction t) {
        if (t == null) return false;
        if (transactionHistory == null) {
            transactionHistory = new Transaction[1];
            transactionHistory[0] = t;
            numTransactions = 1;
            return true;
        }
        if (numTransactions < transactionHistory.length) {
            transactionHistory[numTransactions] = t;
            numTransactions++;
            return true;
        } else {
            Transaction[] temp = transactionHistory;
            transactionHistory = new Transaction[temp.length + 1];
            for (int i = 0; i < temp.length; i++) transactionHistory[i] = temp[i];
            transactionHistory[temp.length] = t;
            numTransactions++;
            return true;
        }
    }

    /**
     * Update a customer's loyalty points by name and id. If multiple match, updates first.
     */
    public void updateCustomerLoyalty(String name, String id, int newLoyalty) {
        if (customers == null) return;
        for (int i = 0; i < numCustomer; i++) {
            Customer c = customers[i];
            if (c != null && c.getName().equals(name) && c.getId().equals(id)) {
                c.setLoyaltyPoint(newLoyalty);
                return;
            }
        }
    }

    /**
     * Display all customers' toString() to stdout.
     */
    public String displayAllCustomerInfo() {
        String string = "";
        for (int i = 0; i < numCustomer; i++) {
            if (customers[i] != null) string += customers[i].toString() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        }
        return string;
    }

    /**
     * Display all loyal customers' info.
     */
    public String displayAllLoyalCustomerInfo() {
        String string = "";
        for (int i = 0; i < numCustomer; i++) {
            Customer c = customers[i];
            if (c != null && c.isLoyal()) string += c.toString() + "\n";
        }
        return string;
    }

    /**
     * Display all vehicles currently in inventory.
     */
    public String displayInventory() {
        String string = "";
        for (int i = 0; i < numCars; i++) {
            if (vehicles[i] != null) string += vehicles[i].toString() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        }
        return string;
    }

    /**
     * Display full transaction history.
     */
    public String displayTransactionHistory() {
        String string = "";
        for (int i = 0; i < numTransactions; i++) {
            if (transactionHistory[i] != null) string += transactionHistory[i].toString() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        }
        return string;
    }

    /**
     * Create an account for a customer identified by name and id. Prompts the user on the console
     * for account-specific details then attaches the new account to the customer.
     * accountType should be one of: "Seller", "Buyer", "TradeIn" (case-insensitive).
     * @return true if account created and attached, false if customer not found or input error
     */
    public boolean createAccountForCustomer(String name, String id, String accountType, Account account) {
        if (name == null || id == null || accountType == null || account == null) return false;
        Customer target = null;
        for (int i = 0; i < numCustomer; i++) {
            Customer c = customers[i];
            if (c != null && c.getName().equals(name) && c.getId().equals(id)) {
                target = c;
                break;
            }
        }
        if (target == null) return false;

        // Attach the provided account (no prompting here; GUI handles input)
        target.createAccount(accountType, account);
        appendMessage("Created account of type " + accountType + " for " + name);
        return true;
    }

    public Vehicle[] showAllApplicableForCustomer(Account acc) {
        if(acc instanceof BuyerAccount) {
            return ((BuyerAccount)acc).showAllApplicableVehicles(vehicles);
        } else {
            return ((TradeInAccount)acc).showAllApplicableVehicles(vehicles);
        }
        
    }
}
