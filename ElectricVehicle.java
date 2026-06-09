/**  
* File Name: ElectricVehicle.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
* Description: The ElectricVehicle class represents a fully electric vehicle.
* It extends the Vehicle class and includes electric-specific
* specifications, charging information, and advanced features.
*/
public class ElectricVehicle extends Vehicle{

   // Constants

   /** Additional maintenance cost for the autopilot system. */
   public static final int AUTO_PILOT_PRICE = 50;

   /** Additional maintenance cost for the battery system. */
   public static final int BATTERY_PRICE = 300;

   /** Additional maintenance cost for multiple driving modes. */
   public static final int MODES_PRICE = 50;

   // Fields

   /** Indicates whether the vehicle has an autopilot system. */
   private boolean hasAutoPilot;

   /** Indicates whether the vehicle has multiple driving modes. */
   private boolean hasModes;

   /** Type of charger used by the vehicle. */
   private String chargerType;

   /** Electric vehicle specifications. */
   private ElectricSpec electricSpec;

   // Accessors

   /** @return electric vehicle specifications */
   public ElectricSpec getElectricSpec(){
      return electricSpec;
   }

   /** @return charger type */
   public String getChargerType(){
      return chargerType;
   }

   /** @return whether the vehicle has driving modes */
   public boolean getHasModes(){
      return hasModes;
   }

   /** @return whether the vehicle has autopilot */
   public boolean getHasAutoPilot(){
      return hasAutoPilot;
   }

   // Mutators

   /** @param electricSpec electric vehicle specifications */
   public void setElectricSpec(ElectricSpec electricSpec){
      this.electricSpec = electricSpec;
   }

   /** @param chargerType charger type */
   public void setChargerType(String chargerType){
      this.chargerType = chargerType;
   }

   /** @param hasModes driving modes status */
   public void setHasModes(boolean hasModes){
      this.hasModes = hasModes;
   }

   /** @param hasAutoPilot autopilot status */
   public void setHasAutoPilot(boolean hasAutoPilot){
      this.hasAutoPilot =hasAutoPilot;
   }

   /**
    * Constructs an ElectricVehicle object with general vehicle
    * information and electric-specific features.
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
    * @param electricSpec electric vehicle specifications
    * @param hasAutoPilot autopilot status
    * @param hasModes driving modes status
    * @param chargerType charger type
    */
   public ElectricVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, ElectricSpec electricSpec, boolean hasAutoPilot,boolean hasModes,String chargerType){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,electricSpec);
      this.electricSpec = electricSpec;
      this.hasAutoPilot = hasAutoPilot;
      this.hasModes = hasModes;
      this.chargerType = chargerType;
   }

   /**
    * Calculates the maintenance fee for the electric vehicle.
    *
    * @return total maintenance fee
    */
   public int calculateMaintenanceFee(){
      int temp = 0;
      if(hasAutoPilot){
         temp+=AUTO_PILOT_PRICE;
      }
      if(hasModes){
         temp+=MODES_PRICE;
      }
      return electricSpec.getBaseMaintenanceFee()+BATTERY_PRICE+temp;
   }
   
   /**
    * Returns a formatted string containing all electric vehicle
    * information and features.
    *
    * @return electric vehicle details as a string
    */
   public String toString(){
      return super.toString()+
         "Has Autopilot: "+hasAutoPilot+
         "\nHas Modes: "+hasModes+
         "\nCharger Type: "+chargerType + "\n";
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (this == obj) return true;
      if (!(obj instanceof ElectricVehicle)) return false;
      ElectricVehicle other = (ElectricVehicle) obj;
      boolean specEqual = true;
      if (this.electricSpec == null) {
         specEqual = other.electricSpec == null;
      } else {
         specEqual = this.electricSpec.equals(other.electricSpec, 100.0);
      }
      boolean chargerEqual = (this.chargerType == null) ? other.chargerType == null : this.chargerType.equals(other.chargerType);
      return super.equals(obj) && this.hasAutoPilot == other.hasAutoPilot && this.hasModes == other.hasModes && chargerEqual && specEqual;
   }
}