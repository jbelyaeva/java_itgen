package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerProfileData;

public class WorkerModificationTests extends TestBase {

  @Test
  public void testWorkerModification() {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().selectedWorker();
    app.getWorkerHelper().modifyWorker();
    app.getWorkerHelper().modifiWorkerForm(new WorkerProfileData("Сережа", "Абак", "01.01.2001", "02.02.2002", "Ж", "Барбадос", "Чикаго", "(GMT-05:00) Тихий океан/о-в Пасхи", "Английский", "89669878585", "sk", "11111111111", "11111111111", "te", "fb", "vk", "ok", "inst"));
    app.getWorkerHelper().submitWorkerModify();
  }
}
