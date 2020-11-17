package tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialBranchService;
import data.services.MaterialNewService;
import data.services.MaterialService;
import app.testbase.TestBase;
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

  @AfterMethod(alwaysRun = true)
  public void clean() {
    for (int i = 0; i < 2; i++) {
      materialClean = app.dbmaterial().lastMaterial();
      materialService.DeleteById(materialClean.getId());
    }
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialNewService.drop();
  }
}
