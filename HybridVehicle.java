/* 
File Name: HybridVehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The HybridVehicle class.
*/
public class HybridVehicle extends Vehicle{

   public static final int RECHARGEABLE_PRICE = 80;
   public static final int MODES_PRICE = 40;
   public static final int PLUG_IN_PRICE = 80;
   
   private HybridSpec hybridSpec;
   private boolean isRechargeable;
   private boolean hasModes;
   private boolean hasPlugIn;
   private String chargerType;

   public HybridSpec getHybridSpec(){
      return hybridSpec;
   }
   public boolean getIsRechargeable(){
      return isRechargeable;
   }
   public boolean getHasModes(){
      return hasModes;
   }
   public boolean getHasPlugIn(){
      return hasPlugIn;
   }
   public String getChargerType(){
      return chargerType;
   }
   
   public void setHybridSpec(HybridSpec hybridSpec){
      this.hybridSpec = hybridSpec;
   }
   public void setIsRechargeable(boolean isRechargeable){
      this.isRechargeable = isRechargeable;
   }
   public void setHasModes(boolean hasModes){
      this.hasModes = hasModes;
   }
   public void setHasPlugIn(boolean hasPlugIn){
      this.hasPlugIn = hasPlugIn;
   }
   public void setChargerType(String chargerType){
      this.chargerType = chargerType;
   }
   

   public HybridVehicle (String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, HybridSpec hybridSpec, boolean isRechargeable, boolean hasModes, boolean hasPlugIn, String chargerType){
      super(modelName,modelBrand, typeVehicle, year, basePrice, safetyRating, vin, towRating, typeWheelControl, transmissionType, trimLevel, maxSpeed, numSeats, color, maintenancePeriod, range,hybridSpec);
      this.hybridSpec = hybridSpec;
      this.isRechargeable = isRechargeable;
      this.hasModes = hasModes;
      this.hasPlugIn = hasPlugIn;
      this.chargerType = chargerType;
   }


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


   public String toString(){
      return super.toString()+
         "\nIs Rechargeable: "+isRechargeable+
         "\nHas Modes: "+hasModes+
         "\nhas PlugIn: "+hasPlugIn+
         "\nCharger Type: "+chargerType;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (this == obj) return true;
      if (!(obj instanceof HybridVehicle)) return false;
      HybridVehicle other = (HybridVehicle) obj;
      boolean specEqual = true;
      if (this.hybridSpec == null) {
         specEqual = other.hybridSpec == null;
      } else {
         specEqual = this.hybridSpec.equals(other.hybridSpec, 100.0);
      }
      boolean chargerEqual = (this.chargerType == null) ? other.chargerType == null : this.chargerType.equals(other.chargerType);
      return super.equals(obj) && this.isRechargeable == other.isRechargeable && this.hasModes == other.hasModes && this.hasPlugIn == other.hasPlugIn && chargerEqual && specEqual;
   }

}