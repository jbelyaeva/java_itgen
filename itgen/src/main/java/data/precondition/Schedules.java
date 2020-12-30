package data.precondition;

public class Schedules extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //завтра регулярное занятие буз ученика на первом уроке (Бокша, руск яз, Scratch)
  public void set1_RegularScheduleWithoutStudentOnFirstLesson(String period) {
    trScheduleTomorrow()
            .RegularScheduleWithoutStudentOnFirstLesson(
                    period, "newSchedule", "14", "newStudent", "1", "ru");
  }

  //завтра регулярное занятие буз учеников (Бокша, руск яз, Scratch)
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
}
