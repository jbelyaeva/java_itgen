package tests.requests;
/* Т-320
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое разовое на Scratch, время дефолтное по ui к Бокше
 * есть: пустая разовая группа к Дефолтному тенеру на это же время
 * Записать ученика в группу к Дефолтному тренеру. Перейти в профиль ученика таб Заявки. Заявка зеленая. Не вполнена.*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestNotDoneIfRecordInOtherGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set6_requestOnScratch2hSingle("14", "student");
    data.schedules().set30_SingleScheduleWithoutStudent(period);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestNotDoneIfRecordInOtherGroup() throws InterruptedException {
    app.goTo().menuSchedule();
    app.schedule().recordStudentOn2h("Олег Олегов", "schedule");
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.check().findElement(app.request().getRequestInStack());
    app.check().textElement(app.request().getStatusRequest(), "Не выполнена");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
  }
}
