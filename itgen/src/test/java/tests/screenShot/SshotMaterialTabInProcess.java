package tests.screenShot;

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.services.MaterialBranchService;
import data.services.MaterialService;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotMaterialTabInProcess extends TestBase {
  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();
  MaterialData materialClean = null;
  MaterialService materialService = new MaterialService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
    app.trMaterial()
        .newMaterial(
            "MaterialTabInProcess",
            "666",
            "Жуки",
            "checking",
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
  public void testMaterialTabInProcess() throws AWTException, IOException {
    app.goTo().menuTasks();
    app.goTo().menuMaterials();
    app.material().tabInProgress();
    app.material().tabCheck();
    app.material().branchWaitForReview();
    String name = "Admin_MaterialTabInProgress_RU_Chrome";
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
    Assert.assertEquals(diff.getDiffSize(), 0);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialClean = app.dbmaterial().lastMaterial();
    materialService.DeleteById(materialClean.getId());
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
  }
}
