package org.example.enemy;

//=======================================================================================
//
// Purpose: Observer interface for enemy strategy reactions to player movement
//
//=======================================================================================

public interface EnemyObserver
{
    void onPlayerMoved(String newRoomId);
}
