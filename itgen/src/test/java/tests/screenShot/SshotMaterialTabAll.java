package tests.screenShot;

import app.testbase.TestBase;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.services.MaterialBranchService;
import data.services.MaterialService;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static app.appmanager.ApplicationManager.properties;

public class SshotMaterialTabAll extends TestBase {

  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .newMaterial(
            "MaterialTabAll",
            "666",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "adaptation",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность");
  }

  @Test()
  public void testMaterialTabAll() throws AWTException, IOException {
    app.goTo().menuMaterials();
    app.material().openBranch();

    String name = "Admin_MaterialTabAll_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.sshot().changeTopBar();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                properties.getProperty("expected"),
                properties.getProperty("actual"),
                properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    if (diff.getDiffSize() > 100) {
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialClean = app.dbmaterial().lastMaterial();
    materialService.DeleteById(materialClean.getId());
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
  }
}
