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
   
   public String getCustomerID(){
      return customerID;
   }
   
   public int getFinalPrice(){
      return finalPrice;
   }
   
   public boolean getIsTradeIn(){
      return isTradeIn;
   }
   
   public boolean getIsSold(){
      return isSold;
   }
   
   public boolean getIsBought(){
      return isBought;
   }
   
   public boolean getIsLease(){
      return isLease;
   }
   
   public int getMonth(){
      return month;
   }
   
   public int getDate(){
      return date;
   }
   
   public int getYear(){
      return year;
   }
   
   public Vehicle getVehicle(){
      return vehicle;
   }
   

   // Mutators
   public void setcustomerName(String customerName){
      this.customerName = customerName;
   }
   
   public void setCustomerID (String customerID){
      this.customerID = customerID;
   }
   public void setFinalPrice (int finalPrice){
      this.finalPrice = finalPrice;
   }
   public void setIsTradeIn (boolean isTradeIn){
      this.isTradeIn = isTradeIn;
   }
   public void setIsSold (boolean isSold){
      this.isSold = isSold;
   }
   public void setIsBought (boolean isBought){
      this.isBought = isBought;
   }
   public void setIsLease (boolean isLease){
      this.isLease = isLease;
   }
   public void setMonth (int month){
      this.month = month;
   }
   public void setDate (int date){
      this.date = date;
   }
   public void setYear (int year){
      this.year = year;
   }
   public void setVehicle (Vehicle vehicle){
      this.vehicle = vehicle;
   }
   
   
   
   // Constructor  !WIP need Customer
   public Transaction(Customer customer, int finalPrice,boolean isTradeIn, boolean isBought,boolean isSold,boolean isLease, int month,int date,int year,Vehicle vehicle){
      this.customerName = customer.name;
      this.customerID = customer.id;
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
   
   
   
   
   
   
   
   
}