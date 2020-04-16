package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FamilyCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/students_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students = gson.fromJson(json, new TypeToken<List<StudentData>>(){}.getType()); // List<StudentData>.class
      return students.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }
  @AfterMethod
  public static void closeEntityManagerFactory() {app.db().dbClose();}
  // в этом тесте не передаются тестовые данные для родителей.
  // пока не знаю как сделать провайдер данных, который может передавать данные разных типов из двух файлов

  @Test(dataProvider = "validStudentsFromJson")
  public void testFamilyCreation(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Students students=app.db().students();
    List<StudentData> before = app.students().list();
    app.students().createFamily(student);
    app.goTo().gotoStudents();
    List<StudentData> after = app.students().list();
    Assert.assertEquals(after.size(),before.size()+1);
 //   assertThat(after.size(), equalTo(before.size() + 1));

    String id = app.students().getIdNewStudent(before,after);//вычисляем id нового ученика
    StudentData student_add = new StudentData().withId(id).withFirstName(student.getFirstname()).withLastName(student.getLastname());
    before.add(student_add); //создаем ученика с найденным id и данными об ученике из файла с тестовыми данными

   // Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));//
    assertThat(new HashSet<Object>(after), equalTo(new HashSet<Object>(before)));

  }

  @Test(dataProvider = "validStudentsFromJson",enabled=false)
  public void testDbFamilyCreation(StudentData student) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Students students=app.db().students();
    List<StudentData> before = app.students().list();
    app.students().createFamily(student);
    app.goTo().gotoStudents();
    List<StudentData> after = app.students().list();
    Assert.assertEquals(after.size(),before.size()+1);
    //   assertThat(after.size(), equalTo(before.size() + 1));
    String id = app.students().getIdNewStudent(before,after);//вычисляем id нового ученика
    StudentData student_add = new StudentData().withId(id).withFirstName(student.getFirstname()).withLastName(student.getLastname());
    before.add(student_add); //создаем ученика с найденным id и данными об ученике из файла с тестовыми данными

    // Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    assertThat(new HashSet<Object>(after), equalTo(new HashSet<Object>(before)));
  }
}
