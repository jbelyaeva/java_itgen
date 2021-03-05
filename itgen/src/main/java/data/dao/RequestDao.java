package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.model.requests.RequestData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class RequestDao implements Dao<RequestData> {

  @Override
  public void save(RequestData request) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(request);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class).filter("id", id);
    UpdateOperations ops = datastore.createUpdateOperations(RequestData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<RequestData>) ops);
  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(RequestData request) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(request);
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    datastore.delete(query);
  }

  @Override
  public void deleteField(String idRequest, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class)
        .field("id")
        .equal(idRequest);
    datastore.update(query, datastore.createUpdateOperations(RequestData.class).unset(nameField));
  }

  @Override
  public RequestData deleteById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class).filter("id", id);
    RequestData request = datastore.findAndDelete(query);
    return request;
  }

  @Override
  public RequestData findById(String id) {
    return null;
  }
}
