/* * 
* File Name: HybridVehicle.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
* Description: The HybridVehicle class represents a hybrid vehicle that combines
* features of both gasoline and electric-powered vehicles.
* It extends the Vehicle class and includes hybrid-specific
* specifications and charging features.
*/
public class HybridVehicle extends Vehicle{

   // Constants

   /** Additional maintenance cost for rechargeable systems. */
   public static final int RECHARGEABLE_PRICE = 80;

   /** Additional maintenance cost for multiple driving modes. */
   public static final int MODES_PRICE = 40;

   /** Additional maintenance cost for plug-in systems. */
   public static final int PLUG_IN_PRICE = 80;
   
   // Fields

   /** Hybrid vehicle specifications. */
   private HybridSpec hybridSpec;

   /** Indicates whether the vehicle is rechargeable. */
   private boolean isRechargeable;

   /** Indicates whether the vehicle has multiple driving modes. */
   private boolean hasModes;

   /** Indicates whether the vehicle supports plug-in charging. */
   private boolean hasPlugIn;

   /** Type of charger used by the vehicle. */
   private String chargerType;

   // Accessors

   /** @return hybrid vehicle specifications */
   public HybridSpec getHybridSpec(){
      return hybridSpec;
   }

   /** @return rechargeable status */
   public boolean getIsRechargeable(){
      return isRechargeable;
   }

   /** @return whether the vehicle has driving modes */
   public boolean getHasModes(){
      return hasModes;
   }

   /** @return plug-in status */
   public boolean getHasPlugIn(){
      return hasPlugIn;
   }

   /** @return charger type */
   public String getChargerType(){
      return chargerType;
   }
   
   // Mutators

   /** @param hybridSpec hybrid specifications */
   public void setHybridSpec(HybridSpec hybridSpec){
      this.hybridSpec = hybridSpec;
   }

   /** @param isRechargeable rechargeable status */
   public void setIsRechargeable(boolean isRechargeable){
      this.isRechargeable = isRechargeable;
   }

   /** @param hasModes driving modes status */
   public void setHasModes(boolean hasModes){
      this.hasModes = hasModes;
   }

   /** @param hasPlugIn plug-in charging status */
   public void setHasPlugIn(boolean hasPlugIn){
      this.hasPlugIn = hasPlugIn;
   }

   /** @param chargerType charger type */
   public void setChargerType(String chargerType){
      this.chargerType = chargerType;
   }
   
   /**
    * Constructs a HybridVehicle object with general vehicle
    * information and hybrid-specific features.
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
    * @param hybridSpec hybrid specifications
    * @param isRechargeable rechargeable status
    * @param hasModes driving modes status
    * @param hasPlugIn plug-in charging status
    * @param chargerType charger type
    */
   public HybridVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, HybridSpec hybridSpec, boolean isRechargeable, boolean hasModes, boolean hasPlugIn, String chargerType){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,hybridSpec);
      this.hybridSpec = hybridSpec;
      this.isRechargeable = isRechargeable;
      this.hasModes = hasModes;
      this.hasPlugIn = hasPlugIn;
      this.chargerType = chargerType;
   }

   /**
    * Calculates the maintenance fee for the hybrid vehicle
    * based on its features and hybrid specifications.
    *
    * @return total maintenance fee
    */
   public int calculateMaintenanceFee(){
      int temp = 0;
      if(isRechargeable){
         temp+=RECHARGEABLE_PRICE;
      }
      if(hasModes){
         temp+=MODES_PRICE;
      }
      if(hasPlugIn){
         temp+=PLUG_IN_PRICE;
      }
      return hybridSpec.getBaseMaintenanceFee()+temp;
   }

   /**
    * Returns a formatted string containing all hybrid vehicle
    * information and features.
    *
    * @return hybrid vehicle details as a string
    */
   public String toString(){
      return super.toString()+
         "\nIs Rechargeable: "+isRechargeable+
         "\nHas Modes: "+hasModes+
         "\nhas PlugIn: "+hasPlugIn+
         "\nCharger Type: "+chargerType;
   }

}