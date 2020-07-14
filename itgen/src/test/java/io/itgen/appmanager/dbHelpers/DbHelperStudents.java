package io.itgen.appmanager.dbHelpers;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.StudentData;
import io.itgen.model.Students;

import java.util.List;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class DbHelperStudents {

  public Students students() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class).filter("roles", "child");
    List<StudentData> students = query.find().toList();
    return new Students(students);
  }

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
