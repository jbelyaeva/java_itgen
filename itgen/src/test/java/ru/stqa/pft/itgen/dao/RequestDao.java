package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.RequestData;
import ru.stqa.pft.itgen.model.Requests;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;

import java.util.List;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

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

