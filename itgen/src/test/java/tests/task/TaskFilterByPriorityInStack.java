package tests.task;
/*автотаска, которую взял пользователь будет всегда в стеке, пока не выполнится. Остальные автотаски
 * не отображаются  в стеке, т.к. не имеют создателя(авто) и пока исполнителя, но их количество указано в счетчике
 * в табе. Счетчик в меню (красный) считает те задачи, в которых пользователь исполнитель.*/
// фильтр по умолчанию на среднем и высоком приоритете, чек-бокс на исполнителе

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import java.util.ArrayList;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskFilterByPriorityInStack extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.tasksManual()
        .set4_newManualTaskOnAdmin("FilterTaskFirst", "Узнать почту у родителя", "open", "21");
    data.tasksAuto().set5_newAutoTask("FilterTaskAutoFirst", "contactForPayment", "open", "21");
    data.tasksManual()
        .set3_newManualTaskOnSuperAdmin("FilterTaskSecond", "Узнать почту у родителя", "open",
            "student");
    data.tasksManual()
        .set7_newManualTaskOnTrainer("FilterTaskThird", "Проверить материалы", "open", "14",
            "student");
    data.tasksAuto()
        .set2_AutoTaskYesterdayTake("FilterTaskAutoSecond", "contactForPayment", "inProgress",
            "21");
    data.tasksManual()
        .set8_ManualTaskYesterdayDone("FilterDone", "Записать на пробное", "closed", "21");
    data.tasksManual()
        .set11_ManualTaskTodayWaitAnswer("FilterWait", "Записать на пробное", "wait", "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, статус Выполненные
  public void testFilterByPriorityInStack_1() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().selectStatusDone();
    app.task().btnLowPriority();
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    assertThat(app.task().getNameClientUI(), equalTo("Дефолтный Ребенок"));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(
      retryAnalyzer = RunTestAgain.class) // приоритет средний, чек-бокс Исполнитель, Не Выполненные
  public void testFilterByPriorityInStack_2() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();

    // проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    // проверка, что найдена именно Настя Бокша и она одна
    assertThat(app.task().getNameClientUI(), equalTo("Олегов Олег"));
    // проверка, счетчика задач
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    // проверка, красного счетчика
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, чек-бокс Создатель, Не выполненные
  public void testFilterByPriorityInStack_3() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().selectAssigner(); // сняли чб Исполнитель
    app.task().selectCreator();
    // проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(3));
    // проверка, что выборка правильная
    ArrayList<String> listExpected = new ArrayList<String>();
    listExpected.add("Олегов Олег");
    listExpected.add("Олегов Олег");
    listExpected.add("Дефолтный Ребенок");
    assertThat(app.task().getNamesClientUI(), equalTo(listExpected));

    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, чек-бокс Исполнитель, Не выпол
  public void testFilterByPriorityInStack_4() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnLowPriority();
    Thread.sleep(5000);
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, чек-бокс Создатель, Не выпол
  public void testFilterByPriorityInStack_5() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().selectAssigner(); // убрали чб Исполнитель
    app.task().selectCreator();
    app.task().btnLowPriority();
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, статус Выполненные, Исполнитель
  public void testFilterByPriorityInStack_6() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();
    app.task().selectStatusDone();
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, статус Жду ответа, Исполнитель
  public void testFilterByPriorityInStack_7() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();
    app.task().selectStatusWaitAnswer();
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    assertThat(app.task().getNameClientUI(), equalTo("Дефолтный Ребенок"));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student().family();
  }
}
