package io.itgen.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.ParentData;
import io.itgen.model.Parents;
import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.StudentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("parentDeletion", false, "txa");

    app.trStudent()
        .newStudent(
            "forParentDeletion",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "parentDeletion");

    app.trParent()
        .newParent(
            "forParDeletion",
            "Зина",
            "Зинина",
            "AL",
            "Europe/Minsk",
            "ru",
            "parentDeletion",
            "123456789",
            "mail1@list.ru",
            true);
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

    for (ParentData parent : before) { // найти в списке "до" родителя с таким id
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
    familyService.DeleteById("parentDeletion");
    ParentService parentService = new ParentService();
    parentService.DeleteById("forParDeletion");
  }
}
