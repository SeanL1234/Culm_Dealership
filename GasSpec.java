/**
 * GasSpec.java
 * Author: Fabian Hui
 * Teacher: Ms. Lam
 * Date: June 3, 2026
 * 
 * Description: [TODO: Add description]
 */

public class GasSpec extends Spec {

    public static int BIG_FUELCAP = 50; // can change
    public static int BIG_FUELCAP_BY_PRICE = 20; //
    public static int BIG_FUELCAP_BY_RATE = 10; // can change
    public static int DEPRECIATE_FUELEFF = 10; // can change
    public static int DEPRECIATE_FUELEFF_BY_PRICE = 20; // can change
    public static int DEPRECIATE_FUELEFF_BY_RATE = 10; // can change

    private String engineType;
    private int fuelCapacity;
    private int fuelEfficiency;

    /**
     * Constructs a GasSpec object with the specified values.
     *
     * @param mileage the mileage of the vehicle
     * @param age the age of the vehicle
     * @param warrantyExpireYear the year the warranty expires
     * @param lastMaintenance a description of the last maintenance performed
     * @param baseMaintenanceFee the base maintenance fee
     * @param engineType the engine type
     * @param fuelCapacity the fuel tank capacity
     * @param fuelEfficiency the fuel efficiency rating
     */
    public GasSpec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee, 
        String engineType, int fuelCapacity, int fuelEfficiency) {
        super(mileage, age, warrantyExpireYear, lastMaintenance, baseMaintenanceFee);
        this.engineType = engineType;
        this.fuelCapacity = fuelCapacity;
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * Returns the engine type.
     *
     * @return the engine type
     */
    public String getEngineType() {
        return engineType;
    }

    /**
     * Sets the engine type.
     *
     * @param engineType the engine type to set
     */
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * Returns the fuel capacity.
     *
     * @return the fuel capacity
     */
    public int getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * Sets the fuel capacity.
     *
     * @param fuelCapacity the fuel capacity to set
     */
    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * Returns the fuel efficiency.
     *
     * @return the fuel efficiency
     */
    public int getFuelEfficiency() {
        return fuelEfficiency;
    }

    /**
     * Sets the fuel efficiency.
     *
     * @param fuelEfficiency the fuel efficiency to set
     */
    public void setFuelEfficiency(int fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    /**
     * Calculates the yearly depreciation rate for the vehicle.
     *
     * @return the yearly depreciation rate
     */
    @Override
    public int calculateYearlyDepreciationRate() {
        int temp = 0;
        
        if (fuelEfficiency < DEPRECIATE_FUELEFF) {
            temp += DEPRECIATE_FUELEFF_BY_RATE;
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
     * Calculates the expected price after depreciation adjustments.
     *
     * @param basePrice the base price of the vehicle
     * @return the expected price after depreciation
     */
    @Override
    public int calculateExpectedPrice(int basePrice) {
        int temp = 0;
                
        if (fuelEfficiency < DEPRECIATE_FUELEFF) {
            temp += DEPRECIATE_FUELEFF_BY_PRICE;
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
     * Compares this GasSpec to another spec and checks if they match by the given percentage.
     *
     * @param spec the other spec to compare against
     * @param percentMatch the minimum match percentage required
     * @return true if the specs match by the given percentage, false otherwise
     */
    public boolean equals(Object spec, double percentMatch) {
        if (spec == null || !(spec instanceof GasSpec)) {
            return false;
        } else {
            GasSpec gasSpec = (GasSpec) spec;
            int matchCount = 0;
            int totalCount = 0;

            if (this.engineType.equals(gasSpec.engineType)) {
                matchCount++;
            }
            totalCount++;

            if (this.fuelCapacity >= gasSpec.fuelCapacity) {
                matchCount++;
            }
            totalCount++;

            if (this.fuelEfficiency >= gasSpec.fuelEfficiency) {
                matchCount++;
            }
            totalCount++;

            if (super.getAge() <= gasSpec.getAge()) {
                matchCount++;
            }
            totalCount++;

            if (super.getMileage() <= gasSpec.getMileage()) {
                matchCount++;
            }
            totalCount++;

            double matchPercentage = (double) matchCount / totalCount * 100;
            return matchPercentage >= percentMatch;
        }
    }

    /**
     * Returns a string representation of the GasSpec object.
     *
     * @return the string representation of the gas specification
     */
    public String toString() {
        String temp = "";
        temp += "Gas Spec:\n";
        temp += "Engine Type: " + engineType + "\n";
        temp += "Fuel Capacity: " + fuelCapacity + "\n";
        temp += "Fuel Efficiency: " + fuelEfficiency + "\n";
        return super.toString() + temp;
    }
}