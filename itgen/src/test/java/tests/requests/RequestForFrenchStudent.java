package tests.requests;
/* Т-310
 * есть семья : французская семья
 * в админке направлений пайтон доступен для записи на французском
 * Перейти в окно записи, ввести имя ученика, появится направление питон
 * Оставить на него заявку и убедится, что заявка создалась*/

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.requests.Requests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RequestForFrenchStudent extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set3_FrFamilyWithStudentAndParent();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "en").getId(), "lang", "fr");
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "fr").getId(), "visibility",
            "visible");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testRequestForFrenchStudent() {
    String name = "Машина Маша";
    app.goTo().menuSchedule();
    Requests before = app.dbrequest().allList();
    app.windowSchedule().makeRequestOnFrenchPython(name);
    Requests after = app.dbrequest().allList();

    app.check().equalityOfTwoElements(after.size(), before.size() + 1);
    app.check().findElement(app.request().getRequestInStack());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().taskAndSchedule().student().family().parent().requests();
    data.skillsService()
        .updateField(data.skillsService().findBySkillId("2", "fr").getId(), "lang", "en");
  }
}
