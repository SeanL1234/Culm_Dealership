/* 
File Name: GasVehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The GasVehicle class.
*/
public class GasVehicle extends Vehicle{

   public static final int ENGINE_PRICE = 400;

   private int maxHorsePower;
   private GasSpec gasSpec;

      /**
       * Returns the maximum horsepower of this gas vehicle.
       * @return horsepower
       */
   public int getMaxHorsePower(){
      return maxHorsePower;
   }
      /**
       * Returns the GasSpec instance for this vehicle.
       * @return GasSpec
       */
   public GasSpec getGasSpec(){
      return gasSpec;
   }
      /**
       * Set the maximum horsepower.
       * @param maxHorsePower horsepower value
       */

   public void setMaxHorsePower(int maxHorsePower){
      this.maxHorsePower = maxHorsePower;
      /**
       * Set the GasSpec for this vehicle.
       * @param gasSpec GasSpec instance
       */
   }

   public void setGasSpec(GasSpec gasSpec){
      this.gasSpec = gasSpec;
   }
      /**
       * Calculate maintenance fee for a gas vehicle.
       * @return maintenance fee
       */


   public GasVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, int maxHorsePower, GasSpec gasSpec){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,gasSpec);
      this.gasSpec = gasSpec;
      this.maxHorsePower = maxHorsePower;
   }

      /**
       * Equality check includes superclass fields, horsepower and spec.
       */
   public int calculateMaintenanceFee(){
      return gasSpec.getBaseMaintenanceFee()+ENGINE_PRICE;
   }
   
   public String toString(){
      return super.toString()+
         "\nMax Horse Power: "+maxHorsePower;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (this == obj) return true;
      if (!(obj instanceof GasVehicle)) return false;
      GasVehicle other = (GasVehicle) obj;
      boolean specEqual = true;
      if (this.gasSpec == null) {
         specEqual = other.gasSpec == null;
      } else {
         specEqual = this.gasSpec.equals(other.gasSpec, 100.0);
      }
      return super.equals(obj) && this.maxHorsePower == other.maxHorsePower && specEqual;
   }

}