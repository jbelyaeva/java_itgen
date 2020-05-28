package ru.stqa.pft.itgen.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MFSessionFactory {

  public MFSessionFactory() {
  }

  public static Datastore morphiaSessionFactoryUtil() {
    MongoClientOptions.Builder options = new MongoClientOptions.Builder();
    //set your connection option here
    options.connectionsPerHost(200); //max pool size
    MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 3001), options.build());
    Morphia morphia = new Morphia();
    morphia.getMapper().getOptions().setStoreEmpties(true);
    morphia.mapPackage("ru.stqa.pft.itgen.model");
    Datastore datastore = morphia.createDatastore(mongoClient, "meteor");
    return datastore;

  }
}
