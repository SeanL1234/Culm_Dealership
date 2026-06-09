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
        messages += msg + System.lineSeparator();
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

    public DealershipSystem(String customerFileName, String inventoryFileName, String transactionFileName) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(customerFileName));
            numCustomer = Integer.parseInt(read.readLine());
            customers = new Customer[numCustomer];
            for(int i = 0; i < numCustomer; i++) {
                String name = read.readLine();
                String id = read.readLine();
                int loyal = Integer.parseInt(read.readLine());
                String seller = read.readLine();
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
                String buyer = read.readLine();
                stat = read.readLine();
                BuyerAccount buyerAcc = null;
                if(!stat.equals("null")) {
                    boolean isOrganization = stat.equals("Y");;
                    boolean isFamily = read.readLine().equals("Y");
                    int budget = Integer.parseInt(read.readLine());
                    String typeCar = read.readLine();
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
                    buyerAcc = new BuyerAccount(isOrganization, isFamily, budget, typeCar, expectation, percentMatch, rangeOfAccept);
                }
                String trade = read.readLine();
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
                        car = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
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
                        car = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
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
                        car = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                    }
                    transactions[x] = new Transaction(thisName, ID, finalPrice, isTradeIn, isSeller, isBuyer, isLease, month, date, year, car);
                }
                
                customers[i] = new Customer(loyal, name, id, new Account[] {sellerAcc, buyerAcc, tradeAcc}, transactions);
            }
        
            read.close();
            read = new BufferedReader(new FileReader(inventoryFileName));
            numCars = Integer.parseInt(read.readLine());
            vehicles = new Vehicle[numCars];
            for(int i = 0; i < numCars; i++) {
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
                vehicles[i] = car;
            }
        
            read.close();
            read = new BufferedReader(new FileReader(transactionFileName));
            numTransactions = Integer.parseInt(read.readLine());
            transactionHistory = new Transaction[numTransactions];
            for(int i = 0; i < numTransactions; i++) {
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
                    car = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
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
                    car = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
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
                    car = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                }
                transactionHistory[i] = new Transaction(thisName, ID, finalPrice, isTradeIn, isSeller, isBuyer, isLease, month, date, year, car);
            }
        
            read.close();
        } catch(IOException ex) {
            appendMessage("I/O ERROR while loading files: " + ex.getMessage());
            // store stack trace into messages for GUI debugging
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

    // public Transaction acceptDeal(Customer customer, Account acc) {
    //     if(acc == null) {
    //         return null;
    //     } else if(!acc.validate(vehicles)) {
    //         return null;
    //     } else {
    //         int suggestedPrice = customer.getRandomizedDealPrice();
    //         if(acc instanceof SellerAccount) {
    //             if(((SellerAccount)acc).sellVehicle(suggestedPrice)) {
    //                 return new Transaction(customer.getName(), customer.getId(), suggestedPrice, false, false, true, false, 0, 0, 0, ((SellerAccount)acc).getOwnedVehicle());
    //             }
    //         } else if(acc instanceof BuyerAccount) {
    //             if(((BuyerAccount)acc).buyVehicle(suggestedPrice)) {
    //                 return new Transaction(customer.getName(), customer.getId(), suggestedPrice, false, true, false, false, 0, 0, 0, ((BuyerAccount)acc).());
    //             }
    //         }
    //         return null;
    //     }
    // }

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

    // /**
    //  * Search the inventory for a vehicle whose spec exactly equals the provided expectation
    //  * (100% match).
    //  * @param expectation the Spec to search for
    //  * @return the first matching Vehicle or null if none found
    //  */

    // public Vehicle searchVehicleBySpec(Spec expectation) {
    //     for (int i = 0; i < vehicles.length; i++) {
    //         if (vehicles[i].getVehicleSpec().equals(expectation, 100.0)) {
    //             return vehicles[i];
    //         }
    //     }
    //     return null;
    // }

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
        int idx = 0;
        for (int i = 0; i < customers.length; i++) {
            String[] parts = customers[i].getName().split(" ");
            String ln = parts[parts.length - 1];
            if (ln.equals(lastName)) {
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

    public int searchLNHelper(String lastName) {
        int counter = 0;
        for (int i = 0; i < customers.length; i++) {
            String[] parts = customers[i].getName().split(" ");
            String ln = parts[parts.length - 1];
            if (ln.equals(lastName)) {
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
     * Helper that counts transactions for a given customer ID.
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

    public Transaction[] searchTransactionsByVehicle(Vehicle vehicle) {
        int count = searchTNVehicle(vehicle);
        Transaction[] result = new Transaction[count];
        int idx = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicle().equals(vehicle)) {
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
    public int createDealPrice(String type, Account acc, Vehicle vehicle) {
        String t = type.toLowerCase();
        int base = -1;
        if (t.equals("buyer")) {
            base = vehicle.getBasePrice();
        } else if (t.equals("seller")) {
            base = ((SellerAccount)acc).getOfferedPrice();
        } else {
            return -1;
        }

        // multiplier between 1.0 and 1.5
        double mult = 1.0 + Math.random() * 0.5;
        return (int)(base * mult);
    }

    /**
     * Helper that counts transactions that reference a given vehicle.
     * @param vehicle vehicle to count transactions for
     * @return number of matching transactions
     */

    public int searchTNVehicle(Vehicle vehicle) {
        int counter = 0;
        for (int i = 0; i < transactionHistory.length; i++) {
            if (transactionHistory[i].getVehicle().equals(vehicle)) {
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
     * Load inventory from a file and overwrite current vehicles array.
     * File format mirrors the format used in the constructor: first line is numCars,
     * then for each car the fields in a fixed order including a type marker (Gas/Electric/Hybrid)
     * followed by type-specific fields.
     */
    public void loadInventory(String inventoryFileName) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(inventoryFileName));
            numCars = Integer.parseInt(read.readLine());
            vehicles = new Vehicle[numCars];
            for (int i = 0; i < numCars; i++) {
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
                if (typeCar.equals("Gas")) {
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
                } else if (typeCar.equals("Electric")) {
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
                vehicles[i] = car;
            }
            read.close();
        } catch (IOException ex) {
            appendMessage("I/O ERROR while loading inventory: " + ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            appendMessage(sw.toString());
        }
    }

    /**
     * Save current inventory to a file using the same format as loadInventory.
     */
    public void saveInventory(String inventoryFileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inventoryFileName));
            bw.write(String.valueOf(numCars) + System.lineSeparator());
            for (int i = 0; i < numCars; i++) {
                Vehicle v = vehicles[i];
                bw.write(v.getModelBrand() + System.lineSeparator());
                bw.write(v.getModelName() + System.lineSeparator());
                bw.write(String.valueOf(v.getYear()) + System.lineSeparator());
                bw.write(String.valueOf(v.getBasePrice()) + System.lineSeparator());
                bw.write(v.getTrimLevel() + System.lineSeparator());
                bw.write(String.valueOf(v.getMaxSpeed()) + System.lineSeparator());
                bw.write(v.getTypeVehicle() + System.lineSeparator());
                bw.write(v.getTypeWheelControl() + System.lineSeparator());
                bw.write(v.getTransmissionType() + System.lineSeparator());
                bw.write(String.valueOf(v.getSafetyRating()) + System.lineSeparator());
                bw.write(String.valueOf(v.getNumSeats()) + System.lineSeparator());
                bw.write(v.getColor() + System.lineSeparator());
                bw.write(String.valueOf(v.getTowRating()) + System.lineSeparator());
                bw.write(v.getMaintenancePeriod() + System.lineSeparator());
                bw.write(String.valueOf(v.getRange()) + System.lineSeparator());
                bw.write(v.getVin() + System.lineSeparator());
                if (v instanceof GasVehicle) {
                    bw.write("Gas" + System.lineSeparator());
                    GasVehicle gv = (GasVehicle) v;
                    bw.write(String.valueOf(gv.getMaxHorsePower()) + System.lineSeparator());
                    GasSpec gs = gv.getGasSpec();
                    bw.write(String.valueOf(gs.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(gs.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(gs.getEngineType() + System.lineSeparator());
                    bw.write(String.valueOf(gs.getFuelCapacity()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getFuelEfficiency()) + System.lineSeparator());
                } else if (v instanceof ElectricVehicle) {
                    bw.write("Electric" + System.lineSeparator());
                    ElectricVehicle ev = (ElectricVehicle) v;
                    bw.write((ev.getHasAutoPilot() ? "Y" : "N") + System.lineSeparator());
                    bw.write((ev.getHasModes() ? "Y" : "N") + System.lineSeparator());
                    bw.write(ev.getChargerType() + System.lineSeparator());
                    ElectricSpec es = ev.getElectricSpec();
                    bw.write(String.valueOf(es.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(es.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(es.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getBatteryHealthPercentage()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getChargingTime()) + System.lineSeparator());
                } else { // Hybrid
                    bw.write("Hybrid" + System.lineSeparator());
                    HybridVehicle hv = (HybridVehicle) v;
                    bw.write((hv.getIsRechargeable() ? "Y" : "N") + System.lineSeparator());
                    bw.write((hv.getHasModes() ? "Y" : "N") + System.lineSeparator());
                    bw.write((hv.getHasPlugIn() ? "Y" : "N") + System.lineSeparator());
                    bw.write(hv.getChargerType() + System.lineSeparator());
                    HybridSpec hs = hv.getHybridSpec();
                    bw.write(String.valueOf(hs.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(hs.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getPowerReturnRate()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getChargingTime()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getFuelEfficiency()) + System.lineSeparator());
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
     * Load transaction history from file and overwrite current transactionHistory.
     * File format mirrors the constructor's transaction section.
     */
    public void loadTransactionHistory(String transactionFileName) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(transactionFileName));
            numTransactions = Integer.parseInt(read.readLine());
            transactionHistory = new Transaction[numTransactions];
            for (int i = 0; i < numTransactions; i++) {
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
                Vehicle car = null;
                if (typeCar.equals("Gas")) {
                    int maxHorsePower = Integer.parseInt(read.readLine());
                    int mileage = Integer.parseInt(read.readLine());
                    int age = Integer.parseInt(read.readLine());
                    int warrantyExpireYear = Integer.parseInt(read.readLine());
                    String lastMaintenance = read.readLine();
                    int baseMaintenanceFee = Integer.parseInt(read.readLine());
                    String engineType = read.readLine();
                    int cap = Integer.parseInt(read.readLine());
                    int eff = Integer.parseInt(read.readLine());
                    car = new GasVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, maxHorsePower, new GasSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, engineType, cap, eff));
                } else if (typeCar.equals("Electric")) {
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
                    car = new ElectricVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new ElectricSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, batteryHealthPercentage, chargingTime), hasAutoPilot, hasModes, chargerType);
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
                    car = new HybridVehicle(modelName, brandName, type, carYear, basePrice, safe, VIN, tow, wheel, trans, trim, maxSpeed, seats, color, maintenance, range, new HybridSpec(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee, powerReturnRate, chargingTime, fuelEfficiency), isRechargeable, hasModes, hasPlugIn, chargerType);
                }
                transactionHistory[i] = new Transaction(thisName, ID, finalPrice, isTradeIn, isSeller, isBuyer, isLease, month, date, year, car);
            }
            read.close();
        } catch (IOException ex) {
            appendMessage("I/O ERROR while loading transaction history: " + ex.getMessage());
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
            bw.write(String.valueOf(numTransactions) + System.lineSeparator());
            for (int i = 0; i < numTransactions; i++) {
                Transaction t = transactionHistory[i];
                bw.write(t.getCustomerName() + System.lineSeparator());
                bw.write(t.getCustomerID() + System.lineSeparator());
                bw.write(String.valueOf(t.getFinalPrice()) + System.lineSeparator());
                bw.write((t.getIsTradeIn() ? "Y" : "N") + System.lineSeparator());
                bw.write((t.getIsSold() ? "Y" : "N") + System.lineSeparator());
                bw.write((t.getIsBought() ? "Y" : "N") + System.lineSeparator());
                bw.write((t.getIsLease() ? "Y" : "N") + System.lineSeparator());
                bw.write(String.valueOf(t.getMonth()) + System.lineSeparator());
                bw.write(String.valueOf(t.getDate()) + System.lineSeparator());
                bw.write(String.valueOf(t.getYear()) + System.lineSeparator());
                Vehicle v = t.getVehicle();
                bw.write(v.getModelBrand() + System.lineSeparator());
                bw.write(v.getModelName() + System.lineSeparator());
                bw.write(String.valueOf(v.getYear()) + System.lineSeparator());
                bw.write(String.valueOf(v.getBasePrice()) + System.lineSeparator());
                bw.write(v.getTrimLevel() + System.lineSeparator());
                bw.write(String.valueOf(v.getMaxSpeed()) + System.lineSeparator());
                bw.write(v.getTypeVehicle() + System.lineSeparator());
                bw.write(v.getTypeWheelControl() + System.lineSeparator());
                bw.write(v.getTransmissionType() + System.lineSeparator());
                bw.write(String.valueOf(v.getSafetyRating()) + System.lineSeparator());
                bw.write(String.valueOf(v.getNumSeats()) + System.lineSeparator());
                bw.write(v.getColor() + System.lineSeparator());
                bw.write(String.valueOf(v.getTowRating()) + System.lineSeparator());
                bw.write(v.getMaintenancePeriod() + System.lineSeparator());
                bw.write(String.valueOf(v.getRange()) + System.lineSeparator());
                bw.write(v.getVin() + System.lineSeparator());
                if (v instanceof GasVehicle) {
                    bw.write("Gas" + System.lineSeparator());
                    GasVehicle gv = (GasVehicle) v;
                    bw.write(String.valueOf(gv.getMaxHorsePower()) + System.lineSeparator());
                    GasSpec gs = gv.getGasSpec();
                    bw.write(String.valueOf(gs.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(gs.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(gs.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(gs.getEngineType() + System.lineSeparator());
                    bw.write(String.valueOf(gs.getFuelCapacity()) + System.lineSeparator());
                    bw.write(String.valueOf(gs.getFuelEfficiency()) + System.lineSeparator());
                } else if (v instanceof ElectricVehicle) {
                    bw.write("Electric" + System.lineSeparator());
                    ElectricVehicle ev = (ElectricVehicle) v;
                    bw.write((ev.getHasAutoPilot() ? "Y" : "N") + System.lineSeparator());
                    bw.write((ev.getHasModes() ? "Y" : "N") + System.lineSeparator());
                    bw.write(ev.getChargerType() + System.lineSeparator());
                    ElectricSpec es = ev.getElectricSpec();
                    bw.write(String.valueOf(es.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(es.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(es.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getBatteryHealthPercentage()) + System.lineSeparator());
                    bw.write(String.valueOf(es.getChargingTime()) + System.lineSeparator());
                } else { // Hybrid
                    bw.write("Hybrid" + System.lineSeparator());
                    HybridVehicle hv = (HybridVehicle) v;
                    bw.write((hv.getIsRechargeable() ? "Y" : "N") + System.lineSeparator());
                    bw.write((hv.getHasModes() ? "Y" : "N") + System.lineSeparator());
                    bw.write((hv.getHasPlugIn() ? "Y" : "N") + System.lineSeparator());
                    bw.write(hv.getChargerType() + System.lineSeparator());
                    HybridSpec hs = hv.getHybridSpec();
                    bw.write(String.valueOf(hs.getMileage()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getAge()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getWarrantyExpireYear()) + System.lineSeparator());
                    bw.write(hs.getLastMaintenance() + System.lineSeparator());
                    bw.write(String.valueOf(hs.getBaseMaintenanceFee()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getPowerReturnRate()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getChargingTime()) + System.lineSeparator());
                    bw.write(String.valueOf(hs.getFuelEfficiency()) + System.lineSeparator());
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
            if (customers[i] != null) string += customers[i].toString() + "\n";
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
     * Display all vehicles from a given manufacturer.
     */
    public String displayAllManufacturer(String manufacturer) {
        String string = "";
        for (int i = 0; i < numCars; i++) {
            Vehicle v = vehicles[i];
            if (v != null && manufacturer.equals(v.getModelBrand())) string += v.toString() + "\n";
        }
        return string;
    }

    /**
     * Display full transaction history.
     */
    public String displayTransactionHistory() {
        String string = "";
        for (int i = 0; i < numTransactions; i++) {
            if (transactionHistory[i] != null) string += transactionHistory[i].toString() + "\n";
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

    // public String showAllApplicableForBuyer() {

    // }
}
