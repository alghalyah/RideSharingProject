/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.project;

/**
 *
 * @author alghalyah
 */
public class Rider extends Person implements IRider {
    private String email;
    private String homeCity;
    
    public Rider (int id) {
    super(id);
}
    @Override
     public String getEmail() {
         return email;
     }
     
    @Override
     public void setEmail(String email) {
         this.email = email;
     }

    @Override
   public String getHomeCity() {
       return homeCity;
   }

    @Override
    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }
    
@Override
    public int compareTo(IRider other) {
     return( this.getId() - other.getId() ) ;
    }
    
@Override
    public String toString() {
        return super.toString() + " Email: " + email + " Home City: " + homeCity;
    }
    
    
    
}
