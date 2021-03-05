package tests.task;
/*автотаска, которую взял пользователь будет всегда в стеке, пока не выполнится. Остальные автотаски
 * не отображаются  в стеке, т.к. не имеют создателя(авто) и пока исполнителя, но их количество указано в счетчике
 * в табе. Счетчик в меню (красный) считает те задачи, в которых пользователь исполнитель.*/
// в данном кейсе проверяется, что автотаска, взятая в работу есть в стеке, и по фильтру выходит
// 1 ручная таска с исполтнителем - укзаный юзер

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskFilterByUserInStack extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set1_FamilyWithStudentAndParent();
    data.tasksManual()
        .set4_newManualTaskOnAdmin("FilterTaskFirst", "Узнать почту у родителя", "open", "21");
    data.tasksAuto().set5_newAutoTask("FilterTaskAutoFirst", "contactForPayment", "open", "21");
    data.tasksManual()
        .set6_newManualTaskCreatorAdminAssigneeSuper("FilterTaskSecond",
            "Узнать почту у родителя", "open", "student");
    data.tasksManual()
        .set7_newManualTaskOnTrainer("FilterTaskThird", "Проверить материалы", "open", "14",
            "student");
    data.tasksAuto()
        .set2_AutoTaskYesterdayTake("FilterTaskAutoSecond", "contactForPayment", "inProgress",
            "21");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testFiltrByUserInStack() throws InterruptedException {
    app.goTo().urlTasks();
    app.task().btnMiddlePriority();
    app.task().filtrByUserInStek("Бокша");

    // проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    // проверка, что найдена именно Настя Бокша и она одна
    assertThat(app.task().getNameSearchUI(), equalTo("Бокша Настя"));
    // проверка, что счетчик задач в окне = 1
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    // проверка, что красный счетчик в меню = 1
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
    app.task().refresh();
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student().parent().taskAndSchedule();
  }
}
