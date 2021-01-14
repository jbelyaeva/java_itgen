package data.precondition;

import data.model.users.StudentData;
import data.model.users.TrainerData;
import data.services.StudentService;
import data.services.TrainerService;
import java.util.Date;

public class Chat extends TransactionManager {

  protected static final DataManager data = new DataManager();
  private TrainerService trainerService = new TrainerService();
  private StudentService studentService = new StudentService();

  //Диалог студента с тренером. Диалог начал тренер
  public void set1_DialogStudentTrainer(String messageOld, String idStudent, String idTrainer) {
    StudentData student = studentService.findById(idStudent);
    TrainerData trainer = trainerService.findById(idTrainer);
    Object[] users = new Object[2];
    users[0] = student;
    users[1] = trainer;

    trChat().saveChatRoom("RoomMessage", new Date(), "d", idTrainer, users, "Student_Trainer");
    trChat().saveChatMessage("newChatMessage", "RoomMessage", new Date(), messageOld, idStudent);
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", idStudent, 1, 0, idStudent, messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", idTrainer, 1, 1, idStudent, messageOld);
  }
}
