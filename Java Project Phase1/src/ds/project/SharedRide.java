/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;
public class SharedRide extends Ride implements ISharedRide {
    private final LinkedList<IRider> participants = new LinkedList<>();

    public SharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
                      String dropoffLocation, IDriver driver) {
        super(pickupLocation, pickupTime, dropoffTime, dropoffLocation, driver);
    }
    public LinkedList<IRider> getParticipants() { return participants; }
    public boolean addParticipant(IRider rider) {
        if (rider == null || hasRider(rider.getId())) return false;
        if (!participants.empty()) {
            participants.findfirst();
            while (!participants.last()) participants.findnext();
        }
        participants.insert(rider);
        return true;
    }
    public boolean removeParticipantById(int riderId) {
        if (participants.empty()) return false;
        participants.findfirst();
        while (true) {
            if (participants.retrieve().getId() == riderId) {
                participants.remove();
                return true;
            }
            if (participants.last()) break;
            participants.findnext();
        }
        return false;
    }
    public boolean hasRider(int riderId) {
        if (participants.empty()) return false;
        participants.findfirst();
        while (true) {
            if (participants.retrieve().getId() == riderId) return true;
            if (participants.last()) break;
            participants.findnext();
        }
        return false;
    }
    public boolean isEmpty() { return participants.empty(); }
    public String toString() { return "Shared " + super.toString(); }
}
