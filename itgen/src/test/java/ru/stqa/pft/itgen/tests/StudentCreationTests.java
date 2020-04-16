package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

  @Test(dataProvider = "validStudentsFromJson")
  public void testStudentCreation(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    int before = app.students().getStudentCount();
    app.students().createFamily();
    app.students().addStudent();
    app.students().fillStudentForm(student);
    app.students().submitStudentCreation();
    app.goTo().gotoStudents();
    int after = app.students().getStudentCount();
    Assert.assertEquals(after, before + 1);
  }
}