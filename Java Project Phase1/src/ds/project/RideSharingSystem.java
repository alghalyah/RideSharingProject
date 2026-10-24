/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

/**
 *
 * @author Layan
 */
public class RideSharingSystem implements IRideSharingSystem {
    
   private RiderList riders;
   private DriverList drivers ;
   private RideList rides;
   
   
   public RideSharingSystem() {
       riders = new RiderList();
       drivers = new DriverList();
       rides = new RideList();   
       
   }
    
   
   
   
    @Override
  public boolean addRider(IRider rider) {
      return riders.add(rider);
  } 
     @Override
    public IRider searchRiderById(int riderId){
        return riders.findById(riderId);
    }
    
    
     @Override
    public IRider searchRiderByEmail( String email ){
        return riders.findByEmail(email);
        
    }
    
     @Override
    public LinkedList <IRider> searchRidersByName ( String fullName){
        return riders.findByName(fullName);
       
    }
    
     @Override
    
    public LinkedList <IRider> searchRidersByHomeCity ( String homeCity){
      return riders.findByHomeCity(homeCity);
    }
    
   
      @Override
      public LinkedList <IRider> getAllRiders() {
          return riders.getAll();
      }
    
    
      
     @Override
    public boolean addDriver( IDriver driver){
        return drivers.add(driver);
        
    }
    
    
     @Override
    public IDriver searchDriverById( int driverId){
        return drivers.findById(driverId);
    }
    
    
         @Override
    public IDriver searchDriverByVehiclePlate ( String vehiclePlate){
        return drivers.findByVehiclePlate(vehiclePlate);
    }

    
    
       @Override  
public LinkedList <IDriver> getAllDrivers(){
    return drivers.getAll();
    
}
    

    @Override
public LinkedList  <IDriver> searchDriversByVehicleType( VehicleType vehicleType){
    return drivers.findByVehicleType(vehicleType);
}




 @Override
 public LinkedList <IRide> searchRidesByPickupLocation ( String pickupLocation){
     return rides.findByPickupLocation(pickupLocation);
 }


 @Override
 public LinkedList <IRide> searchRidesByRiderName (String riderName){
     return rides.findByRiderName(riderName);
 }


 @Override
 public LinkedList <IRide> getAllRidesAlphabetically() {
     return rides.getAllAlphabetically();
 }



 @Override
public boolean loadRidersFromCSV ( String ridersFilePath){
    try (java.io.BufferedReader br = new java.io.BufferedReader( new java.io.FileReader(ridersFilePath))){
        
     String line;
     
     while ((line =br.readLine()) != null){
         
     }
        
      return true; }
    catch (java.io.IOException e) {
        return false;
        
        
        
    }
}
















}
