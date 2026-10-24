/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

import ds.project.IRide;

/**
 *
 * @author alghalyah
 */
 abstract class Person implements IPerson {
    private String name;
    private String phoneNumber;
    private final int id;
    private LinkedList<IRide> rideHistory;
    
    public Person(int id) {
        this.id =id;
        name = "";
        phoneNumber = "";
        rideHistory = new LinkedList<IRide>();

    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
        
    public String getPhoneNumber() {
        return phoneNumber;
    }

    
    
        
  public void setPhoneNumber(String phoneNumber) {
    if (phoneNumber == null || phoneNumber.length() != 10)
        throw new IllegalArgumentException(
            "Phone number must contain exactly 10 digits"
        );

    for (int i = 0; i < phoneNumber.length(); i++) {
        if (!Character.isDigit(phoneNumber.charAt(i)))
            throw new IllegalArgumentException(
                "Please enter digits only"
            );
    }

    this.phoneNumber = phoneNumber;
}
 
           
    
    
    public LinkedList<IRide> getRideHistory() {
        return rideHistory;
    }
    
 @Override   
    public String toString() {
    return "Name: " + name + " Phone Number: " + phoneNumber + " ID: " + id;
}

    
}
