/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

/**
 *
 * @author alghalyah
 */
public class DriverList implements IDriverList {
    
    LinkedList<IDriver> drivers;
    
    public DriverList() {
        drivers = new LinkedList<IDriver>();
    }
    
    
    public boolean add(IDriver driver) {

    if (drivers.empty()) {
        drivers.insert(driver);
        return true;
    }

    // check for duplicate ID
    if (findById(driver.getId()) != null)
        return false;

    // check for duplicate vehicle plate
    if (findByVehiclePlate(driver.getVehiclePlate()) != null)
        return false;

    drivers.findfirst();

    while (true) {

        if (driver.compareTo(drivers.retrieve()) < 0) {
            IDriver temp = drivers.retrieve();

            drivers.update(driver);
            drivers.insert(temp);

            return true;
        }// makes sure driver is inserted in correct position

        if (drivers.last()) {
            drivers.insert(driver);
            return true;
        }

        drivers.findnext();
    }
}
       
       public IDriver findById(int driverId) {
           if(!drivers.empty()){
               
               drivers.findfirst();
           while(!drivers.last()) {
               if(drivers.retrieve().getId()== driverId)
                   return drivers.retrieve();
               
               drivers.findnext();
       }
           if(drivers.retrieve().getId()== driverId)
                   return drivers.retrieve();
           }
           return null;
       }
       
           
           
    public LinkedList<IDriver> findByName(String fullName) {
        
       LinkedList<IDriver> nameList = new LinkedList<IDriver>();
       
       if(!drivers.empty()) {
           
       
           drivers.findfirst();
           while(!drivers.last()) {
               if(fullName.equalsIgnoreCase(drivers.retrieve().getName()))
                   nameList.insert(drivers.retrieve());
           drivers.findnext();
       }
           //loop goes through all IDriver instances and adds matched names to nameList
           
           
           if(fullName.equalsIgnoreCase(drivers.retrieve().getName()))
                   nameList.insert(drivers.retrieve());
           //checks on last element
       }
       
         return nameList;  
       }
    
     public IDriver findByVehiclePlate(String vehiclePlate) {
         IDriver d = null;
         if(!drivers.empty()) {
         drivers.findfirst();
         
         while(!drivers.last()) {
             if(drivers.retrieve().getVehiclePlate().equalsIgnoreCase(vehiclePlate)) {
               d = drivers.retrieve();  
             return d;
             }
               drivers.findnext();
         } 
         //check last element    
        if(drivers.retrieve().getVehiclePlate().equalsIgnoreCase(vehiclePlate))
               d = drivers.retrieve();         
               
     }
         return d;
     }
     
  public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType) {
      
       LinkedList<IDriver> vList = new LinkedList<IDriver>();
       if(!drivers.empty()) {
           
       
           drivers.findfirst();
           while(!drivers.last()) {
               if(vehicleType == drivers.retrieve().getVehicleType())
                   vList.insert(drivers.retrieve());
               
           drivers.findnext();
       }
           //loop goes through all IDriver instances and adds matched veichles to vList
           
           
           if(vehicleType==drivers.retrieve().getVehicleType())
                   vList.insert(drivers.retrieve());
           //checks on last element
       }
       
         return vList;  
       
  }
  
    public LinkedList<IDriver> getAll() {
        return drivers;
        }

    public boolean removeById(int driverId) {
        
        if(findById(driverId) != null) {
            drivers.remove();
        return true;
        }
        /*here findById already makes the node point at the driver of wanted Id,
        which makes remove valid
        */
        return false;
    }
    
    public boolean removeByVehiclePlate(String vehiclePlate) {
        
        if(findByVehiclePlate(vehiclePlate) != null) {
            drivers.remove();
        return true;
        }
        /*here findByVehiclePlate already makes the node point at the driver of wanted veichle plate,
        which makes remove valid
        */
        return false;
    }

    public int removeByName(String fullName) {
      int count = 0;
      
      if(!drivers.empty()) {
          drivers.findfirst();
          
          while(!drivers.last()) {
              if(drivers.retrieve().getName().equalsIgnoreCase(fullName)) {
                  drivers.remove();
              count++;
              
          }
             else
             drivers.findnext(); 
             //else is important here because remoove already moves current to the next position
      }
          
          if(drivers.retrieve().getName().equalsIgnoreCase(fullName)) {
                  drivers.remove();
              count++;
  }
          
      }
          return count;

    }
    
    public int removeByVehicleType(VehicleType vehicleType) {
        int count = 0;
      
      if(!drivers.empty()) {
          drivers.findfirst();
          
          while(!drivers.last()) {
              if(drivers.retrieve().getVehicleType()==vehicleType) {
                  drivers.remove();
              count++;
              
          }
             else
             drivers.findnext(); 
             //else is important here because remoove already moves current to the next position
      }
          
          if(drivers.retrieve().getVehicleType()==vehicleType) {
                  drivers.remove();
              count++;
  }
          
      }
          return count;

    }


 public int size() {
    int count = 0;

    if (!drivers.empty()) {
        drivers.findfirst();

        while (!drivers.last()) {
            count++;
            drivers.findnext();
        }

        count++; // count the last node
    }

    return count;
}

}
