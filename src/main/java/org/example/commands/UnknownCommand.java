package org.example.commands;

import org.example.view.GameView;

//=======================================================================================
//
// Purpose: Handles unrecognised input with a custom message
// Input  : view, raw input string
//
//=======================================================================================
public class UnknownCommand implements GameCommand
{
    private final GameView view;
    private final String input;

    public UnknownCommand(GameView view, String input)
    {
        this.view = view;
        this.input = input;
    }

    @Override
    public void execute()
    {
        view.showMessage("That is an unknown command: " + input
                + "\nType 'help' or 'h' for a list of commands.");
    }

    @Override
    public void undo()
    {
        System.out.println("Cannot undo unknown command!");
    }
}
