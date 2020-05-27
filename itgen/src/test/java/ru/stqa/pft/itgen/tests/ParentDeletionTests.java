package ru.stqa.pft.itgen.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Status;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.ParentService;
import ru.stqa.pft.itgen.services.StudentService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("parentDeletion").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("forParentDeletion").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("parentDeletion").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);
    ParentService parentService = new ParentService();
    ParentData parent = new ParentData().withId("forParDeletion").withFirstName("Зина").withLastName("Зинина")
            .withRoles(Arrays.asList("parent"))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withFamilyId("parentDeletion").withLocate("ru")
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")));
    parentService.create(parent);
  }

  @Test
  public void testParentDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentDeletion");
    app.parent().delete();
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() - 1));

    for (ParentData parent : before) { //найти в списке "до" родителя с таким id
      if (parent.getId().equals("forParDeletion")) {
        assertThat(after, equalTo(before.without(parent)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("forParentDeletion");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("parentDeletion");
    ParentService parentService = new ParentService();
    parentService.findByIdAndDelete("forParDeletion");
  }
}
