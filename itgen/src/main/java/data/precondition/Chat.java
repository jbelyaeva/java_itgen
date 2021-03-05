package data.precondition;

import data.model.users.ParentData;
import data.model.users.StudentData;
import data.model.users.TrainerData;
import data.model.users.WorkerData;
import data.services.ParentService;
import data.services.StudentService;
import data.services.TrainerService;
import data.services.WorkerService;
import java.util.Date;

public class Chat extends TransactionManager {

  protected static final DataManager data = new DataManager();
  private TrainerService trainerService = new TrainerService();
  private StudentService studentService = new StudentService();
  private WorkerService workerService = new WorkerService();
  private ParentService parentService = new ParentService();

  //Диалог студента с тренером. Диалог начал ребенок
  public void set1_DialogStudentTrainer(String messageOld, String idStudent, String idTrainer) {
    StudentData student = studentService.findById(idStudent);
    TrainerData trainer = trainerService.findById(idTrainer);
    Object[] users = new Object[2];
    users[0] = student;
    users[1] = trainer;

    trChat().saveChatRoom("RoomMessage", new Date(), "d", idStudent, users, "Student_Trainer");
    trChat().saveChatMessage("newChatMessage", "RoomMessage", new Date(), messageOld, idStudent);
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", idStudent, 1, 0, idStudent, messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", idTrainer, 1, 1, idStudent, messageOld);
  }

  //Диалог студента с тренером. Диалог начал тренер
  public void set2_DialogStudentTrainerOwner(String messageOld, String idStudent,
      String idTrainer) {
    StudentData student = studentService.findById(idStudent);
    TrainerData trainer = trainerService.findById(idTrainer);
    Object[] users = new Object[2];
    users[0] = student;
    users[1] = trainer;

    trChat().saveChatRoom("RoomMessage", new Date(), "d", idTrainer, users, "Student_Trainer");
    trChat().saveChatMessage("newChatMessage", "RoomMessage", new Date(), messageOld, idTrainer);
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", idTrainer, 1, 0, idTrainer, messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", idStudent, 1, 1, idTrainer, messageOld);
  }

  //Диалог супера с тренером. Диалог начал тренер
  public void set3_DialogSuperTrainer(String messageOld) {
    WorkerData superadmin = workerService.findById("666");
    TrainerData trainer = trainerService.findById("23");
    Object[] users = new Object[2];
    users[0] = superadmin;
    users[1] = trainer;

    trChat().saveChatRoom("RoomMessage", new Date(), "m", "23", users, "Admin_Trainer");
    trChat().saveChatMessage("ForeignMessage", "RoomMessage", new Date(), messageOld, "23");
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "m", "RoomMessage", "23", 1, 0, "23", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "m", "RoomMessage", "-1", 1, 1, "23", messageOld);
  }

  //Диалог супера с тренером. Диалог начал супер
  public void set3_1_DialogTrainerSuper(String messageOld) {
    WorkerData superadmin = workerService.findById("666");
    TrainerData trainer = trainerService.findById("23");
    Object[] users = new Object[2];
    users[0] = superadmin;
    users[1] = trainer;

    trChat().saveChatRoom("RoomMessage", new Date(), "m", "666", users, "Admin_Trainer");
    trChat().saveChatMessage("ForeignMessage", "RoomMessage", new Date(), messageOld, "666");
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "m", "RoomMessage", "23", 1, 0, "23", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "m", "RoomMessage", "-1", 1, 1, "23", messageOld);
  }

  //Автосообщение родителю.
  public void set4_DialogAutoMessageParent() {
    String messageOld = "Добрый день! Чем могу помочь? Если у вас появятся вопросы...";
    WorkerData superadmin = workerService.findById("666");
    ParentData parent = parentService.findById("22");
    Object[] users = new Object[2];
    users[0] = superadmin;
    users[1] = parent;

    trChat().saveChatRoom("RoomMessage", new Date(), "m", "-1", users, "Admin_Parent");
    trChat().saveChatMessage("deleteMessage", "RoomMessage", new Date(), messageOld, "-1");
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "m", "RoomMessage", "22", 1, 1, "-1", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "m", "RoomMessage", "-1", 1, 0, "-1", messageOld);
  }

  //Автосообщение родителю.
  public void set5_DialogTrainerParent(String messageOld) {
    ParentData parent = parentService.findById("22");
    TrainerData trainer = trainerService.findById("23");
    Object[] usersDialog2 = new Object[2];
    usersDialog2[0] = parent;
    usersDialog2[1] = trainer;

    trChat().saveChatRoom("RoomParent", new Date(), "d", "22", usersDialog2, "Parent_Trainer");
    trChat().saveChatMessage("MessageParent", "RoomParent", new Date(), messageOld, "21");
    trChat()
        .saveChatSubscription(
            "subsc1Par", new Date(), "d", "RoomParent", "22", 1, 0, "22", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2Par", new Date(), "d", "RoomParent", "23", 1, 1, "22", messageOld);
  }
}
