package tests.requests;
/* Т-313
 * есть семья : новый ребенок и родитель
 * оставить заявку на часовое пробное на Scratch, время дефолтное по ui */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestOnTrial1hScratchTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestOnTrial1hScratch() {
    String name = "Олег Олегов";
    app.base().refresh();
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnTrialScratch(name);
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    data.requestService().DeleteById(app.dbrequest().findFirst().getId());
    data.requests().set2_requestOnScratch1hTrial();
    Requests afterNew = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
