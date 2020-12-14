package tests.parents;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.family.FamilyData;
import data.model.users.ParentData;
import data.model.users.Parents;
import data.model.users.StudentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Status;
import data.provides.LocaleUtilsTestData;
import data.services.FamilyService;
import data.services.ParentService;
import data.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParentCreationTests extends TestBase {
  String id;

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("parentCreate").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("forParentCreation")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("parentCreate")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  @Test(dataProvider = "validParentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testParentCreation(ParentData parent) {
    app.goTo().menuTrainers();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentCreation");
    app.parent().create(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    id = app.parent().getIdNewParentDB(before, after);
    ParentData parentAdd =
        parent
            .withId(id)
            .withFirstName(parent.getFirstName())
            .withLastName(parent.getLastName())
            .withCountry(parent.getCountry())
            .withCity(parent.getCity())
            .withTimeZone(parent.getTimeZone())
            .withLocate(parent.getLocate())
            .withNote(parent.getNote());
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("forParentCreation");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("parentCreate");
    ParentService parentService = new ParentService();
    parentService.DeleteById(id);
  }
}
