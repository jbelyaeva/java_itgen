package io.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.itgen.model.Families;
import io.itgen.model.FamilyDataUI;
import io.itgen.model.StudentData;
import io.itgen.model.Students;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FamilyCreationTests extends TestBase {
  String idFamily;
  //StudentData studentClean=null;

  @DataProvider
  public Iterator<Object[]> validFamiliesFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/families_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<FamilyDataUI> families = gson.fromJson(json, new TypeToken<List<FamilyDataUI>>() {
      }.getType()); // List<FamilyDataUI>.class
      return families.stream().map((f) -> new Object[]{f}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validFamiliesFromJson")
  public void testFamilyCreation(FamilyDataUI family) {
    app.goTo().menuStudents();
    Families before = app.db().families();
    app.family().create(family);
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() + 1));
    String url = app.family().getURL();
    idFamily = app.family().getId(url);
    Students users = app.db().familyComposition(idFamily);
    assertThat(users.size(), equalTo(2));
  }

  @AfterMethod(alwaysRun = true)
  public void cleanFamily() {
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete(idFamily);
    Students students = app.db().familyComposition(idFamily); //в данном случае в списрок Students попадут ученики и родители
    StudentService studentService = new StudentService();
    TaskService taskService = new TaskService();
    for (StudentData studentClean : students) {
      studentService.findByIdAndDelete(studentClean);
      taskService.findByIdAndDelete(studentClean);
    }
  }

}
