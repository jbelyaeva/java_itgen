package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerNewMessageInChatOnLesson extends TestBase {
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamilyOnLesson().set2_StudentOnLesson();
    data.chat().set1_DialogStudentTrainer(messageOld, "student", "23");

  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerNewMessageInChatOnLesson() {
    app.trainer().maxBrowser();
    app.trainer().goToLesson("newSchedule");
    assertThat(app.chat().indicatorNewMessageOnLessonByTrainer(), equalTo(true));
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().student().family().finishedLesson().taskAndSchedule().chat();
  }
}
