package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

import com.sun.istack.NotNull;
import io.itgen.general.TimeGeneral;
import io.itgen.model.StudentData;
import io.itgen.model.schedule.FinishedSlots;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import java.util.HashSet;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerStartLesson extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  private final TimeGeneral time = new TimeGeneral();
  private String period="";

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    app.trScheduleToday().SingleScheduleWithOneStudentOnTrail(period,"startLessonByTrainer",
        "23", "startLessonByTrainer", "1", "ru");

    app.trFamily().newFamily("startLessonByTrainer", false, "txa");

    app.trStudent()
        .newStudent(
            "startLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "startLessonByTrainer");
  }

  @Test
  public void testTrainerStartLesson() {
    app.trainer().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.trainer().startLesson("startLessonByTrainer");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("startLessonByTrainer");
    studentService.DeleteById("startLessonByTrainer");
    familyService.DeleteById("startLessonByTrainer");
  }

  private void check(Schedules before, Schedules after) {
    //проверка, что появилось поле startedAt
    assertTrue(!(
        scheduleService.findById("startLessonByTrainer").getSlots().get(0).getStartedAt() == null));

    scheduleService.DeleteById("startLessonByTrainer");
    app.trScheduleToday().SingleScheduleWithOneStudentOnTrail(period,"startLessonByTrainer",
        "23", "startLessonByTrainer", "1", "ru");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
