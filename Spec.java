public abstract class Spec {

    public static int DEPRECIATE_AGE = 5;
    public static int DEPRECIATE_AGE_BY_VALUE = 20; // can change
    public static int DEPRECIATE_AGE_BY_RATE = 10; // can change
    public static int DEPRECIATE_MILEAGE = 100000;
    public static int DEPRECIATE_MILEAGE_BY_VALUE = 20; // can change
    public static int DEPRECIATE_MILEAGE_BY_RATE = 10; // can change


    private int mileage;
    private int age;
    private int warrantyExpireYear;
    private String lastMaintenance;
    private int baseMaintenanceFee;

    /**
     * Constructs a Spec object with the given attributes.
     *
     * @param mileage the current mileage
     * @param age the age of the vehicle in years
     * @param warrantyExpireYear the year the warranty expires
     * @param lastMaintenance the last maintenance description
     * @param baseMaintenanceFee the base maintenance fee
     */
    public Spec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee) {
        this.mileage = mileage;
        this.age = age;
        this.warrantyExpireYear = warrantyExpireYear;
        this.lastMaintenance = lastMaintenance;
        this.baseMaintenanceFee = baseMaintenanceFee;
    }

    /**
     * Returns the current mileage.
     *
     * @return the current mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * Sets the current mileage.
     *
     * @param mileage the new mileage
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * Returns the age of the vehicle.
     *
     * @return the age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the vehicle.
     *
     * @param age the age in years
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the warranty expiry year.
     *
     * @return the warranty expiry year
     */
    public int getWarrantyExpireYear() {
        return warrantyExpireYear;
    }

    /**
     * Sets the warranty expiry year.
     *
     * @param warrantyExpireYear the year the warranty expires
     */
    public void setWarrantyExpireYear(int warrantyExpireYear) {
        this.warrantyExpireYear = warrantyExpireYear;
    }

    /**
     * Returns the description of the last maintenance.
     *
     * @return the last maintenance description
     */
    public String getLastMaintenance() {
        return lastMaintenance;
    }

    /**
     * Sets the description of the last maintenance.
     *
     * @param lastMaintenance the last maintenance description
     */
    public void setLastMaintenance(String lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    /**
     * Returns the base maintenance fee.
     *
     * @return the base maintenance fee
     */
    public int getBaseMaintenanceFee() {
        return baseMaintenanceFee;
    }

    /**
     * Sets the base maintenance fee.
     *
     * @param baseMaintenanceFee the base maintenance fee
     */
    public void setBaseMaintenanceFee(int baseMaintenanceFee) {
        this.baseMaintenanceFee = baseMaintenanceFee;
    }

    public abstract int calculateYearlyDepreciationRate();

    public abstract int calculateExpectedPrice(int basePrice);

    public abstract boolean equals(Spec spec, double percentMatch);

    /**
     * Calculates the expected value of the vehicle after the given number of years.
     * Uses the current yearly depreciation rate and expected price based on the base price.
     *
     * @param basePrice the base price of the vehicle
     * @param year the number of years to project into the future
     * @return the projected value after the specified number of years
     */
    public int calculateValueAfterYear(int basePrice, int year){
        int depreciationRate = calculateYearlyDepreciationRate();
        int price = calculateExpectedPrice(basePrice);
        
        return calculateValueAfterYear(year, depreciationRate, price);
    }

    public int calculateValueAfterYear(int year, int depreciationRate, int price){
        if (year > 0){
            return price; 
        } else {
            return calculateValueAfterYear(year - 1, depreciationRate, price + (price * depreciationRate / 100));
        }
    }

    /**
     * Returns a string representation of the vehicle specification.
     * Includes mileage, age, warranty expiry, last maintenance, and base maintenance fee.
     *
     * @return a formatted string containing the spec details
     */
    public String toString(){
        String temp = "";
        temp += "Mileage: " + mileage + "\n";
        temp += "Age: " + age + "\n";
        temp += "Warranty Expire Year: " + warrantyExpireYear + "\n";
        temp += "Last Maintenance: " + lastMaintenance + "\n";
        temp += "Base Maintenance Fee: " + baseMaintenanceFee + "\n";
        return temp;
    }

}