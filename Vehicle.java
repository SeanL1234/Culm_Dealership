/* 
File Name: Vehicle.java
Name: Tommy Xiao
Class: ICS4U1-21
Date: May 28, 2026
Description: The Vehicle class serves as an abstract superclass for all vehicle types.
It stores common vehicle information such as model details, pricing,
specifications, performance data, and maintenance information.
*/
abstract class Vehicle {

   // Constants

   /** Percentage range used to determine whether a vehicle is considered cheap. */
   public static final double CHEAP_RANGE = 0.2;

   /** Minimum number of seats required for a vehicle to be family-friendly. */
   public static final int FF_NUMSEATS = 5;

   /** Minimum safety rating required for a vehicle to be family-friendly. */
   public static final int FF_SAFETYRATING = 8;

   /** Required wheel control type for a vehicle to be family-friendly. */
   public static final String FF_WHEELCONTROL = "AWD";
   
   // Fields

   /** Vehicle model name. */
   private String modelName;

   /** Vehicle manufacturer brand. */
   private String modelBrand;

   /** Vehicle category or type. */
   private String typeVehicle;

   /** Vehicle model year. */
   private int year;

   /** Vehicle base price. */
   private int basePrice;

   /** Vehicle safety rating. */
   private int safetyRating;

   /** Vehicle Identification Number. */
   private String vin;

   /** Vehicle towing capacity. */
   private int towRating;

   /** Wheel control system (AWD, FWD, RWD, etc.). */
   private String typeWheelControl;

   /** Transmission type. */
   private String transmissionType;

   /** Vehicle trim level. */
   private String trimLevel;

   /** Maximum speed of the vehicle. */
   private int maxSpeed;

   /** Number of seats. */
   private int numSeats;

   /** Vehicle color. */
   private String color;

   /** Recommended maintenance interval. */
   private String maintenancePeriod;

   /** Driving range of the vehicle. */
   private int range;

   /** Technical specifications of the vehicle. */
   private Spec vehicleSpec;
   
   // Accessors

   /** @return vehicle model name */
   public String getModelName(){
      return modelName;
   }

   /** @return vehicle brand */
   public String getModelBrand(){
      return modelBrand;
   }

   /** @return vehicle type */
   public String getTypeVehicle(){
      return typeVehicle;
   }
   
   /** @return model year */
   public int getYear(){
      return year;
   }
   
   /** @return base price */
   public int getBasePrice() {
      return basePrice;
   }
   
   /** @return safety rating */
   public int getSafetyRating(){
      return safetyRating;
   }
   
   /** @return VIN */
   public String getVin(){
      return vin;
   }
   
   /** @return tow rating */
   public int getTowRating(){
      return towRating;
   }
   
   /** @return wheel control type */
   public String getTypeWheelControl(){
      return typeWheelControl;
   }
   
   /** @return transmission type */
   public String getTransmissionType(){
      return transmissionType;
   }
   
   /** @return trim level */
   public String getTrimLevel(){
      return trimLevel;
   }
   
   /** @return maximum speed */
   public int getMaxSpeed(){
      return maxSpeed;
   }
   
   /** @return number of seats */
   public int getNumSeats(){
      return numSeats;
   }
   
   /** @return vehicle color */
   public String getColor(){
      return color;
   }
   
   /** @return maintenance period */
   public String getMaintenancePeriod(){
      return maintenancePeriod;
   }
   
   /** @return driving range */
   public int getRange(){
      return range;
   }

   /** @return vehicle specifications */
   public Spec getVehicleSpec() {
      return vehicleSpec;
   }
   
   // Mutators

   /** @param modelName vehicle model name */
   public void setModelName(String modelName){
      this.modelName = modelName;
   }
   
   /** @param modelBrand vehicle brand */
   public void setModelBrand(String modelBrand){
      this.modelBrand = modelBrand;
   }
   
   /** @param typeVehicle vehicle type */
   public void setTypeVehicle(String typeVehicle){
      this.typeVehicle = typeVehicle;
   }
   
   /** @param year model year */
   public void setYear(int year){
      this.year = year;
   }
   
   /** @param basePrice vehicle base price */
   public void setBasePrice(int basePrice){
      this.basePrice = basePrice;
   }
   
   /** @param safetyRating safety rating */
   public void setSafetyRating(int safetyRating){
      this.safetyRating = safetyRating;
   }
   
   /** @param vin vehicle identification number */
   public void setVin(String vin){
      this.vin = vin;
   }
   
   /** @param towRating towing capacity */
   public void setTowRating(int towRating){
      this.towRating = towRating;
   }
   
   /** @param typeWheelControl wheel control type */
   public void setTypeWheelControl(String typeWheelControl){
      this.typeWheelControl = typeWheelControl;
   }
   
   /** @param transmissionType transmission type */
   public void setTransmissionType(String transmissionType){
      this.transmissionType = transmissionType;
   }
   
   /** @param trimLevel vehicle trim level */
   public void setTrimLevel(String trimLevel){
      this.trimLevel = trimLevel;
   }
   
   /** @param maxSpeed maximum speed */
   public void setMaxSpeed(int maxSpeed){
      this.maxSpeed = maxSpeed;
   }
   
   /** @param numSeats number of seats */
   public void setNumSeats(int numSeats){
      this.numSeats = numSeats;
   }
   
   /** @param color vehicle color */
   public void setColor(String color){
      this.color = color;
   }
   
   /** @param maintenancePeriod maintenance interval */
   public void setMaintenancePeriod(String maintenancePeriod){
      this.maintenancePeriod = maintenancePeriod;
   }
   
   /** @param range vehicle driving range */
   public void setRange(int range){
      this.range = range;
   }
   
   /** @param vehicleSpec vehicle specifications */
   public void setVehicleSpec(Spec vehicleSpec){
      this.vehicleSpec = vehicleSpec;
   }
   
   
   // Constructor

   /**
    * Constructs a Vehicle object with all vehicle information.
    *
    * @param modelName vehicle model name
    * @param modelBrand manufacturer brand
    * @param typeVehicle vehicle type
    * @param year model year
    * @param basePrice vehicle base price
    * @param safetyRating safety rating
    * @param vin vehicle identification number
    * @param towRating towing capacity
    * @param typeWheelControl wheel control system
    * @param transmissionType transmission type
    * @param trimLevel trim level
    * @param maxSpeed maximum speed
    * @param numSeats number of seats
    * @param color vehicle color
    * @param maintenancePeriod maintenance interval
    * @param range driving range
    * @param vehicleSpec technical specifications
    */
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
   
   /**
    * Compares this vehicle with another vehicle for equality.
    *
    * @param obj object being compared
    * @return true if all attributes are equal, false otherwise
    */
   public boolean equals(Object obj){
      if(obj == null){
         return false;
      }
      if(this == obj){
         return true;
      }
      if(!(obj instanceof Vehicle)){
         return false;
      }else{
         Vehicle other = (Vehicle) obj;
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
            this.vehicleSpec.equals(other.vehicleSpec,100);
            // Using the equals methoeds in Spec !!!
      }
   }
   
   /**
    * Compares the prices of two vehicles.
    *
    * @param other vehicle being compared
    * @return positive if more expensive, negative if cheaper, 0 if equal
    */
   public int compareToPrice(Vehicle other){
      return this.basePrice-other.basePrice;
   }
   
   /**
    * Determines whether the vehicle is considered cheap.
    *
    * @return true if expected price is within the cheap range
    */
   public boolean isCheap(){
      return vehicleSpec.calculateExpectedPrice(basePrice) < basePrice + (basePrice*CHEAP_RANGE);
   }
   
   /**
    * Determines whether the vehicle is family-friendly.
    *
    * @return true if seating, safety, and wheel control requirements are met
    */
   public boolean isFamilyFriendly(){
      return numSeats >=FF_NUMSEATS && safetyRating>=FF_SAFETYRATING && typeWheelControl.equals(FF_WHEELCONTROL);
   }
   
   /**
    * Displays the most important vehicle information.
    *
    * @return formatted string containing essential vehicle details
    */
   public String displayEssentials(){
      return "VIN: "+vin +"\nModel: "+ modelName +"\nBrand: "+ modelBrand +"\nYear: "+ year +"\nColor: "+color;
   }
   
   /**
    * Calculates the maintenance fee for the vehicle.
    *
    * @return maintenance fee
    */
   abstract int calculateMaintenanceFee();
   
   /**
    * Returns a formatted string containing all vehicle information.
    *
    * @return vehicle details as a string
    */
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