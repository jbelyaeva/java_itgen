package tests.family;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.family.Families;
import data.model.users.Students;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FamilyDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFamilyDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Families before = app.db().families();
    app.student().selectStudentInListUIById("student");
    app.family().delete(); // удаляем выбранного студента
    Families after = app.db().families();
    assertThat(after.size(), equalTo(before.size() - 1));
    Students users = app.db().familyComposition("newFamily");
    assertThat(users.size(), equalTo(0));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().parent().student().family();
  }
}
