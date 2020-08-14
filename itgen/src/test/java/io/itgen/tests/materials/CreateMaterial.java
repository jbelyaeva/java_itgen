package io.itgen.tests.materials;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.materials.MaterialData;
import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//надо навесить проверку на созданный материал
//протестить форму
//решить вопрос с веткой

public class CreateMaterial extends TestBase {

  @DataProvider
  public Iterator<Object[]> validMaterialFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/material_creation.json")))) {
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

  @Test(dataProvider = "validMaterialFromJson")
  public void testCreateMaterial(MaterialData material) {
    app.goTo().menuTasks();
    app.goTo().menuMaterials();
    app.material().addNewMaterial(material);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {

  }
}
