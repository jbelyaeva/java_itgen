package data.precondition;

import app.appmanager.HelperBase;

public class DefaultFamilyStudentsAndSchedules extends TranzactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет,в статусе Занимаетсяб прошедший вчера
   * пробное и который записан на постоянное расписание на завтра в то же время. Оба занятия в
   * одному тренеру на Scratch
   *
   * @period время расписания
   */
  public void set1_LessonYesterdayFinished_RegularLessonTomorrowWithStudent_StudentAddInDefaultFamily(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "learning",
            "1",
            "1",
            1);

    trScheduleTomorrow()
        .RegularScheduleWithOneOldStudent(
            period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет,в статусе Занимается прошедший вчера
   * пробное и который записан на разовое расписание на завтра в то же время. Оба занятия в одному
   * тренеру на Scratch
   *
   * @period время расписания
   */
  public void set2_LessonYesterdayFinished_SingleLessonTomorrowWithStudent_StudentAddInDefaultFamily(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "learning",
            "1",
            "1",
            1);

    trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            period,
            "newSchedule",
            "14",
            "newStudent",
            "1",
            "ru");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик младше 7 лет
   */
  public void set3_addNewStudentYanger7Years() {
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
            base.DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial"
        );
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик старше 7 лет
   */
  public void set13_addNewStudentOlder7Years() {
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
            "1",
            2,
            "noTrial");

  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет,сразу записан на платное ученик записан
   * на разовое расписание на завтра к Бокше на Scratch
   *
   * @period время расписания
   */
  public void set4_SingleLessonTomorrowWithStudent_StudentAddInDefaultFamily(String period) {
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
            base.DateWithCorrectionDays(-1460),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "learning"
        );

    trScheduleTomorrow().SingleScheduleWithOneNewStudent(
        period,
        "newSchedule",
        "14",
        "newStudent",
        "1",
        "ru");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет,записан на пробное на разовое расписание
   * на завтра к Бокше на Scratch
   *
   * @period время расписания
   */
  public void set5_SingleLessonTomorrowTrialWithStudent_StudentAddInDefaultFamily(String period) {
    trScheduleTomorrow()
        .SingleScheduleWithOneStudentRecordOnTrail(
            period, "newStudent", "14", "newStudent", "1", "ru");

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
            "1",
            2,
            "withTrial"
        );
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, и есть разовое расписание на завтра к
   * Бокше на Scratch
   *
   * @period время расписания
   */
  public void set6_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(String period) {
    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newSchedule", "14");

    trStudent()
        .newStudent(
            "student",
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
            "1",
            2,
            "noTrial"
        );
    data.studentService().deleteField("student", "services");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, вчера прошедший пробное Scratch и есть
   * разовое расписание на завтра . Оба занятия в одному тренеру Бокше
   *
   * @period время расписания
   */
  public void set7_LessonYesterdayFinished_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);

    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newSchedule", "14");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, вчера прошедший пробное по Minecraft  и
   * есть разовое расписание на завтра. Оба занятия к одному тренеру Бокше
   *
   * @period время расписания
   */
  public void set8_LessonMinecaftYesterdayFinished_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
      String period) {
    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "21",
            2,
            1,
            "trialFinished",
            "21",
            "21",
            1);
    trScheduleYesterday()
        .finishingFirstTrialLesson(period, "ScheduleYesterday", "14", "newStudent", "21");

    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newSchedule", "14");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, вчера прошедший пробное Scratch и есть
   * постоянное расписание на завтра на это же время. Оба занятия в одному тренеру Бокше
   *
   * @period время расписания
   */
  public void set9_LessonYesterdayFinished_RegularLessonTomorrowWithoutStudent_StudentAddInDefaultFamily(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson
            ("newStudent",
                "Маша",
                "Машина",
                "expert",
                "BL",
                "Europe/Minsk",
                2,
                base.DateWithCorrectionDays(-3650),
                "ru",
                "ru",
                "12345678i",
                "ru",
                "1",
                2,
                1,
                "trialFinished",
                "1",
                "1",
                1);

    trScheduleTomorrow()
        .RegularScheduleWithoutStudents(period, "newSchedule", "14");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, прошедший вчера пробное по Scratch, и
   * есть разовое расписание на завтра к Бокше на Scratch
   *
   * @period время расписания
   */
  public void set10_LessonYesterdayFinished_SingleLessonTomorrowTrialWithStudent_StudentAddInDefaultFamily(
      String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");
    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newSchedule", "14");

    trStudent()
        .studentAddDefaultFamilyAfterLesson
            ("newStudent",
                "Маша",
                "Машина",
                "expert",
                "BL",
                "Europe/Minsk",
                2,
                base.DateWithCorrectionDays(-3650),
                "ru",
                "ru",
                "12345678i",
                "ru",
                "1",
                2,
                1,
                "trialFinished",
                "1",
                "1",
                1);
  }

  /**
   * К дефолтной семье добавлен ученик старше 10 лет. Вчера было завершено пробное, а затем так же
   * вчера им было пропущено занятие. Есть доступное разовое занятие на завтра
   */
  public void set11_YesterdayTwoLessonWasAndSkip_SingleLessonTomorrowWithoutStudent_StudentAddInDefaultFamily() {
    String periodFirst = "10:00 - 12:00";
    String periodSecond = "18:00 - 20:00";
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedTrialSchedule", "14", "newStudent", "1");

    trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSchedule",
            "14",
            "newStudent",
            "1",
            3,
            "skipped",
            true,
            false,
            false,
            "ru",
            120
        );

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);

    trScheduleTomorrow()
        .SingleScheduleWithoutStudent(
            periodFirst,
            "LkCancelLessonInSingleSchedule",
            "14");
  }

  /**
   * Есть дефолтная семья, к которой добавлен ученик 10 лет, прошедший вчера пробное по Scratch
   *
   * @period время расписания
   */
  public void set12_LessonYesterdayFinished_StudentAddInDefaultFamily(String period) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedTrialSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "trialFinished",
            "1",
            "1",
            1);
  }

  /* Новый ученик записан на завтра в 14:00-16:00 на пробное на майнкрафт к Насте
   * Новый ученик записан на завтра в 20:00-22:00 на пробное на скрейч к Насте
   */
  public void set14_TomorrowTwoTrialLessons_StudentAddInDefaultFamily() {
    String periodFirstLesson = "14:00 - 16:00";
    String periodSecondLesson = "20:00 - 22:00";

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
            "1",
            2,
            "noTrial");

    trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        periodFirstLesson,
        "first",
        "14",
        "newStudent",
        "21",
        "ru");

    trScheduleTomorrow().SingleScheduleWithOneStudentRecordOnTrail(
        periodSecondLesson,
        "second",
        "14",
        "newStudent",
        "1",
        "ru");
  }

  /**
   * В дефолтную семью добавлен ученик с завершенным пробным вчера занятием c тренером Настей и с
   * завершенным платным разовым с Сорвано тоже с тренером Настей
   */
  public void set15_YesterdayTwoLessonsWasAndAbort_StudentAddInDefaultFamily() {
    String periodFirst = "16:00 - 18:00";
    String periodSecond = "19:00 - 21:00";

    trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedFirstSchedule", "14", "newStudent", "1");

    trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSecondSchedule",
            "14",
            "newStudent",
            "1",
            1,
            "abort",
            false,
            true,
            false,
            "ru",
            120
        );

    trStudent()
        .studentAddDefaultFamilyAfterLesson
            ("newStudent",
                "Маша",
                "Машина",
                "expert",
                "BL",
                "Europe/Minsk",
                2,
                base.DateWithCorrectionDays(-3650),
                "ru",
                "ru",
                "12345678i",
                "ru",
                "1",
                2,
                1,
                "finishedTrial",
                "1",
                "1",
                1);
  }

  /**
   * В дефолтную семью добавлен ученик с завершенным пробным вчера занятием c тренером Настей и с
   * завершенным платным разовым с Небыл тоже с тренером Настей
   */
  public void set16_YesterdayTwoLessonsWasAndAbort_StudentAddInDefaultFamily() {
    String periodFirst = "16:00 - 18:00";
    String periodSecond = "19:00 - 21:00";

    trScheduleYesterday()
        .finishingFirstTrialLesson(
            periodFirst, "FinishedFirstSchedule", "14", "newStudent", "1");

    trScheduleYesterday()
        .finishedLesson(
            periodSecond,
            "FinishedSecondSchedule",
            "14",
            "newStudent",
            "1",
            1,
            "skipped",
            false,
            true,
            false,
            "ru",
            120);

    trStudent()
        .studentAddDefaultFamilyAfterLesson
            ("newStudent",
                "Маша",
                "Машина",
                "expert",
                "BL",
                "Europe/Minsk",
                2,
                base.DateWithCorrectionDays(-3650),
                "ru",
                "ru",
                "12345678i",
                "ru",
                "1",
                2,
                1,
                "learning",
                "1",
                "1",
                1);
  }

  /**
   * В дефолтную семью добавлен ученик с завершенным пробным вчера занятием c тренером Настей и
   * записан на завтра на занятие, которое начнется через hoursInMs часа
   *
   * @hoursInMs - через сколько часов начнется занятие
   * @period - расписание занятия
   */
  public void set18_StudentAfterTrialWithLessonToday_LessonSingleTomorrowWithoutStudent_StudentAddInDefaultFamily(
      String period, long hoursInMs) {
    trScheduleYesterday()
        .finishingFirstTrialLesson(
            period, "FinishedSchedule", "14", "newStudent", "1");

    trStudent()
        .studentAddDefaultFamilyAfterLesson(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            1,
            "learning",
            "1",
            "1",
            1);

    trScheduleToday()
        .SingleScheduleWithOneStudentStartedSeveralHours(
            hoursInMs, "newSchedule", "14", "newStudent", "1", "ru");

    trScheduleTomorrow().SingleScheduleWithoutStudent(period, "newScheduleSecond", "14");
  }
}

