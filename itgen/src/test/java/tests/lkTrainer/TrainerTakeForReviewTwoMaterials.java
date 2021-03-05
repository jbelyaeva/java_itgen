package tests.lkTrainer;
/*T-140
 * Есть два материала для проверки. взять сначала один материал, потом другой
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerTakeForReviewTwoMaterials extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set9_newTwoMaterials();
    data.materials().perms("23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerTakeForReviewTwoMaterials() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.material().trainerTakeOnCheck("material");
    app.material().trainerTakeSecondMaterialOnCheck("materialSecond");
    app.material().branchTookForReview();
    app.check().countElements(app.material().getMaterialsOnBranchTakenForReview(), 2);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
