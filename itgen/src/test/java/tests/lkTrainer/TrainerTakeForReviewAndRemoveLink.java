package tests.lkTrainer;
/*T-454
 * Есть опубликованный английский материал Beetles
 * есть русский материал Жуки ожидающий проверки
 * Жуки связан с Beetles
 * Удалить связь под тренром
 * Зайти в таб Все и проверить, что у материала Beetles есть кнопка Связать
 * У тренера есть право на редактирование и проверку материала
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerTakeForReviewAndRemoveLink extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set7_MaterialPublishedLinkedWithMaterialChecking("666", "23");
    data.materials().perms("23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerTakeForReviewAndRemoveLink() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.material().trainerRemoveLinkMaterialWhenChecking("material");
    app.material().findPublishedMaterial("materialEngPublished");
    app.check().findElement(app.material().getBtnLink());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material();
  }
}
