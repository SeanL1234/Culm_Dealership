/* 
File Name: GasVehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The GasVehicle class.
*/
public class GasVehicle extends Vehicle{

   public static final int ENGINE_PRICE = 4000;

   private int maxHorsePower;
   private GasSpec gasSpec;

   public int getMaxHorsePower(){
      return maxHorsePower;
   }
   public GasSpec getGasSpec(){
      return gasSpec;
   }

   public void setMaxHorsePower(int maxHorsePower){
      this.maxHorsePower = maxHorsePower;
   }

   public void setGasSpec(GasSpec gasSpec){
      this.gasSpec = gasSpec;
   }


   public GasVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, int maxHorsePower, GasSpec gasSpec){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,gasSpec);
      this.gasSpec = gasSpec;
      this.maxHorsePower = maxHorsePower;
   }

   // calculateMaintenanceFee() pending

   public String toString(){
      return super.toString()+"\nMax Horse Power: "+maxHorsePower;
   }

}