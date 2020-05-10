package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.ParentService;
import ru.stqa.pft.itgen.services.StudentService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentCreationTests extends TestBase {
  String id;

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
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("parentCreate").withTrialBonusOff(false).withTierId("txa")
            .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
    familyService.create(family);
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("forParentCreation").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("parentCreate").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student);

  }

  @Test(dataProvider = "validParentsFromJson")
  public void testParentCreation(ParentData parent) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentCreation");
    app.parent().create(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    id = app.parent().getIdNewParentDB(before, after);
    ParentData parentAdd = parent.withId(id).withFirstName(parent.getFirstName()).withLastName(parent.getLastName())
            .withCountry(parent.getCountry()).withCity(parent.getCity()).withTimeZone(parent.getTimeZone())
            .withLocate(parent.getLocate()).withNote(parent.getNote());
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }


  @AfterMethod
  public void clean() {
    FamilyService familyService = new FamilyService();
    FamilyData familyClean = familyService.findById("parentCreate");
    familyService.delete(familyClean);
    StudentService studentService = new StudentService();
    StudentData studentClean = studentService.findById("forParentCreation");
    studentService.delete(studentClean);
    ParentService parentService = new ParentService();
    ParentData parentClean = parentService.findById(id);
    if (parentClean!=null){
    parentService.delete(parentClean);}
  }

}