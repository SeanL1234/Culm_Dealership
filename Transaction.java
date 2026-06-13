/**  
* File Name: Transaction.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
 * Description: The Transaction class represents a vehicle transaction between
 * a customer and the dealership. It stores customer information,
 * transaction details, date information, and the vehicles obtained and sold.
 */
public class Transaction {

   // Constants

   /** Threshold used to determine whether a transaction is expensive. */
   public static final double EXPENSIVE_THRSHOLD = 0.4;
   
   // Fields

   /** Customer's name. */
   private String customerName;

   /** Customer's unique ID. */
   private String customerID; 

   /** Final agreed transaction price. */
   private int finalPrice;

   /** Indicates whether a trade-in vehicle was included. */
   private boolean isTradeIn;

   /** Indicates whether the vehicle was sold. */
   private boolean isSold;

   /** Indicates whether the vehicle was bought by the dealership. */
   private boolean isBought;

   /** Indicates whether the transaction was a lease. */
   private boolean isLease;

   /** Transaction month. */
   private int month;

   /** Transaction day. */
   private int date;

   /** Transaction year. */
   private int year;

   /** Vehicle obtained by the dealership in this transaction. */
   private Vehicle obtainedVehicle;
   /** Vehicle sold by the dealership in this transaction. */
   private Vehicle soldVehicle;
   
   // Accessors

   /** @return customer's name */
   public String getCustomerName(){
      return customerName;
   }

   /** @return customer's ID */
   public String getCustomerID(){
      return customerID;
   }

   /** @return final transaction price */
   public int getFinalPrice(){
      return finalPrice;
   }

   /** @return trade-in status */
   public boolean getIsTradeIn(){
      return isTradeIn;
   }

   /** @return sold status */
   public boolean getIsSold(){
      return isSold;
   }

   /** @return bought status */
   public boolean getIsBought(){
      return isBought;
   }

   /** @return lease status */
   public boolean getIsLease(){
      return isLease;
   }

   /** @return transaction month */
   public int getMonth(){
      return month;
   }

   /** @return transaction day */
   public int getDate(){
      return date;
   }

   /** @return transaction year */
   public int getYear(){
      return year;
   }

   /** @return the vehicle sold by the dealership in this transaction, or null */
   public Vehicle getVehicleSold(){
      return soldVehicle;
   }

   /**
    * Returns the vehicle obtained by the dealership in this transaction.
    * @return obtained vehicle, or null
    */
   public Vehicle getVehicleObtained(){
      return obtainedVehicle;
   }

   // Mutators

   /** @param customerName customer's name */
   public void setcustomerName(String customerName){
      this.customerName = customerName;
   }

   /** @param customerID customer's ID */
   public void setCustomerID (String customerID){
      this.customerID = customerID;
   }

   /** @param finalPrice final transaction price */
   public void setFinalPrice (int finalPrice){
      if(finalPrice>=0){
         this.finalPrice = finalPrice;
      }
   }

   /** @param isTradeIn trade-in status */
   public void setIsTradeIn (boolean isTradeIn){
      this.isTradeIn = isTradeIn;
   }

   /** @param isSold sold status */
   public void setIsSold (boolean isSold){
      this.isSold = isSold;
   }

   /** @param isBought bought status */
   public void setIsBought (boolean isBought){
      this.isBought = isBought;
   }

   /** @param isLease lease status */
   public void setIsLease (boolean isLease){
      this.isLease = isLease;
   }

   /** @param month transaction month */
   public void setMonth (int month){
      if(month>0){
         this.month = month;
      }
   }

   /** @param date transaction day */
   public void setDate (int date){
      if(date>0){
         this.date = date;
      }
   }

   /** @param year transaction year */
   public void setYear (int year){
      if(year>=0){
         this.year = year;
      }
   }

   // /** @param vehicle vehicle involved in the transaction */
   // public void setVehicle (Vehicle vehicle){
   //    this.vehicle = vehicle;
   // }

   /**
    * Constructs a Transaction with customer info, flags, date, and vehicles involved.
    *
    * @param customerName name of the customer
    * @param customerID customer identifier
    * @param finalPrice agreed transaction price ({@code -1} for some trade-ins)
    * @param isTradeIn whether this is a trade-in transaction
    * @param isBought whether the dealership bought a vehicle
    * @param isSold whether the dealership sold a vehicle
    * @param isLease whether this is a lease transaction
    * @param month transaction month
    * @param date transaction day of month
    * @param year transaction year
    * @param obtainedVehicle vehicle obtained by the dealership (may be null)
    * @param soldVehicle vehicle sold by the dealership (may be null)
    */
   public Transaction(String customerName, String customerID, int finalPrice,boolean isTradeIn, boolean isBought,boolean isSold,boolean isLease, int month,int date,int year,Vehicle obtainedVehicle, Vehicle soldVehicle){
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
      this.obtainedVehicle = obtainedVehicle;
      this.soldVehicle = soldVehicle;
   }
   
   /**
    * Calculates the profit made from the transaction.
    *
    * @return profit amount
    */
   public int determineProfitMade(){
      if(finalPrice == -1) {
         return obtainedVehicle.getBasePrice() - soldVehicle.getBasePrice();
      } else if(soldVehicle == null) {
         return obtainedVehicle.getBasePrice() - finalPrice;
      }
      return finalPrice-soldVehicle.getBasePrice();
   }
   
   /**
    * Determines whether the dealership made a positive profit on this transaction.
    * Delegates to {@link #determineProfitMade()}.
    *
    * @return true if profit is greater than zero
    */
   public boolean wasProfitable(){
      return determineProfitMade() > 0;
   }
   
   /**
    * Returns a formatted string containing all transaction details.
    *
    * @return transaction information as a string
    */
   public String toString(){
      String temp = "";
      temp += "Customer Name: "+ customerName+
         "\nCustomer ID: "+customerID+
         "\nFinal Price: "+finalPrice+
         "\nIs TradeIn: "+isTradeIn+
         "\nIs Sold: "+ isSold+
         "\nIs Bought: "+isBought+
         "\nIs Lease: "+isLease+
         "\nMonth: "+ month+
         "\nDate: "+ date+
         "\nYear: "+ year;
         if(soldVehicle != null) temp += "\nVehicle Sold: " + soldVehicle.displayEssentials();
         if(obtainedVehicle != null) temp += "\nVehicle Obtained: " + obtainedVehicle.displayEssentials();
      return temp + "\n";
   }
   

}