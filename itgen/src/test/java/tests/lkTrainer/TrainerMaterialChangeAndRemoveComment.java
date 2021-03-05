package tests.lkTrainer;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerMaterialChangeAndRemoveComment extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set8_MaterialWithCommentOnChecking();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerMaterialChangeAndRemoveComment() throws InterruptedException {
    String newComment = "Новый коммент";
    app.goTo().menuMaterials();
    app.material().modifyComment("material", newComment);
    Thread.sleep(1000);
    app.check().textElement(app.material().getTextCommentInMaterial(), newComment);
    app.material().btnRemoveComment();
    Thread.sleep(1000);
    app.check().notFindElement(app.material().getTextCommentInMaterial());
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material();
  }
}
