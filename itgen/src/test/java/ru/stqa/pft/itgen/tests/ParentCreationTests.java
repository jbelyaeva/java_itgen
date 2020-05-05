package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;
import ru.stqa.pft.itgen.model.StudentData;
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
      app.student().create(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1987").withPclevel("expert").withCountry("AL"));
    }
  }

  @Test(dataProvider = "validParentsFromJson")
  public void testParentCreation(ParentData parent) {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = app.db().parents();
    createParent(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    String id = app.parent().getIdNewParentDB(before, after);
    ParentData parentAdd = parent.withId(id).withFirstName(parent.getFirstName()).withLastName(parent.getLastName())
            .withCountry(parent.getCountry()).withCity(parent.getCity()).withTimeZone(parent.getTimeZone())
            .withLocate(parent.getLocate()).withNote(parent.getNote());
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }


  private void createParent(ParentData parent) {
    //находим студента без родителя, если такого нет, то создаем такого
    String url = "";
    Students students = app.db().students();
    int a = 1;
    for (StudentData student : students) {
      StudentData studentWithoutParent = students.iterator().next();
      String id = studentWithoutParent.getFamilyId();
      if (app.db().familyСomposition(id).size() == 1) {
        app.parent().create(parent);
        a = 0;
        break;
      } else {
        a = a + 1;
      }
    }
    if (a > 0) {
      app.student().create(new StudentData().withFirstName("Маша").withLastName("Машина")
              .withBirthdayUi("01.01.1987").withPclevel("expert").withCountry("AL"));
      app.student().selectedStudentAfterCreate();
      app.parent().createForStudent(parent);
    }
  }
}