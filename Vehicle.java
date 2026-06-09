/** 
* File Name: Vehicle.java
* Name: Tommy Xiao
* Class: ICS4U1-21
* Date: May 28, 2026
* Description: The Vehicle class serves as an abstract superclass for all vehicle types.
* It stores common vehicle information such as model details, pricing,
* specifications, performance data, and maintenance information.
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
   /**
    * Returns the model name of the vehicle.
    * @return model name
    */

   /** @return vehicle brand */
   public String getModelBrand(){
      return modelBrand;
   }
   /**
    * Returns the manufacturer/brand of the vehicle.
    * @return brand name
    */

   /** @return vehicle type */
   public String getTypeVehicle(){
      return typeVehicle;
   }
   /**
    * Returns the type/category of the vehicle.
    * @return type description
    */
   
   /** @return model year */
   public int getYear(){
      return year;
   }
   /**
    * Returns the manufacture year of the vehicle.
    * @return year
    */
   
   /** @return base price */
   public int getBasePrice() {
      return basePrice;
   }
   /**
    * Returns the base price of the vehicle.
    * @return base price in whole dollars
    */
   
   /** @return safety rating */
   public int getSafetyRating(){
      return safetyRating;
   }
   /**
    * Returns the safety rating of the vehicle.
    * @return safety rating
    */
   
   /** @return VIN */
   public String getVin(){
      return vin;
   }
   /**
    * Returns the VIN of the vehicle.
    * @return vehicle identification number
    */
   
   /** @return tow rating */
   public int getTowRating(){
      return towRating;
   }
   /**
    * Returns the towing capacity rating.
    * @return tow rating
    */
   
   /** @return wheel control type */
   public String getTypeWheelControl(){
      return typeWheelControl;
   }
   /**
    * Returns the wheel control type (e.g., AWD).
    * @return wheel control type
    */
   
   /** @return transmission type */
   public String getTransmissionType(){
      return transmissionType;
   }
   /**
    * Returns the transmission type.
    * @return transmission type string
    */
   
   /** @return trim level */
   public String getTrimLevel(){
      return trimLevel;
   }
   /**
    * Returns the trim level of the vehicle.
    * @return trim level
    */
   
   /** @return maximum speed */
   public int getMaxSpeed(){
      return maxSpeed;
   }
   /**
    * Returns the maximum speed of the vehicle.
    * @return max speed in km/h (or configured unit)
    */
   
   /** @return number of seats */
   public int getNumSeats(){
      return numSeats;
   }
   /**
    * Returns the number of seats in the vehicle.
    * @return number of seats
    */
   
   /** @return vehicle color */
   public String getColor(){
      return color;
   }
   /**
    * Returns the vehicle's color.
    * @return color string
    */
   
   /** @return maintenance period */
   public String getMaintenancePeriod(){
      return maintenancePeriod;
   }
   /**
    * Returns the maintenance interval description.
    * @return maintenance period string
    */
   
   /** @return driving range */
   public int getRange(){
      return range;
   }
   /**
    * Returns the vehicle's range (e.g., km for electric vehicles).
    * @return range value
    */

   /** @return vehicle specifications */
   public Spec getVehicleSpec() {
      return vehicleSpec;
   }
   /**
    * Returns the specification object describing this vehicle.
    * @return Spec instance
    */
   
   // Mutators

   /** @param modelName vehicle model name */
   public void setModelName(String modelName){
      this.modelName = modelName;
   }
   /**
    * Set the model name.
    * @param modelName model name
    */
   
   /** @param modelBrand vehicle brand */
   public void setModelBrand(String modelBrand){
      this.modelBrand = modelBrand;
   }
   /**
    * Set the model brand.
    * @param modelBrand brand name
    */
   
   /** @param typeVehicle vehicle type */
   public void setTypeVehicle(String typeVehicle){
      this.typeVehicle = typeVehicle;
   }
   /**
    * Set the type of vehicle.
    * @param typeVehicle type string
    */
   
   /** @param year model year */
   public void setYear(int year){
      if(year>=0){
         this.year = year;
      }
   }
   /**
    * Set the manufacture year.
    * @param year year
    */
   
   /** @param basePrice vehicle base price */
   public void setBasePrice(int basePrice){
      if(basePrice>=0){
         this.basePrice = basePrice;
      }
   }
   /**
    * Set the base price.
    * @param basePrice price in whole dollars
    */
   
   /** @param safetyRating safety rating */
   public void setSafetyRating(int safetyRating){
      if(safetyRating>=0){
         this.safetyRating = safetyRating;
      }
   }
   /**
    * Set the safety rating.
    * @param safetyRating rating value
    */
   
   /** @param vin vehicle identification number */
   public void setVin(String vin){
      this.vin = vin;
   }
   /**
    * Set the vehicle VIN.
    * @param vin vehicle identification number
    */
   
   /** @param towRating towing capacity */
   public void setTowRating(int towRating){
      if(towRating>=0){
         this.towRating = towRating;
      }
   }
   /**
    * Set the tow rating.
    * @param towRating tow rating
    */
   
   /** @param typeWheelControl wheel control type */
   public void setTypeWheelControl(String typeWheelControl){
      this.typeWheelControl = typeWheelControl;
   }
   /**
    * Set the wheel control type.
    * @param typeWheelControl control type
    */
   
   /** @param transmissionType transmission type */
   public void setTransmissionType(String transmissionType){
      this.transmissionType = transmissionType;
   }
   /**
    * Set the transmission type.
    * @param transmissionType transmission string
    */
   
   /** @param trimLevel vehicle trim level */
   public void setTrimLevel(String trimLevel){
      this.trimLevel = trimLevel;
   }
   /**
    * Set the trim level.
    * @param trimLevel trim string
    */
   
   /** @param maxSpeed maximum speed */
   public void setMaxSpeed(int maxSpeed){
      if(maxSpeed>=0){
         this.maxSpeed = maxSpeed;
      }
   }
   /**
    * Set the maximum speed.
    * @param maxSpeed max speed
    */
   
   /** @param numSeats number of seats */
   public void setNumSeats(int numSeats){
      if(numSeats>=0){
         this.numSeats = numSeats;
      }
   }
   /**
    * Set the number of seats.
    * @param numSeats seat count
    */
   
   /** @param color vehicle color */
   public void setColor(String color){
      this.color = color;
   }
   /**
    * Set the color.
    * @param color color string
    */
   
   /** @param maintenancePeriod maintenance interval */
   public void setMaintenancePeriod(String maintenancePeriod){
      this.maintenancePeriod = maintenancePeriod;
   }
   /**
    * Set the maintenance period description.
    * @param maintenancePeriod maintenance string
    */
   
   /** @param range vehicle driving range */
   public void setRange(int range){
      if(range>=0){
         this.range = range;
      }
   }
   /**
    * Set the vehicle range.
    * @param range range value
    */
   
   /** @param vehicleSpec vehicle specifications */
   public void setVehicleSpec(Spec vehicleSpec){
      this.vehicleSpec = vehicleSpec;
   }
   /**
    * Set the Spec object describing this vehicle.
    * @param vehicleSpec Spec instance
    */
   
   
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
    * Compares this vehicle with another for equality based on fields and spec.
    * @param obj other object to compare
    * @return true if equal
    */
   
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
    * Compare by base price.
    * @param other other vehicle
    * @return positive if this is more expensive, negative if cheaper
    */
   
   /**
    * Determines whether the vehicle is considered cheap.
    *
    * @return true if expected price is within the cheap range
    */
   public boolean isCheap(){
      return vehicleSpec.calculateExpectedPrice(basePrice) < basePrice + (basePrice*CHEAP_RANGE);
   }
   /**
    * Determine whether the vehicle is considered cheap based on expected price.
    * @return true if cheap
    */
   
   /**
    * Determines whether the vehicle is family-friendly.
    *
    * @return true if seating, safety, and wheel control requirements are met
    */
   public boolean isFamilyFriendly(){
      return numSeats >=FF_NUMSEATS && safetyRating>=FF_SAFETYRATING && typeWheelControl.equals(FF_WHEELCONTROL);
   }
   /**
    * Returns whether the vehicle is family-friendly based on seats, safety and wheel control.
    * @return true if family-friendly
    */
   
   /**
    * Displays the most important vehicle information.
    *
    * @return formatted string containing essential vehicle details
    */
   public String displayEssentials(){
      return "VIN: "+vin +
         "\nModel: "+ modelName +
         "\nBrand: "+ modelBrand +
         "\nYear: "+ year +
         "\nColor: "+color;
   }
   /**
    * Display essential info (VIN, model, brand, year, color).
    * @return short description string
    */
   
   /**
    * Calculates the maintenance fee for the vehicle.
    *
    * @return maintenance fee
    */
   abstract int calculateMaintenanceFee();
   /**
    * Calculate the maintenance fee for this vehicle. Implementation is subclass-specific.
    * @return maintenance fee in whole dollars
    */
   
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
         "\n\nVehicle Spec: \n"+vehicleSpec;
   
   }
   
}