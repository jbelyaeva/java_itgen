package io.itgen.appmanager.transactionHelper;

import io.itgen.general.TimeGeneral;
import io.itgen.model.general.Activity;
import io.itgen.model.general.Comments;
import io.itgen.model.general.D;
import io.itgen.model.general.New;
import io.itgen.model.general.Old;
import io.itgen.model.tasks.Lesson;
import io.itgen.model.tasks.TaskData;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.services.TrainerService;
import io.itgen.services.WorkerService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TrTaskHelper {

  private final TaskService taskService = new TaskService();
  private final StudentService studentService = new StudentService();
  private final TrainerService trainerService = new TrainerService();
  private final WorkerService workerService = new WorkerService();
  private final ArrayList<String> watchers = new ArrayList<>();
  private final ArrayList<Comments> comments = new ArrayList<>();
  private final ArrayList<Activity> activities = new ArrayList<>();
  private final TimeGeneral time = new TimeGeneral();

  public void newManualTask(
      String idTask,
      String idCreator,
      String idAssignee,
      String text,
      int priority,
      Date createAt,
      String status,
      Date dateWithTime,
      double dateSort,
      String idUser) {
    TaskData task =
        new TaskData()
            .withId(idTask)
            .withCreator(idCreator)
            .withAssignee(idAssignee)
            .withText(text)
            .withPriority(priority)
            .withCreateAt(createAt)
            .withStatus(status)
            .withWatchers(watchers)
            .withComments(comments)
            .withActivity(activities)
            .withDueDateWithTime(dateWithTime)
            .withDueDateSort(dateSort)
            .withLinkUser(idUser);
    taskService.save(task);
  }

  public void newAutoTask(
      String idTask,
      String text,
      Date createAt,
      String status,
      Date dateWithTime,
      double dateSort,
      String idUser,
      String period) {
    TaskData task =
        new TaskData()
            .withId(idTask)
            .withT(text)
            .withCreateAt(createAt)
            .withStatus(status)
            .withWatchers(watchers)
            .withComments(comments)
            .withActivity(activities)
            .withDueDateWithTime(dateWithTime)
            .withDueDateSort(dateSort)
            .withLinkUser(idUser)
            .withLesson(
                new Lesson()
                    .withSId("recordOnSchedule")
                    .withSt((double) time.getMsLocalTime())
                    .withW(time.Stime(period)));
    taskService.save(task);
  }

  public void saveAutoTask(
      String idTask,
      String text,
      Date createAt,
      String status,
      Date dateWithTime,
      double dateSort,
      String idAssignee,
      String idUser,
      String uId,
      String period,
      Date[] dates,
      String[] texts,
      String[] clients,
      String[] commentaries,
      String activities) {
    TaskData task =
        new TaskData()
            .withId(idTask)
            .withT(text)
            .withCreateAt(createAt)
            .withStatus(status)
            .withWatchers(getWatchers(idAssignee, activities))
            .withComments(getComments(commentaries, activities))
            .withActivity(
                getActivities(
                    idAssignee, activities, uId, idUser, dates, texts, clients, commentaries))
            .withDueDateWithTime(dateWithTime)
            .withDueDateSort(dateSort)
            .withAssignee(idAssignee)
            .withLinkUser(idUser)
            .withPriority(0)
            .withLesson(
                new Lesson()
                    .withSId("Schedule")
                    .withSt((double) time.getMsLocalTime())
                    .withW(time.Stime(period)));
    taskService.save(task);
  }

  public void saveManualTask(
      String idTask,
      String text,
      Date createAt,
      String status,
      Date dateWithTime,
      double dateSort,
      String idAssignee,
      String idUser,
      String creator,
      String uId,
      int priority,
      Date[] dates,
      String[] texts,
      String[] clients,
      String[] commentaries,
      String activities) {
    TaskData task =
        new TaskData()
            .withId(idTask)
            .withText(text)
            .withCreateAt(createAt)
            .withStatus(status)
            .withWatchers(getWatchers(idAssignee, activities))
            .withComments(getComments(commentaries, activities))
            .withActivity(
                getActivities(
                    idAssignee, activities, uId, idUser, dates, texts, clients, commentaries))
            .withDueDateWithTime(dateWithTime)
            .withDueDateSort(dateSort)
            .withAssignee(idAssignee)
            .withLinkUser(idUser)
            .withCreator(creator)
            .withPriority(priority);
    taskService.save(task);
  }

  private List<Activity> getActivities(
      String idAssignee,
      String activites,
      String uId,
      String idUser,
      Date[] dates,
      String[] texts,
      String[] clients,
      String[] commentaries) {
    switch (activites) {
      case ("newAutoTask_takeAutoTask"):
        return Collections.singletonList(
            new Activity().withUId(idAssignee).withTs(new Date()).withT("tookToWork"));
      case ("takeAutoTask_doneAutoTask"):
        return Arrays.asList(
            new Activity().withUId(uId).withTs(new Date()).withT("tookToWork"),
            new Activity().withUId(uId).withTs(new Date()).withT("completed"),
            new Activity().withUId("-1").withTs(new Date()).withT("closed"));
      case ("takeAutoTask_takeOnControlAutoTask"):
        return Arrays.asList(
            new Activity().withUId(idAssignee).withTs(new Date()).withT("tookToWork"),
            new Activity().withUId(idAssignee).withTs(new Date()).withT("watched"));
      case ("takeAutoTask_changeAssigneeAutoTask"): // супера на тренера
        return Arrays.asList(
            new Activity().withUId(uId).withTs(new Date()).withT("tookToWork"),
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("assigneeChanged")
                .withD(
                    new D()
                        .withNewChangeData(
                            new New()
                                .withId(idAssignee)
                                .withN(
                                    ""
                                        + trainerService.findById(idAssignee).getFirstName()
                                        + " "
                                        + trainerService.findById(idAssignee).getLastName()
                                        + ""))
                        .withOldChangeData(
                            new Old()
                                .withId(uId)
                                .withN(
                                    ""
                                        + workerService.findById(uId).getFirstName()
                                        + " "
                                        + workerService.findById(uId).getLastName()
                                        + ""))));
      case ("takeAutoTask_changeDateAutoTask"):
        return Arrays.asList(
            new Activity().withUId(uId).withTs(new Date()).withT("tookToWork"),
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("dateChanged")
                .withD(
                    new D()
                        .withNewChangeData(new New().withV(dates[1]))
                        .withOldChangeData(new Old().withV(dates[0]))),
            new Activity().withUId(uId).withTs(new Date()).withT("completed"));
      case ("takeAutoTask_changeClientAutoTask"):
        return Arrays.asList(
            new Activity().withUId(uId).withTs(new Date()).withT("tookToWork"),
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("linkUserUpdated")
                .withD(
                    new D()
                        .withNewChangeData(
                            new New()
                                .withId(idUser)
                                .withN(
                                    ""
                                        + studentService.findById(idUser).getFirstname()
                                        + " "
                                        + studentService.findById(idUser).getLastname()
                                        + "")
                                .withT(studentService.findById(idUser).getRoles().get(0)))
                        .withOldChangeData(
                            new Old()
                                .withId(clients[0])
                                .withN(
                                    ""
                                        + studentService.findById(clients[0]).getFirstname()
                                        + " "
                                        + studentService.findById(clients[0]).getLastname()
                                        + "")
                                .withT(studentService.findById(clients[0]).getRoles().get(0)))));
      case ("newTask_leaveAutoCommentTask"):
        return Arrays.asList(
            new Activity().withUId(uId).withTs(new Date()).withT("tookToWork"),
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("commentAdded")
                .withD(new D().withNewData(commentaries[0])));
      case ("newTask_waitAnswer"):
        return Collections.singletonList(
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("statusChanged")
                .withD(new D().withNewData("wait").withOldData("open")));
      case ("newTask_doneTask"):
        return Collections.singletonList(
            new Activity()
                .withUId(idAssignee)
                .withTs(new Date())
                .withT("statusChanged")
                .withD(new D().withNewData("closed").withOldData("open")));
      case ("newTask_changeDateTask"):
        return Collections.singletonList(
            new Activity()
                .withUId(idAssignee)
                .withTs(new Date())
                .withT("dateChanged")
                .withD(
                    new D()
                        .withNewChangeData(new New().withV(dates[1]))
                        .withOldChangeData(new Old().withV(dates[0]))));
      case ("newTask_takeOnControlTask"):
        return Collections.singletonList(
            new Activity().withUId(idAssignee).withTs(new Date()).withT("watched"));
      case ("newTask_changeTextTask"):
        return Collections.singletonList(
            new Activity()
                .withUId(idAssignee)
                .withTs(new Date())
                .withT("textChanged")
                .withD(new D().withNewData(texts[1]).withOldData(texts[0])));
      case ("newTask_changePriority"):
        return Collections.singletonList(
            new Activity().withUId(idAssignee).withTs(new Date()).withT("priorityChanged"));
      case ("newTask_changeAssignee"): // супера на тренера
        return Collections.singletonList(
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("assigneeChanged")
                .withD(
                    new D()
                        .withNewChangeData(
                            new New()
                                .withId(idAssignee)
                                .withN(
                                    ""
                                        + trainerService.findById(idAssignee).getFirstName()
                                        + " "
                                        + trainerService.findById(idAssignee).getLastName()
                                        + ""))
                        .withOldChangeData(
                            new Old()
                                .withId(uId)
                                .withN(
                                    ""
                                        + workerService.findById(uId).getFirstName()
                                        + " "
                                        + workerService.findById(uId).getLastName()
                                        + ""))));
      case ("newTask_leaveCommentTask"):
        return Collections.singletonList(
            new Activity()
                .withUId(uId)
                .withTs(new Date())
                .withT("commentAdded")
                .withD(new D().withNewData(commentaries[0])));
      default:
        return new ArrayList<>();
    }
  }

  private List<String> getWatchers(String idAssignee, String activites) {
    if (activites.equals("takeAutoTask_takeOnControlAutoTask")
        || activites.equals("newTask_takeOnControlTask")) {
      String[] watchers = {idAssignee};
      return Arrays.asList(watchers);
    }
    return new ArrayList<>();
  }

  private List<Comments> getComments(String[] commentaries, String activites) {
    String id = "";

    switch (activites) {
      case ("newTask_leaveCommentTask"):
        id = "different";
        break;
      case ("newTask_leaveAutoCommentTask"):
        id = "differents";
        break;
      default:
        return new ArrayList<>();
    }

    return Arrays.asList(
        new Comments()
            .withId(id)
            .withOwner(commentaries[1])
            .withText(commentaries[0])
            .withCreateAt(new Date()));
  }
}
