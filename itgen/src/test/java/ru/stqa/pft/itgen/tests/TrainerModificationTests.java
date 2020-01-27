package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

public class TrainerModificationTests extends TestBase {

  @Test
  public void testTrainerModification() {
    app.getNavigationHelper().gotoTrainer();
    app.getTrainerHelper().selectedTrainer();  // выбирает 9-го по списку тренера (его надо предварительно создать!!!)
    app.getTrainerHelper().modifyTrainer();
    app.getTrainerHelper().modifiTrainerForm(new TrainerData("Ганц25", "Абак896", "01.10.1950", "15.05.1905", "Ж", "3", "Белиз", "(GMT-06:00) Америка/Бойла, Северная Дакота", "Английский", "Сватково", "4", "89115223697", "sk9", "89035489756", "89165587893", "tg56", "fb7", "vk00", "ok78", "inst555", "hello, world!!!"));
    app.getTrainerHelper().submitTrainerModify();
  }
}
