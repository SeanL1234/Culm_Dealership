/**
 * SellerAccount.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 2, 2026
 * 
 * Description: Represents a seller account in the dealership system.
 * This class extends Account and manages seller-specific information such as offered price, rating, and owned vehicle.
 * It provides methods to validate sales, check vehicle existence in inventory, and determine if a sale can be completed
 * based on the seller's rating and negotiation parameters.
 */
public class SellerAccount extends Account {
    private final int BAD_THRESHOLD = 6;
    private int offeredPrice;
    private int rating;
    private Vehicle ownedVehicle;
    private double rangeOfAccept;

    /**
     * Constructor that initializes a SellerAccount with organization/family status and seller information.
     * @param isOrgaization boolean indicating if the account is for an organization
     * @param isFamily boolean indicating if the account is for a family
     * @param offeredPrice the price the seller is asking for the vehicle
     * @param rating the seller's rating in the system
     * @param ownedVehicle the Vehicle object the seller is offering to sell
     * @param rangeOfAccept price buffer below offered price still negotiable (50% chance)
     */
    public SellerAccount(boolean isOrgaization, boolean isFamily, int offeredPrice, int rating, Vehicle ownedVehicle, double rangeOfAccept) {
        super(isOrgaization, isFamily);
        this.offeredPrice = offeredPrice;
        this.rating = rating;
        this.ownedVehicle = ownedVehicle;
        this.rangeOfAccept = rangeOfAccept;
    }

    /**
     * Returns the seller's offered price for the vehicle.
     * @return int representing the offered price
     */
    public int getOfferedPrice() {
        return offeredPrice;
    }

    /**
     * Returns the seller's rating.
     * @return int representing the seller's rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Returns the vehicle the seller owns and is trying to sell.
     * @return Vehicle object being sold
     */
    public Vehicle getOwnedVehicle() {
        return ownedVehicle;
    }

    /**
     * Returns the price buffer subtracted from the offered price when negotiating.
     * Deals at or above {@code offeredPrice - rangeOfAccept} may still be accepted randomly.
     *
     * @return range-of-accept value in dollars
     */
    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    /**
     * Updates the seller's offered price.
     * @param offeredPrice the new offered price
     */
    public void setOfferedPrice(int offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    /**
     * Updates the seller's rating.
     * @param rating the new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Updates the vehicle the seller is selling.
     * @param ownedVehicle the new Vehicle object being sold
     */
    public void setOwnedVehicle(Vehicle ownedVehicle) {
        this.ownedVehicle = ownedVehicle;
    }

    /**
     * Updates the price buffer used when negotiating seller deals.
     * @param rangeOfAccept the new range-of-accept value
     */
    public void setRangeOfAccept(double rangeOfAccept) {
        this.rangeOfAccept = rangeOfAccept;
    }

    /**
     * Checks if the seller's vehicle already exists in the current inventory.
     * @param inventory array of all Vehicle objects currently in inventory
     * @return boolean indicating if the vehicle is already listed
     */
    public boolean vehicleAlreadyExists(Vehicle[] inventory) {
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i].getVin().equals(ownedVehicle.getVin())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if the seller can sell their vehicle in the dealership.
     * @param inventory array of all Vehicle objects currently in inventory
     * @return boolean indicating if the seller is eligible to sell (must have good rating and unique vehicle)
     */
    public boolean validate(Vehicle[] inventory) {
        return !isRatedBad() && !vehicleAlreadyExists(inventory);
    }

    /**
     * Determines if the seller has a bad rating (below the threshold).
     * @return boolean indicating if the seller is rated as bad
     */
    public boolean isRatedBad() {
        return rating < BAD_THRESHOLD;
    }

    /**
     * Determines if the seller will accept a deal at the given price.
     * If price matches or exceeds the offered price, deal is accepted.
     * If price is within the acceptable range, acceptance is decided randomly (50% chance).
     * Otherwise, the deal is rejected.
     * @param dealPrice the price being offered for the vehicle
     * @return boolean indicating if the seller accepts the deal
     */
    public boolean sellVehicle(int dealPrice) {
        if(dealPrice >= offeredPrice) {
            return true;
        } else if(dealPrice >= offeredPrice - rangeOfAccept) {
            return Math.random() > 0.5;
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the SellerAccount.
     * @return String containing seller information including offered price, rating, vehicle, and range of acceptance
     */
    public String toString() {
        return super.toString() + 
            "\nOffered Price: $" + offeredPrice + 
            "\nRating: " + rating + 
            "\nOwned Vehicle: " + ownedVehicle + 
            "\nRange of Accept: $" + rangeOfAccept;  
    }
}