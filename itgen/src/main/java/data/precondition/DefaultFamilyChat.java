package data.precondition;

import app.appmanager.HelperBase;
import java.util.Date;

public class DefaultFamilyChat extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * В дефолтную семью добавлен ученик 10 лет в статусе Будет пробное (есть запись на завтра на
   * разовое) У родителя есть диалог с дефолтным тренером
   *
   * @period время занятия 22 - id дефолтного родителя, 23 - id дефолтного тренера
   */
  public void set1_DialogWithTrainer_StudentAddInDefaultFamily_TrialWillBeTomorrow(String period) {
    String messageOld = "Привет";
    trScheduleTomorrow()
        .SingleScheduleWithOneNewStudent(period, "newSchedule", "23", "newStudent", "1", "ru");

    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );

    Object[] users = new Object[2];
    users[0] = data.parentService().findById("22");
    users[1] = data.trainerService().findById("23");

    trChat().saveChatRoom("RoomMessage", new Date(), "d", "23", users, "Parent_Trainer");
    trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "22");
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "22", 1, 0, "22", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, 1, "22", messageOld);
  }

  /**
   * Ребенок записан на пробное с тренером Бокша и Дефолтным тренером. От Дефолтного тренера  есть
   * новое сообщение родителю
   */
  public void set2_DialogWithTrainer1_TwoTrialWillBeTomorrowWithTrainer1AndTrainer2() {
    String periodFirst = "16:00 - 18:00";
    String periodSecond = "19:00 - 21:00";
    String messageOld = "Привет";

    trScheduleTomorrow()
        .SingleScheduleWithOneNewStudent(periodFirst, "newScheduleFirst", "14", "newStudent", "1",
            "ru");
    trScheduleTomorrow()
        .SingleScheduleWithOneNewStudent(periodSecond, "newScheduleSecond", "23", "newStudent", "1",
            "ru");

    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "111",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );

    Object[] users = new Object[2];
    users[0] = data.parentService().findById("22");
    users[1] = data.trainerService().findById("23");

    trChat().saveChatRoom("RoomMessage", new Date(), "d", "23", users, "Parent_Trainer");
    trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "22");
    trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "22", 1, 0, "22", messageOld);
    trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, 1, "22", messageOld);
  }
}
