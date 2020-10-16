package io.itgen.tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.Materials;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialNewService;
import io.itgen.services.MaterialService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialsLink extends TestBase {

  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();
  MaterialNewService materialNewService = new MaterialNewService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .publishedMaterial(
            "MaterialLinkRU",
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

    app.trMaterial()
        .publishedMaterial(
            "MaterialLinkEN",
            "14",
            "Beetles",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  @Test()
  public void testMaterialLink() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().linkMaterials("MaterialLinkRU", "Beetles");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

  private void check(Materials after) {
    app.trMaterial()
        .linkedMaterial(
            "MaterialLinkEN",
            "MaterialLinkRU",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
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
    for (int i=0; i<2; i++){
    materialClean = app.dbmaterial().lastMaterial();
    materialService.DeleteById(materialClean.getId());}

    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialNewService.drop();
  }
}
