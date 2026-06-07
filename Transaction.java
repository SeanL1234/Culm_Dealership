/* 
File Name: Transaction.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The Transaction class.
*/
public class Transaction {

   // Constants
   public static final double EXPENSIVE_THRSHOLD = 0.4;
   
   // Fields
   private String customerName;
   private String customerID; 
   private int finalPrice;
   private boolean isTradeIn;
   private boolean isSold;
   private boolean isBought;
   private boolean isLease;
   private int month;
   private int date;
   private int year;
   private Vehicle vehicle;
   
   // Accessors
   public String getCustomerName(){
      return customerName;
   }
   /**
    * Returns the customer's name associated with this transaction.
    * @return customer name
    */
   public String getCustomerID(){
      return customerID;
   }
   /**
    * Returns the customer ID associated with this transaction.
    * @return customer ID
    */
   public int getFinalPrice(){
      return finalPrice;
   }
   /**
    * Returns the final transaction price.
    * @return final price in whole dollars
    */
   public boolean getIsTradeIn(){
      return isTradeIn;
   }
   /**
    * Indicates whether this transaction involved a trade-in vehicle.
    * @return true if trade-in, false otherwise
    */
   public boolean getIsSold(){
      return isSold;
   }
   /**
    * Indicates whether a vehicle was sold in this transaction.
    * @return true if sold
    */
   public boolean getIsBought(){
      return isBought;
   }
   /**
    * Indicates whether a vehicle was bought in this transaction.
    * @return true if bought
    */
   public boolean getIsLease(){
      return isLease;
   }
   /**
    * Indicates whether this transaction was a lease.
    * @return true if lease
    */
   public int getMonth(){
      return month;
   }
   /**
    * Returns the month when the transaction occurred.
    * @return month number (1-12)
    */
   public int getDate(){
      return date;
   }
   /**
    * Returns the day of month when the transaction occurred.
    * @return day of month
    */
   public int getYear(){
      return year;
   }
   /**
    * Returns the year when the transaction occurred.
    * @return four-digit year
    */
   public Vehicle getVehicle(){
      return vehicle;
   }
   /**
    * Returns the vehicle involved in this transaction.
    * @return Vehicle object
    */
   

   // Mutators
   public void setcustomerName(String customerName){
      this.customerName = customerName;
   }
   /**
    * Sets the customer's name for this transaction.
    * @param customerName customer name
    */
   public void setCustomerID (String customerID){
      this.customerID = customerID;
   }
   /**
    * Sets the customer ID for this transaction.
    * @param customerID customer identifier
    */
   public void setFinalPrice (int finalPrice){
      this.finalPrice = finalPrice;
   }
   /**
    * Sets the final price for this transaction.
    * @param finalPrice price in whole dollars
    */
   public void setIsTradeIn (boolean isTradeIn){
      this.isTradeIn = isTradeIn;
   }
   /**
    * Marks whether this transaction is a trade-in.
    * @param isTradeIn true if trade-in
    */
   public void setIsSold (boolean isSold){
      this.isSold = isSold;
   }
   /**
    * Marks whether a vehicle was sold in this transaction.
    * @param isSold true if sold
    */
   public void setIsBought (boolean isBought){
      this.isBought = isBought;
   }
   /**
    * Marks whether a vehicle was bought in this transaction.
    * @param isBought true if bought
    */
   public void setIsLease (boolean isLease){
      this.isLease = isLease;
   }
   /**
    * Marks whether this transaction is a lease.
    * @param isLease true if lease
    */
   public void setMonth (int month){
      this.month = month;
   }
   /**
    * Sets the month for the transaction date.
    * @param month month number (1-12)
    */
   public void setDate (int date){
      this.date = date;
   }
   /**
    * Sets the day of month for the transaction date.
    * @param date day of month
    */
   public void setYear (int year){
      this.year = year;
   }
   /**
    * Sets the year for the transaction date.
    * @param year four-digit year
    */
   public void setVehicle (Vehicle vehicle){
      this.vehicle = vehicle;
   }
   /**
    * Sets the vehicle involved in this transaction.
    * @param vehicle Vehicle object
    */
   

   public Transaction(String customerName, String customerID, int finalPrice,boolean isTradeIn, boolean isBought,boolean isSold,boolean isLease, int month,int date,int year,Vehicle vehicle){
      this.customerName = customerName;
      this.customerID = customerID;
      this.finalPrice = finalPrice;
      this.isTradeIn = isTradeIn;
      this.isSold = isSold;
      this.isBought = isBought;
      this.isLease = isLease;
      this.month = month;
      this.date = date;
      this.year = year;
      this.vehicle = vehicle;
   }
   /**
    * Determine profit made on this transaction (final price minus vehicle base price).
    * @return profit in whole dollars
    */
   public int determineProfitMade(){
      return finalPrice-vehicle.getBasePrice();
   }
   
   /**
    * Indicates whether the transaction resulted in profit.
    * @return true if final price > vehicle base price
    */
   public boolean wasProfitable(){
      return finalPrice>vehicle.getBasePrice();
   }
   
   /**
    * Returns a human-readable representation of the transaction details.
    * @return string describing the transaction
    */
   public String toString(){
      return "Customer Name: "+ customerName+
         "\nCustomer ID: "+customerID+
         "\nFinal Price: "+finalPrice+
         "\nIs TradeIn: "+isTradeIn+
         "\nIs Sold: "+ isSold+
         "\nIs Bought: "+isBought+
         "\nIs Lease: "+isLease+
         "\nMonth: "+ month+
         "\nDate: "+ date+
         "\nYear: "+ year+
         vehicle.displayEssentials();
   }
   

}