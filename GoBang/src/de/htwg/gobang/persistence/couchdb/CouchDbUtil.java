package de.htwg.gobang.persistence.couchdb;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDbUtil {


    private static final CouchDbConnector db;
//    private static final Logger LOGGER =
//        Logger.getLogger(MasterDecoratorController.class);

    static {
        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder()
                .url("http://lenny2.in.htwg-konstanz.de:5984").build();

        } catch (MalformedURLException e) {
//            LOGGER.error("Malformed URL", e);
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("goBangPlayers", true);
        db.createDatabaseIfNotExists();
    }

    private CouchDbUtil() {
    }

    public static CouchDbConnector getDB() {
        return db;
    }
}
