package io.itgen.tests.lkParent;
// к дефолтному родителю и ученику добавляется еще ученик, которого запишем на пробное и затем удалим этого ученика
//и расписание в after-методе

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewStudent extends TestBase {
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
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/students_par_creation_bad.json")))) {
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
  public void testAddNewStudent(StudentData student) {
    Students before = app.dbstudents().students();
    app.lkParent().create(student);
    Students after = app.dbstudents().students();
    studentClean = app.student().getNewStudentDB(before, after);
    assertThat(after.size(), equalTo(before.size() + 1));
    StudentData studentAdd = student.withId(studentClean.getId());
    assertThat(after, equalTo(before.withAdded(studentAdd)));
  }

  @Test(dataProvider = "noValidStudentsFromJson")
  public void testBadAddNewStudent(StudentData student) {
    Students before = app.dbstudents().students();
    app.lkParent().createBad(student);
    Students after = app.dbstudents().students();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
    studentClean = null;
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    if (studentClean == null) return;
      TaskService taskService = new TaskService();
      taskService.findByIdAndDelete(studentClean);
      StudentService studentService = new StudentService();
      studentService.findByIdAndDelete(studentClean);
  }


}
