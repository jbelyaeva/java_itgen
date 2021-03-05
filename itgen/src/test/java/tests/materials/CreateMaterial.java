package tests.materials;
/* Кейс: создать материал под админом, заполнив форму валидными значеням, предворительно создав
 * ветку при помощи транзакции. Проверить, что материал корректно записался в бд, нет
 * сообщений об ошибках, протестить форму валидными значениями*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.provides.LocaleUtilsTestData;
import data.services.MaterialService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateMaterial extends TestBase {

  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().newBranchScratch();
  }

  @Test(dataProvider = "validMaterialFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterial(MaterialData material) {
    app.goTo().menuMaterials();
    app.material().addNewMaterial(material);
    Materials materialAfter = app.dbmaterial().materials();
    materialClean = app.dbmaterial().lastMaterial();
    check(materialAfter, materialClean.getId(), material);
    app.goTo().menuTasks();
  }

  private void check(Materials after, String id, MaterialData material) {
    data.materials().set11_checkingMaterialFromObject(id, material);

    MaterialData materialAdd = materialService.findById(id);

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
