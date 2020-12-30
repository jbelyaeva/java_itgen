package tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialLeaveComment extends TestBase {
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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testMaterialLeaveComment() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().leaveComment("MaterialLeaveComment", "Комментарий");
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
    data.postClean().material();
  }
}
