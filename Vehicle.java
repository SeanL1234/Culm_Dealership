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
   /**
    * Returns the model name of the vehicle.
    * @return model name
    */

   public String getModelBrand(){
      return modelBrand;
   }
   /**
    * Returns the manufacturer/brand of the vehicle.
    * @return brand name
    */

   public String getTypeVehicle(){
      return typeVehicle;
   }
   /**
    * Returns the type/category of the vehicle.
    * @return type description
    */
   
   public int getYear(){
      return year;
   }
   /**
    * Returns the manufacture year of the vehicle.
    * @return year
    */
   
   public int getBasePrice() {
      return basePrice;
   }
   /**
    * Returns the base price of the vehicle.
    * @return base price in whole dollars
    */
   
   public int getSafetyRating(){
      return safetyRating;
   }
   /**
    * Returns the safety rating of the vehicle.
    * @return safety rating
    */
   
   public String getVin(){
      return vin;
   }
   /**
    * Returns the VIN of the vehicle.
    * @return vehicle identification number
    */
   
   public int getTowRating(){
      return towRating;
   }
   /**
    * Returns the towing capacity rating.
    * @return tow rating
    */
   
   public String getTypeWheelControl(){
      return typeWheelControl;
   }
   /**
    * Returns the wheel control type (e.g., AWD).
    * @return wheel control type
    */
   
   public String getTransmissionType(){
      return transmissionType;
   }
   /**
    * Returns the transmission type.
    * @return transmission type string
    */
   
   public String getTrimLevel(){
      return trimLevel;
   }
   /**
    * Returns the trim level of the vehicle.
    * @return trim level
    */
   
   public int getMaxSpeed(){
      return maxSpeed;
   }
   /**
    * Returns the maximum speed of the vehicle.
    * @return max speed in km/h (or configured unit)
    */
   
   public int getNumSeats(){
      return numSeats;
   }
   /**
    * Returns the number of seats in the vehicle.
    * @return number of seats
    */
   
   public String getColor(){
      return color;
   }
   /**
    * Returns the vehicle's color.
    * @return color string
    */
   
   public String getMaintenancePeriod(){
      return maintenancePeriod;
   }
   /**
    * Returns the maintenance interval description.
    * @return maintenance period string
    */
   
   public int getRange(){
      return range;
   }
   /**
    * Returns the vehicle's range (e.g., km for electric vehicles).
    * @return range value
    */

   public Spec getVehicleSpec() {
      return vehicleSpec;
   }
   /**
    * Returns the specification object describing this vehicle.
    * @return Spec instance
    */
   
   // Mutators
   public void setModelName(String modelName){
      this.modelName = modelName;
   }
   /**
    * Set the model name.
    * @param modelName model name
    */
   
   public void setModelBrand(String modelBrand){
      this.modelBrand = modelBrand;
   }
   /**
    * Set the model brand.
    * @param modelBrand brand name
    */
   
   public void setTypeVehicle(String typeVehicle){
      this.typeVehicle = typeVehicle;
   }
   /**
    * Set the type of vehicle.
    * @param typeVehicle type string
    */
   
   public void setYear(int year){
      this.year = year;
   }
   /**
    * Set the manufacture year.
    * @param year year
    */
   
   public void setBasePrice(int basePrice){
      this.basePrice = basePrice;
   }
   /**
    * Set the base price.
    * @param basePrice price in whole dollars
    */
   
   public void setSafetyRating(int safetyRating){
      this.safetyRating = safetyRating;
   }
   /**
    * Set the safety rating.
    * @param safetyRating rating value
    */
   
   public void setVin(String vin){
      this.vin = vin;
   }
   /**
    * Set the vehicle VIN.
    * @param vin vehicle identification number
    */
   
   public void setTowRating(int towRating){
      this.towRating = towRating;
   }
   /**
    * Set the tow rating.
    * @param towRating tow rating
    */
   
   public void setTypeWheelControl(String typeWheelControl){
      this.typeWheelControl = typeWheelControl;
   }
   /**
    * Set the wheel control type.
    * @param typeWheelControl control type
    */
   
   public void setTransmissionType(String transmissionType){
      this.transmissionType = transmissionType;
   }
   /**
    * Set the transmission type.
    * @param transmissionType transmission string
    */
   
   public void setTrimLevel(String trimLevel){
      this.trimLevel = trimLevel;
   }
   /**
    * Set the trim level.
    * @param trimLevel trim string
    */
   
   public void setMaxSpeed(int maxSpeed){
      this.maxSpeed = maxSpeed;
   }
   /**
    * Set the maximum speed.
    * @param maxSpeed max speed
    */
   
   public void setNumSeats(int numSeats){
      this.numSeats = numSeats;
   }
   /**
    * Set the number of seats.
    * @param numSeats seat count
    */
   
   public void setColor(String color){
      this.color = color;
   }
   /**
    * Set the color.
    * @param color color string
    */
   
   public void setMaintenancePeriod(String maintenancePeriod){
      this.maintenancePeriod = maintenancePeriod;
   }
   /**
    * Set the maintenance period description.
    * @param maintenancePeriod maintenance string
    */
   
   public void setRange(int range){
      this.range = range;
   }
   /**
    * Set the vehicle range.
    * @param range range value
    */
   
   public void setVehicleSpec(Spec vehicleSpec){
      this.vehicleSpec = vehicleSpec;
   }
   /**
    * Set the Spec object describing this vehicle.
    * @param vehicleSpec Spec instance
    */
   
   
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
            this.vehicleSpec.equals(other.vehicleSpec);
            // Using the equals methoeds in Spec !!!
      }
   }
   /**
    * Compares this vehicle with another for equality based on fields and spec.
    * @param obj other object to compare
    * @return true if equal
    */
   
   public int compareToPrice(Vehicle other){
      return this.basePrice-other.basePrice;
   }
   /**
    * Compare by base price.
    * @param other other vehicle
    * @return positive if this is more expensive, negative if cheaper
    */
   
   public boolean isCheap(){
      return vehicleSpec.calculateExpectedPrice(basePrice) < basePrice + (basePrice*CHEAP_RANGE);
   }
   /**
    * Determine whether the vehicle is considered cheap based on expected price.
    * @return true if cheap
    */
   
   //!WIP need typeWheelControl
   public boolean isFamilyFriendly(){
      return numSeats >=FF_NUMSEATS && safetyRating>=FF_SAFETYRATING && typeWheelControl.equals(FF_WHEELCONTROL);
   }
   /**
    * Returns whether the vehicle is family-friendly based on seats, safety and wheel control.
    * @return true if family-friendly
    */
   
   //!WIP might need more
   public String displayEssentials(){
      return "VIN: "+vin +"\nModel: "+ modelName +"\nBrand: "+ modelBrand +"\nYear: "+ year +"\nColor: "+color;
   }
   /**
    * Display essential info (VIN, model, brand, year, color).
    * @return short description string
    */
   
   abstract int calculateMaintenanceFee();
   /**
    * Calculate the maintenance fee for this vehicle. Implementation is subclass-specific.
    * @return maintenance fee in whole dollars
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