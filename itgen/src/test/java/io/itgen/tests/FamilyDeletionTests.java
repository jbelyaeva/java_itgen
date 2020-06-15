package io.itgen.tests;

import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.itgen.model.Families;
import io.itgen.model.FamilyData;
import io.itgen.model.StudentData;
import io.itgen.model.Students;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FamilyDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("famDeletion").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("famDeletion").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("famDeletion").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  @Test
  public void testFamilyDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Families before = app.db().families();
    app.student().selectStudentInListUIById("famDeletion");
    app.family().delete();//удаляем выбранного студента
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() - 1));
    Students users = app.db().familyComposition("famDeletion");
    assertThat(users.size(), equalTo(0));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("famDeletion");
    FamilyService familyService = new FamilyService();
    familyService.findByIdAndDelete("famDeletion");
  }
}
