/** 
* File Name: GasVehicle.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
* Description: The GasVehicle class represents a gasoline-powered vehicle.
* It extends the Vehicle class and includes gas-specific
* specifications and engine performance information.
*/
public class GasVehicle extends Vehicle{

   // Constants

   /** Additional maintenance cost associated with the engine. */
   public static final int ENGINE_PRICE = 400;

   // Fields

   /** Maximum horsepower produced by the vehicle. */
   private int maxHorsePower;

   /** Gas vehicle specifications. */
   private GasSpec gasSpec;

   // Accessors

   /** @return maximum horsepower */
   public int getMaxHorsePower(){
      return maxHorsePower;
   }

   /** @return gas vehicle specifications */
   public GasSpec getGasSpec(){
      return gasSpec;
   }

   // Mutators

   /** @param maxHorsePower maximum horsepower */
   public void setMaxHorsePower(int maxHorsePower){
      this.maxHorsePower = maxHorsePower;
   }

   /** @param gasSpec gas vehicle specifications */
   public void setGasSpec(GasSpec gasSpec){
      this.gasSpec = gasSpec;
   }

   /**
    * Constructs a GasVehicle object with general vehicle
    * information and gas-specific specifications.
    *
    * @param modelName vehicle model name
    * @param modelBrand manufacturer brand
    * @param typeVehicle vehicle type
    * @param year model year
    * @param basePrice base price
    * @param safetyRating safety rating
    * @param vin vehicle identification number
    * @param towRating towing capacity
    * @param typeWheelControl wheel control type
    * @param transmissionType transmission type
    * @param trimLevel trim level
    * @param maxSpeed maximum speed
    * @param numSeats number of seats
    * @param color vehicle color
    * @param maintenancePeriod maintenance interval
    * @param range driving range
    * @param maxHorsePower maximum horsepower
    * @param gasSpec gas vehicle specifications
    */
   public GasVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, int maxHorsePower, GasSpec gasSpec){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,gasSpec);
      this.gasSpec = gasSpec;
      this.maxHorsePower = maxHorsePower;
   }

   /**
    * Calculates the maintenance fee for the gas vehicle.
    *
    * @return total maintenance fee
    */
   public int calculateMaintenanceFee(){
      return gasSpec.getBaseMaintenanceFee()+ENGINE_PRICE;
   }
   
   /**
    * Returns a formatted string containing all gas vehicle
    * information and specifications.
    *
    * @return gas vehicle details as a string
    */
   public String toString(){
      return super.toString()+
         "\nMax Horse Power: "+maxHorsePower;
   }

}