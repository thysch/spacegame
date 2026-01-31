//=======================================================================================
//
// Purpose: Room object
//
//=======================================================================================
package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Room
{
    public String description;
    public String item;

    public Map<String, String> exits = new HashMap<>();
    public Map<String, String> items = new HashMap<>();

    //-----------------------------------------------------------------------------
    // Purpose: Returns an item name (not sure if needed but keeping i guess?)
    // Input  : string (item)
    //-----------------------------------------------------------------------------
    public String getItem( String itemName )
    {
        return items.get( itemName.toLowerCase() );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Remove an item from the room
    // Input  : string (item)
    //-----------------------------------------------------------------------------
    public String removeItem(String itemName)
    {
        itemName = itemName.toLowerCase().trim();
        // System.out.println( "[DEBUG] Looking for: " + itemName + " in items keys: " + items.keySet() );
        String removed = items.remove( itemName );
        // System.out.println("[DEBUG Room] Removed result: " + removed);
        return removed;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Add an item to the room
    // Input  : string (item), string (description)
    //          note: description currently initialized as just "a *item*"
    //-----------------------------------------------------------------------------
    public void addItem( String name, String desc )
    {
        items.put( name.toLowerCase().trim(), desc );
    }

    //-----------------------------------------------------------------------------
    // Purpose: List items in current room
    //-----------------------------------------------------------------------------
    public String getItemsDescription()
    {
        if ( items.isEmpty() ) {
            return "You see nothing special here.";
        }
        return "You see here: " + String.join( ", ", items.keySet() ) + ".";
    }
}
