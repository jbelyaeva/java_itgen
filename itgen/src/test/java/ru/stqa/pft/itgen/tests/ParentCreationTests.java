package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;
import ru.stqa.pft.itgen.model.StudentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents = gson.fromJson(json, new TypeToken<List<ParentData>>() {
      }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().students().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.students().createStudent(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1987").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test(dataProvider = "validParentsFromJson")
  public void testParentCreation(ParentData parent) {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = app.db().parents();
    app.parents().creationParent(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    String id = app.parents().getIdNewParentDB(before, after);
    ParentData parentAdd = parent.withId(id).withFirstName(parent.getFirstName()).withLastName(parent.getLastName());
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }


}