package io.itgen.tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialBranchs;
import io.itgen.model.materials.MaterialData;
import io.itgen.services.MaterialBranchService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MaterialAddNewBranch extends TestBase {
  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();

  @Test()
  public void testMaterialAddNewBranch() {
    app.goTo().menuMaterials();
    MaterialBranchs before=app.dbmaterial().materialBranchs();
    app.material().addNewBranch("Scratch");
    MaterialBranchs after=app.dbmaterial().materialBranchs();
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
