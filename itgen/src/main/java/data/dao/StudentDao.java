package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.users.StudentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

public class StudentDao {

  public void save(StudentData student) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(student);
  }

  public void delete(StudentData student) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(student);
  }

  public StudentData findByIdAndDelete(StudentData student) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<StudentData> query =
        datastore.createQuery(StudentData.class).filter("id", student.getId());
    StudentData studentdata = datastore.findAndDelete(query);
    return studentdata;
  }

  public StudentData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("id", id);
    StudentData studentdata = datastore.findAndDelete(query);
    return studentdata;
  }

  public StudentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(StudentData.class).field("id").equal(id).first();
  }

  public void deleteField(String idStudet, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).field("id").equal(idStudet);
    datastore.update(query, datastore.createUpdateOperations(StudentData.class).unset(nameField));

  }

  public <E> void updateField(String idStudent, String nameField, E[] data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("id", idStudent);
    UpdateOperations ops = datastore.createUpdateOperations(StudentData.class).set(nameField, data);
    datastore.update(query, (UpdateOperations<StudentData>) ops);
  }
}
