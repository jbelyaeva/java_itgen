package tests.lkTrainer;
/*T-380
 * Есть опубликованный материал
 * Под тренером в табе Новое отображается этот материал
 * Проверить счетчик ui в табе Новое
 * отображение нового материала на ветке
 * Удалить материал из нового
 * проверить, что счетчик обнулился
 * и в бд в новых нет материалов
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerNewMaterialInTabNew extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set5_MaterialNew("23");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerNewMaterialInTabNew() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    app.check().textElement(app.material().getCounterInTabNew(), "1");
    app.trainer().tabNew();
    app.trainer().openBranchInTabNew();
    app.check()
        .textElement(app.material().getMaterialInTabNew(), "Жуки");
    app.material().checkNew();
    app.material().btnRemoveFromNew();
    app.check().textElement(app.material().getCounterInTabNew(), "0");
    assert (data.materialNewService()
        .findById("newMaterial")
        .getNewMaterials()
        .getNewMaterials()
        .isEmpty());
    app.trainer().gotoMaterial();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
