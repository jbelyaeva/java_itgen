package tests.requests;
/* Т-327
 * есть : новый ребенок и родитель
 * есть: заявку на двухчасовое постоянное на Python, к тренеру Вероника
 * Вероника не ведет пайтон
 * в админке направлений пайтон не доступен для записи
 * есть пустое разовое занятие на завтра , тренер ведет скрейч
 * зайти к ученику в профиль в таб Заявки
 * Перейти в запись из заявки - в направление подставился скретч, в тренерах
 * подставилось Все, отображаются группы на скретч*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestISkillNotAvailable extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "18:00 - 20:00";
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.requests().set5_requestOnPython2hSingle();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "ru").getId(), "visibility", "hidden");
    data.schedules().set3_SingleScheduleWithoutStudent(period, "4");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestISkillNotAvailable() {
    app.goTo().menuStudents();
    app.student().selectStudentInListUIById("student");
    app.student().tabRequests();
    app.request().btnApply();
    app.base().refresh();
    app.base().waitElementWithText(5, By.xpath("(//div[@class='filter']//div)[17]"), "Scratch");
    app.check().textElement(By.xpath("(//div[@class='filter']//div)[25]"), "Все");
    app.check().findElement(By.xpath("//div[@class='group-item best']"));
    //добавить проверку алерта
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family().parent().requests();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "ru").getId(), "visibility",
            "visible");
  }
}
