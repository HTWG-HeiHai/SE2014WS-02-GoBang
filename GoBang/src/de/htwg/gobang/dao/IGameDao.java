package de.htwg.gobang.dao;

import java.util.List;

import de.htwg.gobang.controller.IGbLogic;
import de.htwg.gobang.entities.IGamePlayer;

public interface IGameDao {

    void saveOrUpdateGame(IGbLogic controller);

    boolean isGameExisting(IGamePlayer player1, IGamePlayer player2);

    IGbLogic loadGame(IGamePlayer player1, IGamePlayer player2);

    List<IGbLogic> listAllGames(IGamePlayer player);
}
