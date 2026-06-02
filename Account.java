/**
 * Account.java
 * Author: Sean Liu
 * Teacher: Ms. Lam
 * Date: June 2, 2026
 * 
 * Description: Abstract base class that represents a dealership account.
 * This class serves as the parent class for all account types (BuyerAccount, SellerAccount, TradeInAccount).
 * It stores common account information such as whether the account is for an organization or family,
 * and provides a discount constant for organizational accounts.
 */
abstract class Account {
    private boolean isOrganization;
    private boolean isFamily;
    private final double ORGANIZATION_DISCOUNT = 0.2;

    /**
     * Constructor that initializes an Account with organization and family flags.
     * @param isOrganization boolean indicating if the account is for an organization
     * @param isFamily boolean indicating if the account is for a family
     */
    public Account(boolean isOrganization, boolean isFamily) {
        this.isOrganization = isOrganization;
        this.isFamily = isFamily;
    }

    /**
     * Default constructor that initializes an Account with both flags set to false.
     */
    public Account() {
        isOrganization = false;
        isFamily = false;
    }

    /**
     * Abstract method that validates if an account meets criteria based on available vehicles.
     * This method must be implemented by all subclasses.
     * @param vehicles array of Vehicle objects to validate against
     * @return boolean indicating if the account validation is successful
     */
    abstract boolean validate(Vehicle[] vehicles);

    /**
     * Returns a string representation of the Account.
     * @return String containing the organization and family status
     */
    public String toString() {
        return "Is Organization: " + isOrganization + "\nIs Family: " + isFamily;
    }
} 