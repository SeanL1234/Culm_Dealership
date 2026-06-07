/**
 * TradeInAccount.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 2, 2026
 * 
 * Description: Represents a trade-in account in the dealership system.
 * This class extends Account and manages trade-in customer information such as the vehicle being traded in,
 * customer rating, and desired vehicle specifications. It provides methods to validate trades, find applicable
 * vehicles to trade for, and negotiate based on the customer's vehicle value and preferences.
 */
public class TradeInAccount extends Account {
    private final int BAD_THRESHOLD = 6;
    private Vehicle vehicleForTrading;
    private int rating;
    private Spec expectation;
    private double percentMatch;
    private double rangeOfAccept;

    /**
     * Constructor that initializes a TradeInAccount with organization/family status and trade-in information.
     * @param isOrgaization boolean indicating if the account is for an organization
     * @param isFamily boolean indicating if the account is for a family
     * @param expectation the Spec object containing desired vehicle specifications for the trade
     * @param rating the customer's rating in the system
     * @param vehicleForTrading the Vehicle object being traded in
     * @param percentMatch the percentage match required for a vehicle to be considered applicable
     */
    public TradeInAccount(boolean isOrgaization, boolean isFamily, Spec expectation, int rating, Vehicle vehicleForTrading, double percentMatch) {
        super(isOrgaization, isFamily);
        this.expectation = expectation;
        this.rating = rating;
        this.vehicleForTrading = vehicleForTrading;
        this.percentMatch = percentMatch;
    }

    /**
     * Validates if the customer can complete a trade-in transaction.
     * @param vehicle array of Vehicle objects available for trade
     * @return boolean indicating if the trade is valid (must have good rating and trade-in value supports lowest applicable vehicle)
     */
    public boolean validate(Vehicle[] vehicle) {
        return !isRatedBad() && vehicleForTrading.getBasePrice() >= findLowestInApplicable(vehicle);
    }

    /**
     * Returns the vehicle being traded in.
     * @return Vehicle object being traded in
     */
    public Vehicle getVehicleForTrading() {
        return vehicleForTrading;
    }

    /**
     * Returns the customer's rating.
     * @return int representing the customer's rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the customer's desired vehicle specification expectations.
     * @return Spec object containing the customer's expectations
     */
    public Spec getExpectation() {
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
     * Returns the acceptable range of price deviation.
     * @return double representing the range of acceptance
     */
    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    /**
     * Updates the vehicle being traded in.
     * @param vehicleForTrading the new Vehicle object being traded in
     */
    public void setVehicleForTrading(Vehicle vehicleForTrading) {
        this.vehicleForTrading = vehicleForTrading;
    }

    /**
     * Updates the customer's rating.
     * @param rating the new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Updates the customer's desired vehicle specification expectations.
     * @param expectation the new Spec object with updated expectations
     */
    public void setExpectation(Spec expectation) {
        this.expectation = expectation;
    }

    /**
     * Updates the percentage match required for vehicle applicability.
     * @param percentMatch the new percent match threshold
     */
    public void setPercentMatch(double percentMatch) {
        this.percentMatch = percentMatch;
    }

    /**
     * Updates the acceptable price range deviation.
     * @param rangeOfAccept the new range of acceptance value
     */
    public void setRangeOfAccept(double rangeOfAccept) {
        this.rangeOfAccept = rangeOfAccept;
    }

    /**
     * Counts the number of vehicles that match the customer's specifications.
     * @param vehicles array of all available Vehicle objects for trade
     * @return int representing the count of applicable vehicles
     */
    public int getApplicableNum(Vehicle[] vehicles) {
        int counter = 0;
        for(int i = 0; i < vehicles.length; i++) {
            if(vehicles[i].getVehicleSpec().equals(expectation, percentMatch)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Finds the lowest price among all vehicles that meet the customer's specifications.
     * @param vehicles array of all available Vehicle objects for trade
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
     * Returns an array of all vehicles that meet the customer's specifications for trade.
     * @param vehicles array of all available Vehicle objects for trade
     * @return array of Vehicle objects that match the customer's expectations, or null if none match
     */
    public Vehicle[] showAllApplicableVehicles(Vehicle[] vehicles) {
        Vehicle[] applicable = null;
        if(getApplicableNum(vehicles) != 0) {
            applicable = new Vehicle[getApplicableNum(vehicles)];
            int x = 0;
            for(int i = 0; i < vehicles.length; i++) {
                if(vehicles[i].getVehicleSpec().equals(expectation, percentMatch)) {
                    applicable[x] = vehicles[i];
                    x++;
                }   
            }
        }

        return applicable;
    }

    /**
     * Determines if the customer has a bad rating (below the threshold).
     * @return boolean indicating if the customer is rated as bad
     */
    public boolean isRatedBad() {
        return rating < BAD_THRESHOLD;
    }

    public boolean tradeVehicle(Vehicle tradeFor) {
        if(vehicleForTrading.getBasePrice() < tradeFor.getBasePrice() - rangeOfAccept) {
            return true;
        } else if(vehicleForTrading.getBasePrice() < tradeFor.getBasePrice()) {
            return Math.random() > 0.5;
        }
        return false;
    }

    /**
     * Returns a string representation of the TradeInAccount.
     * @return String containing trade-in customer information including vehicle, rating, expectations, and percent match
     */
    public String toString() {
        return super.toString() + 
            "\nVehicle for Trading: " + vehicleForTrading + 
            "\nRating: " + rating + 
            "\nExpectations: " + expectation + 
            "\nPercent Match: %" + percentMatch * 100 + 
            "\nRange of Accept: $" + rangeOfAccept;  
    }
}