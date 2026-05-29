public class BuyerAccount extends Account {
    private int budget;
    private String typeCar;
    private Spec expectation;
    private double percentMatch;
    private double rangeOfAccept;

    public BuyerAccount(boolean isOrgaization, boolean isFamily, int budget, String typeCar, Spec expectation, double percentMatch) {
        super(isOrgaization, isFamily);
        this.budget = budget;
        this.typeCar = typeCar;
        this.expectation = expectation;
        this.percentMatch = percentMatch;
    }

    public int getBudget() {
        return budget;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public Spec getExpectations() {
        return expectation;
    }

    public double getPercentMatch() {
        return percentMatch;
    }

    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    public void updateBudget(int newBudget) {
        budget = newBudget;
    }

    public void updateTypeCar(String newTypeCar) {
        typeCar = newTypeCar;
    }

    public void updateExpectations(Spec newExpectations) {
        expectation = newExpectations;
    }

    public void changeRangeOfAccept(double newRange) {
        rangeOfAccept = newRange;
    }
    
    public boolean validate(Vehicle[] vehicles) {
        return budget >= findLowestInApplicable(vehicles);
    }

    public Vehicle[] showAllApplicableVehicles(Vehicle[] vehicles) {
        Vehicle[] applicable = null;
        if(getApplicableNum(vehicles) != 0) {
            applicable = new Vehicle[getApplicableNum(vehicles)];
            int x = 0;
            for(int i = 0; i < vehicles.length; i++) {
                if(vehicles[i].getSpec().compareToSpec(expectation, percentMatch)) {
                    applicable[x] = vehicles[i];
                    x++;
                }   
            }
        }

        return applicable;
    }

    public int getApplicableNum(Vehicle[] vehicles) {
        int counter = 0;
        for(int i = 0; i < vehicles.length; i++) {
            if(vehicles[i].getSpec().compareToSpec(expectation, percentMatch)) {
                counter++;
            }
        }
        return counter;
    }

    public boolean buyVehicle(double finalPrice) {
        if(finalPrice <= budget - rangeOfAccept) {
            return true;
        } else {
            double random = Math.random();
            return random >= 0.5;
        }
    }

    public int findLowestInApplicable(Vehicle[] vehicles) {
        Vehicle[] arr = showAllApplicableVehicles(vehicles);
        double smallest = arr[0].getBasePrice();
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].getBasePrice() < smallest) {
                smallest = arr[i].getBasePrice();
            }
        }
        return (int)smallest;
    }

    public String toString() {
        return super.toString() + 
            "\nBudget: $" + budget + 
            "\nType Car: " + typeCar + 
            "\nExpectations: " + expectation + 
            "\nPercent Match: %" + percentMatch * 100 + 
            "\nRange of Accept: $" + rangeOfAccept;  
    }
}