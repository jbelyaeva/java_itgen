package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.services.StudentService;
import ru.stqa.pft.itgen.services.WorkerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentCreationTests extends TestBase {
String id;
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
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>() {
      }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> noValidStudentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/students_creation_bad.json")))) {
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

  @Test(dataProvider = "validStudentsFromJson")
  public void testStudentCreation(StudentData student) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Students before = app.db().students();
    app.student().create(student);
    Students after = app.db().students();
    assertThat(after.size(), equalTo(before.size() + 1));
    id = app.student().getIdNewStudentDB(before, after);
    StudentData studentAdd = student.withId(id);
    assertThat(after, equalTo(before.withAdded(studentAdd)));
    verifyStudentsListInUI();
  }

  @Test(dataProvider = "noValidStudentsFromJson")
  public void testBadStudentCreation(StudentData student) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Students before = app.db().students();
    app.student().createBad(student);
    Students after = app.db().students();
    id = app.student().getIdNewStudentDB(before, after);
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }


  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    StudentData studentClean = studentService.findById(id);
    if (studentClean != null) {
      studentService.delete(studentClean);}
  }


}