package ru.stqa.pft.itgen.tests;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class StudentCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/students_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {}.getType()); // List<StudentData>.class
      return students.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  //проверка через бд
  @Test (dataProvider = "validStudentsFromJson", enabled = false)
  public void testStudentCreation(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Students before=app.db().students();
    app.students().createStudent(student);
    Students after=app.db().students();
    assertThat(after.size(), equalTo(before.size()+1));
    String id = app.students().getIdNewStudentDB(before,after);
    StudentData studentAdd=student.withId(id);
//    assertThat(after, equalTo(before.withAdded(studentAdd)));
    Assert.assertEquals(after, before.withAdded(studentAdd));
    verifyStudentsListInUI();
  }

  @Test(dataProvider = "validStudentsFromJson")
  public void testStudentCreation2(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Students before=app.db().students();
    app.students().createStudent(student);
    Students after=app.db().students();
    assertThat(after.size(), equalTo(before.size()+1));
    String id = app.students().getIdNewStudentDB(before,after);
    StudentData studentAdd=student.withId(id);
//    before.add(studentAdd);
//    boolean xxx = Iterables.elementsEqual(new ArrayList<>(after), new ArrayList<>(before.withAdded(studentAdd)));
//    boolean xxx = Iterables.elementsEqual(
//            after != null ? after : new ArrayList<>(),
//            before.withAdded(studentAdd) != null ? before.withAdded(studentAdd) : new ArrayList<>());
//    Assert.assertEquals(after, deepEquals(before.withAdded(studentAdd)));
    assertThat(new ArrayList<>(after), equalTo(new ArrayList<>(before.withAdded(studentAdd))));
//    Assert.assertEquals(after, before.withAdded(studentAdd));
    verifyStudentsListInUI();
  }
}