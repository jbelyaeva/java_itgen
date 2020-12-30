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

  @Test(retryAnalyzer = RunTestAgain.class)
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
    data.postClean().material();
  }
}
