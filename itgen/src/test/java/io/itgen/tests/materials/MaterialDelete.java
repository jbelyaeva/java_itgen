package io.itgen.tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.Materials;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialDelete extends TestBase {

  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .checkingMaterial(
            "MaterialDelete",
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
  public void testMaterialDelete() throws InterruptedException {
    app.goTo().menuTasks();
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().deleteMaterial("MaterialDelete");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
  }

  private void check(Materials after) {
    app.trMaterial()
        .checkingMaterialDalete(
            "MaterialDelete",
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

    MaterialData materialAdd = materialService.findById("MaterialTakeOnCheck");

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals("MaterialTakeOnCheck")) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialClean = app.dbmaterial().lastMaterial();
    materialService.DeleteById(materialClean.getId());
  }
}