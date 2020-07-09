package io.itgen.appmanager.tranzactionHelper.schedule;

import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.FinishedSlots;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;

public class TrScheduleTomorrowHelper {
  private final TimeGeneral time = new TimeGeneral();
  private final ScheduleService scheduleService = new ScheduleService();

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
                                    .withScore(3)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
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
                                    .withTrial(true)
                                    .withS("normal")))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие без учеников
  public void SingleScheduleWithoutStudent(String period, String idSchedule, String idTrainer) {

    ArrayList<C> listC = new ArrayList<>();
    ArrayList<FinishedSlots> listFSlots = new ArrayList<>();
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
                        .withC(listC)))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // завтра регулярное занятие без ученика
  public void RegularScheduleWithoutStudents(String period, String idSchedule, String idTrainer) {

    int week = 604800000;
    ArrayList<C> listC = new ArrayList<>();
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
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week)
                                .withE(time.EtimeTomorrow(period) + week))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 2)
                                .withE(time.EtimeTomorrow(period) + week * 2))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 3)
                                .withE(time.EtimeTomorrow(period) + week * 3))
                        .withC(listC)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
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
    int week = 604800000;
    ArrayList<C> listC = new ArrayList<>();
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
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week)
                                .withE(time.EtimeTomorrow(period) + week))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 2)
                                .withE(time.EtimeTomorrow(period) + week * 2))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 3)
                                .withE(time.EtimeTomorrow(period) + week * 3))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
  }

  // завтра регулярное расписание с учеником на всех занятиях
  public void RegularScheduleWithOneStudent(
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
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
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week)
                                .withE(time.EtimeTomorrow(period) + week))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 2)
                                .withE(time.EtimeTomorrow(period) + week * 2))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period) + week * 3)
                                .withE(time.EtimeTomorrow(period) + week * 3))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
  }
}
