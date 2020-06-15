package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.RequestData;
import io.itgen.model.Requests;

import java.util.List;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class RequestDao {

  public Requests requests() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    List<RequestData> requests = query.find().toList();
    return new Requests(requests);
  }

  public RequestData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class)
            .filter("id", id);
    RequestData request = datastore.findAndDelete(query);
    return request;
  }

  public void save(RequestData request) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(request);
  }

  public void delete(RequestData request) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(request);
  }

}

