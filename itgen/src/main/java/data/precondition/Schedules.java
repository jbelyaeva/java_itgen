package data.precondition;

public class Schedules extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //завтра регулярное занятие без ученика на первом уроке (Бокша, руск яз, Scratch)
  public void set1_RegularScheduleWithoutStudentOnFirstLesson(String period) {
    trScheduleTomorrow()
        .RegularScheduleWithoutStudentOnFirstLesson(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  //завтра регулярное занятие без учеников (Бокша, руск яз, Scratch)
  public void set2_RegularScheduleWithoutStudents(String period) {
    trScheduleTomorrow()
        .RegularScheduleWithoutStudents(period, "newSchedule", "14");
  }

  //завтра разовое занятие буз учеников (Бокша, руск яз, Scratch)
  public void set3_SingleScheduleWithoutStudent(String period) {
    trScheduleTomorrow()
        .SingleScheduleWithoutStudent(period, "newSchedule", "14");
  }

  // завтра регулярное занятие, на которое записали ученика (Бокша, Scratch, рус)
  public void set4_RegularScheduleWithStudent(String period) {
    trScheduleTomorrow()
        .RegularScheduleWithOneOldStudent(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // завтра разовое занятие, на которое записали ученика (Бокша, Scratch, рус)
  public void set5_SingleScheduleWithStudent(String period) {
    trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // завтра пробное занятие, на которое записали ученика (Бокша, Scratch, рус)
  public void set6_SingleScheduleWithOneStudentRecordOnTrailOnScratch(String period) {
    trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // завтра пробное занятие, на которое записали ученика (Бокша, Minecraft, рус)
  public void set6_SingleScheduleWithOneStudentRecordOnTrailOnMinecraft(String period) {
    trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        period, "newSchedule", "14", "newStudent", "21", "ru");
  }

  // завтра разовое занятие, на которое записали ученика (Бокша, Minecraft, рус)
  public void set7_SingleScheduleWithOneStudentRecordOnSingleOnMinecraft(String period) {
    trScheduleTomorrow().SingleScheduleWithOneNewStudent(
        period, "newSchedule", "14", "newStudent", "21", "ru");
  }

  // сегодня разовое занятие, на которое записали ученика (Бокша, Scratch, рус)
  public void set8_TodaySingleScheduleWithStudent(String period) {
    trScheduleToday().SingleScheduleWithOneStudentOnTrail(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня постоянное занятие, на которое записали нового ученика (Бокша, Scratch, рус)
  public void set9_TodayRegularScheduleWithStudentOnAllLessons(String period) {
    trScheduleToday().RegularScheduleWithOneNewStudent(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня постоянное занятие, на которое записали нового ученика на первый час (Бокша, Scratch, рус)
  public void set10_TodayRegularScheduleWithStudentOnAllLessonsOnFirst1h(String period) {
    trScheduleToday().RegularScheduleWithOneNewStudentOnFirst1h(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня постоянное занятие, на которое записали нового ученика на второй час (Бокша, Scratch, рус)
  public void set11_TodayRegularScheduleWithStudentOnAllLessonsOnSecond1h(String period) {
    trScheduleToday().RegularScheduleWithOneNewStudentOnSecond1h(
        period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня разовое занятие, на которое записали ученика (Бокша, Scratch, рус)
  public void set12_TodaySingleScheduleWithStudent(String period, String id) {
    trScheduleToday()
        .SingleScheduleWithOneStudent(
            period, id, "14", "newStudent", "1", "ru");
  }

  // сегодня разовое занятие, на которое записали ученика на первый час (Бокша, Scratch, рус)
  public void set13_TodaySingleScheduleWithStudentOnFirst1h(String period) {
    trScheduleToday()
        .SingleScheduleWithOneStudentOnFirst1h(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня разовое занятие, на которое записали ученика на второй час(Бокша, Scratch, рус)
  public void set14_TodaySingleScheduleWithStudentOnSecond1h(String period) {
    trScheduleToday()
        .SingleScheduleWithOneStudentOnSecond1h(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня постоянное занятие, на которое записали ученика на пробное на первый урок(Бокша, Scratch, рус)
  public void set15_TodayRegularScheduleWithStudentWithTrialOnFirstLesson(String period) {
    trScheduleToday()
        .RegularScheduleWithOneNewStudentWithTrialOnFirstLesson(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  // сегодня разовое занятие, на которое записали ученика на пробное (Бокша, Scratch, рус)
  public void set16_TodaySingleScheduleWithStudentOnTrial(String period) {
    trScheduleToday()
        .SingleScheduleWithOneStudentOnTrail(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  //сегодня регулярное занятие без ученика на первом уроке (Бокша, руск яз, Scratch)
  public void set17_TodayRegularScheduleWithoutStudentOnFirstLesson(String period) {
    trScheduleToday()
        .RegularScheduleWithoutStudentOnFirstLesson(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  //сегодня разовое занятие без учеников (Бокша, руск яз, Scratch)
  public void set18_SingleScheduleWithoutStudent(String period, String idTrainer) {
    trScheduleToday()
        .SingleScheduleWithoutStudents(period, "newSchedule", idTrainer);
  }

  //сегодня регулярное занятие без учеников (Бокша, руск яз, Scratch)
  public void set19_TodayRegularScheduleWithoutStudents(String period, String idTrainer) {
    trScheduleToday()
        .RegularScheduleWithoutStudents(period, "newSchedule", idTrainer);
  }

  //сегодня регулярное занятие без учеников на первом занятии тренер с @id1, на остальных с @id2
  public void set20_TodayRegularScheduleWithoutStudentsWithDifferentTrainers(String period,
      String id1, String id2) {
    trScheduleToday()
        .RegularScheduleWithoutStudentsWithDifferentTrainers(period, "newSchedule", id1, id2);
  }

  //сегодня регулярное занятие без учеников , все занятия заблокированы
  public void set21_TodayRegularScheduleWithoutStudentsWithBlockedAll(String period, String note) {
    trScheduleToday()
        .RegularScheduleWithoutStudentsWithBlockedAll(period, note);
  }

  //сегодня регулярное занятие без учеников , первое занятие заблокировано
  public void set22_TodayRegularScheduleWithoutStudentsWithFirstBlocked(String period,
      String note) {
    trScheduleToday()
        .RegularScheduleWithoutStudentsWithFirstBlocked(period, note);
  }

  //сегодня заблокированное разовое занятие без учеников
  public void set23_TodaySingleScheduleWithoutStudentsBlocked(String period, String note) {
    trScheduleToday()
        .SingleScheduleWithoutStudentsBlocked(period, note);
  }

  //сегодня регулярное занятие без учеников , первое занятие отменено
  public void set23_TodayRegularScheduleWithoutStudentsWithFirstCanceled(String period) {
    trScheduleToday()
        .RegularScheduleWithoutStudentsWithFirstCanceled(period);
  }

  //сегодня отмененное разовое занятие без учеников
  public void set24_TodaySingleScheduleWithoutStudentsCanceled(String period) {
    trScheduleToday()
        .SingleScheduleWithoutStudentsCanceled(period);
  }

  //сегодня разовое занятие на новое направление
  public void set25_TodayStartSingleScheduleWithOneStudentOnNewSkill(String period) {
    long alreadyRun = 7200000;//2ч
    trScheduleToday()
        .StartSingleScheduleWithOneStudentOnNewSkill(
            (double) alreadyRun,
            period,
            "newSchedule",
            "23",
            "21",
            "1",
            "ru");
    ;
  }
}
