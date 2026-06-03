public class ElectricSpec extends Spec {

    public static int BIG_FUELCAP = 50; // can change
    public static int BIG_FUELCAP_BY_PRICE = 20; //
    public static int BIG_FUELCAP_BY_RATE = 10; // can change
    public static int DEPRECIATE_CHARGING_TIME = 10; // can change
    public static int DEPRECIATE_CHARGING_TIME_BY_VALUE = 10; // can change
    public static int DEPRECIATE_CHARGING_TIME_BY_RATE = 10; // can change

    private double batteryHealthPercentage;
    private int chargingTime;

    public double getBatteryHealthPercentage() {
        return batteryHealthPercentage;
    }

    public void setBatteryHealthPercentage(double batteryHealthPercentage) {
        this.batteryHealthPercentage = batteryHealthPercentage;
    }

    public int getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    /**
     * Constructor for ElectricSpec.
     * Initializes an ElectricSpec object with vehicle specifications and electric vehicle properties.
     * 
     * @param mileage the total mileage of the vehicle
     * @param age the age of the vehicle in years
     * @param warrantyExpireYear the year the warranty expires
     * @param lastMaintenance the date of last maintenance
     * @param baseMaintenanceFee the base maintenance cost
     * @param engineType the type of engine
     * @param fuelCapacity the capacity of the fuel tank
     * @param fuelEfficiency the fuel efficiency rating
     */
    public ElectricSpec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee, 
        double batteryHealthPercentage, int chargingTime) {
        super(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee);
        this.batteryHealthPercentage = batteryHealthPercentage;
        this.chargingTime = chargingTime;
    }

    /**
     * Calculates the yearly depreciation rate based on fuel efficiency, age, and mileage.
     * Returns a cumulative depreciation percentage based on multiple factors.
     * @return the calculated yearly depreciation rate
     */
    @Override
    public int calculateYearlyDepreciationRate() {
        int temp = 0;
        
        // if (getFuelEfficiency() < DEPRECIATE_FUELEFF) {
        //     temp += DEPRECIATE_FUELEFF_BY_RATE;
        // }

        if (getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_RATE;
        }

        if (getMileage() >= DEPRECIATE_MILEAGE) {
            temp += DEPRECIATE_MILEAGE_BY_RATE;
        }

        return temp;
    }

    /**
     * Calculates the expected selling price of the vehicle based on depreciation factors.
     * Deducts depreciation amounts from the base price based on age, mileage, and fuel efficiency.
     * @param basePrice the original base price of the vehicle
     * @return the calculated expected price after depreciation
     */
    @Override
    public int calculateExpectedPrice(int basePrice) {
        int temp = 0;
                
        // if (getFuelEfficiency() < DEPRECIATE_FUELEFF) {
        //     temp += DEPRECIATE_FUELEFF_BY_PRICE;
        // }

        if (getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_PRICE;
        }
        if (getMileage() >= DEPRECIATE_MILEAGE) {
            temp += DEPRECIATE_MILEAGE_BY_PRICE;
        }

        return basePrice - temp;    
    }

    /**
     * Compares this ElectricSpec with another Spec object to determine matching compatibility.
     * Checks matching criteria including engine type, fuel capacity, efficiency, age, and mileage.
     * @param spec the Spec object to compare with
     * @param percentMatch the required percentage match threshold (0-100)
     * @return true if the match percentage meets or exceeds the threshold; false otherwise
     */
    @Override
    public boolean equals(Object spec, double percentMatch) {
        if (spec == null || !(spec instanceof GasSpec)) {
            System.out.println("Not a GasSpec");
            return false;
        } else if (spec == this) {
            return true;
        } else {
            GasSpec gasSpec = (GasSpec) spec;
            int matchCount = 0;
            int totalCount = 0;

            // if (this.engineType.equals(gasSpec.engineType)) {
            //     matchCount++;
            // }
            // totalCount++;

            // if (this.getFuelCapacity() >= gasSpec.getFuelCapacity()) {
            //     matchCount++;
            // }
            // totalCount++;

            // if (this.getFuelEfficiency() >= gasSpec.getFuelEfficiency()) {
            //     matchCount++;
            // }
            // totalCount++;

            if (this.getAge() <= gasSpec.getAge()) {
                matchCount++;
            }
            totalCount++;

            if (this.getMileage() <= gasSpec.getMileage()) {
                matchCount++;
            }
            totalCount++;

            double matchPercentage = (double) matchCount / totalCount * 100;
            boolean result = matchPercentage >= percentMatch;
            if (!result) {
                System.out.println("Not matching enough. Match percentage: " + matchPercentage);
            } 
            return result;
        }
    }

    /**
     * Returns a string representation of the ElectricSpec object.
     * Includes engine type, fuel capacity, fuel efficiency, and inherited spec properties.
     * @return a formatted string containing all relevant spec details
     */
    public String toString() {
        String temp = "";
        temp += "Electric Spec:\n";
        // temp += "Engine Type: " + engineType + "\n";
        // temp += "Fuel Capacity: " + fuelCapacity + "\n";
        // temp += "Fuel Efficiency: " + fuelEfficiency + "\n";
        return super.toString() + temp;
    }
}