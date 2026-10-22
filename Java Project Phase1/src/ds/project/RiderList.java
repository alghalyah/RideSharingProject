/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

import ds.project.IRider;

/**
 *
 * @author alghalyah
 */
public class RiderList implements IRiderList {
    
    LinkedList<IRider> riders;
    
    public RiderList() {
        riders = new LinkedList<IRider>();
    }
    
    public boolean add(IRider rider) {

    if (riders.empty()) {
        riders.insert(rider);
        return true;
    }

    // check for duplicate ID
    if (findById(rider.getId()) != null)
        return false;

    // check for duplicate email
    if (findByEmail(rider.getEmail()) != null)
        return false;

    riders.findfirst();

    while (true) {

        if (rider.compareTo(riders.retrieve()) < 0) {
            IRider temp = riders.retrieve();

            riders.update(rider);
            riders.insert(temp);

            return true;
        }

        if (riders.last()) {
            riders.insert(rider);
            return true;
        }

        riders.findnext();
    }
}
     
     
    public IRider findById(int riderId) {
        if(!riders.empty()){
               
               riders.findfirst();
           while(!riders.last()) {
               if(riders.retrieve().getId()== riderId)
                   return riders.retrieve();
               
               riders.findnext();
       }
           if(riders.retrieve().getId()== riderId)
                   return riders.retrieve();
           }
           return null;
    }
    
    
    public LinkedList<IRider> findByName(String fullName) {
        
        LinkedList<IRider> nameList = new LinkedList<IRider>();
       
       if(!riders.empty()) {
           
       
           riders.findfirst();
           while(!riders.last()) {
               if(fullName.equalsIgnoreCase(riders.retrieve().getName()))
                   nameList.insert(riders.retrieve());
           riders.findnext();
       }
           //loop goes through all IRider instances and adds matched names to nameList
           
           
           if(fullName.equalsIgnoreCase(riders.retrieve().getName()))
                   nameList.insert(riders.retrieve());
           //checks on last element
       }
       
         return nameList;  
    }


public IRider findByEmail(String email) {
         IRider r = null;
         if(!riders.empty()) {
         riders.findfirst();
         
         while(!riders.last()) {
             if(riders.retrieve().getEmail().equalsIgnoreCase(email)) {
               r = riders.retrieve();  
             return r;
             }
               riders.findnext();
         } 
         //check the last element    
        if(riders.retrieve().getEmail().equalsIgnoreCase(email))
               r = riders.retrieve();         
               
     }
         return r; 
}

public LinkedList<IRider> findByHomeCity(String homeCity) {
    
    LinkedList<IRider> cityList = new LinkedList<IRider>();
       
       if(!riders.empty()) {
           
       
           riders.findfirst();
           while(!riders.last()) {
               if(homeCity.equalsIgnoreCase(riders.retrieve().getHomeCity()))
                   cityList.insert(riders.retrieve());
           riders.findnext();
       }
           //loop goes through all IRider instances and adds matched home cities to cityList
           
           
           if(homeCity.equalsIgnoreCase(riders.retrieve().getHomeCity()))
                   cityList.insert(riders.retrieve());
           //checks on last element
       }
       
         return cityList;  
}

  public LinkedList<IRider> getAll() {
      return riders;
  }
  
  public boolean removeById(int riderId) {
        
        if(findById(riderId) != null) {
            riders.remove();
        return true;
        }
        /*here findById already makes the node point at the rider of wanted Id,
        which makes remove valid
        */
        return false;
    }
  
    public boolean removeByEmail(String email) {
        if (findByEmail(email) != null) {
           riders.remove();
            return true;
    }
        return false;
        /*here findByEmail already makes the node point at the driver of wanted Email,
        which makes remove valid
        */

  }
    
 public int removeByName(String fullName) {
     int count = 0;
      
      if(!riders.empty()) {
          riders.findfirst();
          
          while(!riders.last()) {
              if(riders.retrieve().getName().equalsIgnoreCase(fullName)) {
                  riders.remove();
              count++;
              
          }
             else
             riders.findnext(); 
             //else is important here because remoove already moves current to the next position
      }
          
          if(riders.retrieve().getName().equalsIgnoreCase(fullName)) {
                  riders.remove();
              count++;
  }
          
      }
          return count;

    }
    
   public int removeByHomeCity(String homeCity) {
       int count = 0;
      
      if(!riders.empty()) {
          riders.findfirst();
          
          while(!riders.last()) {
              if(riders.retrieve().getHomeCity().equalsIgnoreCase(homeCity)) {
                  riders.remove();
              count++;
              
          }
             else
             riders.findnext(); 
             //else is important here because remoove already moves current to the next position
      }
          
          if(riders.retrieve().getHomeCity().equalsIgnoreCase(homeCity)) {
                  riders.remove();
              count++;
  }
          
      }
          return count;

   }
   
    public int size() {
    int count = 0;

    if (!riders.empty()) {
        riders.findfirst();

        while (!riders.last()) {
            count++;
            riders.findnext();
        }

        count++; // Count the last rider
    }

    return count;
}

}
