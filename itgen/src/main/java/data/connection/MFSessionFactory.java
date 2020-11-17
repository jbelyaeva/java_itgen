package data.connection;

import static app.appmanager.ApplicationManager.properties;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MFSessionFactory {

  public MFSessionFactory() {}

  public static Datastore morphiaSessionFactoryUtil() {
    MongoClientOptions.Builder options = new MongoClientOptions.Builder();
    // set your connection option here
    MongoClient mongoClient;

    try {
      MongoClientURI uri = new MongoClientURI(properties.getProperty("mongoURI"));
      mongoClient = new MongoClient(uri);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Can't connect with URI. Fallback to host/port config");
      options.connectionsPerHost(200); // max pool size
      mongoClient =
          new MongoClient(
              new ServerAddress(
                  properties.getProperty("dbBaseUrl"),
                  Integer.parseInt(properties.getProperty("dbBasePort"))),
              options.build());
    }

    Morphia morphia = new Morphia();
    morphia.getMapper().getOptions().setStoreEmpties(true);
    morphia.mapPackage("io.itgen.model");
    return morphia.createDatastore(mongoClient, properties.getProperty("dbName"));
  }
}
