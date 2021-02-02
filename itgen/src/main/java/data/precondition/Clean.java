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
    data.materialNewService().drop();
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
    data.parentService().DeleteById("parent");
    return this;
  }

  public Clean worker() {
    data.workerService().DeleteById("newWorker");
    return this;
  }

  public Clean trainer() {
    data.trainerService().DeleteById("newTrainer");
    return this;
  }

  public Clean family() {
    data.familyService().DeleteById("newFamily");
    data.familyService().DeleteById("family");
    return this;
  }

  public Clean finishedLesson() {
    data.finishedChildLessonService().drop();
    data.finishedLessonService().drop();
    data.commentService().drop();
    return this;
  }

  public Clean tests() {
    data.testService().drop();
    data.resultsService().drop();
    return this;
  }

  public Clean communities() {
    data.communitiesService().dropCommunity();
    data.communitiesService().dropCommPost();
    data.communitiesService().dropCommPostComment();
    return this;
  }

  public Clean achievements() {
    data.achievementsService().drop();
    return this;
  }

  public Clean requests() {
    data.requestService().drop();
    return this;
  }
}
