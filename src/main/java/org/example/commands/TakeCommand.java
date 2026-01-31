//=======================================================================================
//
// Purpose: Pick up an item in the current room
// Input  : model, item
//
//=======================================================================================
package org.example.commands;

import org.example.model.GameModel;

public class TakeCommand implements GameCommand
{
    private final GameModel model;
    private final String itemName;

    public TakeCommand( GameModel model, String itemName )
    {
        this.model = model;
        this.itemName = itemName;
    }

    @Override
    public void execute()
    {
        boolean success = model.takeItem( itemName );
        // System.out.println( "[DEBUG] model.takeItem() returned " + success);

    }
}
