package data.provides;

import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.CommentService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialService;
import data.services.PaymentService;
import data.services.ScheduleService;
import data.services.TaskService;

public class Clean {

  private final TaskService taskService = new TaskService();
  private final ScheduleService scheduleService = new ScheduleService();
  private final PaymentService paymentService = new PaymentService();
  private final CommentService commentService = new CommentService();
  private final MaterialChildsService materialChildsService = new MaterialChildsService();
  private final MaterialBranchService materialBranchService = new MaterialBranchService();
  private final FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  private final FinishedLessonService finishedLessonService = new FinishedLessonService();
  private final MaterialService materialService = new MaterialService();
  private final ChatRoomService chatRoomService = new ChatRoomService();
  private final ChatMessageService chatMessageService = new ChatMessageService();
  private final ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();

  public Clean() {
  }

  public Clean dropTaskAndSchedule() {
    scheduleService.drop();
    taskService.drop();
    return this;
  }

  public Clean dropChat() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
    return this;
  }

  public Clean dropMaterial() {
    materialBranchService.drop();
    materialChildsService.drop();
    materialService.drop();
    return this;
  }

  public Clean dropPayment() {
    paymentService.drop();
    return this;
  }

  public Clean dropFinishedLesson() {
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    commentService.drop();
    return this;
  }
}
