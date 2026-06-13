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

    /**
     * Returns the battery health as a fraction (0.0–1.0).
     * @return battery health percentage
     */
    public double getBatteryHealthPercentage() {
        return batteryHealthPercentage;
    }

    /**
     * Sets the battery health percentage.
     * @param batteryHealthPercentage battery health as a fraction (0.0–1.0)
     */
    public void setBatteryHealthPercentage(double batteryHealthPercentage) {
        this.batteryHealthPercentage = batteryHealthPercentage;
    }

    /**
     * Returns the charging time used in depreciation and matching.
     * @return charging time
     */
    public int getChargingTime() {
        return chargingTime;
    }

    /**
     * Sets the charging time.
     * @param chargingTime charging time value
     */
    public void setChargingTime(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    /**
     * Constructs an ElectricSpec with base spec fields and electric-specific properties.
     *
     * @param mileage total vehicle mileage
     * @param age vehicle age in years
     * @param warrantyExpireYear year the warranty expires
     * @param lastMaintenance date or description of last maintenance
     * @param baseMaintenanceFee base maintenance cost
     * @param batteryHealthPercentage battery health as a fraction (0.0–1.0)
     * @param chargingTime charging time value used in depreciation and matching
     */
    public ElectricSpec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee, 
        double batteryHealthPercentage, int chargingTime) {
        super(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee);
        this.batteryHealthPercentage = batteryHealthPercentage;
        this.chargingTime = chargingTime;
    }

    /**
     * Calculates the cumulative yearly depreciation rate for an electric vehicle.
     * Adds rates when charging time, age, or mileage exceed their depreciation thresholds.
     * @return total yearly depreciation rate percentage
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
     * Calculates expected selling price by subtracting fixed depreciation amounts
     * from the base price when charging time, age, or mileage exceed thresholds.
     * @param basePrice the original base price of the vehicle
     * @return expected price after depreciation deductions
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
     * Determines whether this electric spec matches another at or above the
     * given percent threshold. Compares age (this &lt;= other), mileage
     * (this &lt;= other), and charging time (this &lt;= other).
     *
     * @param spec the spec to compare (must be an {@link ElectricSpec})
     * @param percentMatch required match percentage (0–100); 0 always matches
     * @return true if the match percentage meets or exceeds the threshold
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
     * Returns a string with base spec fields plus battery health percentage
     * and charging time.
     * @return formatted electric specification details
     */
    public String toString() {
        String temp = "";
        temp += "\nElectric Spec:\n";
        temp += "Battery Health Percentage: " + batteryHealthPercentage + "\n";
        temp += "Charging Time: " + chargingTime + "\n";
        return super.toString() + temp;
    }
}