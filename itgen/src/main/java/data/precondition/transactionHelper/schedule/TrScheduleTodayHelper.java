package data.precondition.transactionHelper.schedule;

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
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TrScheduleTodayHelper {

  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();
  private final ArrayList<Slots> slots = new ArrayList<>();
  private final ArrayList<C> list = new ArrayList<>();

  private List<Slots> generateSlots(String period, String idTrainer, ArrayList<List<C>> listsC) {
    int week = 604800000;
    ArrayList<Slots> slots = new ArrayList<>();
    for (int i = 0; i < listsC.size(); i++) {
      slots.add(
          new Slots()
              .withId(idTrainer)
              .withW(time.date() + week * i)
              .withSt(
                  new ST()
                      .withS(time.Stime(period) + week * i)
                      .withE(time.Etime(period) + week * i))
              .withC(listsC.get(i)));
    }
    return slots;
  }

  private List<Slots> generateSlots1h(String period, String idTrainer, ArrayList<List<C>> listsC) {
    int week = 604800000;
    ArrayList<Slots> slots = new ArrayList<>();
    for (int i = 0; i < listsC.size(); i++) {
      slots.add(
          new Slots()
              .withId(idTrainer)
              .withW(time.date() + week * i)
              .withSt(
                  new ST()
                      .withS(time.Stime(period) + week * i)
                      .withE(time.Etime1h(period) + week * i))
              .withC(listsC.get(i)));
    }
    return slots;
  }

  public void SingleScheduleWithOneStudentOnTrail(
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void StartSingleScheduleWithOneStudentOnTrail(
      Double alreadyRun,
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void StartSingleScheduleWithOneStudentOnNewSkill(
      Double alreadyRun,
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withS("normal")
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withNewSubj(true)
                                    .withKind("oneTime")
                                    .withS("normal")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void FinishedSingleScheduleWithOneStudentOnTrail(
      Double alreadyRun,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang,
      String status) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(slots)
            .withFinishedSlots(
                Collections.singletonList(
                    new FinishedSlots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withS(status)
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withKind("trial")
                                    .withScore(0)
                                    .withRating(0)
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))
                        .withStartedAt((double) new Date().getTime() - alreadyRun)
                        .withFinishedAt((double) new Date().getTime())))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void SingleScheduleWithOneStudentStartedSeveralHours(
      long hoursImMs,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    String period = time.getPeriod(time.getTimeNow() + hoursImMs);
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Collections.singletonList(
                new Slots()
                    .withId(idTrainer)
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(
                        Collections.singletonList(
                            new C()
                                .withId(idStudent)
                                .withType(3)
                                .withSubject(idSubject)
                                .withLang(lang)
                                .withKind("oneTime")
                                .withScore(0)
                                .withRating(0)
                                .withS("normal")
                                .withStartTime(time.Stime(period))
                                .withEndTime(time.Etime(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  public void SingleScheduleWithoutStudents(
      String period,
      String idSchedule,
      String idTrainer) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // сегодня регулярное занятие без ученика
  public void RegularScheduleWithoutStudents(String period, String idSchedule, String idTrainer) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 4; i++) {
      listsC.add(new ArrayList<>());
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withLessonFormat(0)
            .withWholeness(false);
    scheduleService.save(schedule);
  }

  // сегодня регулярное занятие без ученика
  public void RegularIF1hScheduleWithoutStudents(String period, String idSchedule,
      String idTrainer) {
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    for (int i = 0; i < 4; i++) {
      listsC.add(new ArrayList<>());
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots1h(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish1h(period)))
            .withDuration(60)
            .withLessonFormat(1)
            .withWholeness(true);
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
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    listsC.add(
        Collections.singletonList(
            new C()
                .withId(idStudent)
                .withType(3)
                .withSubject(idSubject)
                .withLang(lang)
                .withS("normal")
                .withNewSubj(true)
                .withKind("permanent")
                .withStartTime(time.Stime(period))
                .withEndTime(time.Etime(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Collections.singletonList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  //регулярное занятие с одним учеником, записанным на второй час постоянно
  public void RegularScheduleWithOneNewStudentOnSecond1h(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    listsC.add(
        Collections.singletonList(
            new C()
                .withId(idStudent)
                .withType(2)
                .withSubject(idSubject)
                .withLang(lang)
                .withNewSubj(true)
                .withKind("permanent")));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Collections.singletonList(
              new C()
                  .withId(idStudent)
                  .withType(2)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withKind("permanent")
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  public void RegularScheduleWithOneNewStudentOnFirst1h(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    listsC.add(
        Collections.singletonList(
            new C()
                .withId(idStudent)
                .withType(1)
                .withSubject(idSubject)
                .withLang(lang)
                .withNewSubj(true)
                .withKind("permanent")));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Collections.singletonList(
              new C()
                  .withId(idStudent)
                  .withType(1)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withKind("permanent")
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  public void RegularIFScheduleWithOneNewStudentOn1h(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    listsC.add(
        Collections.singletonList(
            new C()
                .withId(idStudent)
                .withType(3)
                .withSubject(idSubject)
                .withLang(lang)
                .withNewSubj(true)
                .withKind("permanent")
                .withS("normal")
                .withScore(3)
                .withStartTime(time.Stime(period))
                .withEndTime(time.Etime1h(period))));

    for (int i = 0; i < 3; i++) {
      listsC.add(
          Collections.singletonList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withKind("permanent")
                  .withS("normal")
                  .withScore(3)
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime1h(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots1h(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish1h(period)))
            .withWholeness(true)
            .withDuration(60)
            .withLessonFormat(1);
    scheduleService.save(schedule);
  }

  //  разовое занятие, на которое записан ученик без пробного
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(
                            new ST()
                                .withS(time.Stime(period))
                                .withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withNewSubj(true)
                                    .withScore(3)
                                    .withKind("oneTime")
                                    .withStartTime(time.Stime(period))
                                    .withEndTime(time.Etime(period))))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withDuration(120)
            .withWholeness(false)
            .withLessonFormat(0)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // заархивированное разовое занятие
  public void archivedSingleSchedule(
      String period,
      String idSchedule) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(slots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true)
            .withArchiveReason("manual")
            .withArchived(true);
    scheduleService.save(schedule);
  }

  // заархивированное разовое занятие
  public void archivedRegularSchedule(
      String period,
      String idSchedule) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(slots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withArchiveReason("manual")
            .withArchived(true);
    scheduleService.save(schedule);
  }

  //  разовое занятие с учеником(без пробного) на первом часу
  public void SingleScheduleWithOneStudentOnFirst1h(
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withId(idStudent)
                                    .withType(1)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withNewSubj(true)
                                    .withS("normal")
                                    .withKind("oneTime")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  //  разовое занятие с учеником(без пробного) на втором часу
  public void SingleScheduleWithOneStudentOnSecond1h(
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
            .withFromDate(time.date())
            .withSlots(
                Collections.singletonList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Collections.singletonList(
                                new C()
                                    .withId(idStudent)
                                    .withType(2)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withNewSubj(true)
                                    .withS("normal")
                                    .withKind("oneTime")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  //регулярное заняние с учеником, записанным на пробное на первое занятие
  public void RegularScheduleWithOneNewStudentWithTrialOnFirstLesson(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);
    listsC.add(
        Collections.singletonList(
            new C()
                .withId(idStudent)
                .withType(3)
                .withSubject(idSubject)
                .withLang(lang)
                .withKind("trial")));

    for (int i = 0; i < 3; i++) {
      listsC.add(new ArrayList<>());
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  // регулярное расписание, c  учеником, удаленным с первого занятия (переместился лейбл Новое)
  public void RegularScheduleWithoutStudentOnFirstLesson(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<List<C>> listsC = new ArrayList<>(4);

    for (int i = 0; i < 1; i++) {
      listsC.add(new ArrayList<>());
    }

    for (int i = 0; i < 1; i++) {
      listsC.add(
          Collections.singletonList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withNewSubj(true)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime(period) + week * i)));
    }

    for (int i = 0; i < 2; i++) {
      listsC.add(
          Arrays.asList(
              new C()
                  .withId(idStudent)
                  .withType(3)
                  .withSubject(idSubject)
                  .withLang(lang)
                  .withS("normal")
                  .withKind("permanent")
                  .withStartTime(time.Stime(period) + week * i)
                  .withEndTime(time.Etime(period) + week * i)));
    }
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(this.generateSlots(period, idTrainer, listsC))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  //регулярное занятие без учеников с одним тренером на первом занятии и др тренером на последующих
  public void RegularScheduleWithoutStudentsWithDifferentTrainers(
      String period,
      String idSchedule,
      String id1,
      String id2) {
    int week = 604800000;
    ArrayList<Slots> listsSlots = new ArrayList<>();

    for (int i = 0; i < 1; i++) {
      listsSlots.add(
          new Slots()
              .withId(id2)
              .withW(time.date())
              .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
              .withC(list));
    }

    for (int i = 1; i < 4; i++) {
      listsSlots.add(
          new Slots()
              .withId(id1)
              .withW(time.date() + week * i)
              .withSt(new ST().withS(time.Stime(period) + week * i)
                  .withE(time.Etime(period) + week * i))
              .withC(list));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(listsSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  //регулярное расписание - все занятия заблокированы
  public void RegularScheduleWithoutStudentsWithBlockedAll(String period, String note) {
    int week = 604800000;
    ArrayList<Slots> listsSlots = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      listsSlots.add(
          new Slots()
              .withId("14")
              .withW(time.date() + week * i)
              .withSt(new ST().withS(time.Stime(period) + week * i)
                  .withE(time.Etime(period) + week * i))
              .withC(list)
              .withBlocked(true)
              .withBlockDesc(note));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId("newSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(listsSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  //регулярное расписание - первое занятие заблокировано
  public void RegularScheduleWithoutStudentsWithFirstBlocked(String period, String note) {
    int week = 604800000;
    ArrayList<Slots> listsSlots = new ArrayList<>();
    for (int i = 0; i < 1; i++) {
      listsSlots.add(
          new Slots()
              .withId("14")
              .withW(time.date())
              .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
              .withC(list)
              .withBlocked(true)
              .withBlockDesc(note));
    }

    for (int i = 1; i < 4; i++) {
      listsSlots.add(
          new Slots()
              .withId("14")
              .withW(time.date() + week * i)
              .withSt(new ST().withS(time.Stime(period) + week * i)
                  .withE(time.Etime(period) + week * i))
              .withC(list));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId("newSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(listsSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  public void SingleScheduleWithoutStudentsBlocked(String period, String note) {
    ScheduleData schedule =
        new ScheduleData()
            .withId("newSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)
                        .withBlocked(true)
                        .withBlockDesc(note)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  //регулярное занятие, первое отменено
  public void RegularScheduleWithoutStudentsWithFirstCanceled(String period) {
    int week = 604800000;
    ArrayList<Slots> listsSlots = new ArrayList<>();
    for (int i = 0; i < 1; i++) {
      listsSlots.add(
          new Slots()
              .withId("14")
              .withW(time.date())
              .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
              .withC(list)
              .withCancelled(true));
    }

    for (int i = 1; i < 4; i++) {
      listsSlots.add(
          new Slots()
              .withId("14")
              .withW(time.date() + week * i)
              .withSt(new ST().withS(time.Stime(period) + week * i)
                  .withE(time.Etime(period) + week * i))
              .withC(list));
    }

    ScheduleData schedule =
        new ScheduleData()
            .withId("newSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(listsSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withLessonFormat(0);
    scheduleService.save(schedule);
  }

  //отмененное разовое занятие без учеников
  public void SingleScheduleWithoutStudentsCanceled(String period) {
    ScheduleData schedule =
        new ScheduleData()
            .withId("newSchedule")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)
                        .withCancelled(true)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }
}
