/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

public class RideList implements IRideList {
    private final LinkedList<IRide> rides = new LinkedList<>();
    private int count = 0;

    public boolean addRide(IRide ride) {
        if (ride == null) return false;

        if (rides.empty()) {
            rides.insert(ride);
            count++;
            return true;
        }

        rides.findfirst();
        while (true) {
            if (rides.retrieve().getRideId() == ride.getRideId()) return false;
            if (rides.last()) break;
            rides.findnext();
        }

        rides.findfirst();
        while (true) {
            IRide existingRide = rides.retrieve();

            if (ride.compareTo(existingRide) < 0) {
                rides.update(ride);
                rides.insert(existingRide);
                count++;
                return true;
            }

            if (rides.last()) {
                rides.insert(ride);
                count++;
                return true;
            }
            rides.findnext();
        }
    }

    public boolean removeRideById(int rideId) {
        if (rides.empty()) return false;

        rides.findfirst();
        while (true) {
            if (rides.retrieve().getRideId() == rideId) {
                rides.remove();
                count--;
                return true;
            }
            if (rides.last()) break;
            rides.findnext();
        }
        return false;
    }

    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<>();
        if (rides.empty()) return result;

        rides.findfirst();
        while (true) {
            result.insert(rides.retrieve());
            if (rides.last()) break;
            rides.findnext();
        }
        return result;
    }

    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<>();
        if (pickupLocation == null || rides.empty()) return result;

        rides.findfirst();
        while (true) {
            IRide ride = rides.retrieve();
            if (pickupLocation.equalsIgnoreCase(ride.getPickupLocation())) {
                result.insert(ride);
            }
            if (rides.last()) break;
            rides.findnext();
        }
        return result;
    }

    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<>();
        if (riderFullName == null || rides.empty()) return result;

        rides.findfirst();
        while (true) {
            IRide ride = rides.retrieve();
            boolean found = false;

            if (ride instanceof IPrivateRide) {
                IRider rider = ((IPrivateRide) ride).getRider();
                found = rider != null && riderFullName.equalsIgnoreCase(rider.getName());
            } else if (ride instanceof ISharedRide) {
                LinkedList<IRider> participants = ((ISharedRide) ride).getParticipants();
                if (participants != null && !participants.empty()) {
                    participants.findfirst();
                    while (true) {
                        IRider rider = participants.retrieve();
                        if (rider != null && riderFullName.equalsIgnoreCase(rider.getName())) {
                            found = true;
                            break;
                        }
                        if (participants.last()) break;
                        participants.findnext();
                    }
                }
            }

            if (found) result.insert(ride);
            if (rides.last()) break;
            rides.findnext();
        }
        return result;
    }

    public int size() {
        return count;
    }
}
