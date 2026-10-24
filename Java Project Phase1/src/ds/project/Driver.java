/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

/**
 *
 * @author alghalyah
 */
public class Driver extends Person implements IDriver {
   
    private String vehiclePlate;
    private VehicleType vehicleType;
            
            
    public Driver(int id) {
        super(id); 
       vehiclePlate = "";
    }
    
    
    @Override
    public String getVehiclePlate(){
        return vehiclePlate;
    }

    
    
    @Override
    public void setVehiclePlate(String vehiclePlate) {

    if (vehiclePlate == null || vehiclePlate.length() != 7)
        throw new IllegalArgumentException("Plate length is invalid");

    for (int i = 0; i < 3; i++)
        if (!Character.isLetter(vehiclePlate.charAt(i))
                || !Character.isUpperCase(vehiclePlate.charAt(i)))
            throw new IllegalArgumentException("Plate must contain 3 upper case letters");

    for (int i = 3; i < 7; i++)
        if (!Character.isDigit(vehiclePlate.charAt(i)))
            throw new IllegalArgumentException("Plate must contain 4 digits");

    this.vehiclePlate = vehiclePlate;
}

    
    @Override
       public VehicleType getVehicleType() {
            return vehicleType;
        }

    @Override
        public void setVehicleType(VehicleType vehicleType){
            this.vehicleType = vehicleType;
        }

@Override
   public int compareTo(IDriver other) {
   return Integer.compare(this.getId(), other.getId());
   }
        @Override
        public String toString () {
            return super.toString() + " Vehicle Type: " + vehicleType + " Vehicle Plate: "  
                    + vehiclePlate;
        }

}
    
    
