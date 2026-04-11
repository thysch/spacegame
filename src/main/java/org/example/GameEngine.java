//=======================================================================================
//
// Purpose: Singleton game engine to control game loop
//
//=======================================================================================
package org.example;
//package org.example.commands;

import org.example.commands.GameCommand;
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
//        view.showHelp();
        view.showMessage("Would you like instructions? (Please type 'help' or 'h' now or anytime during the game) or type 'start' to begin");

        String input1;
        do
        {
            view.showPrompt();
            input1 = controller.readCommand();

            if (input1.equals("help") || input1.equals("h"))
            {
                view.showHelp();
            }
            else if (!input1.equals("start"))
            {
                view.showMessage("Please type 'help', 'h', or 'start' to begin the game");
            }
        }
        while (!input1.equals("start"));

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
