public class TradeInAccount extends Account {
    private final int BAD_THRESHOLD = 6;
    private Vehicle vehicleForTrading;
    private int rating;
    private Spec expectation;
    private double percentMatch;
    private double rangeOfAccept;

    public TradeInAccount(boolean isOrgaization, boolean isFamily, Spec expectation, int rating, Vehicle vehicleForTrading, double percentMatch) {
        super(isOrgaization, isFamily);
        this.expectation = expectation;
        this.rating = rating;
        this.vehicleForTrading = vehicleForTrading;
        this.percentMatch = percentMatch;
    }

    public boolean validate(Vehicle[] vehicle) {
        return !isRatedBad() && vehicleForTrading.getBasePrice() >= findLowestInApplicable(vehicle);
    }

    // Accessors
    public Vehicle getVehicleForTrading() {
        return vehicleForTrading;
    }

    public int getRating() {
        return rating;
    }

    public Spec getExpectation() {
        return expectation;
    }

    public double getPercentMatch() {
        return percentMatch;
    }

    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    // Mutators
    public void setVehicleForTrading(Vehicle vehicleForTrading) {
        this.vehicleForTrading = vehicleForTrading;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setExpectation(Spec expectation) {
        this.expectation = expectation;
    }

    public void setPercentMatch(double percentMatch) {
        this.percentMatch = percentMatch;
    }

    public void setRangeOfAccept(double rangeOfAccept) {
        this.rangeOfAccept = rangeOfAccept;
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

    public boolean isRatedBad() {
        return rating < BAD_THRESHOLD;
    }
}