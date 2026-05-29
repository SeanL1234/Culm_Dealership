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

    public Spec(int mileage, int age, int warrantyExpireYear, String lastMaintenance, int baseMaintenanceFee) {
        this.mileage = mileage;
        this.age = age;
        this.warrantyExpireYear = warrantyExpireYear;
        this.lastMaintenance = lastMaintenance;
        this.baseMaintenanceFee = baseMaintenanceFee;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWarrantyExpireYear() {
        return warrantyExpireYear;
    }

    public void setWarrantyExpireYear(int warrantyExpireYear) {
        this.warrantyExpireYear = warrantyExpireYear;
    }

    public String getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(String lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public int getBaseMaintenanceFee() {
        return baseMaintenanceFee;
    }

    public void setBaseMaintenanceFee(int baseMaintenanceFee) {
        this.baseMaintenanceFee = baseMaintenanceFee;
    }

    public abstract int calculateYearlyDepreciationRate();

    public abstract int calculateExpectedPrice(int basePrice);

    public abstract boolean equals(Spec spec, double percentMatch);

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