package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/students_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {
      }.getType()); // List<StudentData>.class
      return students.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  //проверка через бд
  @Test (dataProvider = "validStudentsFromJson")
  public void testStudentCreation(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Students before=app.db().students();
    app.students().createStudent(student);
    Students after=app.db().students();
    assertThat(after.size(), equalTo(before.size()+1));
    String id = app.students().getIdNewStudentDB(before,after);
    StudentData studentAdd=new StudentData().withId(id).withFirstName(student.getFirstname()).withLastName(student.getLastname());
    assertThat(new HashSet<Object>(after), equalTo(new HashSet<Object>(before.withAdded(studentAdd))));
   // assertThat(after, equalTo(before.withAdded(studentAdd)));
    verifyStudentsListInUI();
  }


}