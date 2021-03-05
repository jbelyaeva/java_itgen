package tests.lkTrainer;
/*T-457
 * Есть ветка Скретч
 * Под заведующим тренером создать материал в табе все
 * новый материал имеет статус "published"
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.provides.LocaleUtilsTestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerManagerCreatePublishedMaterial extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().newBranchScratch();
  }

  @Test(dataProvider = "validMaterialLkTrainerFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testTrainerManagerCreatePublishedMaterial(MaterialData material) {
    app.trainer().gotoMaterial();
    app.material().addNewMaterialInTabAll(material);
    app.check().equalityOfTwoElements(app.dbmaterial().lastMaterial().getStatus(), "published");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
