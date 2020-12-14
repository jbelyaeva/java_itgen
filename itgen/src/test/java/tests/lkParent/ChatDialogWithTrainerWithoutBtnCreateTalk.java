package tests.lkParent;
/* T-91 */
/* В дефолтную семью добавлен ученик в статусе Будет пробное
/* У родителя есть диалог с тренером
/* Открыть чат и проверить, что нет кнопки Создать беседу
 */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.ParentService;
import data.services.StudentService;
import data.services.TrainerService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDialogWithTrainerWithoutBtnCreateTalk extends TestBase {

  private final ParentService parentService = new ParentService();
  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();

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
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogWithTrainerWithoutBtnCreateTalk() {
    app.student().refresh();
    app.chat().btnOpenChat();
    app.check().notFindElement(app.chat().getBtnCreateTalk());
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    app.postClean().dropChat();
    app.postClean().dropTaskAndSchedule();
    studentService.DeleteById("newStudent");
  }
}
