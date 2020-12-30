package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.ParentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ParentDao {

  public void save(ParentData parent) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(parent);
  }

  public ParentData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).filter("id", id);
    ParentData parent = datastore.findAndDelete(query);
    return parent;
  }

  public ParentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ParentData.class).field("id").equal(id).first();
  }

  public void deleteField(String idParent, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).field("id").equal(idParent);
    datastore.update(query, datastore.createUpdateOperations(ParentData.class).unset(nameField));
  }
}
