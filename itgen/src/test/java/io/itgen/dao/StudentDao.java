package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.StudentData;

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
}
