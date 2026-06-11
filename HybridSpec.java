/**
 * HybridSpec.java
 * Author: Fabian Hui
 * Teacher: Ms. Lam
 * Date: June 3, 2026
 * 
 * Description: The class representing the specifications of a hybrid vehicle, 
 * extending the base Spec class.
 */

public class HybridSpec extends Spec {

    // constants for depreciation factors specific to hybrid vehicles

    // Constant indicating after what fuel effeiciency percentage does the depreciation start
    public static int DEPRECIATE_FUELEFF = 10; 

    // Constant indicating how much to depreciate based on fuel efficiency by price
    public static int DEPRECIATE_FUELEFF_BY_PRICE = 10; 

    // Constant indicating the rate of depreciate further based on fuel efficiency
    public static int DEPRECIATE_FUELEFF_BY_RATE = 10; 

    // Constant indicating after what charging time does the depreciation start
    public static int DEPRECIATE_CHARGING_TIME = 10; 

    // Constant indicating how much to depreciate based on charging time by price
    public static int DEPRECIATE_CHARGING_TIME_BY_PRICE = 10; 

    // Constant indicating the rate of depreciate further based on fuel efficiency
    public static int DEPRECIATE_CHARGING_TIME_BY_RATE = 10; 

    private int powerReturnRate;
    private int chargingTime;
    private int fuelEfficiency;

    /**
     * Constructs a HybridSpec object with hybrid vehicle specifications.
     * @param mileage the current mileage of the vehicle
     * @param age the age of the vehicle in years
     * @param warrantyExpireYear the year the warranty expires
     * @param lastMaintenance the date of last maintenance
     * @param baseMaintenanceFee the base maintenance fee
     * @param powerReturnRate the power return rate of the hybrid system
     * @param chargingTime the charging time in minutes
     * @param fuelEfficiency the fuel efficiency in mpg
     */
    public HybridSpec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee, int powerReturnRate, int chargingTime, int fuelEfficiency) {
        super(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee);
        this.powerReturnRate = powerReturnRate;
        this.chargingTime = chargingTime;
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * Gets the power return rate of the hybrid system.
     * @return the power return rate
     */
    public int getPowerReturnRate() {
        return powerReturnRate;
    }

    /**
     * Sets the power return rate of the hybrid system.
     * @param powerReturnRate the power return rate to set
     */
    public void setPowerReturnRate(int powerReturnRate) {
        this.powerReturnRate = powerReturnRate;
    }

    /**
     * Gets the charging time of the hybrid vehicle.
     * @return the charging time in minutes
     */
    public int getChargingTime() {
        return chargingTime;
    }

    /**
     * Sets the charging time of the hybrid vehicle.
     * @param chargingTime the charging time in minutes to set
     */
    public void setChargingTime(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    /**
     * Gets the fuel efficiency of the hybrid vehicle.
     * @return the fuel efficiency in mpg
     */
    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    /**
     * Sets the fuel efficiency of the hybrid vehicle.
     * @param fuelEfficiency the fuel efficiency in mpg to set
     */
    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * Calculates the yearly depreciation rate based on age and mileage.
     * @return the yearly depreciation rate
     */
    @Override
    public int calculateYearlyDepreciationRate() {
        int temp = 0;
        
        if (getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_RATE;
        }
        if (getMileage() >= DEPRECIATE_MILEAGE) {
            temp += DEPRECIATE_MILEAGE_BY_RATE;
        }

        return temp;
    }

    /**
     * Calculates the yearly depreciation rate based on rechargeable status, age, and mileage.
     * @param isRechargeable indicates if the hybrid vehicle is rechargeable
     * @return the yearly depreciation rate
     */
    public int calculateYearlyDepreciationRate(boolean isRechargeable) {
        int temp = 0;

        if (isRechargeable) {
            if (chargingTime >= DEPRECIATE_CHARGING_TIME) {
                temp += DEPRECIATE_CHARGING_TIME_BY_RATE;
            }
        }

        if (getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_RATE;
        }

        if (super.getMileage() >= DEPRECIATE_MILEAGE) {
            temp += DEPRECIATE_MILEAGE_BY_RATE;
        }

        return temp;
    }

    /**
     * Calculates the expected price based on depreciation factors.
     * @param basePrice the base price of the vehicle
     * @return the expected price after depreciation
     */
    @Override
    public int calculateExpectedPrice(int basePrice) {
        int temp = 0;
                
        if (fuelEfficiency < DEPRECIATE_FUELEFF) {
            temp += DEPRECIATE_FUELEFF_BY_PRICE;
        }

        if (chargingTime >= DEPRECIATE_CHARGING_TIME) {
            temp += DEPRECIATE_CHARGING_TIME_BY_PRICE;
        }

        if (getAge() >= DEPRECIATE_AGE) {
            temp += DEPRECIATE_AGE_BY_PRICE;
        }
        if (getMileage() >= DEPRECIATE_MILEAGE) {
            temp += DEPRECIATE_MILEAGE_BY_PRICE;
        }

        return basePrice - temp;    
    }
    
    /**
     * Compares this HybridSpec with another spec based on a percentage match threshold.
     * @param spec the spec object to compare with
     * @param percentMatch the percentage match threshold
     * @return true if the match percentage is greater than or equal to the threshold, false otherwise
     */
    @Override
    public boolean equals(Object spec, double percentMatch) {
        if (spec == null || !(spec instanceof HybridSpec)) {
            return false;
        } else if (spec == this) {
            return true;
        } else {
            HybridSpec hybridSpec = (HybridSpec) spec;
            int matchCount = 0;
            int totalCount = 0;

            if (this.powerReturnRate >= hybridSpec.powerReturnRate) {
                matchCount++;
            }
            totalCount++;

            if (this.chargingTime <= hybridSpec.chargingTime) {
                matchCount++;
            }
            totalCount++;

            if (this.fuelEfficiency >= hybridSpec.fuelEfficiency) {
                matchCount++;
            }
            totalCount++;

            if (this.getAge() <= hybridSpec.getAge()) {
                matchCount++;
            }
            totalCount++;

            if (this.getMileage() <= hybridSpec.getMileage()) {
                matchCount++;
            }
            totalCount++;

            double matchPercentage = (double) matchCount / totalCount * 100;
            return matchPercentage >= percentMatch;
        }
    }

    /**
     * Returns a string representation of the HybridSpec object.
     * @return a string containing the hybrid specifications and parent specifications
     */
    @Override
    public String toString() {
        String temp = "";
        temp += "\nHybrid Spec:\n";
        temp += "Power Return Rate: " + powerReturnRate + "\n";
        temp += "Charging Time: " + chargingTime + "\n";
        temp += "Fuel Efficiency: " + fuelEfficiency + "\n";
        return super.toString() + temp;
    }
}