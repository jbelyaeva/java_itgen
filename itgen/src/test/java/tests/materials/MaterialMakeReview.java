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

public class MaterialMakeReview extends TestBase {
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    data.materials().set10_TwoMaterialTakeChecking("666");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testMaterialMakeReview() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().makeReview("material");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuTasks();
  }

  private void check(Materials after) {
    data.materials().set3_MaterialPublished("666");

    MaterialData materialAdd = materialService.findById("material");

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals("material")) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material().payment();
  }
}
