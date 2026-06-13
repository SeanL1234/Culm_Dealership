/**
 * ElectricSpec.java
 * Author: Fabian Hui
 * Teacher: Ms. Lam
 * Date: June 3, 2026
 * Description: The class representing the specifications of an electric vehicle, 
 * extending the base Spec class.
 */

public class ElectricSpec extends Spec {

    // Constants for depreciation factors specific to electric vehicles

    // Constant indicating after what charging time percentage does the depreciation start
    public static int DEPRECIATE_CHARGING_TIME = 80; 

    // Constant indicating how much to depreciate based on charging time by price
    public static int DEPRECIATE_CHARGING_TIME_BY_VALUE = 10; 

    // Constant indicating the rate of depreciate further based on charging time
    public static int DEPRECIATE_CHARGING_TIME_BY_RATE = 10; 

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
        
        if (chargingTime >= DEPRECIATE_CHARGING_TIME) {
            temp += DEPRECIATE_CHARGING_TIME_BY_RATE;
        }

        if (super.getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_RATE;
        }

        if (super.getMileage() >= DEPRECIATE_MILEAGE) {
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
                
        if (chargingTime >= DEPRECIATE_CHARGING_TIME) {
            temp += DEPRECIATE_CHARGING_TIME_BY_VALUE;
        }

        if (super.getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_PRICE;
        }
        if (super.getMileage() >= DEPRECIATE_MILEAGE) {
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
        if(percentMatch == 0) {
            return true;
        } else if (spec == null || !(spec instanceof ElectricSpec)) {
            return false;
        } else if (spec == this) {
            return true;
        } else {
            ElectricSpec electricSpec = (ElectricSpec) spec;
            int matchCount = 0;
            int totalCount = 0;

            if (super.getAge() <= electricSpec.getAge()) {
                matchCount++;
            }
            totalCount++;

            if (super.getMileage() <= electricSpec.getMileage()) {
                matchCount++;
            }

            if (this.chargingTime <= electricSpec.getChargingTime()) {
                matchCount++;
            }
            totalCount++;

            double matchPercentage = (double) matchCount / totalCount * 100;
            return matchPercentage >= percentMatch;
        }
    }

    /**
     * Returns a string representation of the ElectricSpec object.
     * Includes engine type, fuel capacity, fuel efficiency, and inherited spec properties.
     * @return a formatted string containing all relevant spec details
     */
    public String toString() {
        String temp = "";
        temp += "\nElectric Spec:\n";
        temp += "Battery Health Percentage: " + batteryHealthPercentage + "\n";
        temp += "Charging Time: " + chargingTime + "\n";
        return super.toString() + temp;
    }
}