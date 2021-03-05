package tests.lkTrainer;
/*T-377
 * Под тренером проверить (опубликовать) бывший на проверке материал
 * У тренера есть право на редактирование и проверку материала
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.Materials;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerMakeReview extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set2_MaterialTakeChecking("23", "666");
    data.materials().perms("23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerMakeReview() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    Materials before = app.dbmaterial().materials();
    app.material().makeReviewTrainer("material");
    Materials after = app.dbmaterial().materials();
    app.check().equalityOfTwoElements(after.size(), before.size());

    data.materials().set3_MaterialPublished("23");
    Materials afterNew = app.dbmaterial().materials();
    app.check().equalityOfTwoElements(after, afterNew);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
