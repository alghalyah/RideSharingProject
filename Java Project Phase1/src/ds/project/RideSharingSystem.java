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
 public boolean removeRider(int riderId){
     
     IRider rider =searchRiderById(riderId);
     
        //we check if the rider exists ,otherwise false
        if (rider == null) {
            return false;
        }
    

    LinkedList<IRide> allRides = rides.getAllAlphabetically();

     //Check if there are rides to process
     if (!allRides.empty()) {
         allRides.findfirst();

         //Check each ride
         while (true) {
             IRide ride = allRides.retrieve();

             //Remove private ride of this rider
             if (ride instanceof PrivateRide && ride.hasRider(riderId)) {
                 rides.removeRideById(ride.getRideId());
             } //Remove rider from shared ride
             else if (ride instanceof SharedRide && ride.hasRider(riderId)) {
                 SharedRide shared = (SharedRide) ride;

                 LinkedList<IRider> participants = shared.getParticipants();
                 //Check shared ride participants
                 if (!participants.empty()) {
                     participants.findfirst();

                     //Find and remove the rider
                     while (true) {
                         if (participants.retrieve().getId() == riderId) {
                             participants.remove();
                             break;
                         }
                         if (participants.last()) {
                             break;
                         }

                         participants.findnext();
                     }

                     //Remove shared ride if it has no participants
                     if (participants.empty()) {
                         rides.removeRideById(ride.getRideId());
                     }

                 }

                 if (allRides.last()) {
                     break;
                 }

                 allRides.findnext();
             }

         }}

         //remove rider fom rifer list
         riders.removeById(riderId);
     
         return true;
     }
     
 
 @Override
 public boolean removeDriver(int driverId){
     
     //Check if the driver exists
     IDriver driver = searchDriverById(driverId);
     if(driver == null){
         return false; 
     }
     
     LinkedList<IRide> allRides = rides.getAllAlphabetically();
             
           //Check if there are rides to process
             if(!allRides.empty()){
                 allRides.findfirst();
                
                 
                 
                 //Check each ride
                 while(true){
                     IRide ride =allRides.retrieve();
                     
                     
                  //Remove rides assigned to this driver
                  if(ride.getDriver()!=null &&
                          ride.getDriver().getId()==driverId){
                      
                      rides.removeRideById(ride.getRideId());
                  }
                     
                     if(allRides.last()){
                         break;
                     }
                     
                     allRides.findnext();
                    
                 }
             }
             
             //Remove driver from driver list
             drivers.removeById(driverId);
             return true;
       
  
 }
 
 

     @Override
     public boolean loadRidersFromCSV ( String ridersFilePath ){
    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(ridersFilePath))) {

             String line;

             while ((line = br.readLine()) != null) {
                 String[] parts = line.split(",");
                 int id = Integer.parseInt(parts[0].trim());
                 Rider r = new Rider(id);
                 r.setName(parts[1].trim());
                 r.setEmail(parts[2].trim());
                 r.setPhoneNumber(parts[3].trim());
                 r.setHomeCity(parts[4].trim());

                 addRider(r);

             }

             return true;
         } catch (java.io.IOException e) {
             return false;

         }
     }
     

     @Override
     public boolean loadDriversFromCSV
     (String driversFilePath){
     
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(driversFilePath))) {

             String line;
             while ((line = br.readLine()) != null) {
                 String[] parts = line.split(",");

                 int id = Integer.parseInt(parts[0].trim());
                 Driver d = new Driver(id);

                 d.setName(parts[1].trim());
                 d.setPhoneNumber(parts[2].trim());
                 d.setVehiclePlate(parts[3].trim());
                 d.setVehicleType(VehicleType.valueOf(parts[4].trim()));

                 addDriver(d);

             }

             return true;

         } catch (java.io.IOException e) {
             return false;

        }
    }

    @Override
    public boolean loadRidesFromCSV(String ridesFilePath) {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(ridesFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String pickupLocation = parts[1].trim();
                String pickupTimeText = parts[2].trim();
                String dropoffTimeText = parts[3].trim();
                String dropoffLocation = parts[4].trim();
                int driverId = Integer.parseInt(parts[5].trim());
                String ridersText = parts[6].trim();

                String[] p = pickupTimeText.split("[/ :]+");
                IDateTime pickupTime = new DateTime(
                        Integer.parseInt(p[2]),
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1]),
                        Integer.parseInt(p[3]),
                        Integer.parseInt(p[4])
                );

                String[] d = dropoffTimeText.split("[/ :]+");
                IDateTime dropoffTime = new DateTime(
                        Integer.parseInt(d[2]),
                        Integer.parseInt(d[0]),
                        Integer.parseInt(d[1]),
                        Integer.parseInt(d[3]),
                        Integer.parseInt(d[4])
                );
                IDriver driver = searchDriverById(driverId);

                //Invalid time range
                if (pickupTime.compareTo(dropoffTime) >= 0) {
                    return false;
                }
                if (type.equals("PRIVATE")) {
                    int riderId = Integer.parseInt(ridersText);
                    IRider rider = searchRiderById(riderId);

                    if (driver == null || rider == null) {
                        return false;
                    }

                    //Check for overlapping rides
        LinkedList<IRide> allRides = rides.getAllAlphabetically();
        if (!allRides.empty()) {
            allRides.findfirst();


           while(true){
               IRide existingRide = allRides.retrieve();
               
               
               
               boolean overlap = pickupTime.compareTo(existingRide.getDropoffTime())< 0 && 
                       dropoffTime.compareTo(existingRide.getPickupTime())>0;
               
               if (overlap){
                   
                   
                //Rider schedule conflict    
                   if(existingRide.hasRider(riderId))
                       return false;
                   
                   //Driver schedule conflict
                   if(existingRide.getDriver()!=null &&
                           existingRide.getDriver().getId() == driverId)
                       return false; }
                  
                   
                   if(allRides.last())
                   break;
                   
                   allRides.findnext();
           }
           }
                   
               
           PrivateRide ride = new PrivateRide(
           pickupLocation, pickupTime , dropoffTime,dropoffLocation,driver,rider);
           if(!rides.addRide(ride))
               return false;
}
       else if (type.equals("SHARED")){
           if(driver==null)
               return false;
           
           SharedRide ride = new SharedRide(pickupLocation, pickupTime , dropoffTime,dropoffLocation,driver);

                     String[] riderIds = ridersText.split(";");
                     
                     for(int i=0 ;i< riderIds.length; i++){
                          int riderId = Integer.parseInt(riderIds[i].trim());
                     IRider rider = searchRiderById(riderId);
                     
                     if( !ride.addParticipant(rider))
                         return false;
                     }
                     
                     
                 //Check for ovrlapping rides
                 LinkedList<IRide> sharedAllRides = rides.getAllAlphabetically();
                     
                     if(!sharedAllRides.empty()){
                         sharedAllRides.findfirst();
                         
                         while(true){
                             IRide existingRide = sharedAllRides.retrieve();
                             
                             
                        boolean overlap = pickupTime.compareTo(existingRide.getDropoffTime())< 0 && 
                       dropoffTime.compareTo(existingRide.getPickupTime())>0;
                        
                             if(overlap){
                             
                                 //Rider schedule conflict
                                 for(int i = 0; i<riderIds.length;i++){
                                     int riderId = Integer.parseInt(riderIds[i].trim());
                                     
                                     if(existingRide.hasRider(riderId))
                             return false; }
                                 
                                 
                                 //Driver schedule conflict
                                 if(existingRide.getDriver()!=null &&
                                         existingRide.getDriver().getId() ==driverId)
                                     return false ; }
                                         
                                 if(sharedAllRides.last())
                                 break;
                                 
                                 sharedAllRides.findnext(); }}
                                
                                 
                     
                     
                     
       if(!rides.addRide(ride))
           return false; }}
return true; 
   }catch(java.io.IOException e){
       return false;}}

@Override

public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
  LinkedList<IRider> result = new  LinkedList<IRider>();   
 LinkedList<IRide> found = rides.findByPickupLocation(pickupLocation);
 

if (found.empty())
    return result;


found.findfirst();
while(true){
    
    IRide ride = found.retrieve();
    if( ride instanceof SharedRide){
        SharedRide shared = (SharedRide) ride;
         LinkedList<IRider> ridersList = shared.getParticipants();
         if(!ridersList.empty()){
             ridersList.findfirst();
             while(true){
                 IRider rider = ridersList.retrieve();
                 boolean exists = false;
                 if(!result.empty()){
                     result.findfirst();
                     
                     while(true){
                         if(result.retrieve().getId()==rider.getId()){
                             exists = true;
                             break;}
                         if(result.last())
                             break;
                         result.findnext();
                         }
                     }
                 if(!exists)
                     result.insert(rider);
                 
                 if(ridersList.last())
                     break;
                 
                 ridersList.findnext();
                 }
             }
         
         if(found.last())
                 break;
         found.findnext();}
}
    return result;
         }
    

@Override
public boolean schedulePrivateRide(
String piuckupLocation ,
IDateTime pickupTime ,
IDateTime dropoffTime,
String dropoffLocation,
int riderId,
int driverId){
    
    
    IRider rider = searchRiderById(riderId);
    if (rider == null) {
        return false;
    }

    IDriver driver = searchDriverById(driverId);
    if (driver == null) {
        return false;
    }

    //Invalid time range
    if (pickupTime.compareTo(dropoffTime) >= 0) {
        return false;
    }

    LinkedList<IRide> allRides = rides.getAllAlphabetically();
    
    if(!allRides.empty()) {
    allRides.findfirst();
    
    while(true){
        IRide ride = allRides.retrieve();
         boolean overlap = pickupTime.compareTo(ride.getDropoffTime())<0 && 
                 dropoffTime.compareTo(ride.getPickupTime())>0 ;
         
         if(overlap){
             
             //Rider schedule conflict
             if(ride.hasRider(riderId))
                 return false;
             
             //driver schedule conflict
             if(ride.getDriver()!= null  && ride.getDriver().getId()==driverId)
             return false;
         }
             
             if(allRides.last())
                 break;
             
             allRides.findnext();
    }
    }
    
    PrivateRide newRide = new PrivateRide(
      piuckupLocation ,
      pickupTime ,
      dropoffTime,
      dropoffLocation,   
       driver, rider);
            
            
         return rides.addRide(newRide);
}
            

    @Override
    public boolean scheduleSharedRide(
            String pickupLocation, IDateTime pickupTime,
            IDateTime dropoffTime,
            String dropoffLocation,
            int[] riderIds,
            int driverId) {

    


    //check existece of driver
    IDriver driver = searchDriverById(driverId);
    if (driver == null) {
        return false;
    }
    
    
    //invalid time range
    if (pickupTime.compareTo(dropoffTime) >= 0) {
        return false;
    }

//check existence of rider
    if (riderIds == null || riderIds.length == 0) {
        return false;
    }

    for (int i = 0; i < riderIds.length; i++) {
        IRider rider = searchRiderById(riderIds[i]);

        if (rider == null) {
            return false;
        }
    }

        // check for overlapping rides
        LinkedList<IRide> allRides = rides.getAllAlphabetically();

        if (!allRides.empty()) {
            allRides.findfirst();
            while (true) {
                IRide ride = allRides.retrieve();

                boolean overlap = pickupTime.compareTo(ride.getDropoffTime()) < 0
                        && dropoffTime.compareTo(ride.getPickupTime()) > 0;

                if (overlap) {

                    //Rider schedule conflict
                    for (int i = 0; i < riderIds.length; i++) {
                        if (ride.hasRider(riderIds[i])) {
                            return false;
                        }
                    }
                    //Driver schedule conflict
                    if (ride.getDriver() != null && ride.getDriver().getId() == driverId)
       return false;
           
       }
       
       
       if(allRides.last())
           break;
       
       allRides.findnext();
       }
       }
        
    SharedRide newRide = new SharedRide(
    pickupLocation,
            pickupTime,
            dropoffTime,
            dropoffLocation,
            driver);
    
    
    //Add riders to shared ride
    for( int i =0 ; i<riderIds.length; i++){
        IRider rider = searchRiderById(riderIds[i]);
                
           if(!newRide.addParticipant(rider))     
             return false;   
                
    }
    return rides.addRide(newRide);
                
                    
                
    }  
    }


















         
            
            
            
    
    
                
     
        
        
       
            
            
            
            
    
    
    
    
    
   



















   






















