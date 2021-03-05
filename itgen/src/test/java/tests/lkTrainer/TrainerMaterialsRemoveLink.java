package tests.lkTrainer;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerMaterialsRemoveLink extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set7_1_MaterialPublishedLinked();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerMaterialsRemoveLink() {
    app.goTo().menuMaterials();
    app.base().refresh();
    app.material().goInLinkedMaterials("material");
    app.material().btnRemoveLink();
    app.check().notFindElement(app.material().getTabEng());
    app.check().countElements(app.material().getProjectsInBranch(), 2);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material();
  }
}
