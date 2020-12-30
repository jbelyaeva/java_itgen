package tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialBranchs;
import data.services.MaterialBranchService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MaterialAddNewBranch extends TestBase {
  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testMaterialAddNewBranch() {
    app.goTo().menuMaterials();
    MaterialBranchs before = app.dbmaterial().materialBranchs();
    app.material().addNewBranch("Scratch");
    MaterialBranchs after = app.dbmaterial().materialBranchs();
    assertThat(after.size(), equalTo(before.size() + 1));
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
  }
}
