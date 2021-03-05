package tests.materials;
/* Кейс: проверить, что материал не создается при невалидных значениях формы, отследить всплывающе
 * предупреждения*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.provides.LocaleUtilsTestData;
import data.services.MaterialBranchService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateMaterialBad extends TestBase {

  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().newBranchScratch();
  }

  @Test(dataProvider = "noValidMaterialFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterialBad(MaterialData material) {
    app.goTo().menuMaterials();
    Materials materialBefore = app.dbmaterial().materials();
    app.material().addNewMaterialBad(material);
    Materials materialAfter = app.dbmaterial().materials();
    assertThat(materialBefore.size(), equalTo(materialAfter.size()));
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterialNoBranch() {
    app.goTo().menuMaterials();
    Materials materialBefore = app.dbmaterial().materials();

    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    MaterialData material =
        new MaterialData()
            .withOriginality("original")
            .withLang("ru")
            .withSkill("1")
            .withTitle("Жук")
            .withType("video")
            .withMaterialLink("https://docs.google.com")
            .withLevel("easy");

    app.material().addNewMaterialBad(material);
    Materials materialAfter = app.dbmaterial().materials();
    assertThat(materialBefore.size(), equalTo(materialAfter.size()));
    app.goTo().menuTasks();
  }
}
