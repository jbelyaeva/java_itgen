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

public class MaterialLeaveComment extends TestBase {

  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .publishedMaterial(
            "MaterialLeaveComment",
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
  }

  @Test()
  public void testMaterialLeaveComment() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().leaveComment("MaterialLeaveComment","Комментарий");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuTasks();
  }

  private void check(Materials after) {
    app.trMaterial()
        .publishedMaterialWithComment(
            "MaterialLeaveComment",
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
            "666",
            "Комментарий");

    MaterialData materialAdd = materialService.findById("MaterialLeaveComment");

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals("MaterialLeaveComment")) {
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
  }
}
