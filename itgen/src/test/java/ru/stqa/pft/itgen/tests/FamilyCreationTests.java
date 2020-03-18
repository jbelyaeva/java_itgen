package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

  // в этом тесте не передаются тестовые данные для родителей.
  // пока не знаю как сделать провайдер данных, который может передавать данные разных типов из двух файлов
  @Test(dataProvider = "validStudentsFromJson")
  public void testFamilyCreation(StudentData student, ParentData parent) {
    app.getNavigationHelper().gotoStudents();
    app.getStudentHelper().createFamily();
    app.getStudentHelper().addStudent();
    app.getStudentHelper().addParent();
    app.getStudentHelper().fillStudentForm(student);
    app.getStudentHelper().fillFamilyParentForm(parent);
    app.getStudentHelper().submitFamilyCreation();
  }
}
