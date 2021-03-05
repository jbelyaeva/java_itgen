package tests.parents;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.ParentData;
import data.model.users.Parents;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParentModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(dataProvider = "validModifyParentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testParentModification(ParentData parent) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("student");
    app.parent().modify(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size()));

    for (ParentData parentModify : before) { // найти в списке "до" родителя с таким id
      if (parentModify.getId().equals("newParent")) {
        ParentData parentAdd = parent.withId(parentModify.getId());
        assertThat(after, equalTo(before.without(parentModify).withAdded(parentAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student().parent();
  }
}
