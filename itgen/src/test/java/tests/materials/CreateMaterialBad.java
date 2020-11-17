package tests.materials;
/* Кейс: проверить, что материал не создается при невалидных значениях формы, отследить всплывающе
 * предупреждения*/

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.general.RunTestAgain;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialData;
import data.model.materials.Materials;
import data.services.MaterialBranchService;
import app.testbase.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateMaterialBad extends TestBase {
  MaterialBranchData materialBranchClean = null;
  MaterialBranchService materialBranchService = new MaterialBranchService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
  }

  @DataProvider
  public Iterator<Object[]> noValidMaterialFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/material_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<MaterialData> materials =
          gson.fromJson(json, new TypeToken<List<MaterialData>>() {}.getType());
      return materials.stream()
          .map((s) -> new Object[] {s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @Test(dataProvider = "noValidMaterialFromJson")
  public void testCreateMaterialBad(MaterialData material) {
    app.goTo().menuMaterials();
    Materials materialBefore = app.dbmaterial().materials();
    app.material().addNewMaterialBad(material);
    Materials materialAfter = app.dbmaterial().materials();
    assertThat(materialBefore.size(), equalTo(materialAfter.size()));
    app.goTo().menuTasks();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testCreateMaterialNoBranch() {
    app.goTo().menuMaterials();
    Materials materialBefore = app.dbmaterial().materials();

    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    MaterialData material =
        new MaterialData()
            .withOriginality("original")
            .withLang("ru")
            .withSkill("1")
            .withTitle("Жук")
            .withType("video")
            .withMaterialLink("https://docs.google.com")
            .withLevel("easy");

    app.material().addNewMaterialBad(material);
    Materials materialAfter = app.dbmaterial().materials();
    assertThat(materialBefore.size(), equalTo(materialAfter.size()));
    app.goTo().menuTasks();
  }
}
