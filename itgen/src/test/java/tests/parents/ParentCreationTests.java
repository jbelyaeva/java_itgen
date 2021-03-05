package tests.parents;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.ParentData;
import data.model.users.Parents;
import data.provides.LocaleUtilsTestData;
import data.services.ParentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParentCreationTests extends TestBase {
  String id;

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set7_FamilyWithStudent();
  }

  @Test(dataProvider = "validParentsFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testParentCreation(ParentData parent) {
    app.goTo().menuTrainers();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("newStudent");
    app.parent().create(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() + 1));

    id = app.parent().getIdNewParentDB(before, after);
    data.newFamily().set6_1_NewParentFromObject(id, "newFamily", parent);
    ParentData parentAdd = data.parentService().findById(id);
    assertThat(after, equalTo(before.withAdded(parentAdd)));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student();
    ParentService parentService = new ParentService();
    parentService.DeleteById(id);
  }
}
