package de.htwg.gobang.entities.impl;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import de.htwg.gobang.entities.IGameDao;
import de.htwg.gobang.entities.IGamePlayer;

public class DAO_db4o implements IGameDao{

	@Override
	public void create() {
		ObjectContainer db = Db4o.openFile("GoBang_Db4o");
		try {
			IGamePlayer player = new GamePlayer("asdjfy");
			db.set(player);
			System.out.println("Stored player");
		}
		finally {
			db.close();
		}
		
	}

	@Override
	public Object read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
