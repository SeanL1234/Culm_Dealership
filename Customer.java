/**
 * Customer.java
 * Author: Fabian Hui
 * Teacher: Ms. Lam
 * Date: June 3, 2026
 * 
 * Description: [TODO: Add description]
 */

import java.io.*; // idk if i want this to stay 

public class Customer{
    
    public static int LOYAL_THRESHOLD = 5; // can change
    public static int LOYAL_RANGE = 10; // can change
    public static int LOYAL_MULTIPLIER = 10; // can change
    public static int NEW_MULTIPLIER = 10; // can change

    private int bargainingRange;
    private int loyaltyPoint;
    private String name;
    private String id;
    private Account[] customerAccount;
    private Transaction[] customerTransactionHistory;



    /**
     * Constructs a Customer with the provided name, id, account list, and transaction history.
     *
     * @param name the customer's name
     * @param id the customer's identifier
     * @param customerAccount the customer's accounts
     * @param customerTransactionHistory the customer's transaction history
     */
    public Customer(int loyaltyPoint, String name, String id, Account[] customerAccount, Transaction[] customerTransactionHistory) {
        this.name = name;
        this.id = id;
        this.customerAccount = customerAccount;
        this.customerTransactionHistory = customerTransactionHistory;
        this.loyaltyPoint = loyaltyPoint;
    }

    /**
     * Construct a Customer with only a name. The id is auto-generated.
     * @param name customer's full name
     */
    public Customer(String name) {
        this.name = name;
        id = generateUID();
        customerAccount = new Account[3];
    }

    // /**
    //  * Constructs a Customer from a file containing customer data.
    //  *
    //  * @param fileName path to the file containing customer information
    //  */
    // public Customer(String fileName){
    //     try {
    //         BufferedReader in = new BufferedReader(new FileReader(fileName));
    //         name = in.readLine();
    //         id = in.readLine();
    //         loyaltyPoint = Integer.parseInt(in.readLine());
    //         // accounts and transaction format goes here, too lazy to write
    //         in.close();
    //     } catch (IOException iox) {
    //         System.out.println("An error occurred while reading the file");
    //     }
        
    // }
    
     /**
     * Compares this customer's loyalty points to another customer's loyalty points.
     *
     * @param other the customer to compare against
     * @return 1 if this customer has more loyalty points or if other is null,
     *         -1 if this customer has fewer loyalty points,
     *         0 if both have the same loyalty points
     */
    public int compareToLoyaltyPoints(Customer other) {
        if (other == null || this.loyaltyPoint > other.loyaltyPoint){
            return 1;
        } else if (this.loyaltyPoint < other.loyaltyPoint) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Determines whether the customer is loyal based on their loyalty points.
     *
     * @return true if the customer's loyalty points are greater than or equal to the loyalty threshold,
     *         otherwise false
     */
    public boolean isLoyal() {
        return loyaltyPoint >= LOYAL_THRESHOLD;
    }

    /**
     * Returns the bargaining range for this customer.
     * @return bargaining range in dollars
     */
    public int getBargainingRange() {
        return bargainingRange;
    }

    /**
     * Set the bargaining range for this customer.
     * @param bargainingRange range in dollars
     */
    public void setBargainingRange(int bargainingRange) {
        this.bargainingRange = bargainingRange;
    }

    /**
     * Returns the customer's loyalty points.
     * @return loyalty points
     */
    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    /**
     * Set the customer's loyalty points.
     * @param loyaltyPoint points to set
     */
    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    /**
     * Returns the customer's full name.
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the customer's name.
     * @param name full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the customer's id.
     * @return id string
     */
    public String getId() {
        return id;
    }

    /**
     * Set the customer's id.
     * @param id identifier string
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the array of accounts for this customer (seller, buyer, trade-in order).
     * @return Account[] of length 3
     */
    public Account[] getCustomerAccount() {
        return customerAccount;
    }
    
    /**
     * Returns the seller account for this customer, if any.
     * @return seller Account or null
     */
    public Account getSellerAccount() {
        return customerAccount[0];
    }

    /**
     * Returns the buyer account for this customer, if any.
     * @return buyer Account or null
     */
    public Account getBuyerAccount() {
        return customerAccount[1];
    }

    /**
     * Returns the trade-in account for this customer, if any.
     * @return trade-in Account or null
     */
    public Account getTradeInAccount() {
        return customerAccount[2];
    }

    /**
     * Set the customer's accounts array.
     * @param customerAccount array of Account objects
     */
    public void setCustomerAccount(Account[] customerAccount) {
        this.customerAccount = customerAccount;
    }

    /**
     * Returns the customer's transaction history.
     * @return Transaction[] of past transactions
     */
    public Transaction[] getCustomerTransactionHistory() {
        return customerTransactionHistory;
    }

    /**
     * Set the customer's transaction history.
     * @param customerTransactionHistory array of Transaction
     */
    public void setCustomerTransactionHistory(Transaction[] customerTransactionHistory) {
        this.customerTransactionHistory = customerTransactionHistory;
    }

    public void updateTransactionHistory(Transaction transaction) {
        Transaction[] temp = customerTransactionHistory;
        customerTransactionHistory = new Transaction[temp.length+1];
        for(int i = 0; i < temp.length; i++) {
            customerTransactionHistory[i] = temp[i];
        }
        customerTransactionHistory[customerTransactionHistory.length-1] = transaction;
    }

    /**
     * Generate a short UID prefix from the customer's name.
     * @return partial UID string
     */
    private String generateUID(){
        String[] nameArray = name.split(" ");
        String uid = "" + nameArray[0].charAt(0) + nameArray[1].charAt(0);
        return generateUID(uid, 0);
    }

    /**
     * Recursive helper to finish UID generation.
     */
    private String generateUID(String uid, int index){
        if (index == 5){
            return uid;
        } else {
            uid += (int)(Math.random() * 10);
            return generateUID(uid, index + 1);
        }
    }

    /**
     * Equality check based on customer id.
     * @param obj other object
     * @return true if obj is Customer with same id
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Customer)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Customer other = (Customer) obj;
            return this.id.equals(other.id);
        }
    }

     /**
     * Generates a randomized deal price for the customer based on loyalty status.
     * Loyal customers receive a deal based on the loyal range and multiplier.
     * New customers receive a deal based on the loyal range and new multiplier.
     *
     * @return a randomized deal price
     */
    public int getRandomizedDealPrice(){
        if (isLoyal()) {
            return (int) (Math.random() * LOYAL_RANGE * LOYAL_MULTIPLIER);
        } else {
            return (int) (Math.random() * LOYAL_RANGE * NEW_MULTIPLIER);
        }
    }

    /**
     * Returns customer information as a string.
     * @return formatted customer info
     */
    public String toString(){
        String temp = "";
        temp += "Customer Name: " + name + "\n";
        temp += "Customer ID: " + id + "\n";
        temp += "Loyalty Points: " + loyaltyPoint + "\n";
        return temp;
    }

    /**
     * Returns essential customer info (name and id).
     * @return essential info string
     */
    public String getEssentialInfo(){
        String temp = "";
        temp += "Customer Name: " + name + "\n";
        temp += "Customer ID: " + id + "\n";
        return temp;
    }

    /**
     * Attach an account to this customer. Type should be "Seller", "Buyer" or other for TradeIn.
     * @param type account type label
     * @param acc account instance
     */
    public void createAccount(String type, Account acc) {
        if(type.equals("Seller")) {
            customerAccount[0] = acc;
        } else if(type.equals("Buyer")) {
            customerAccount[1] = acc;
        } else {
            customerAccount[2] = acc;
        }
    }

}