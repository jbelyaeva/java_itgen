package tests.task;
/*автотаска, которую взял пользователь будет всегда в стеке, пока не выполнится. Остальные автотаски
 * не отображаются  в стеке, т.к. не имеют создателя(авто) и пока исполнителя, но их количество указано в счетчике
 * в табе. Счетчик в меню (красный) считает те задачи, в которых пользователь исполнитель.*/
// фильтр по умолчанию на среднем и высоком приоритете, чек-бокс на исполнителе

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.FamilyService;
import data.services.StudentService;
import data.services.TaskService;
import java.util.ArrayList;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskFiltrByPriorityInStack extends TestBase {

  private final TaskService taskService = new TaskService();
  private final StudentService studentService = new StudentService();
  private final FamilyService familyService = new FamilyService();
  private final Date createAt = new Date();
  private final Date duoDateWithTimeFirst = new Date();
  private final Date duoDateWithTimeSecond = new Date(new Date().getTime() - 86400000);
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("Student", false, "txc");

    app.trStudent()
        .newStudent(
            "Student",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Student",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");

    app.trTask()
        .newManualTask(
            "FiltrTaskFirst",
            "666",
            "777",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "21");
    app.trTask()
        .newAutoTask(
            "FiltrTaskAutoFirst",
            "contactForPayment",
            createAt,
            "open",
            duoDateWithTimeFirst,
            duoDateSort,
            "21",
            "21.00 : 23.00");
    taskService.deleteField("FiltrTaskAutoFirst", "priority");
    app.trTask()
        .newManualTask(
            "FiltrTaskSecond",
            "666",
            "666",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .newManualTask(
            "FiltrTaskThird",
            "666",
            "14", // Настя Бокша - тренер
            "Проверить материалы",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .saveAutoTask(
            "FiltrTaskAutoSecond",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTimeSecond,
            duoDateSort,
            "666",
            "21",
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
    taskService.deleteField("FiltrTaskAutoSecond", "priority");
    app.trTask()
        .saveManualTask(
            "FilterDone",
            "Записать на пробное",
            createAt,
            "closed",
            duoDateWithTimeSecond,
            duoDateSort,
            "666",
            "21",
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");

    app.trTask()
        .saveManualTask(
            "FiltrWait",
            "Записать на пробное",
            createAt,
            "wait",
            duoDateWithTimeFirst,
            duoDateSort,
            "666",
            "21",
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_waitAnswer");
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, статус Выполненные
  public void testFiltrByPriorityInStack_1() throws InterruptedException {
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
  public void testFiltrByPriorityInStack_2() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();

    // проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    // проверка, что найдена именно Настя Бокша и она одна
    assertThat(app.task().getNameClientUI(), equalTo("Машина Маша"));
    // проверка, счетчика задач
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    // проверка, красного счетчика
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, чек-бокс Создатель, Не выполненные
  public void testFiltrByPriorityInStack_3() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().selectAssigner(); // сняли чб Исполнитель
    app.task().selectCreator();
    // проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(3));
    // проверка, что выборка правильная
    ArrayList<String> listExpected = new ArrayList<String>();
    listExpected.add("Машина Маша");
    listExpected.add("Машина Маша");
    listExpected.add("Дефолтный Ребенок");
    assertThat(app.task().getNamesClientUI(), equalTo(listExpected));

    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, чек-бокс Исполнитель, Не выпол
  public void testFiltrByPriorityInStack_4() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnLowPriority();
    Thread.sleep(5000);
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет низкий, чек-бокс Создатель, Не выпол
  public void testFiltrByPriorityInStack_5() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().selectAssigner(); // убрали чб Исполнитель
    app.task().selectCreator();
    app.task().btnLowPriority();
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, статус Выполненные, Исполнитель
  public void testFiltrByPriorityInStack_6() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();
    app.task().selectStatusDone();
    assertThat(app.task().getCountSearchUI(), equalTo(0));
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
  }

  @Test(retryAnalyzer = RunTestAgain.class) // приоритет средний, статус Жду ответа, Исполнитель
  public void testFiltrByPriorityInStack_7() throws InterruptedException {
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
    taskService.drop();
    studentService.DeleteById("Student");
    familyService.DeleteById("Student");
  }
}
