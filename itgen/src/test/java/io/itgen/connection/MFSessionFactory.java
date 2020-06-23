package io.itgen.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import static io.itgen.appmanager.ApplicationManager.properties;

public class MFSessionFactory {

  public MFSessionFactory() {
  }

  public static Datastore morphiaSessionFactoryUtil() {
    MongoClientOptions.Builder options = new MongoClientOptions.Builder();
    //set your connection option here
    options.connectionsPerHost(200); //max pool size
    MongoClient mongoClient = new MongoClient(new ServerAddress(properties.getProperty("dbBaseUrl"),
            Integer.parseInt(properties.getProperty("dbBasePort"))), options.build());
    Morphia morphia = new Morphia();
    morphia.getMapper().getOptions().setStoreEmpties(true);
    morphia.mapPackage("io.itgen.model");
    Datastore datastore = morphia.createDatastore(mongoClient, "meteor");
    return datastore;

  }
}
