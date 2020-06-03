package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.StudentData;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class StudentDao {

  public void save(StudentData student) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(student);
  }

  public void delete(StudentData student) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.delete(student);
  }

  public StudentData findByIdAndDelete(StudentData student) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class)
            .filter("id", student.getId());
    StudentData studentdata = datastore.findAndDelete(query);
    return studentdata;
  }

  public StudentData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class)
            .filter("id", id);
    StudentData studentdata = datastore.findAndDelete(query);
    return studentdata;
  }
}
