package io.itgen.tests.students;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;

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
  StudentData studentClean;

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
    app.goTo().menuTrainers();
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().create(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size() + 1));
    studentClean = app.student().getNewStudentDB(before, after);
    StudentData studentAdd = student.withId(studentClean.getId());
    assertThat(after, equalTo(before.withAdded(studentAdd)));
    verifyStudentsListInUI();
    app.goTo().menuTasks();
  }

  @Test(dataProvider = "noValidStudentsFromJson")
  public void testBadStudentCreation(StudentData student) {
    app.goTo().menuTrainers();
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().createBad(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
    studentClean = null;
   }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (studentClean != (null)) {
      FamilyService familyService = new FamilyService();
      familyService.DeleteById(studentClean.getFamilyId());
      TaskService taskService = new TaskService();
      taskService.DeleteById(studentClean);
      StudentService studentService = new StudentService();
      studentService.DeleteById(studentClean);
    }
  }
}