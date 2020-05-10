package ru.stqa.pft.itgen.tests;

import javafx.scene.Parent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.ParentService;
import ru.stqa.pft.itgen.services.StudentService;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family = new FamilyData().withId("parentDeletion").withTrialBonusOff(false).withTierId("txa")
            .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
    familyService.create(family);
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("forParentDeletion").withFirstName("Маша").withLastName("Машина")
            .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("parentDeletion").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
            .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
    studentService.create(student);
    ParentService parentService = new ParentService();
    ParentData parent = new ParentData().withId("forParDeletion").withFirstName("Зина").withLastName("Зинина")
            .withRoles(Collections.singletonList(new ParentData.Roles().withRoles("parent")))
            .withCountry("AL").withTimeZone("Europe/Minsk")
            .withFamilyId("parentDeletion").withLocate("ru")
            .withContacts(Collections.singletonList(new ParentData.Contacts().withType("phone").withVal("1234567899")));
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
    FamilyService familyService = new FamilyService();
    FamilyData familyClean = familyService.findById("parentDeletion");
    familyService.delete(familyClean);
    StudentService studentService = new StudentService();
    StudentData studentClean = studentService.findById("forParentDeletion");
    studentService.delete(studentClean);
    ParentService parentService = new ParentService();
    ParentData parentClean = parentService.findById("forParDeletion");
    if (parentClean != null) {
      parentService.delete(parentClean);
    }
  }
}
