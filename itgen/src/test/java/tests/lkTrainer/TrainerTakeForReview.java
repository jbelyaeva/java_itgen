package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerTakeForReview extends TestBase {
  MaterialService materialService = new MaterialService();

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
    app.material().trainerTakeOnCheck("MaterialTakeOnCheck");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.postClean().material().payment();
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
