//=======================================================================================
//
// Purpose: Singleton game engine to control game loop
//
//=======================================================================================
package org.example;

import org.example.controller.CommandInvoker;
import org.example.controller.GameController;
import org.example.model.GameModel;
import org.example.view.GameView;

public enum GameEngine
{
    INSTANCE;

    private final GameModel model;
    private final GameView view;
    private final GameController controller;
    private final CommandInvoker invoker;

    GameEngine()
    {
        this.model = new GameModel();
        this.view = new GameView();
        this.controller = new GameController( model, view );
        this.invoker = new CommandInvoker();
    }


    //-----------------------------------------------------------------------------
    // Purpose: Intro message ( currently redundant with model.showMessage() )
    //-----------------------------------------------------------------------------
    public void getMessage()
    {
        System.out.println( "Hello I am a singleton." );
    }

    //-----------------------------------------------------------------------------
    // Purpose: Run the game loop
    //-----------------------------------------------------------------------------
    public void run()
    {
        view.showWelcome();
        while ( !model.isGameOver() )
        {
            view.render( model.getCurrentRoomDescription() );
            view.showPrompt();
            String input = controller.readCommand();
            boolean quit = controller.processCommand( input );
            if ( quit ) break;
        }
        view.showGameOver( model.isVictory() );
    }
}
