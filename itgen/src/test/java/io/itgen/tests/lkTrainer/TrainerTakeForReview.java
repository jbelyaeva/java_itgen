package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.Materials;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialNewService;
import io.itgen.services.MaterialService;
import io.itgen.services.PaymentService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerTakeForReview extends TestBase {

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
        .newMaterial(
            "MaterialTakeOnCheck",
            "666",
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
            "Развивает внимательность");
    String[] perms = {"1.ru", "1.en", "1.fr", "1.he", "1.de", "1.es", "2.ru"};
    String[] reviews = {"1.ru", "1.en", "1.fr", "1.he", "1.de", "1.es", "2.ru"};
    app.trMaterial().addMaterialPerms("23", perms, reviews);
  }

  @Test
  public void testTrainerTakerForReview() {
    app.trainer().gotoSchedule();
    app.trainer().gotoMaterial();
    Materials before = app.dbmaterial().materials();
    app.material().TakeOnCheck();
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
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

  private void check(Materials after) {
    app.trMaterial()
        .checkingMaterial(
            "MaterialTakeOnCheck",
            "666",
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
            "23");

    MaterialData materialAdd = materialService.findById("MaterialMakeReview");

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals("MaterialMakeReview")) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }
}
