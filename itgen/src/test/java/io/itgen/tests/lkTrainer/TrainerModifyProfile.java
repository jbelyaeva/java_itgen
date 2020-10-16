package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.general.RunTestAgain;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import io.itgen.model.TrainerData;
import io.itgen.model.Trainers;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
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

public class TrainerModifyProfile extends TestBase {

  @DataProvider
  public Iterator<Object[]> ValidTrainerFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/trainer_modification-lk.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers =
          gson.fromJson(
              json, new TypeToken<List<TrainerData>>() {}.getType());
      return trainers.stream().map((s) -> new Object[] {s}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "ValidTrainerFromJson", retryAnalyzer = RunTestAgain.class)
  public void testTrainerModifyProfile(TrainerData trainer)  {
    Trainers before = app.db().trainers();
    app.trainer().modifyInLk(trainer);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size()));
  }
}
