/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

public abstract class Ride implements IRide {

    private static int nextId = 1;
    private final int rideId;
    private String pickupLocation, dropoffLocation;
    private final IDateTime pickupTime, dropoffTime;
    private IDriver driver;

    protected Ride(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
            String dropoffLocation, IDriver driver) {
        if (pickupLocation == null || dropoffLocation == null || pickupTime == null || dropoffTime == null) {
            throw new IllegalArgumentException("Ride details cannot be null");
        }//ensures that no ride details are null
        if (pickupTime.compareTo(dropoffTime) >= 0) {
            throw new IllegalArgumentException("Drop-off must be after pickup");
        }
        this.rideId = nextId++;
        this.pickupLocation = pickupLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.dropoffLocation = dropoffLocation;
        this.driver = driver;
    }

    public int getRideId() {
        return rideId;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }
        pickupLocation = location;
    } //inserts if not null

    public IDateTime getPickupTime() {
        return pickupTime;
    }

    public IDateTime getDropoffTime() {
        return dropoffTime;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }//inserts if not null
        dropoffLocation = location;
    }

    public IDriver getDriver() {
        return driver;
    }

    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    public int compareTo(IRide other) {
        return pickupLocation.compareToIgnoreCase(other.getPickupLocation());
    }

    public abstract boolean hasRider(int riderId);
    
   @Override
    public String toString() {
        return "Ride #" + rideId + " | " + pickupLocation + " -> " + dropoffLocation
                + " | " + pickupTime.format() + " - " + dropoffTime.format()
                +  " | Driver: " + driver.getName();}
    }

