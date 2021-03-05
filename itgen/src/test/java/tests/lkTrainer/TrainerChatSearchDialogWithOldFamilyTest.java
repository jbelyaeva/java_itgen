package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerChatSearchDialogWithOldFamilyTest extends TestBase {
  String messageOld = "Привет";

  @BeforeMethod
  public void ensurePreconditions() {
    data.chat().set1_DialogStudentTrainer(messageOld, "21", "23");
    data.chat().set5_DialogTrainerParent(messageOld);
    /*StudentData student = studentService.findById("21");
    ParentData parent = parentService.findById("22");
    TrainerData trainer = trainerService.findById("23");
    Object[] usersDialog1 = new Object[2];
    usersDialog1[0] = student;
    usersDialog1[1] = trainer;

    app.trChat()
        .saveChatRoom("RoomStudent", new Date(), "d", "21", usersDialog1, "Student_Trainer");
    app.trChat().saveChatMessage("MessageOnLesson", "RoomStudent", new Date(), messageOld, "21");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomStudent", "21", 1, 0, "21", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomStudent", "23", 1, 1, "21", messageOld);

    Object[] usersDialog2 = new Object[2];
    usersDialog2[0] = parent;
    usersDialog2[1] = trainer;

    app.trChat().saveChatRoom("RoomParent", new Date(), "d", "22", usersDialog2, "Parent_Trainer");
    app.trChat().saveChatMessage("MessageParent", "RoomParent", new Date(), messageOld, "21");
    app.trChat()
        .saveChatSubscription(
            "subsc1Par", new Date(), "d", "RoomParent", "22", 1, 0, "22", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2Par", new Date(), "d", "RoomParent", "23", 1, 1, "22", messageOld);*/
  }

  @Test
  public void testTrainerChatSearchDialogWithOldStudent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("ребенок"), equalTo(true));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithOldParent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("родитель"), equalTo(true));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().chat();
  }
}
