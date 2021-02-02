package tests.requests;
/* Т-315
 * есть семья : новый ребенок и родитель
 * оставить заявку на двухчасовое разовое на Scratch, время дефолтное по ui, к тренеру Дефолтный */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestToTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestToTrainer() {
    String name = "Олег Олегов";
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestToTrainer(name, "23");
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    data.requestService().DeleteById(app.dbrequest().findFirst().getId());
    data.requests().set6_requestOnScratch2hSingle("23", "student");
    Requests afterNew = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after, afterNew);
    app.check().findElement(app.request().getRequestInStack());
    app.check().textElement(app.request().getLabelTrainer(), "Дефолтный Тренер");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
  }
}
