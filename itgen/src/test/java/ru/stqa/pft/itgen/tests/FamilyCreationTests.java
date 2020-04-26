package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.Families;
import ru.stqa.pft.itgen.model.FamilyDataUI;
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

public class FamilyCreationTests extends TestBase {

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

  //в этом тесте проверки только через бд
  @Test(dataProvider = "validFamiliesFromJson")
  public void testFamilyCreation(FamilyDataUI family) {
    app.goTo().gotoTasks();
    app.goTo().gotoStudents();
    Families before = app.db().families();
    app.families().createFamily(family);
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() + 1));

    String url = app.families().getURL();
    String urlFamily = app.families().getIdFamily(url);
    Students users = app.db().family(urlFamily);
    assertThat(users.size(), equalTo(2));

  }

}
