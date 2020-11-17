package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.model.users.Students;
import data.services.FamilyService;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StudentCreationTests extends TestBase {
  StudentData studentClean;

  @BeforeMethod
  public void ensurePreconditions() {
    String[] deleteElements = {"//div[contains(@id,'MeteorToys')]"};
    app.sshot().deleteElements(deleteElements);
  }

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {}.getType());
      return students.stream().map((s) -> new Object[] {s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> noValidStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {}.getType());
      return students.stream().map((s) -> new Object[] {s}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validStudentsFromJson", retryAnalyzer = RunTestAgain.class)
  public void testStudentCreation(StudentData student) {
    app.goTo().menuSchedule();
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

  @Test(dataProvider = "noValidStudentsFromJson", retryAnalyzer = RunTestAgain.class)
  public void testBadStudentCreation(StudentData student) {
    app.goTo().menuSchedule();
    app.goTo().menuStudents();
    Students before = app.dbstudents().students();
    app.student().createBad(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
    studentClean = null;
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (studentClean != (null)) {
      FamilyService familyService = new FamilyService();
      familyService.DeleteById(studentClean.getFamilyId());
      TaskService taskService = new TaskService();
      taskService.drop();
      StudentService studentService = new StudentService();
      studentService.DeleteById(studentClean);
    }
  }
}
