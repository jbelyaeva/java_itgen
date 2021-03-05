package tests.requests;
/* Т-312
 * есть семья : новый ребенок и родитель
 * оставить заявку на двухчасовое пробное на Scratch, время дефолтное по ui
 * Кейс создал заявку, взял данные из бд. Затем удалил заявку и вписал гипотетическую. Взял опять данные из бд и
 * сравнил между собой*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestOnTrial2hScratchTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestOnTrial2hScratch() {
    String name = "Олег Олегов";
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnTrialScratch(name);
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    data.requestService().DeleteById(app.dbrequest().findFirst().getId());
    data.requests().set1_requestOnScratch2hTrial();
    Requests afterNew = app.dbrequest().allList();
    app.check().equalityOfTwoElements(after, afterNew);
    app.goTo().menuRequests();
    app.check().findElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
  }

}
