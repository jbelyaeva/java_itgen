package data.precondition;

public class Clean {

  public static final DataManager data = new DataManager();

  public Clean() {
  }

  public Clean taskAndSchedule() {
    data.scheduleService().drop();
    data.taskService().drop();
    return this;
  }

  public Clean chat() {
    data.chatRoomService().drop();
    data.chatMessageService().drop();
    data.chatSubscriptionService().drop();
    return this;
  }

  public Clean material() {
    data.materialBranchService().drop();
    data.materialChildsService().drop();
    data.materialService().drop();
    return this;
  }

  public Clean payment() {
    data.paymentService().drop();
    return this;
  }

  public Clean student() {
    data.studentService().DeleteById("newStudent");
    data.studentService().DeleteById("student");
    return this;
  }

  public Clean parent() {
    data.parentService().DeleteById("newParent");
    return this;
  }

  public Clean worker() {
    data.workerService().DeleteById("newWorker");
    return this;
  }

  public Clean family() {
    data.familyService().DeleteById("newFamily");
    return this;
  }

  public Clean finishedLesson() {
    data.finishedChildLessonService().drop();
    data.finishedLessonService().drop();
    data.commentService().drop();
    return this;
  }
}
