public abstract class Spec {

    public static int DEPRECIATE_AGE = 5;
    public static int DEPRECIATE_MILEAGE = 100000;
    public static int DEPRECIATE_AGE_BY_VALUE = 20; // can change
    public static int DEPRECIATE_MILEAGE_BY_VALUE = 20; // can change


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

    public abstract int calculateYearlyDepreciationRate();

    public abstract int calculateExpectedPrice();

    public abstract boolean compareToSpec(Spec spec, double percentMatch);

    public int calculateValueAfterYear(int year){
        int depreciationRate = calculateYearlyDepreciationRate();
        int price = calculateExpectedPrice();
        
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