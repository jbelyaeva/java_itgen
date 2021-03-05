package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.requests.RequestData;
import data.model.requests.Requests;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import java.util.List;

public class DbHelperRequest {

  // вся коллекция расписания
  public Requests allList() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    List<RequestData> requests = query.find().toList();
    return new Requests(requests);
  }

  public RequestData findFirst() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    RequestData request = query.find().toList().get(0);
    return request;
  }

}
