package app.appmanager.transactionHelper.schedule;

import core.general.TimeGeneral;
import data.model.schedule.C;
import data.model.schedule.FinishedSlots;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrScheduleTomorrowHelper {
  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  private final int week = 604800000;

  private List<Slots> generateSlots(String period, String idTrainer, ArrayList<List<C>> listsC) {
    int week = 604800000;
    ArrayList<Slots> slots = new ArrayList<>();
    for (int i = 0; i < listsC.size(); i++) {
      slots.add(
          new Slots()
              .withId(idTrainer)
              .withW(time.dateTomorrow() + week * i)
              .withSt(
                  new ST()
                      .withS(time.StimeTomorrow(period) + week * i)
                      .withE(time.EtimeTomorrow(period) + week * i))
              .withC(listsC.get(i)));
    }
    return slots;
  }

  // Завтра разовое занятие, на которое записан ученик, после первого успешного пробного
  public void SingleScheduleWithOneStudent(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withScore(3)
                                    .withKind("oneTime")
                                    .withStartTime(time.StimeTomorrow(period))
                                    .withEndTime(time.EtimeTomorrow(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие, на которое записан новый ученик на первый час
  public void CombinationWithOneStudentOnSingleScredule_1(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(1)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withNewSubj(true)
                                    .withScore(3)
                                    .withKind("oneTime")
                                    .withStartTime(time.StimeTomorrow(period))
                                    .withEndTime(time.EtimeTomorrow(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие, на которое записан новый ученик на первый час
  public void CombinationWithOneStudentOnSingleScredule_2(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(2)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withNewSubj(true)
                                    .withScore(3)
                                    .withKind("oneTime")
                                    .withStartTime(time.StimeTomorrow(period))
                                    .withEndTime(time.EtimeTomorrow(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие, на которое записан ученик без пробного
  public void SingleScheduleWithOneNewStudent(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withNewSubj(true)
                                    .withScore(3)
                                    .withKind("oneTime")
                                    .withStartTime(time.StimeTomorrow(period))
                                    .withEndTime(time.EtimeTomorrow(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void SingleScheduleWithOneStudentRecordOnTrail(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withS("normal")
                                    .withStartTime(time.StimeTomorrow(period))
                                    .withEndTime(time.EtimeTomorrow(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие без учеников
  public void SingleScheduleWithoutStudent(String period, String idSchedule, String idTrainer) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 1; i++) {
      listsC.add(new ArrayList<>());
    }
    ArrayList<FinishedSlots> listFSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // завтра регулярное занятие без ученика
  public void RegularScheduleWithoutStudents(String period, String idSchedule, String idTrainer) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 4; i++) {
      listsC.add(new ArrayList<>());
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withLessonFormat(0)
            .withWholeness(false);
    scheduleService.save(schedule);
  }

  // регулярное занятие, тренер на всех уроках 1, на первое занятие записан студент на пробное.
  public void CombinationWithOneStudentOnRegularScredule_1(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);

    listsC.add(
        Arrays.asList(
            new C()
                .withId(idStudent)
                .withType(3)
                .withSubject(idSubject)
                .withLang(lang)
                .withS("normal")
                .withKind("trial")
                .withStartTime(time.StimeTomorrow(period))
                .withEndTime(time.EtimeTomorrow(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(new ArrayList<>());
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withLessonFormat(0)
            .withWholeness(false);
    scheduleService.save(schedule);
  }

  // регулярное занятие, тренер на всех уроках 1, студен записан постоянно на первый час.
  public void CombinationWithOneStudentOnRegularSchedule_2(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {

    ArrayList<List<C>> listsC = new ArrayList<>(4);

    listsC.add(
        Arrays.asList(
            new C()
                .withId(idStudent)
                .withType(1)
                .withSubject(idSubject)
                .withLang(lang)
                .withS("normal")
                .withNewSubj(true)
                .withKind("permanent")
                .withStartTime(time.StimeTomorrow(period))
                .withEndTime(time.EtimeTomorrow(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(1)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.StimeTomorrow(period) + week * i)
                  .withEndTime(time.EtimeTomorrow(period) + week * i)));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  // регулярное занятие, тренер на всех уроках 1, студен записан постоянно на второй час.
  public void CombinationWithOneStudentOnRegularSchedule_3(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {

    ArrayList<List<C>> listsC = new ArrayList<>(4);

    listsC.add(
        Arrays.asList(
            new C()
                .withId(idStudent)
                .withType(2)
                .withSubject(idSubject)
                .withLang(lang)
                .withS("normal")
                .withNewSubj(true)
                .withKind("permanent")
                .withStartTime(time.StimeTomorrow(period))
                .withEndTime(time.EtimeTomorrow(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(2)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.StimeTomorrow(period) + week * i)
                  .withEndTime(time.EtimeTomorrow(period) + week * i)));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  // завтра регулярное расписание, но без ученика на первом занятии
  public void RegularScheduleWithoutStudentOnFirstLesson(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {

    ArrayList<List<C>> listsC = new ArrayList<>(4);

    for (int i = 0; i < 1; i++) {
      listsC.add(new ArrayList<>());
    }
    for (int i = 0; i < 3; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.StimeTomorrow(period) + week * i)
                  .withEndTime(time.EtimeTomorrow(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  // завтра регулярное расписание с учеником на всех занятиях (не новое)
  public void RegularScheduleWithOneOldStudent(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {

    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 4; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.StimeTomorrow(period) + week * i)
                  .withEndTime(time.EtimeTomorrow(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  // завтра регулярное расписание с новым учеником на всех занятиях
  public void RegularScheduleWithOneNewStudent(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);

    listsC.add(
        Arrays.asList(
            new C()
                .withId(idStudent)
                .withType(3)
                .withSubject(idSubject)
                .withLang(lang)
                .withS("normal")
                .withNewSubj(true)
                .withKind("permanent")
                .withStartTime(time.StimeTomorrow(period))
                .withEndTime(time.EtimeTomorrow(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.StimeTomorrow(period) + week * i)
                  .withEndTime(time.EtimeTomorrow(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }
}
