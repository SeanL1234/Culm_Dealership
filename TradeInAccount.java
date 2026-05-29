public class TradeInAccount extends Account {
    private Vehicle vehicleForTrading;
    private int rating;
    private Spec expectation;
    private double percentMatch;
    private double rangeOfAccept;

    public TradeInAccount(boolean isOrgaization, boolean isFamily, Spec expectation, int rating, Vehicle vehicleForTrading, double percentMatch) {
        super(isOrgaization, isFamily);
        this.expectation = expectation;
        this.rating = rating;
    }

    public boolean validate(Vehicle[] vehicle) {
        return true;
    }
}