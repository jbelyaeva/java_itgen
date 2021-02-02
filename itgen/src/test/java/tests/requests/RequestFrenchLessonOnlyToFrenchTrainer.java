package tests.requests;
/* Т-311
 * есть семья : францезская семья
 * в админке направлений пайтон доступен для записи на французском
 * Есть тренер с французским языком
 * Перейти в окно записи, ввести имя ученика,кликнуть по выбору тренера
 * Убедиться, что можно записаться только к нему*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestFrenchLessonOnlyToFrenchTrainer extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set3_FrFamilyWithStudentAndParent();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "en").getId(), "lang", "fr");
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "fr").getId(), "visibility",
            "visible");
    data.newWorker().set2_NewFrenchTrainer();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestFrenchLessonOnlyToFrenchTrainer() throws InterruptedException {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    app.windowSchedule().requestOnFrenchPython(name);
    Thread.sleep(2000);
    String[] etalon = new String[]{"Все", "──────────", "Semov Sem"};
    app.check().equalityOfTwoElements(app.windowSchedule().getListTrainer(), etalon);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests().trainer();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "fr").getId(), "lang", "en");
  }
}
