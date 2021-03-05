package tests.requests;
/* Т-166
 * есть семья : новый ребенок и родитель
 * в админке направления надо изменить Длительность пробного на 1 ч
 * оставить заявку на индивидуальное занятие на Scratch, время дефолтное по ui */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestOnIF extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 1);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestOnIF() {
    String name = "Олег Олегов";
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnIF(name);
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    data.requestService().DeleteById(app.dbrequest().findFirst().getId());
    data.requests().set10_requestOnIFScratch1hTrial();
    Requests afterNew = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
    data.skills()
        .set3_ChangeDurationTrialScratch(data.skillsService().findBySkillId("1", "ru").getId(), 2);
  }
}
