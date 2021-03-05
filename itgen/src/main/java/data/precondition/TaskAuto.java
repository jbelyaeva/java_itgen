package data.precondition;

import data.services.TaskService;
import java.util.Date;

public class TaskAuto extends TransactionManager {

  private final TaskService taskService = new TaskService();

  protected static final DataManager data = new DataManager();

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
//в работе
  public void set1_newAutoTaskToday(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    long duoDateSort = new Date().getTime();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveAutoTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            idUser,
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
    taskService.deleteField(id, "priority");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //В работе
  public void set2_AutoTaskYesterdayTake(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date(new Date().getTime() - 86400000);
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveAutoTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            new Date().getTime(),
            "666",
            idUser,
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
    taskService.deleteField(id, "priority");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Выполнена
  public void set3_AutoTaskTodayDone(String text, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date(new Date().getTime() - 86400000);
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveAutoTask(
            "task",
            text,
            createAt,
            "closed",
            duoDateWithTime,
            new Date().getTime(),
            null,
            idUser,
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_doneAutoTask");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Взята на контроль
  public void set4_AutoTaskTodayControl(String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date(new Date().getTime() - 86400000);
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveAutoTask(
            "AutoTaskTakeOnControl",
            text,
            createAt,
            status,
            duoDateWithTime,
            new Date().getTime(),
            "666",
            idUser,
            idUser,
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_takeOnControlAutoTask");
  }

  //сегодня новая автотаска
  public void set5_newAutoTask(String id, String text, String status, String idUser) {
    Date duoDateWithTime = new Date();
    long duoDateSort = new Date().getTime();
    trTask()
        .newAutoTask(
            id,
            text,
            new Date(),
            status,
            duoDateWithTime,
            duoDateSort,
            idUser,
            "21.00 : 23.00");
    taskService.deleteField(id, "priority");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Взята изменен исполнитель
  public void set6_AutoTaskCreateChangeAssignee(String text, String status, String idUser,
      String idAssignee) {
    Date createAt = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveAutoTask(
            "task",
            text,
            createAt,
            status,
            new Date(),
            new Date().getTime(),
            idAssignee,
            idUser,
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_changeAssigneeAutoTask");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Взята изменен клиент
  public void set7_AutoTaskCreateChangeClient(String text, String status, String idUser) {
    String[] clients = new String[]{"21"};
    Date createAt = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] commentaries = null;

    trTask()
        .saveAutoTask(
            "task",
            text,
            createAt,
            status,
            new Date(),
            new Date().getTime(),
            "666",
            idUser,
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_changeClientAutoTask");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Дата стала на завтра
  public void set8_AutoTaskСhangeDate(String text, String status, String idUser) {
    long duoDateSort = new Date().getTime() + 86400000; // на завтра
    Date duoDateWithTime = new Date(duoDateSort);
    String[] clients = new String[]{"21"};
    Date createAt = new Date();
    String[] texts = null;
    String[] commentaries = null;

    Date[] dates = new Date[]{createAt, duoDateWithTime};
    trTask()
        .saveAutoTask(
            "task",
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            null,
            idUser,
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "takeAutoTask_changeDateAutoTask");
  }

  //автотаска сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //с комментом
  public void set9_AutoTaskLeaveComment(String text, String status, String idUser) {
    long duoDateSort = new Date().getTime() + 86400000; // на завтра
    Date duoDateWithTime = new Date(duoDateSort);
    String[] clients = new String[]{"21"};
    Date createAt = new Date();
    String[] texts = null;
    Date[] dates = null;

    String[] commentaries = new String[]{"Комментарий, comments", "666"};
    trTask()
        .saveAutoTask(
            "AutoTaskLeaveCommentInPopup",
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "666",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newTask_leaveAutoCommentTask");
  }
}
