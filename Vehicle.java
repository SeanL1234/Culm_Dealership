/* 
File Name: Vehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The Vehicle class.
*/
abstract class Vehicle {

   // Constans
   public static final double CHEAP_RANGE = 0.2;
   public static final int FF_NUMSEATS = 5;
   public static final int FF_SAFETYRATING = 8;
   public static final String FF_WHEELCONTROL = "AWD";
   
   // Fields
   private String modelName;
   private String modelBrand;
   private String typeVehicle;
   private int year;
   private int basePrice;
   private int safetyRating;
   private String vin;
   private int towRating;
   private String typeWheelControl;
   private String transmissionType;
   private String trimLevel;
   private int maxSpeed;
   private int numSeats;
   private String color;
   private String maintenancePeriod;
   private int range;
   private Spec vehicleSpec;
   
   // Accessors
   public String getModelName(){
      return modelName;
   }

   public String getModelBrand(){
      return modelBrand;
   }

   public String getTypeVehicle(){
      return typeVehicle;
   }
   
   public int getYear(){
      return year;
   }
   
   public int getBasePrice() {
      return basePrice;
   }
   
   public int getSafetyRating(){
      return safetyRating;
   }
   
   public String getVin(){
      return vin;
   }
   
   public int getTowRating(){
      return towRating;
   }
   
   public String getTypeWheelControl(){
      return typeWheelControl;
   }
   
   public String getTransmissionType(){
      return transmissionType;
   }
   
   public String getTrimLevel(){
      return trimLevel;
   }
   
   public int getMaxSpeed(){
      return maxSpeed;
   }
   
   public int getNumSeats(){
      return numSeats;
   }
   
   public String getColor(){
      return color;
   }
   
   public String getMaintenancePeriod(){
      return maintenancePeriod;
   }
   
   public int getRange(){
      return range;
   }

   public Spec getVehicleSpec() {
      return vehicleSpec;
   }
   
   // Mutators
   public void setModelName(String modelName){
      this.modelName = modelName;
   }
   
   public void setModelBrand(String modelBrand){
      this.modelBrand = modelBrand;
   }
   
   public void setTypeVehicle(String typeVehicle){
      this.typeVehicle = typeVehicle;
   }
   
   public void setYear(int year){
      this.year = year;
   }
   
   public void setBasePrice(int basePrice){
      this.basePrice = basePrice;
   }
   
   public void setSafetyRating(int safetyRating){
      this.safetyRating = safetyRating;
   }
   
   public void setVin(String vin){
      this.vin = vin;
   }
   
   public void setTowRating(int towRating){
      this.towRating = towRating;
   }
   
   public void setTypeWheelControl(String typeWheelControl){
      this.typeWheelControl = typeWheelControl;
   }
   
   public void setTransmissionType(String transmissionType){
      this.transmissionType = transmissionType;
   }
   
   public void setTrimLevel(String trimLevel){
      this.trimLevel = trimLevel;
   }
   
   public void setMaxSpeed(int maxSpeed){
      this.maxSpeed = maxSpeed;
   }
   
   public void setNumSeats(int numSeats){
      this.numSeats = numSeats;
   }
   
   public void setColor(String color){
      this.color = color;
   }
   
   public void setMaintenancePeriod(String maintenancePeriod){
      this.maintenancePeriod = maintenancePeriod;
   }
   
   public void setRange(int range){
      this.range = range;
   }
   
   public void setVehicleSpec(Spec vehicleSpec){
      this.vehicleSpec = vehicleSpec;
   }
   
   
   // Constructor 
   public Vehicle(String modelName, String modelBrand, String typeVehicle, int year, int basePrice, int safetyRating, String vin, int towRating, String typeWheelControl, String transmissionType, String trimLevel, int maxSpeed, int numSeats, String color, String maintenancePeriod, int range, Spec vehicleSpec){
      this.modelName = modelName;
      this.modelBrand = modelBrand;
      this.typeVehicle = typeVehicle;
      this.year = year;
      this.basePrice = basePrice;
      this.safetyRating = safetyRating;
      this.vin = vin;
      this.towRating = towRating;
      this.typeWheelControl = typeWheelControl;
      this.transmissionType = transmissionType;
      this.trimLevel = trimLevel;
      this.maxSpeed = maxSpeed;
      this.numSeats = numSeats;
      this.color = color;
      this.maintenancePeriod = maintenancePeriod;
      this.range = range;
      this.vehicleSpec = vehicleSpec;
   }
   
   public boolean equals(Vehicle other){
      if(other == null){
         return false;
      }
      if(this == other){
         return true;
      }
      if(!(other instanceof Vehicle)){
         return false;
      }else{
         return this.modelName.equals(other.modelName) &&
            this.modelBrand.equals(modelBrand) &&
            this.typeVehicle.equals(other.typeVehicle)&&
            this.year == other.year&&
            this.basePrice == other.basePrice&&
            this.safetyRating == other.safetyRating&&
            this.vin.equals(other.vin)&&
            this.towRating == other.towRating&&
            this.typeWheelControl.equals(other.typeWheelControl)&&
            this.transmissionType.equals(other.transmissionType)&&
            this.trimLevel.equals(other.trimLevel)&&
            this.maxSpeed == other.maxSpeed&&
            this.numSeats == other.numSeats&&
            this.color.equals(other.color)&&
            this.maintenancePeriod.equals(other.maintenancePeriod)&&
            this.range == other.range&&
            this.vehicleSpec.equals(other.vehicleSpec);
            // Using the equals methoeds in Spec !!!
      }
   }
   
   public int compareToPrice(Vehicle other){
      return this.basePrice-other.basePrice;
   }
   
   public boolean isCheap(){
      return vehicleSpec.calculateExpectedPrice()<basePrice+(basePrice*CHEAP_RANGE);
   }
   
   //!WIP need typeWheelControl
   public boolean isFamilyFriendly(){
      return numSeats >=FF_NUMSEATS && safetyRating>=FF_SAFETYRATING && typeWheelControl.equals(FF_WHEELCONTROL);
   }
   
   //!WIP might need more
   public String displayEssentials(){
      return "VIN: "+vin +"\nModel: "+ modelName +"\nBrand: "+ modelBrand +"\nYear: "+ year +"\nColor: "+color;
   }
   
   abstract int calculateMaintenanceFee();
   
   public String toString(){
      return "Model: "+ modelName+
         "\nBrand: "+modelBrand+
         "\nType Vehicle: "+ typeVehicle+
         "\nYear: "+ year+
         "\nBase Price: "+basePrice+
         "\nSafety Rating: "+safetyRating+
         "\nVIN:"+vin+
         "\nTow Rating: "+towRating+
         "\nWheel Control: "+typeWheelControl+
         "\nTransmission Type: "+transmissionType+
         "\nTrim Level: "+trimLevel+
         "\nMax Speed: "+maxSpeed+
         "\nNum Seats: "+numSeats+
         "\nColor:"+color+
         "\nMaintenance Period: "+ maintenancePeriod+
         "\nRange: "+range+
         "\nVehicle Spec: "+vehicleSpec;
   
   }
   
   
   
   
   
   
   
   
   
   
   
}