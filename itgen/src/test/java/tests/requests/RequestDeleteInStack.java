package tests.requests;
/* Т-324
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Scratch, время дефолтное по ui
 * удалить заявку в стеке*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestDeleteInStack extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set3_requestOnScratch2hRegular();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestDeleteInStack() {
    app.goTo().menuRequests();
    Requests before = app.dbrequest().allList();
    app.request().btnDeleteRequest();
    app.request().btnAlertDelete();
    app.check().findElement(app.request().getLabelListEmpty());
    Requests after = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after.size(), before.size() - 1);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
