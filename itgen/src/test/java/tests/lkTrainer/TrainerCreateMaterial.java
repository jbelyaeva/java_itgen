package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialBranchService;
import data.services.MaterialNewService;
import data.services.MaterialService;
import data.services.PaymentService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerCreateMaterial extends TestBase {

  MaterialData materialClean = null;
  MaterialBranchData materialBranchClean = null;
  MaterialService materialService = new MaterialService();
  MaterialBranchService materialBranchService = new MaterialBranchService();
  PaymentService paymentService = new PaymentService();
  MaterialNewService materialNewService = new MaterialNewService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
  }

  @Test(dataProvider = "validMaterialLkTrainerFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterial(MaterialData material) {
    app.trainer().gotoMaterial();
    app.trainer().deleteAlerts();
    app.material().addNewMaterial(material);
    Materials materialAfter = app.dbmaterial().materials();
    materialClean = app.dbmaterial().lastMaterial();
    check(materialAfter, materialClean.getId(), material);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialService.DeleteById(materialClean.getId());
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialNewService.drop();
    paymentService.drop();
  }

  private void check(Materials after, String id, MaterialData material) {
    app.trMaterial()
        .newMaterial(
            id,
            "23",
            material.getTitle().trim(),
            "checking",
            material.getSkill(),
            "CreateNewMaterial",
            material.getType(),
            material.getLevel(),
            material.getLang(),
            material.getOriginality(),
            material.getMaterialLink(),
            material.getProjectLink(),
            material.getSourceLink(),
            material.getDesc().trim());

    MaterialData materialAdd = materialService.findById(id);

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }
}
