//=======================================================================================
//
// Purpose: Handles user input parsing and commands the Model.
// Input  : model, view
//
//=======================================================================================
package org.example.controller;

import org.example.commands.GameCommand;
import org.example.commands.MoveCommand;
import org.example.commands.QuitCommand;
import org.example.commands.TakeCommand;
import org.example.model.GameModel;
import org.example.view.GameView;

import java.util.Scanner;

public class GameController
{
    private final GameModel model;
    private final GameView view;
    private final Scanner scanner;
    private final CommandInvoker invoker;
    private boolean quitProgram = false;

    public GameController( GameModel model, GameView view )
    {
        this.model = model;
        this.view = view;
        this.invoker = new CommandInvoker();
        this.scanner = new Scanner( System.in );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Get raw text input & sanitize (lowercase & trimmed)
    //-----------------------------------------------------------------------------
    public String readCommand()
    {
        String input = scanner.nextLine().toLowerCase().trim();
        return input;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Parse and run command
    // Input  : string (raw input)
    //-----------------------------------------------------------------------------
    public boolean processCommand( String rawInput )
    {
        GameCommand command = parseToCommand( rawInput );
        // System.out.println(command);
        
        if ( command != null )
        {
            invoker.invoke( command );
        }
        else
        {
            view.showMessage( " I don't understand that." );
        }
        return quitProgram;
    }

    //-----------------------------------------------------------------------------
    // Purpose: Parse the game command into 2 words
    //-----------------------------------------------------------------------------
    private GameCommand parseToCommand(String input)
    {
        String[] parts = input.split( " " );
        String first = parts[0];
        String second = parts[1];

        String direction = null;

        // this is the messiest part of the code
        // will be related to chain of command pattern
        if ( isDirection ( first ) )
        {
            direction = mapShortToLong( first );
        }
        else if ( isMovementPrefix( first ) )
        {

            if ( isDirection( second ) )
            {
               direction = mapShortToLong( second );
            }
        }
        else if ( isTakeCommand( first ) )
        {
            //System.out.println( "Take command detected" );
            System.out.println( second.toLowerCase() );
            return new TakeCommand( model, second.toLowerCase() );
        }

        if ( direction != null )
        {
            return new MoveCommand( model, direction );
        }

        // process first word
        return switch ( first )
        {
            case "quit", "exit", "q" -> new QuitCommand( this );
            case "look", "l" ->
            {
                view.render( model.getCurrentRoomDescription() );
                yield null;
            }
            default -> null;
        };
    }

    //-----------------------------------------------------------------------------
    // Purpose: Return true if move command
    //-----------------------------------------------------------------------------
    private boolean isMovementPrefix( String word )
    {
        return switch( word )
        {
            case "move", "go", "walk", "run", "head" -> true;
            default -> false;
        };
    }

    //-----------------------------------------------------------------------------
    // Purpose: Return true if cardinal direction command
    // Input  : string (word)
    //-----------------------------------------------------------------------------
    private boolean isDirection( String word )
    {
        return switch( word )
        {
            case "n", "north", "s", "south",
                 "e", "east", "w", "west" -> true;
            default->false;
        };
    }

    //-----------------------------------------------------------------------------
    // Purpose: Convert cardinal letter to direction
    // Input  : string (n,e,s,w)
    //-----------------------------------------------------------------------------
    private String mapShortToLong( String dir )
    {
        return switch ( dir )
        {
            case "n" -> "north";
            case "e" -> "east";
            case "s" -> "south";
            case "w" -> "west";
            default -> dir;
        };
    }

    //-----------------------------------------------------------------------------
    // Purpose: Return true if "get" command, e.g. "get lamp"
    // Input  : string (word)
    //-----------------------------------------------------------------------------
    private boolean isTakeCommand( String word )
    {
        return switch ( word )
        {
            case "get", "take", "grab" -> true;
            default -> false;
        };
    }

    //-----------------------------------------------------------------------------
    // Purpose: Quit the program
    //-----------------------------------------------------------------------------
    public void requestQuit()
    {
        quitProgram = true;
    }
}
