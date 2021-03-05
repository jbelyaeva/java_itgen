package tests.materials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.materials.Materials;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaterialsLink extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
   data.materials().set14_twoMaterialsEngAndRuForLink();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testMaterialLink() {
    app.goTo().menuMaterials();
    Materials before = app.dbmaterial().materials();
    app.material().linkMaterials("materialRU", "Beetles");
    Materials after = app.dbmaterial().materials();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().material();
  }
}
