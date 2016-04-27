package de.htwg.gobang.persistence;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.htwg.gobang.persistence.couchdb.CouchDbResult;

@JsonDeserialize(as=CouchDbResult.class)
public interface IPersistentResult {

	int getEnemyId();

	void setEnemyId(int id);

	int getWins();

	void setWins(int wins);

	int getLosses();

	void setLosses(int losses);
}
