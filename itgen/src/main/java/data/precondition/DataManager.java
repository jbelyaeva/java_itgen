package data.precondition;

import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.CommentService;
import data.services.CommunitiesService;
import data.services.FamilyService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialNewService;
import data.services.MaterialService;
import data.services.ParentService;
import data.services.PaymentService;
import data.services.ScheduleService;
import data.services.SkillsService;
import data.services.StudentService;
import data.services.TaskService;
import data.services.TestResultsService;
import data.services.TestService;
import data.services.TrainerService;
import data.services.WorkerService;

public class DataManager {

  private final StudentService studentService = new StudentService();
  private final TaskService taskService = new TaskService();
  private final ScheduleService scheduleService = new ScheduleService();
  private final PaymentService paymentService = new PaymentService();
  private final CommentService commentService = new CommentService();
  private final MaterialChildsService materialChildsService = new MaterialChildsService();
  private final MaterialBranchService materialBranchService = new MaterialBranchService();
  private final MaterialNewService materialNewService = new MaterialNewService();
  private final FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  private final FinishedLessonService finishedLessonService = new FinishedLessonService();
  private final MaterialService materialService = new MaterialService();
  private final ChatRoomService chatRoomService = new ChatRoomService();
  private final ChatMessageService chatMessageService = new ChatMessageService();
  private final ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  private final ParentService parentService = new ParentService();
  private final TrainerService trainerService = new TrainerService();
  private final WorkerService workerService = new WorkerService();
  private final FamilyService familyService = new FamilyService();
  private final SkillsService skillsService = new SkillsService();
  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Clean clean = new Clean();
  private final DefaultFamilyStudentsAndSchedules defFamily = new DefaultFamilyStudentsAndSchedules();
  private final DefaultFamilyChat defFamilyChat = new DefaultFamilyChat();
  private final Communities community = new Communities();
  private final NewFamilyWithoutLessons newFamily = new NewFamilyWithoutLessons();
  private final NewFamilyWithSingleLessons newFamilyWithSingleLessons = new NewFamilyWithSingleLessons();
  private final NewFamilyWithRegularLessons newFamilyWithRegularLessons = new NewFamilyWithRegularLessons();
  private final NewWorker newWorker = new NewWorker();
  private final Payments payments = new Payments();
  private final Skills skills = new Skills();
  private final Chat chat = new Chat();
  private final Tests tests = new Tests();
  private final Schedules schedules = new Schedules();
  private final CommunitiesService communitiesService = new CommunitiesService();
  private final DefaultFamilyAndFinishedLessonWithProjects finishedLessonWithProject = new DefaultFamilyAndFinishedLessonWithProjects();

  public DataManager() {
  }

  public Clean postClean() {
    return clean;
  }

  public StudentService studentService() {
    return studentService;
  }

  public ScheduleService scheduleService() {
    return scheduleService;
  }

  public TaskService taskService() {
    return taskService;
  }

  public PaymentService paymentService() {
    return paymentService;
  }

  public CommentService commentService() {
    return commentService;
  }

  public MaterialChildsService materialChildsService() {
    return materialChildsService;
  }

  public MaterialBranchService materialBranchService() {
    return materialBranchService;
  }

  public MaterialNewService materialNewService() {
    return materialNewService;
  }

  public FinishedChildLessonService finishedChildLessonService() {
    return finishedChildLessonService;
  }

  public FinishedLessonService finishedLessonService() {
    return finishedLessonService;
  }

  public MaterialService materialService() {
    return materialService;
  }

  public ChatRoomService chatRoomService() {
    return chatRoomService;
  }

  public ChatMessageService chatMessageService() {
    return chatMessageService;
  }

  public ChatSubscriptionService chatSubscriptionService() {
    return chatSubscriptionService;
  }

  public ParentService parentService() {
    return parentService;
  }

  public TrainerService trainerService() {
    return trainerService;
  }

  public WorkerService workerService() {
    return workerService;
  }

  public FamilyService familyService() {
    return familyService;
  }

  public SkillsService skillsService() {
    return skillsService;
  }

  public TestService testService() {
    return testService;
  }

  public TestResultsService resultsService() {
    return testResultsService;
  }

  public DefaultFamilyStudentsAndSchedules defFamily() {
    return defFamily;
  }

  public DefaultFamilyChat defFamilyChat() {
    return defFamilyChat;
  }

  public Communities community() {
    return community;
  }

  public NewFamilyWithoutLessons newFamily() {
    return newFamily;
  }

  public NewFamilyWithSingleLessons newFamilyWithSingleLessons() {
    return newFamilyWithSingleLessons;
  }

  public NewFamilyWithRegularLessons newFamilyWithRegularLessons() {
    return newFamilyWithRegularLessons;
  }

  public Payments payments() {
    return payments;
  }

  public Skills skills() {
    return skills;
  }

  public Tests tests() {
    return tests;
  }

  public Chat chat() {
    return chat;
  }

  public Schedules schedules() {
    return schedules;
  }

  public CommunitiesService communitiesService() {
    return communitiesService;
  }

  public NewWorker newWorker() {
    return newWorker;
  }

  public DefaultFamilyAndFinishedLessonWithProjects finishedLessonWithProject() {
    return finishedLessonWithProject;
  }
}
