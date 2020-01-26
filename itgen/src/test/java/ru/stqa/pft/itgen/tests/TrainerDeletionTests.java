package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class TrainerDeletionTests extends TestBase {


  @Test
  public void testTrainerDeletion() {
    app.getNavigationHelper().gotoTrainer();
    app.getTrainerHelper().selectedTrainer();  // выбирает 9-го по списку тренера (его надо предварительно создать!!!)
    app.getTrainerHelper().deleteTrainer();
    app.getTrainerHelper().assertDeleteSelectedTrainer();
  }
}
