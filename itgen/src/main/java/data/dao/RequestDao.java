package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.requests.RequestData;
import data.model.requests.Requests;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import java.util.List;

public class RequestDao {

  public Requests requests() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    List<RequestData> requests = query.find().toList();
    return new Requests(requests);
  }

  public RequestData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class).filter("id", id);
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

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    datastore.delete(query);
  }

  public <E> void updateFieldClass(String idRequest, String nameField, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class).filter("id", idRequest);
    UpdateOperations ops = datastore.createUpdateOperations(RequestData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<RequestData>) ops);
  }

  public void deleteField(String idRequest, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class)
        .field("id")
        .equal(idRequest);
    datastore.update(query, datastore.createUpdateOperations(RequestData.class).unset(nameField));
  }
}
