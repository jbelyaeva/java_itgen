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

public class ParentModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("parentModify").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("forParentModify")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("parentModify")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
    ParentService parentService = new ParentService();
    ParentData parent =
        new ParentData()
            .withId("forParModify")
            .withFirstName("Зина")
            .withLastName("Зинина")
            .withRoles(Arrays.asList("parent"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withFamilyId("parentModify")
            .withLocate("ru")
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")));
    parentService.save(parent);
  }

  @Test(dataProvider = "validModifyParentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testParentModification(ParentData parent) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentModify");
    app.parent().modify(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size()));

    for (ParentData parentModify : before) { // найти в списке "до" родителя с таким id
      if (parentModify.getId().equals("forParModify")) {
        ParentData parentAdd = parent.withId(parentModify.getId());
        assertThat(after, equalTo(before.without(parentModify).withAdded(parentAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("forParentModify");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("parentModify");
    ParentService parentService = new ParentService();
    parentService.DeleteById("forParModify");
  }
}
