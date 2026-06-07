/**  
* File Name: Transaction.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
* Description: The Transaction class represents a vehicle transaction between
* a customer and the dealership. It stores customer information,
* transaction details, date information, and the vehicle involved.
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

   /** Vehicle involved in the transaction. */
   private Vehicle vehicle;
   
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

   /** @return vehicle involved in the transaction */
   public Vehicle getVehicle(){
      return vehicle;
   }
   /**
    * Returns the vehicle involved in this transaction.
    * @return Vehicle object
    */
   

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

   /** @param vehicle vehicle involved in the transaction */
   public void setVehicle (Vehicle vehicle){
      this.vehicle = vehicle;
   }
   /**
    * Sets the vehicle involved in this transaction.
    * @param vehicle Vehicle object
    */
   
   /**
    * Constructs a Transaction object with customer, vehicle,
    * transaction details, and date information.
    *
    * @param customer customer involved in the transaction
    * @param finalPrice final transaction price
    * @param isTradeIn whether a trade-in occurred
    * @param isBought whether the vehicle was bought
    * @param isSold whether the vehicle was sold
    * @param isLease whether the vehicle was leased
    * @param month transaction month
    * @param date transaction day
    * @param year transaction year
    * @param vehicle vehicle involved in the transaction
    */
   public Transaction(Customer customer, int finalPrice,boolean isTradeIn, boolean isBought,boolean isSold,boolean isLease, int month,int date,int year,Vehicle vehicle){
      this.customerName = customer.getName();
      this.customerID = customer.getId();
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
    * Calculates the profit made from the transaction.
    *
    * @return profit amount
    */
   public int determineProfitMade(){
      return finalPrice-vehicle.getBasePrice();
   }
   
   /**
    * Determines whether the transaction was profitable.
    *
    * @return true if final price exceeds base price
    */
   public boolean wasProfitable(){
      return finalPrice>vehicle.getBasePrice();
   }
   
   /**
    * Returns a formatted string containing all transaction details.
    *
    * @return transaction information as a string
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