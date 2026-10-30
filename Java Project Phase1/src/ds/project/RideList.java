package ds.project;

public class RideList implements IRideList {

    private final LinkedList<IRide> rides = new LinkedList<>();
    private int count = 0;

    // adds a ride in alphabetical order if its id is unique
    public boolean addRide(IRide ride) {
        if (ride == null) {
            return false;
        }

        // add the ride if the list is empty
        if (rides.empty()) {
            rides.insert(ride);
            count++;
            return true;
        }

        // check for duplicate ride ids
        rides.findfirst();
        while (true) {
            if (rides.retrieve().getRideId() == ride.getRideId()) {
                return false;
            }
            if (rides.last()) {
                break;
            }
            rides.findnext();
        }

        // find the correct alphabetical position
        rides.findfirst();
        while (true) {
            IRide existingRide = rides.retrieve();

            if (ride.compareTo(existingRide) < 0) {
                rides.update(ride);
                rides.insert(existingRide);
                count++;
                return true;
            }

            // insert at the end if the ride comes last
            if (rides.last()) {
                rides.insert(ride);
                count++;
                return true;
            }
            rides.findnext();
        }
    }

    // removes a ride using its id
    public boolean removeRideById(int rideId) {
        if (rides.empty()) {
            return false;
        }

        rides.findfirst();
        while (true) {
            if (rides.retrieve().getRideId() == rideId) {
                rides.remove();
                count--;
                return true;
            }
            if (rides.last()) {
                break;
            }
            rides.findnext();
        }
        return false;
    }

    // returns all rides in alphabetical order
    public LinkedList<IRide> getAllAlphabetically() {
        LinkedList<IRide> result = new LinkedList<>();
        if (rides.empty()) {
            return result;
        }

        rides.findfirst();
        while (true) {
            result.insert(rides.retrieve());
            if (rides.last()) {
                break;
            }
            rides.findnext();
        }
        return result;
    }

    // finds rides by pickup location
    public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
        LinkedList<IRide> result = new LinkedList<>();
        if (pickupLocation == null || rides.empty()) {
            return result;
        }

        rides.findfirst();
        while (true) {
            IRide ride = rides.retrieve();

            // check if the pickup location matches
            if (pickupLocation.equalsIgnoreCase(ride.getPickupLocation())) {
                result.insert(ride);
            }

            if (rides.last()) {
                break;
            }
            rides.findnext();
        }
        return result;
    }

    // finds rides involving a rider by name
    public LinkedList<IRide> findByRiderName(String riderFullName) {
        LinkedList<IRide> result = new LinkedList<>();
        if (riderFullName == null || rides.empty()) {
            return result;
        }

        rides.findfirst();
        while (true) {
            IRide ride = rides.retrieve();
            boolean found = false;

            // check the rider in a private ride
            if (ride instanceof IPrivateRide) {
                IRider rider = ((IPrivateRide) ride).getRider();
                found = rider != null && riderFullName.equalsIgnoreCase(rider.getName());

                // check the participants in a shared ride
            } else if (ride instanceof ISharedRide) {
                LinkedList<IRider> participants = ((ISharedRide) ride).getParticipants();

                if (participants != null && !participants.empty()) {
                    participants.findfirst();
                    while (true) {
                        IRider rider = participants.retrieve();

                        // check if the participant name matches
                        if (rider != null && riderFullName.equalsIgnoreCase(rider.getName())) {
                            found = true;
                            break;
                        }

                        if (participants.last()) {
                            break;
                        }
                        participants.findnext();
                    }
                }
            }

            // add the ride if the rider is found
            if (found) {
                result.insert(ride);
            }

            if (rides.last()) {
                break;
            }
            rides.findnext();
        }
        return result;
    }

    public int size() {
        return count;
    }
}
