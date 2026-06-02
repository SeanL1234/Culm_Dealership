/**
 * BuyerAccount.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 2, 2026
 * 
 * Description: Represents a buyer account in the dealership system.
 * This class extends Account and manages buyer-specific information such as budget, preferred car type,
 * and vehicle specifications. It provides methods to validate purchases, find applicable vehicles,
 * and determine if a vehicle purchase is feasible based on the buyer's budget and preferences.
 */
public class BuyerAccount extends Account {
    private int budget;
    private String typeCar;
    private Spec expectation;
    private double percentMatch;
    private double rangeOfAccept;

    /**
     * Constructor that initializes a BuyerAccount with organization/family status and buyer preferences.
     * @param isOrgaization boolean indicating if the account is for an organization
     * @param isFamily boolean indicating if the account is for a family
     * @param budget the maximum amount the buyer is willing to spend
     * @param typeCar the preferred type of car the buyer wants
     * @param expectation the Spec object containing the buyer's vehicle specifications
     * @param percentMatch the percentage match required for a vehicle to be considered applicable
     */
    public BuyerAccount(boolean isOrgaization, boolean isFamily, int budget, String typeCar, Spec expectation, double percentMatch) {
        super(isOrgaization, isFamily);
        this.budget = budget;
        this.typeCar = typeCar;
        this.expectation = expectation;
        this.percentMatch = percentMatch;
    }

    /**
     * Returns the buyer's budget.
     * @return int representing the budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Returns the buyer's preferred car type.
     * @return String representing the car type
     */
    public String getTypeCar() {
        return typeCar;
    }

    /**
     * Returns the buyer's vehicle specification expectations.
     * @return Spec object containing the buyer's expectations
     */
    public Spec getExpectations() {
        return expectation;
    }

    /**
     * Returns the percentage match required for vehicle applicability.
     * @return double representing the percent match threshold
     */
    public double getPercentMatch() {
        return percentMatch;
    }

    /**
     * Returns the acceptable range of price deviation from the budget.
     * @return double representing the range of acceptance
     */
    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    /**
     * Updates the buyer's budget to a new value.
     * @param newBudget the new budget amount
     */
    public void updateBudget(int newBudget) {
        budget = newBudget;
    }

    /**
     * Updates the buyer's preferred car type.
     * @param newTypeCar the new car type preference
     */
    public void updateTypeCar(String newTypeCar) {
        typeCar = newTypeCar;
    }

    /**
     * Updates the buyer's vehicle specification expectations.
     * @param newExpectations the new Spec object with updated expectations
     */
    public void updateExpectations(Spec newExpectations) {
        expectation = newExpectations;
    }

    /**
     * Updates the acceptable price range deviation.
     * @param newRange the new range of acceptance value
     */
    public void changeRangeOfAccept(double newRange) {
        rangeOfAccept = newRange;
    }
    
    /**
     * Validates if the buyer has sufficient budget for the cheapest applicable vehicle.
     * @param vehicles array of Vehicle objects to validate against
     * @return boolean indicating if the buyer can afford at least one applicable vehicle
     */
    public boolean validate(Vehicle[] vehicles) {
        return budget >= findLowestInApplicable(vehicles);
    }

    /**
     * Returns an array of all vehicles that meet the buyer's specifications.
     * @param vehicles array of all available Vehicle objects
     * @return array of Vehicle objects that match the buyer's expectations, or null if none match
     */
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

    /**
     * Counts the number of vehicles that match the buyer's specifications.
     * @param vehicles array of all available Vehicle objects
     * @return int representing the count of applicable vehicles
     */
    public int getApplicableNum(Vehicle[] vehicles) {
        int counter = 0;
        for(int i = 0; i < vehicles.length; i++) {
            if(vehicles[i].getSpec().compareToSpec(expectation, percentMatch)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Determines if the buyer can purchase a vehicle at the given price.
     * If price is within range, purchase is approved. Otherwise, it's decided randomly (50% chance).
     * @param finalPrice the final price of the vehicle
     * @return boolean indicating if the purchase is approved
     */
    public boolean buyVehicle(double finalPrice) {
        if(finalPrice <= budget - rangeOfAccept) {
            return true;
        } else {
            double random = Math.random();
            return random >= 0.5;
        }
    }

    /**
     * Finds the lowest price among all vehicles that meet the buyer's specifications.
     * @param vehicles array of all available Vehicle objects
     * @return int representing the lowest price of an applicable vehicle
     */
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

    /**
     * Returns a string representation of the BuyerAccount.
     * @return String containing buyer information including budget, car type, expectations, and percent match
     */
    public String toString() {
        return super.toString() + 
            "\nBudget: $" + budget + 
            "\nType Car: " + typeCar + 
            "\nExpectations: " + expectation + 
            "\nPercent Match: %" + percentMatch * 100 + 
            "\nRange of Accept: $" + rangeOfAccept;  
    }
}