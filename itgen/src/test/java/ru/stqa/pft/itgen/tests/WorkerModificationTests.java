package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerModificationTests extends TestBase {

  @Test
  public void testWorkerModification() {
    app.getNavigationHelper().gotoWorker();
    app.getWorkerHelper().selectedWorker();
    app.getWorkerHelper().modifyWorker();
    app.getWorkerHelper().modifiWorkerForm(new WorkerData("Петя","Абакумов1", null, null, "15.02.1983", "12.11.2020", "М", "Бахрейн", "Пересвет", "(GMT+05:00) Азия/Ташкент", "Английский", "89015555565", "skype1", "74995051023", "87995557893", "tg1", "fb1", "vk1", "ok1", "inst1"));
    app.getWorkerHelper().submitWorkerModify();
  }
}
