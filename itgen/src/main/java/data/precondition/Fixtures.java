package data.precondition;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.candidate.CandidateVacanciesData;
import data.model.family.FamilyData;
import data.model.materials.MaterialPermsData;
import data.model.products.ProductData;
import data.model.skills.SkillsData;
import data.model.skills.SkillsOrderData;
import data.model.tier.TierCountryData;
import data.model.tier.TierData;
import data.model.users.ParentData;
import data.model.users.StudentData;
import data.model.users.TrainerData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Fixtures {

  protected static final DataManager data = new DataManager();

  public void fillDatabase() throws IOException, InterruptedException {
    fillStudents();
    fillTrainers();
    fillFamilies();
    fillParents();
    fillSkills();
    fillMaterialsPerm();
    fillCandidateVacancies();
    fillProducts();
    fillTiers();
    Thread.sleep(500);
  }

  private String read(String path) throws IOException {
    try (BufferedReader reader =
        new BufferedReader(new FileReader(path))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      return json;
    }
  }

  private void fillStudents() throws IOException {
    data.studentService().drop();
    Gson gson = new Gson();
    List<StudentData> students =
        gson.fromJson(
            read("src/test/resources/fixture/students_creation.json"),
            new TypeToken<List<StudentData>>() {
            }.getType());
    for (StudentData s : students) {
      data.studentService().save(s);
      data.studentService().deleteField(s.getId(), "className");
    }
  }

  private void fillProducts() throws IOException {
    data.productService().drop();
    Gson gson = new Gson();
    List<ProductData> products =
        gson.fromJson(
            read("src/test/resources/fixture/products_creation.json"),
            new TypeToken<List<ProductData>>() {
            }.getType());
    for (ProductData p : products) {
      data.productService().save(p);
      data.productService().deleteField(p.getId(), "className");
    }
  }

  private void fillTiers() throws IOException {
    data.tierService().dropTierC();
    data.tierService().dropTier();
    Gson gson = new Gson();
    List<TierCountryData> tiersCountry =
        gson.fromJson(
            read("src/test/resources/fixture/tier_country_creation.json"),
            new TypeToken<List<TierCountryData>>() {
            }.getType());
    for (TierCountryData t : tiersCountry) {
      data.tierService().save(t);
      data.tierService().deleteField(t.getId(), "className");
    }

    List<TierData> tiers =
        gson.fromJson(
            read("src/test/resources/fixture/tier_creation.json"),
            new TypeToken<List<TierData>>() {
            }.getType());
    for (TierData t : tiers) {
      data.tierService().saveTier(t);
      data.tierService().deleteFieldTier(t.getId(), "className");
    }
  }

  private void fillMaterialsPerm() throws IOException {
    Gson gson = new Gson();
    List<MaterialPermsData> perms =
        gson.fromJson(
            read("src/test/resources/fixture/material_perm_creation.json"),
            new TypeToken<List<MaterialPermsData>>() {
            }.getType());
    for (MaterialPermsData p : perms) {
      data.materialPermsService().save(p);
      data.materialPermsService().deleteField(p.getId(), "className");
    }
  }

  private void fillSkills() throws IOException {
    data.skillsService().drop();
    data.skillsService().dropSkillOrder();
    Gson gson = new Gson();
    List<SkillsData> skills =
        gson.fromJson(
            read("src/test/resources/fixture/skills_creation.json"),
            new TypeToken<List<SkillsData>>() {
            }.getType());
    for (SkillsData s : skills) {
      data.skillsService().save(s);
      data.skillsService().deleteField(s.getId(), "className");
      data.skillsService().deleteField(s.getId(), "minAgeWithoutTest");
    }

    List<SkillsOrderData> skillsOrder =
        gson.fromJson(
            read("src/test/resources/fixture/skillsOrder_creation.json"),
            new TypeToken<List<SkillsOrderData>>() {
            }.getType());
    for (SkillsOrderData so : skillsOrder) {
      data.skillsService().saveOrder(so);
      data.skillsService().deleteFieldInOrder(so.getId(), "className");
    }
  }

  private void fillParents() throws IOException {
    Gson gson = new Gson();
    List<ParentData> parents =
        gson.fromJson(
            read("src/test/resources/fixture/parents_creation.json"),
            new TypeToken<List<ParentData>>() {
            }.getType());
    for (ParentData p : parents) {
      data.parentService().save(p);
      data.parentService().deleteField(p.getId(), "className");
    }
  }

  private void fillFamilies() throws IOException {
    data.familyService().drop();
    Gson gson = new Gson();
    List<FamilyData> family =
        gson.fromJson(
            read("src/test/resources/fixture/family_creation.json"),
            new TypeToken<List<FamilyData>>() {
            }.getType());
    for (FamilyData f : family) {
      data.familyService().save(f);
      data.familyService().deleteField(f.getId(), "className");
    }
  }

  private void fillTrainers() throws IOException {
    Gson gson = new Gson();
    List<TrainerData> trainers =
        gson.fromJson(
            read("src/test/resources/fixture/trainers_creation.json"),
            new TypeToken<List<TrainerData>>() {
            }.getType());
    for (TrainerData t : trainers) {
      data.trainerService().save(t);
      data.trainerService().deleteField(t.getId(), "className");
    }
  }

  private void fillCandidateVacancies() throws IOException {
    data.candidateService().dropCandVacancy();
    Gson gson = new Gson();
    List<CandidateVacanciesData> vacancies =
        gson.fromJson(
            read("src/test/resources/fixture/vacancies_creation.json"),
            new TypeToken<List<CandidateVacanciesData>>() {
            }.getType());
    for (CandidateVacanciesData v : vacancies) {
      data.candidateService().saveVacancies(v);
      data.candidateService().deleteVacancyField(v.getId(), "className");
    }
  }
}
