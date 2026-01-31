//=======================================================================================
//
// Purpose: Handles strictly text output and formatting.
//
//=======================================================================================
package org.example.view;

public class GameView
{
    public GameView()
    {
    }

    //-----------------------------------------------------------------------------
    // Purpose: Game welcome message
    //-----------------------------------------------------------------------------
    public void showWelcome() {
        System.out.println( "Welcome to game!!" );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Just prints "> "
    //-----------------------------------------------------------------------------
    public void showPrompt()
    {
        System.out.print( "> " );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Print scene description & horizontal line
    // Input  : string (description)
    //-----------------------------------------------------------------------------
    public void render( String description )
    {
        System.out.println( "\n" + description );
        System.out.println( "-".repeat( 50 ) );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Display message
    // Input  : string (message)
    //-----------------------------------------------------------------------------
    public void showMessage( String msg )
    {
        System.out.println( "\n" + msg );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Game over screen for win/lose
    // Input  : bool (victory)
    //-----------------------------------------------------------------------------
    public void showGameOver( boolean won )
    {
        if ( won )
        {
            System.out.println( "\nYou found the treasure! You win!" );
        }
        else
        {
            System.out.println( "\nGame over. Better luck next time." );
        }
    }
}
