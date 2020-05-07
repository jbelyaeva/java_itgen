package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class StudentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {
      }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().students().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.student().create(new StudentData().withFirstName("Вася").withLastName("Петров")
              .withBirthdayUi("01.01.1999").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test(dataProvider = "validStudentsFromJson")
  public void testStudentModification(StudentData student) {
    app.goTo().tasks();
    app.goTo().students();
    Students before = app.db().students();
    StudentData modifyStudent = before.iterator().next();
    app.student().selectStudentInStudentListUI(modifyStudent);
    app.student().modify(student);
    Students after = app.db().students();
    assertThat(after.size(), equalTo(before.size()));
    StudentData studentAdd = student.withId(modifyStudent.getId());
    assertThat(after, equalTo(before.without(modifyStudent).withAdded(studentAdd)));
    verifyStudentsListInUI();
  }
}