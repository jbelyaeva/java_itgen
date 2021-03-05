package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.StudentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class StudentDao implements Dao<StudentData> {

  @Override
  public void save(StudentData student) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(student);
  }

  @Override
  public void delete(StudentData student) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(student);
  }

  @Override
  public StudentData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("id", id);
    StudentData studentdata = datastore.findAndDelete(query);
    return studentdata;
  }

  @Override
  public StudentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(StudentData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class);
    datastore.delete(query);
  }

  @Override
  public void deleteField(String idStudet, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).field("id").equal(idStudet);
    datastore.update(query, datastore.createUpdateOperations(StudentData.class).unset(nameField));
  }

  @Override
  public <E> void updateField(String idStudent, String nameFiled, E data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("id", idStudent);
    UpdateOperations ops = datastore.createUpdateOperations(StudentData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<StudentData>) ops);
  }

  @Override
  public <E> void updateArrayField(String idStudent, String nameField, E[] data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("id", idStudent);
    UpdateOperations ops = datastore.createUpdateOperations(StudentData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<StudentData>) ops);
  }
}
