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

    public MoveCommand( GameModel model, String direction )
    {
        this.model = model;
        this.direction = direction;
    }

    @Override
    public void execute()
    {
        model.move(direction);
    }

}
