package tests.lkTrainer;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerWithoutPerms extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (data.materialPermsService().findById("23") != null) {
      data.materialPermsService().delete(data.materialPermsService().findById("23"));
    }
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerWithoutPerms() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.material().tabInProgress();
    app.check().notFindElement(app.material().getTabChecking());
  }
}
