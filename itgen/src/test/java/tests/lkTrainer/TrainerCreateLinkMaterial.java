package tests.lkTrainer;
/*T-451
 * Под тренером создать материал (перевод), связать с англ проектом (уже есть в бд) и отправить на проверку
 * открыть таб Все, найти англ проект: есть кнопка Удалить связь
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerCreateLinkMaterial extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set6_MaterialEngPublished("23");
  }

  @Test(dataProvider = "validCreateLinkMaterialLkTrainerFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTrainerCreateLinkMaterial(MaterialData material) {
    app.trainer().gotoMaterial();
    app.trainer().deleteAlerts();
    app.material().addNewLinkMaterial(material);
    app.material().goInPublishedMaterial("CreateNewMaterial", "materialEngPublished");
    app.check().findElement(app.material().getBtnRemoveLink());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
