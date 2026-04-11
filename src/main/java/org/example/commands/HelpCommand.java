package org.example.commands;

import org.example.view.GameView;

public class HelpCommand implements GameCommand
{
    private final GameView view;

    public HelpCommand(GameView view)
    {
        this.view = view;
    }

    @Override
    public void execute()
    {
        view.showHelp();
    }

    @Override
    public void undo()
    {
        System.out.println("Cannot undo help!");
    }
}
