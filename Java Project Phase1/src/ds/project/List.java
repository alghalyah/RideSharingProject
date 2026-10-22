/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ds.project;

/**
 *
 * @author alghalyah
 */
public interface List <T> {
    
public void findfirst( );
public void findnext( );
public T retrieve( );
public void update(T e);
public void insert(T e);
public void remove( );
public boolean full( );
public boolean empty( );
public boolean last( );

}
    

