package de.htwg.gobang.dao;

import java.util.List;

import de.htwg.gobang.entities.IGamePlayer;

public interface IPlayerDao {

    void saveOrUpdatePlayer(IGamePlayer player);

//    boolean isGameExisting(IGamePlayer player1, IGamePlayer player2);

    IGamePlayer loadPlayer(IGamePlayer player);

    List<IGamePlayer> listAllPlayers();
}
