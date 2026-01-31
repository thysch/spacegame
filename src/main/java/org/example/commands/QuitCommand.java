//=======================================================================================
//
// Purpose: Quit the game
// Input  : controller
//
//=======================================================================================
package org.example.commands;

import org.example.controller.GameController;

public class QuitCommand implements GameCommand
{
    private final GameController controller;

    public QuitCommand( GameController controller )
    {
        this.controller = controller;
    }

    @Override
    public void execute()
    {
        controller.requestQuit();
    }
}
