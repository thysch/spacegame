package org.example.enemy;

public class ChaseEnemy implements EnemyObserver
{
    private final String roomId;
    private final String name;

    public ChaseEnemy(String name, String roomId)
    {
        this.name = name;
        this.roomId = roomId;
    }

    @Override
    public void onPlayerMoved(String newRoomId)
    {
        if (newRoomId.equals(roomId))
        {
            System.out.println("\n⚠ " + name + " spots you and gives chase!");
        }
    }
}
