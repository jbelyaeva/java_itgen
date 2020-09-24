package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.general.TimeGeneral;
import io.itgen.model.materials.MaterialChilds;
import io.itgen.model.schedule.Comments;
import io.itgen.model.schedule.FinishedChildLessons;
import io.itgen.model.schedule.FinishedLessons;
import io.itgen.model.schedule.Schedules;
import io.itgen.services.CommentService;
import io.itgen.services.FamilyService;
import io.itgen.services.FinishedChildLessonService;
import io.itgen.services.FinishedLessonService;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialChildsService;
import io.itgen.services.MaterialService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerFinishedLessonWithStudentWas_Project extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();
  CommentService commentService = new CommentService();
  MaterialService materialService = new MaterialService();
  MaterialChildsService materialChildsService = new MaterialChildsService();
  MaterialBranchService materialBranchService = new MaterialBranchService();
  private final TimeGeneral time = new TimeGeneral();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  private String period = "";
  private long alreadyRun = 7200000; //2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday().StartSingleScheduleWithOneStudentOnTrail((double) alreadyRun, period,
        "finishLessonByTrainer",
        "23", "finishLessonByTrainer", "1", "ru");

    app.trFamily().newFamily("finishLessonByTrainer", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "finishLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "finishLessonByTrainer");

    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");

    app.trMaterial()
        .publishedMaterial(
            "MaterialOnLessonFirst",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    app.trMaterial()
        .publishedMaterial(
            "MaterialOnLessonSecond",
            "14",
            "Лабиринт",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  @Test
  public void testTrainerFinishedLessonWithStudentWas_Project() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();
    Comments commentsBefore = app.dbschedules().comments();
    MaterialChilds materialChildsBefore = app.dbmaterial().materialChilds();

    app.trainer().finishedLessonWithWas_giveProject("finishLessonByTrainer","Жуки","Лабиринт");
    app.trainer().gotoTask();
    Schedules after = app.dbschedules().schedules();
    FinishedChildLessons finishChildAfter = app.dbschedules().finishedChildLessons();
    FinishedLessons finishAfter = app.dbschedules().finishedLessons();
    Comments commentsAfter = app.dbschedules().comments();
    MaterialChilds materialChildsAfter = app.dbmaterial().materialChilds();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(finishChildAfter.size(), equalTo(finishChildBefore.size()+1));
    assertThat(finishAfter.size(), equalTo(finishBefore.size() + 1));
    assertThat(commentsAfter.size(), equalTo(commentsBefore.size() + 1));
    assertThat(materialChildsAfter.size(), equalTo(materialChildsBefore.size() + 2));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("finishLessonByTrainer");
    studentService.DeleteById("finishLessonByTrainer");
    familyService.DeleteById("finishLessonByTrainer");
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
    materialService.drop();
    materialChildsService.drop();
    materialBranchService.drop();
  }

  private void check(Schedules after) {
    scheduleService.DeleteById("finishLessonByTrainer");
    app.trScheduleToday().FinishedSingleScheduleWithOneStudentOnTrail((double) alreadyRun, period,
        "finishLessonByTrainer",
        "23", "finishLessonByTrainer", "1", "ru", "finished");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
