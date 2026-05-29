public class SellerAccount extends Account {
    private final int BAD_THRESHOLD = 6;
    private int offeredPrice;
    private int rating;
    private Vehicle ownedVehicle;
    private double rangeOfAccept;

    public SellerAccount(boolean isOrgaization, boolean isFamily, int offeredPrice, int rating, Vehicle ownedVehicle) {
        super(isOrgaization, isFamily);
        this.offeredPrice = offeredPrice;
        this.rating = rating;
        this.ownedVehicle = ownedVehicle;
    }

    public int getOfferedPrice() {
        return offeredPrice;
    }

    public int getRating() {
        return rating;
    }

    public Vehicle getOwnedVehicle() {
        return ownedVehicle;
    }

    public double getRangeOfAccept() {
        return rangeOfAccept;
    }

    public void setOfferedPrice(int offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setOwnedVehicle(Vehicle ownedVehicle) {
        this.ownedVehicle = ownedVehicle;
    }

    public void setRangeOfAccept(double rangeOfAccept) {
        this.rangeOfAccept = rangeOfAccept;
    }

    public boolean vehicleAlreadyExists(Vehicle[] inventory) {
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i].getVIN().equals(ownedVehicle.getVIN())) {
                return true;
            }
        }
        return false;
    }

    public boolean validate(Vehicle[] inventory) {
        return !isRatedBad() && !vehicleAlreadyExists(inventory);
    }

    public boolean isRatedBad() {
        return rating < BAD_THRESHOLD;
    }

    public boolean sellVehicle(int dealPrice) {
        if(dealPrice >= offeredPrice) {
            return true;
        } else if(dealPrice >= offeredPrice - rangeOfAccept) {
            return Math.random() > 0.5;
        } else {
            return false;
        }
    }
}