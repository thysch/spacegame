//=======================================================================================
//
// Purpose: Decouples the object that invokes the operation (controller.CommandInvoker)
//          from the one that knows how to perform it (model.GameModel).
//
//=======================================================================================
package org.example.controller;

import org.example.commands.GameCommand;
import java.util.Stack;

public class CommandInvoker
{
    private final Stack<GameCommand> history = new Stack<>();
    private static final int MAX_UNDO = 3; // max number of undo commands

    //-----------------------------------------------------------------------------
    // Purpose: Run a game command
    // Input  : GameCommand
    //-----------------------------------------------------------------------------
    public void invoke( GameCommand command )
    {
        try
        {
            //System.out.println("tried command");
            command.execute();
            history.push( command );
            if (history.size() > MAX_UNDO)
            {
                history.removeFirst();
            }
        }
        catch( Exception e )
        {
            throw new RuntimeException( e );
        }
    }

    //-----------------------------------------------------------------------------
    // Purpose: undo previous action -- NOT IMPLEMENTED YET
    //-----------------------------------------------------------------------------
    public void undoLast()
    {
        if ( !history.isEmpty() )
        {
            GameCommand command = history.pop();
            command.undo();
        }
        else
        {
            System.err.println( "Failed to undo command." );
        }
    }
}
