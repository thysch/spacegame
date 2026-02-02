/*

 */
package org.example.model;

import java.util.HashSet;
import java.util.Set; 
/**
 *
 * @author Andrew Nguyen
 */
public class Player {
    private String currentRoom; 
    private final Set<String> inventory = new HashSet<>();
    
    public Player(String startingRoom) {
        this.currentRoom = startingRoom;
    }
    
    public String getCurrentRoom() {
        return currentRoom;
    }
    
    public void setCurrentRoom(String room) {
        this.currentRoom = room; 
    }
    
    public Set<String> getInventory() {
        return inventory;
    }
    
    public void addItem(String item) {
        
    }
    
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }
}
