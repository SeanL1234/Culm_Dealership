/* 
File Name: ElectricVehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The ElectricVehicle class.
*/
public class ElectricVehicle extends Vehicle{
   public static final int AUTO_PILOT_PRICE = 400;
   public static final int BATTERY_PRICE = 10000;
   public static final int MODES_PRICE = 1600;

   private boolean hasAutoPilot;
   private boolean hasModes;
   private String chargerType;
   private ElectricSpec electricSpec;

   public ElectricSpec getElectricSpec(){
      return electricSpec;
   }
   public String getChargerType(){
      return chargerType;
   }
   public boolean getHasModes(){
      return hasModes;
   }
   public boolean getHasAutoPilot(){
      return hasAutoPilot;
   }

   public void setElectricSpec(ElectricSpec electricSpec){
      this.electricSpec = electricSpec;
   }
   public void setChargerType(String chargerType){
      this.chargerType = chargerType;
   }
   public void setHasModes(boolean hasModes){
      this.hasModes = hasModes;
   }
   public void setHasAutoPilot(boolean hasAutoPilot){
      this.hasAutoPilot =hasAutoPilot;
   }


   public ElectricVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, ElectricSpec electricSpec, boolean hasAutoPilot,boolean hasModes,String chargerType){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,electricSpec);
      this.electricSpec = electricSpec;
      this.hasAutoPilot = hasAutoPilot;
      this.hasModes = hasModes;
      this.chargerType = chargerType;
   }

   // calculateMaintenanceFee() pending

   public String toString(){
      return super.toString()+"\nHas Autopilot: "+hasAutoPilot+"\nHas Modes: "+hasModes+"\nCharger Type: "+chargerType;
   }
}