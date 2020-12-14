package tests.lkParent;
/* T-89 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с тренером1
/* Есть еще семья : родитель+ученик
/* Есть сотрудник
/* Открыть чат и проверить, что через поиск нельзя админа/сотрудника/др родителя/др ученика
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.FamilyService;
import data.services.ParentService;
import data.services.StudentService;
import data.services.TrainerService;
import data.services.WorkerService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithOtherUsers extends TestBase {

  private final ParentService parentService = new ParentService();
  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final FamilyService familyService = new FamilyService();
  private final WorkerService workerService = new WorkerService();

  @BeforeMethod
  public void ensurePreconditions() {
    String period = "19:00 - 21:00";
    String messageOld = "Привет";
    app.trScheduleTomorrow()
        .SingleScheduleWithOneNewStudent(period, "newSchedule", "23", "newStudent", "1", "ru");

    app.trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial"
        );

    Object[] users = new Object[2];
    users[0] = parentService.findById("22");
    users[1] = trainerService.findById("23");

    app.trChat().saveChatRoom("RoomMessage", new Date(), "d", "23", users, "Parent_Trainer");
    app.trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "22");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "22", 1, 0, "22", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, 1, "22", messageOld);

    app.trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    app.trStudent()
        .newStudent(
            "student",
            "Олег",
            "Олегов",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "123456789",
            "ru",
            "1",
            2,
            "noTrial"
        );
    app.trParent().newParent(
        "newParent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "newFamily",
        "+97895554433",
        "julja@tt.ru",
        true
    );
    app.trWorker().saveNewWorker(
        "newWorker",
        "Sem",
        "Semov",
        "employee",
        "BL",
        "Europe/Minsk",
        "ru",
        "ru",
        "+7123455667",
        "email@email.ru"
    );
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithEmployer() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Semov");
    assertThat(record, equalTo("Ничего не нашли..."));
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithAdmin() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Admin");
    assertThat(record, equalTo("Ничего не нашли..."));
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOtherParent() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Родитель");
    assertThat(record, equalTo("Ничего не нашли..."));
    app.chat().btnCloseChat();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOtherStudent() {
    app.student().refresh();
    app.chat().btnOpenChat();
    String record = app.chat().searchBadPersonByUser("Олегов");
    assertThat(record, equalTo("Ничего не нашли..."));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.postClean().dropChat();
    app.postClean().dropTaskAndSchedule();
    studentService.DeleteById("newStudent");
    workerService.DeleteById("newWorker");
    familyService.DeleteById("newStudent");
    parentService.DeleteById("newParent");
  }
}
