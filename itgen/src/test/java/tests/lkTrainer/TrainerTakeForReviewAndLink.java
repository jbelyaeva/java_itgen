package tests.lkTrainer;
/*T-453
 * Есть опубликованный английский материал Beetles
 * есть русский материал Жуки ожидающий проверки
 * Под тренером взять материал на проверку
 * Связать с материалом Beetles (выбрать оргинальность Перевод и заполнить имя связ проекта)
 * Зайти в таб Все и проверить, что у материала Beetles есть кнопка Удалить связь
 * У тренера есть право на редактирование и проверку материала
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerTakeForReviewAndLink extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set2_MaterialTakeChecking("23", "666");
    data.materials().set6_MaterialEngPublished("666");
    data.materials().perms("23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerTakeForReviewAndLink() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.material().trainerLinkedMaterialWhenChecking("material", "Beetles");
    app.material().findPublishedMaterial("materialEngPublished");
    app.check().findElement(app.material().getBtnRemoveLink());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
