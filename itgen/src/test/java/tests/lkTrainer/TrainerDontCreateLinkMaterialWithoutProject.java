package tests.lkTrainer;
/*T-456
 * Под тренером создать материал (перевод), выбрать Оригинальность - Перевод, но не выбирать
 * проект для связи. При нажатии на кнопку Отправить - алерт "Выбирете проект"
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerDontCreateLinkMaterialWithoutProject extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set6_MaterialEngPublished("23");
  }

  @Test(dataProvider = "validCreateLinkMaterialLkTrainerFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTrainerDontCreateLinkMaterialWithoutProject(MaterialData material) {
    app.trainer().gotoMaterial();
    app.trainer().deleteAlerts();
    app.material().addNewLinkMaterialBad(material);
    app.check()
        .textContent(app.material().getErrorFieldProject(), "Поле обязательно для заполнения");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
