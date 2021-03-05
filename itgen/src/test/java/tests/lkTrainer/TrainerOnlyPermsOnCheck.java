package tests.lkTrainer;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerOnlyPermsOnCheck extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().perms("23");
    data.materialPermsService().deleteField("23", "reviewPerms");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerOnlyPermsOnCheck() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.material().tabInProgress();
    app.check().notFindElement(app.material().getTabChecking());
  }
}
