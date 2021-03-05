package data.precondition;

import data.services.TaskService;
import java.util.Date;

public class TasksManual extends TransactionManager {

  private final TaskService taskService = new TaskService();

  protected static final DataManager data = new DataManager();

  //Звязаться с кандидатом, создана супером
  public void set1_newManualTaskCandidate(String text, String status) {
    trTask()
        .newManualTask(
            "task",
            "666",
            "666",
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            null);
    taskService.updateField("task", "linkCandidate", "candidate");
  }

  //Выполнена таска супером
  public void set2_doneNewManualTaskCandidate(String text, String status) {
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveManualTask(
            "task",
            text,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            "666",
            null,
            "666",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");
    taskService.updateField("task", "linkCandidate", "candidate");
  }

  public void set3_newManualTaskOnSuperAdmin(String id, String text, String status, String idUser) {
    trTask()
        .newManualTask(
            id,
            "666",
            "666",
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            idUser);
  }

  public void set4_newManualTaskOnAdmin(String id, String text, String status, String idUser) {
    trTask()
        .newManualTask(
            id,
            "666",
            "777",
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            idUser);
  }

  public void set7_newManualTaskOnTrainer(String id, String text, String status, String idTrainer,
      String idUser) {
    trTask()
        .newManualTask(
            id,
            "666",
            idTrainer,
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            idUser);
  }

  public void set6_newManualTaskCreatorAdminAssigneeSuper(String id, String text, String status,
      String idUser) {
    trTask()
        .newManualTask(
            id,
            "777",
            "666",
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            idUser);
  }

  public void set6_1_ManualTaskCreatorAdminAssigneeSuperPr0(String id, String text, String status,
      String idUser) {
    trTask()
        .newManualTask(
            id,
            "777",
            "666",
            text,
            0,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            idUser);
  }

  //Таска на Бокшу
  public void set7_newManualTaskOnTrainer(String id, String text, String status, String idUser) {
    trTask()
        .newManualTask(
            id,
            "666",
            "14",
            text,
            1,
            new Date(),
            status,
            new Date(),
            new Date().getTime(),
            null);
  }

  //ручная таска созданная вчера на Дефолтного студента, период: "21.00 : 23.00"
  //Выполнена
  public void set8_ManualTaskYesterdayDone(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date(new Date().getTime() - 86400000);
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            new Date().getTime(),
            "666",
            idUser,
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");
  }

  //ручная сегодня созданная сегодня на Дефолтного студента, период: "21.00 : 23.00"
  //Выполнена
  public void set9_ManualTaskDone(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            new Date().getTime(),
            "666",
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");
  }

  public void set9_1_ManualTaskDonePr0(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            new Date().getTime(),
            "666",
            idUser,
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_doneTask");
  }

  public void set10_ManualTaskYesterdayWaitAnswer(String id, String text, String status,
      String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date(new Date().getTime() - 86400000);
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask().saveManualTask(
        id,
        text,
        createAt,
        status,
        duoDateWithTime,
        new Date().getTime(),
        "666",
        idUser,
        "777",
        "666",
        1,
        dates,
        texts,
        clients,
        commentaries,
        "newTask_waitAnswer");
  }

  //ручная таска сегодня, статус Жду ответа
  public void set11_ManualTaskTodayWaitAnswer(String id, String text, String status,
      String idUser) {
    Date createAt = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask().saveManualTask(
        id,
        text,
        createAt,
        status,
        new Date(),
        new Date().getTime(),
        "666",
        idUser,
        "777",
        "666",
        1,
        dates,
        texts,
        clients,
        commentaries,
        "newTask_waitAnswer");
  }

  //ручная таска сегодня, статус Жду ответа
  public void set11_1_ManualTaskTodayWaitAnswerPr0(String id, String text, String status,
      String idUser) {
    Date createAt = new Date();
    Date[] dates = null;
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    trTask().saveManualTask(
        id,
        text,
        createAt,
        status,
        new Date(),
        new Date().getTime(),
        "666",
        idUser,
        "777",
        "666",
        0,
        dates,
        texts,
        clients,
        commentaries,
        "newTask_waitAnswer");
  }

  //ручная таска, создана сегодня, взята на контроль, создатель Админ, повешена на супера
  public void set12_ManualTaskOnControl(String id, String text, String status, String idUser) {
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    long duoDateSort = new Date().getTime();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_takeOnControlTask");
  }

  //ручная таска, создана сегодня, взята на контроль, создатель Админ, повешена на супера
  public void set12_1_ManualTaskOnControlPr0(String id, String text, String status, String idUser) {
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    long duoDateSort = new Date().getTime();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_takeOnControlTask");
  }

  //ручная таска, создана сегодня, изменена дата, создатель Админ, повешена на супера
  public void set13_ManualTaskChangeDateTask(String id, String text, String status, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    long duoDateSort = new Date().getTime() + 86400000;
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changeDateTask");
  }

  //ручная таска, создана сегодня, изменена дата, создатель Админ, повешена на супера
  public void set13_1_ManualTaskChangeDateTaskPr0(String id, String text, String status,
      String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    long duoDateSort = new Date().getTime() + 86400000;
    trTask()
        .saveManualTask(
            id,
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            0,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changeDateTask");
  }

  //ручная таска, создана сегодня, изменен исполнитель
  public void set14_ManualTaskChangeAssignee(String text, String status, String idUser,
      String idAssignee) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    long duoDateSort = new Date().getTime();
    trTask()
        .saveManualTask(
            "task",
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            idAssignee,
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changeAssignee");
  }

  //ручная таска, создана сегодня, изменен приоритет
  public void set15_ManualTaskChangePriority(String text, String status, String idUser,
      int priority) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] texts = null;
    String[] clients = null;
    String[] commentaries = null;
    long duoDateSort = new Date().getTime();

    trTask()
        .saveManualTask(
            "task",
            text,
            createAt,
            status,
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            priority,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changePriority");
  }

  //ручная таска, создана сегодня, изменен заголовок
  public void set16_ManualTaskChangeText(String text, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] clients = null;
    String[] commentaries = null;
    long duoDateSort = new Date().getTime();
    String[] texts = new String[]{"Записать на пробное", "Записать на новое пробное"};
    trTask()
        .saveManualTask(
            "task",
            text,
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changeTextTask");
  }

  //ручная таска, создана сегодня, оставлен комментарий
  public void set17_ManualTaskLeaveComment(String text, String idUser) {
    Date createAt = new Date();
    Date duoDateWithTime = new Date();
    Date[] dates = new Date[]{createAt, duoDateWithTime};
    String[] clients = null;
    long duoDateSort = new Date().getTime();
    String[] texts = null;
    String[] commentaries = new String[]{"Комментарий, comments", "666"};
    trTask()
        .saveManualTask(
            "PopupLeaveCommentByManualTask",
            text,
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            "666",
            idUser,
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_leaveCommentTask");
  }
}
