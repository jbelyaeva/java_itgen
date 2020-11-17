package tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialBranchService;
import data.services.MaterialNewService;
import data.services.MaterialService;
import data.services.PaymentService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialMakeReview extends TestBase {
  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();
  MaterialNewService materialNewService = new MaterialNewService();
  PaymentService paymentService = new PaymentService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .checkingMaterial(
            "MaterialMakeReview",
            "14",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  @Test()
  public void testMaterialMakeReview() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().makeReview("MaterialMakeReview");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuTasks();
  }

  private void check(Materials after) {
    app.trMaterial()
        .publishedMaterial(
            "MaterialMakeReview",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    MaterialData materialAdd = materialService.findById("MaterialMakeReview");

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals("MaterialMakeReview")) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialClean = app.dbmaterial().lastMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialService.DeleteById(materialClean.getId());
    materialNewService.drop();
    paymentService.drop();
  }
}
