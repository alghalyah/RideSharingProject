/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;
public class PrivateRide extends Ride implements IPrivateRide {
    private IRider rider;
    public PrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
                       String dropoffLocation, IDriver driver, IRider rider) {
        super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
        if (rider == null) throw new IllegalArgumentException("Private ride requires a rider");
        this.rider = rider;
    }
    public IRider getRider() { return rider; }
    public void setRider(IRider rider) {
        if (rider == null) throw new IllegalArgumentException("Rider cannot be null");
        this.rider = rider;
    }
    public boolean hasRider(int riderId) { return rider.getId() == riderId; }
    public String toString() { return "Private " + super.toString() + " | Rider: " + rider.getName(); }
}
