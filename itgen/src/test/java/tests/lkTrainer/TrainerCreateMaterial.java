package tests.lkTrainer;
/*T-376
 * Под тренером создать материал и отправить на проверку
 * провести проверку по бд: что появился материал с определенным статусом
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerCreateMaterial extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().newBranchScratch();
  }

  @Test(dataProvider = "validMaterialLkTrainerFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterial(MaterialData material) {
    app.trainer().gotoMaterial();
    app.trainer().deleteAlerts();
    Materials before = app.dbmaterial().materials();
    app.material().addNewMaterial(material);

    Materials after = app.dbmaterial().materials();
    MaterialData materialClean = app.dbmaterial().lastMaterial();
    MaterialData materialAdd = material.withId(materialClean.getId());
    app.check().equalityOfTwoElements(after, before.withAdded(materialAdd));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
