package tests.requests;
/* Т-315
 * есть семья : новый ребенок и родитель
 * оставить заявку на двухчасовое разовое на Scratch, время дефолтное по ui */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestOnSingle2hScratch extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestOnSingle2hScratchTests() {
    String name = "Олег Олегов";
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnSingle2hScratch(name);
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    data.requestService().DeleteById(app.dbrequest().findFirst().getId());
    data.requests().set4_requestOnScratch2hSingle();
    Requests afterNew = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
