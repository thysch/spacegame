package org.example.enemy;

import java.util.List;

public class PatrolEnemy implements EnemyObserver
{
    private final String name;
    private final List<String> patrolRooms;

    public PatrolEnemy(String name, List<String> patrolRooms)
    {
        this.name =name;
        this.patrolRooms = patrolRooms;
    }

    @Override
    public void onPlayerMoved(String newRoomId)
    {
        if (patrolRooms.contains(newRoomId))
        {
            System.out.println("\n👁 " + name + " is patrolling nearby....");
        }
    }
}
