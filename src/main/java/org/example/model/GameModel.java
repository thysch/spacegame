//=======================================================================================
//
// Purpose: Handles game state (rooms, player stats).
//
//=======================================================================================
package org.example.model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.Yaml;
import org.example.model.Player;

public class GameModel
{
    private final Player player;
    private final Map<String, Room> rooms = new HashMap<>();
    private boolean victory = false;

    public GameModel()
    {
        this.player = new Player("start");
        loadFromYaml();
        //System.out.println( "loaded yaml" );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Move the player in a direction (also contains win condition)
    // Input  : string (direction)
    //-----------------------------------------------------------------------------
    public boolean move( String direction )
    {
        //System.out.println( "Moving " + direction );

        Room room = rooms.get(player.getCurrentRoom());
        if ( room == null || !room.exits.containsKey( direction ) ) return false;

        String next = room.exits.get(direction);
        player.setCurrentRoom(next);
        Room newRoom = rooms.get(player.getCurrentRoom()); //
        System.out.println("DEBUG: Player is now in room: " + player.getCurrentRoom());

        if ( "treasure".equals( player.getCurrentRoom() ) )
        {
            victory = true;
        }
        return true;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Take an item in the room and place into inventory
    // Input  : string (item)
    //-----------------------------------------------------------------------------
    public boolean takeItem( String itemName )
    {
        Room room = rooms.get( this.player.getCurrentRoom() );
        //System.out.println("[DEBUG] Current room ID: " + room);

        if ( room == null )
        {
            return false;
        }

        String item = room.removeItem( itemName );
        //System.out.println("[DEBUG] removeItem(\"" + itemName + "\") returned: " + item);
        if ( item == null )
        {
            //System.out.println("[DEBUG] Item NOT found → returning false");
            return false;
        }
        player.addItem(item);
        //System.out.println("[DEBUG] Success: added '" + item + "' to inventory");
        return true;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Lists exits, items, inventory in current room
    //          could prob split this up, at least the inventory part
    //-----------------------------------------------------------------------------
    public String getCurrentRoomDescription()
    {
        Room r = rooms.get( this.player.getCurrentRoom() );
        if ( r == null ) return "Error: no room!";

        StringBuilder sb = new StringBuilder();
        sb.append( r.description ).append( "\n" );
        sb.append( "Exits: " ).append( String.join(", ", r.exits.keySet() ) ).append( "\n" );
        sb.append( "Items: ").append( r.getItemsDescription() ).append( "\n" );
        Set<String> inv = player.getInventory();
        sb.append("Inventory: ").append(inv.isEmpty() ? "empty" : String.join(", ", inv));


        return sb.toString();
    }

    //-----------------------------------------------------------------------------
    // Purpose: Load the rooms.yaml file
    //-----------------------------------------------------------------------------
    private void loadFromYaml()
    {
        Yaml yaml = new Yaml();
        try ( InputStream is = getClass().getClassLoader().getResourceAsStream( "rooms.yaml" ) )
        {
            if ( is == null ) throw new IllegalStateException( "rooms.yaml not found" );

            Map<String, Map<String, Object>> data = yaml.load( is );

            for ( var entry : data.entrySet() ) {
                String id = entry.getKey();
                var props = entry.getValue();

                Room room = new Room();
                room.description = ( String ) props.get( "desc" );
                room.exits = ( Map<String, String> ) props.get( "exits" );

                String item = ( String ) props.get( "item" );

                // this part is really important apparently
                if ( item != null )
                {
                    String cleanItemName = item.toLowerCase().trim();
                    room.addItem( cleanItemName, "a " + cleanItemName );
                    //System.out.println("[LOAD DEBUG] Added item '" + cleanItemName + "' to room '" + id + "'");
                }
                room.item = ( String ) props.get( "item" );

                rooms.put( id, room );
            }
           // player.setCurrentRoom("start");  // or could read from yaml
        }
        catch ( Exception e )
        {
            throw new RuntimeException( "Failed to load rooms.yaml" , e );
        }
    }

    //-----------------------------------------------------------------------------
    // Purpose: Return true if game is over (win or lose)
    //-----------------------------------------------------------------------------
    public boolean isGameOver()
    {
        return victory;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Return true if player won
    //-----------------------------------------------------------------------------
    public boolean isVictory()
    {
        return victory;
    }
}
