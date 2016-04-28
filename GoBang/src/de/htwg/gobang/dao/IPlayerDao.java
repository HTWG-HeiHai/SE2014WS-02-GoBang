package de.htwg.gobang.dao;

import java.util.List;

import de.htwg.gobang.model.IPlayer;

public interface IPlayerDao {

    void saveOrUpdatePlayer(IPlayer player);

//    boolean isGameExisting(IGamePlayer player1, IGamePlayer player2);

    IPlayer loadPlayer(IPlayer player);

    List<IPlayer> listAllPlayers();

	IPlayer getPlayerById(int id);

	boolean containsPlayerById(int id);

	List<IPlayer> getPlayersByName(String name);
}
