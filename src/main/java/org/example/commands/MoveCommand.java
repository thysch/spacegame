//=======================================================================================
//
// Purpose: Move a direction
// Input  : model, direction (north, east, south, west)
//
//=======================================================================================
package org.example.commands;

import org.example.model.GameModel;

public class MoveCommand implements GameCommand
{
    private final GameModel model;
    private final String direction;
    private String previousRoom;

    public MoveCommand( GameModel model, String direction )
    {
        this.model = model;
        this.direction = direction;
    }

    @Override
    public void execute()
    {
        previousRoom = model.getCurrentRoomId();
        model.move(direction);
    }

    @Override
    public void undo()
    {
        if (previousRoom != null)
        {
        model.setCurrentRoom(previousRoom);
        System.out.println("--You are now in the previous room.--\n\n" + previousRoom);
        }
    }

}
