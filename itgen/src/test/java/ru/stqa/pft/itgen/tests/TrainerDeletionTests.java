package ru.stqa.pft.itgen.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TrainerDeletionTests extends TestBase {


  @Test (enabled = false)
  public void testTrainerDeletion() {
    app.getNavigationHelper().gotoTasks();
    app.getNavigationHelper().gotoTrainer();
    int before = app.getTrainerHelper().getTrainerCount();
    app.getTrainerHelper().selectedTrainer();  // выбирает 9-го по списку тренера (его надо предварительно создать!!!)
    app.getTrainerHelper().deleteTrainer();
    app.getTrainerHelper().assertDeleteSelectedTrainer();
    app.getNavigationHelper().gotoTrainer();
    int after = app.getTrainerHelper().getTrainerCount();
    Assert.assertEquals(after, before - 1);
  }
}
