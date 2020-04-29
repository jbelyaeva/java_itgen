package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyDataUI;
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

public class ParentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/parents_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents = gson.fromJson(json, new TypeToken<List<ParentData>>(){}.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().families().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.families().createFamily(new FamilyDataUI().withFirstnameStudent("Маша").withLastnameStudent("Машина")
              .withBirthdayUiStudent("01.01.2009").withPclevelStudent("expert").withCountryStudent("AL")
              .withFirstnameParent("Олег").withLastnameParent("Машин").withCountryParent("AL").withPhoneParent("010101010101"));
    }
  }
  @Test (dataProvider = "validParentsFromJson")
  public void testParentModification(ParentData parent) {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = app.db().parents();
    String url=app.parents().modifyParent(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size()));
    String idParent=app.parents().getId(url);
    ParentData modifyParent = before.iterator().next().withId(idParent);
    ParentData parentAdd = parent.withId(modifyParent.getId());
    assertThat(after, equalTo(before.without(modifyParent).withAdded(parentAdd)));

  }


}



    //
    //    StudentData studentAdd = student.withId(modifyStudent.getId());
    //    assertThat(after, equalTo(before.without(modifyStudent).withAdded(studentAdd)));