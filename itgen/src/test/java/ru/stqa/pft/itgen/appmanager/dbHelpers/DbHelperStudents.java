package ru.stqa.pft.itgen.appmanager.dbHelpers;

import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

import java.util.List;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class DbHelperStudents {

  public Students students() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("roles", "child");
    List<StudentData> students = query.find().toList();
    return new Students(students);
  }
/*
  public Parents parents() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> query = datastore.createQuery(ParentData.class).field("roles").equal("parent");
    List<ParentData> parents = query.find().toList();
    return new Parents(parents);
  }*/

  public Students studentFiltrPol(int pol) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> q = datastore.createQuery(StudentData.class);

    q.and(
            q.criteria("roles").equal("child"),
            q.criteria("gender").equal(pol)
    );

    List<StudentData> students = q.find().toList();
    return new Students(students);
  }

}
