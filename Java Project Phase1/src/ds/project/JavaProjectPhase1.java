/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds.project;
import java.util.Scanner;
/**
 *
 * @author alghalyah
 */
public class JavaProjectPhase1 {

    
    
        
        public static Scanner input = new Scanner (System.in);
    public static IRideSharingSystem rideSharingSystem = new RideSharingSystem();
   
    public static void main(String[] args) {
        // TODO code application logic here
   
        if ( rideSharingSystem.loadRidersFromCSV("riders_100.csv"))
            System.out.println("Reading Riders file Successfully");
       
        if ( rideSharingSystem.loadDriversFromCSV("drivers_30.csv"))
            System.out.println("Reading Drivers file Successfully");
       
        if ( rideSharingSystem.loadRidesFromCSV("rides_40.csv"))
            System.out.println("Reading Rides file Successfully");
      
       int choice;
        System.out.println("Welcome to the Ride Sharing System!");
       
        do {
           
            System.out.println("\n\nPlease choose an option:");
            System.out.println("    1. List all riders.");
            System.out.println("    2. Search for riders by home city.");
            System.out.println("    3. Search for riders by name.");
            System.out.println("    4. Add a rider.");
            System.out.println("    5. List all drivers.");
            System.out.println("    6. Search for drivers by vehicle type.");
            System.out.println("    7. Add a driver.");
            System.out.println("    8. List all rides alphabetically by pickup location.");
            System.out.println("    9. Search for rides by pickup location.");
            System.out.println("    10. Search for rides by rider name.");
            System.out.println("    11. List the participants of a shared ride by pickup location.");
            System.out.println("    12. Add a ride.");
            System.out.println("    0. Exit the program.");
           
         
            System.out.println("\nEnter your choice:");
           
            choice = input.nextInt();
           
            switch (choice)
            {
                case 1: //List all riders
                {
                   printRiders(rideSharingSystem.getAllRiders());
                }
                break;
               
                case 2: //Search for riders by home city
                {
                   System.out.println("Enter home city");
                   
                   input.nextLine();
                   String homeCity = input.nextLine();
                   
                   printRiders(rideSharingSystem.searchRidersByHomeCity(homeCity));
                }
                break;
               
                case 3: // Search for riders by name
                {
                    System.out.println("Enter rider name");
                   
                    input.nextLine();
                   String rname = input.nextLine();
                   
                   printRiders(rideSharingSystem.searchRidersByName(rname));
                }
                break;
               
                case 4: // add rider
                {
                    try {
                    System.out.println("enter rider id");
                    int uniqueID = input.nextInt();
                   
                    System.out.println("enter rider name");
                    String name = input.nextLine();
                    name = input.nextLine();

                    System.out.println("enter rider phone number");
                    String phonenumber = input.nextLine();
                    

                    System.out.println("enter rider email");
                    String email = input.nextLine();
                    
                    
                    System.out.println("enter rider home city");
                    String homecity = input.nextLine();
                   

                    if (( rideSharingSystem.searchRiderById(uniqueID) != null)
                            || (rideSharingSystem.searchRiderByEmail(email) != null) )
                        System.out.println("rider already exists " + "( #" + uniqueID + " )"    );
                    else
                    {
                        IRider rider = new Rider (uniqueID);
                        rider.setName(name);
                        rider.setPhoneNumber(phonenumber);
                        rider.setEmail(email);
                        rider.setHomeCity(homecity);
                        if (rideSharingSystem.addRider(rider) == false)
                            System.out.println("rider could not be added" );
                        else
                            System.out.println("rider added successfully");
                    }
                }
                     catch (IllegalArgumentException e) {
                     System.out.println("Invalid phone number: " + e.getMessage());
                     }
                }
                break;
               
                case 5: //List all drivers
                {
                  LinkedList<IDriver> list = rideSharingSystem.getAllDrivers();
                  if(!list.empty()){
                      list.findfirst();
                      
                      while(true){
                          System.out.println(list.retrieve());
                          
                          if(list.last()){
                              break;
                          }
                          list.findnext();
                      }
                  } else{ System.out.println("no results");
                  }
                  }
                break;

                         case 6: //Search for drivers by vehicle type
                {
                    System.out.println("Enter vehicle type (SEDAN, LUXURY_SEDAN, SUV, VAN )");
                   
                    String value =input.nextLine();
                    value =input.nextLine();
                   
                    VehicleType vehicleType = VehicleType.valueOf(value.trim().toUpperCase().replace(' ', '_'));                   
                    
                    LinkedList<IDriver> result = rideSharingSystem.searchDriversByVehicleType(vehicleType);
                   if(result.empty()){
                       System.out.println("no result");
                   }
                   else {
                       
                       result.findfirst();
                       
                       while(true){
                           System.out.println(result.retrieve());
                    
                           if(result.last())
                               break;
                           
                           result.findnext();
                }}}
                break;
               
                case 7: // add driver
                {
                    try{
                    System.out.println("enter driver id");
                    int uniqueID = input.nextInt();
                   
                    System.out.println("enter driver name");
                    String name = input.nextLine();
                    name = input.nextLine();

                    System.out.println("enter driver phone number");
                    String phonenumber = input.nextLine();
                    //phonenumber = input.nextLine();

                    System.out.println("enter driver vehicle typetype (SEDAN, LUXURY_SEDAN, SUV, VAN )");
                    String value = input.nextLine();
                     //value = input.nextLine();
                    
                    VehicleType vehicleType = VehicleType.valueOf(value.trim().toUpperCase().replace(' ', '_'));                   
                    System.out.println("enter driver vehicle plate");
                    String vehiclePlate = input.nextLine();
                    //vehiclePlate = input.nextLine();

                    if (( rideSharingSystem.searchDriverById(uniqueID) != null)
                            || (rideSharingSystem.searchDriverByVehiclePlate(vehiclePlate) != null) )
                        System.out.println("driver already exists " + "( #" + uniqueID + " )" );
                    else
                    {
                        IDriver driver = new Driver (uniqueID);
                        driver.setName(name);
                        driver.setPhoneNumber(phonenumber);
                        driver.setVehiclePlate(vehiclePlate);
                        driver.setVehicleType(vehicleType);
                        if (rideSharingSystem.addDriver(driver) == false)
                            System.out.println("driver could not be added" );
                        else
                            System.out.println("driver added successfully");
               
                   }
                }
                     catch (IllegalArgumentException e) {
                     System.out.println("Invalid!: " + e.getMessage());
                     }
                }
                break;
               
                case 8: //List all rides alphabetically by pickup location
                {
                   printRides(rideSharingSystem.getAllRidesAlphabetically());
                }
                break;
                
               
                case 9: //Search for rides by pickup location
                {
                    System.out.println("Enter pickup location");
                   
                    input.nextLine();
                   String pickupLocation = input.nextLine();
                   
                   printRides(rideSharingSystem.searchRidesByPickupLocation(pickupLocation));
                }
                break;
               
                case 10: //Search for rides by rider name
                {
                    System.out.println("Enter rider name");
                   
                   input.nextLine();
                   String rname = input.nextLine();
                   
                   
      
              printRides(rideSharingSystem.searchRidesByRiderName(rname));
   
                }
                break;
               
                case 11: //List the participants of a shared ride by pickup location
                {
                    System.out.println("Enter pickup location");
                   
                    input.nextLine();
                   String pickuplocation = input.nextLine();
                   
                   printRiders(rideSharingSystem.getSharedRideParticipants(pickuplocation));
                }
                break;
               
                case 12: //Add a ride (ask the user whether it is private or shared, then prompt accordingly).
                {
                    try {
                    System.out.println("Enter (S) Shared Ride or (P) Private Ride");
                    char ch = input.next().charAt(0);
                    input.nextLine();

                    System.out.println("Enter pickup Location");
                    String pickupLocation = input.nextLine();

                    System.out.println("Enter pickup Date Time (MM/DD/YYYY HH:MM)");
                    IDateTime pickupDateTime = readDateTime(input.nextLine());

                    System.out.println("Enter dropoff Date Time (MM/DD/YYYY HH:MM)");
                    IDateTime dropoffDateTime = readDateTime(input.nextLine());

                    System.out.println("Enter drop off Location");
                    String dropoffLocation = input.nextLine();

                    System.out.println("Enter driver id");
                    int driverId = input.nextInt();

                    if (ch == 'S' || ch == 's') {

                        System.out.println("Enter list of rider ids separated by ;");
                        input.nextLine();
                        String ids = input.nextLine();

                        String[] riderIDs_s = ids.trim().split(";");
                        int[] riderIDs = new int[riderIDs_s.length];

                        for (int i = 0; i < riderIDs_s.length; i++) {
                            riderIDs[i] = Integer.parseInt(riderIDs_s[i].trim());
                        }

                        if (!rideSharingSystem.scheduleSharedRide(
                                pickupLocation, pickupDateTime,
                                dropoffDateTime, dropoffLocation,
                                riderIDs, driverId)) {

                            System.out.println("could not add Shared ride");
                        } else {
                            System.out.println("Shared ride added successfully");
                        }

                    } else if (ch == 'P' || ch == 'p') {

                        System.out.println("Enter rider id");
                        int riderId = input.nextInt();

                        if (!rideSharingSystem.schedulePrivateRide(
                                pickupLocation, pickupDateTime,
                                dropoffDateTime, dropoffLocation,
                                riderId, driverId)) {

                            System.out.println("could not add Private ride");
                        } else {
                            System.out.println("Private ride added successfully");
                        }

                    } else {
                        System.out.println("Invalid ride type");
                    }
                }
                    catch (Exception e){
                        System.out.println("Invalid!: " +  e.getMessage());
                    }
                }
                break;


               
                case 0:
                    System.out.println("Goodbye!");   
                    break;

                default:
                    System.out.println("Bad choice! Try again `");
            }
            
        }while ( choice != 0);
       
    }
   




    private static void printRiders(LinkedList<IRider> list) {
        if (list.empty()) {
            System.out.println("no results");
        } else {
            list.findfirst();
            while (!list.last()) {
                System.out.println(list.retrieve());
                list.findnext();
            }
            System.out.println(list.retrieve());

        }
    }

            
            
     private static void printRides(LinkedList<IRide> list) {
        if (list.empty()) {
            System.out.println("no results");
        } else {
            list.findfirst();

            while (!list.last()) {
                System.out.println(list.retrieve());
                list.findnext();
            }
            System.out.println(list.retrieve());

        }
    }    
            
            
            private static IDateTime readDateTime(String date) {
    String[] parts = date.trim().split("[/ :]+");

    return new DateTime(
        Integer.parseInt(parts[2]),
        Integer.parseInt(parts[0]),
        Integer.parseInt(parts[1]),
        Integer.parseInt(parts[3]),
        Integer.parseInt(parts[4])
    );
}
        
    

}

        
        
        
        
        
       
    
    

