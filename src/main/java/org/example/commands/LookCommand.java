package org.example.commands;

import org.example.model.GameModel;
import org.example.view.GameView;

public class LookCommand implements GameCommand
{
    private final GameModel model;
    private final GameView view;

    public LookCommand(GameModel model, GameView view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void execute()
    {
        view.render(model.getCurrentRoomDescription());
        view.showMessage(model.getNeighborDescriptions());
    }

    @Override
    public void undo()
    {
        System.out.println("Cannot undo look!");
    }
}
