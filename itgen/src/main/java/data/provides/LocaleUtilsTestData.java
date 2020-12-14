package data.provides;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.model.candidate.CandidateData;
import data.model.communities.CommunityData;
import data.model.family.FamilyDataUI;
import data.model.lead.LeadData;
import data.model.materials.MaterialData;
import data.model.typeform.TestData;
import data.model.users.ParentData;
import data.model.users.StudentData;
import data.model.users.TrainerData;
import data.model.users.WorkerData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.DataProvider;

public class LocaleUtilsTestData {

 /* private <E> Iterator<Object[]> iteratorFromJson(String filePath) throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(filePath))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<E> entities =
          gson.fromJson(json, new TypeToken<List<E>>() {
          }.getType());
      return entities.stream()
          .map((p) -> new Object[]{p})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validCandidatesFromJson")
  public Iterator<Object[]> validCandidatesFromJson() throws IOException {
    return this.<CandidateData> iteratorFromJson("src/test/resources/testdata/candidate_creation.json");
  }
  */

  @DataProvider(name = "validCandidatesFromJson")
  public static Iterator<Object[]> validCandidatesFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/candidate_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CandidateData> candidate =
          gson.fromJson(json, new TypeToken<List<CandidateData>>() {
          }.getType());
      return candidate.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validCommunityFromJson")
  public static Iterator<Object[]> validCommunityFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/community_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CommunityData> community =
          gson.fromJson(json, new TypeToken<List<CommunityData>>() {
          }.getType());
      return community.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "noValidCommunityFromJson")
  public static Iterator<Object[]> noValidCommunityFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/community_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CommunityData> community =
          gson.fromJson(json, new TypeToken<List<CommunityData>>() {
          }.getType());
      return community.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validModifyCandidatesFromJson")
  public Iterator<Object[]> validModifyCandidatesFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/candidate_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CandidateData> candidates =
          gson.fromJson(json, new TypeToken<List<CandidateData>>() {
          }.getType());
      return candidates.stream()
          .map((p) -> new Object[]{p})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validModifyCommunityFromJson")
  public Iterator<Object[]> validModifyCommunityFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/community_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<CommunityData> community =
          gson.fromJson(json, new TypeToken<List<CommunityData>>() {
          }.getType());
      return community.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validWorkersTrainersFromJson")
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/workers_trainers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers =
          gson.fromJson(json, new TypeToken<List<TrainerData>>() {
          }.getType());
      return trainers.stream().map((t) -> new Object[]{t}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validWorkersFromJson")
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/workers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers =
          gson.fromJson(
              json, new TypeToken<List<WorkerData>>() {
              }.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[]{w}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validFamiliesFromJson")
  public Iterator<Object[]> validFamiliesFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/families_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<FamilyDataUI> families =
          gson.fromJson(json, new TypeToken<List<FamilyDataUI>>() {
          }.getType());
      return families.stream().map((f) -> new Object[]{f}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validLeadsFromJson")
  public Iterator<Object[]> validLeadsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/leads_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<LeadData> leads = gson.fromJson(json, new TypeToken<List<LeadData>>() {
      }.getType());
      return leads.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validModifyLeadsFromJson")
  public Iterator<Object[]> validModifyLeadsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/leads_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<LeadData> leads =
          gson.fromJson(
              json, new TypeToken<List<LeadData>>() {
              }.getType()); // List<StudentData>.class
      return leads.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validStudentsFromJson")
  public Iterator<Object[]> validStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(
              json, new TypeToken<List<StudentData>>() {
              }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "noValidLkParentStudentsFromJson")
  public Iterator<Object[]> noValidLkParentStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/students_par_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(
              json, new TypeToken<List<StudentData>>() {
              }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "noValidStudentsFromJson")
  public Iterator<Object[]> noValidStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_creation_bad.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {
          }.getType());
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validModifyStudentsFromJson")
  public Iterator<Object[]> validModifyStudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/students_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(
              json, new TypeToken<List<StudentData>>() {
              }.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[]{s}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validMaterialLkTrainerFromJson")
  public Iterator<Object[]> validMaterialLkTrainerFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/trainer_material_creation_lk.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<MaterialData> materials =
          gson.fromJson(json, new TypeToken<List<MaterialData>>() {
          }.getType());
      return materials.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validMaterialFromJson")
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
          gson.fromJson(json, new TypeToken<List<MaterialData>>() {
          }.getType());
      return materials.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "noValidMaterialFromJson")
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
          gson.fromJson(json, new TypeToken<List<MaterialData>>() {
          }.getType());
      return materials.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @DataProvider(name = "validParentsFromJson")
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/parents_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents =
          gson.fromJson(
              json, new TypeToken<List<ParentData>>() {
              }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validModifyParentsFromJson")
  public Iterator<Object[]> validModifyParentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/parents_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents =
          gson.fromJson(
              json, new TypeToken<List<ParentData>>() {
              }.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validSelfRegistrationFromJson")
  public Iterator<Object[]> validSelfRegistrationFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/self_registration.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(json, new TypeToken<List<StudentData>>() {
          }.getType());
      return students.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validTestsFromJson")
  public Iterator<Object[]> validTestsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/tests_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TestData> tests = gson.fromJson(json, new TypeToken<List<TestData>>() {
      }.getType());
      return tests.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider(name = "validDataModifyTestFromJson")
  public Iterator<Object[]> validDataModifyTestFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/tests_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TestData> tests = gson.fromJson(json, new TypeToken<List<TestData>>() {
      }.getType());
      return tests.stream().map((p) -> new Object[]{p}).collect(Collectors.toList()).iterator();
    }
  }
}
