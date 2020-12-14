package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.materials.MaterialChilds;
import data.model.schedule.Comments;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessons;
import data.model.schedule.Schedules;
import data.services.CommentService;
import data.services.FamilyService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
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
  private final long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "ScheduleFinishLessonByTrainer",
            "23",
            "StudentFinishLessonByTrainer",
            "1",
            "ru");

    app.trFamily().newFamily("FamilyFinishLessonByTrainer", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "StudentFinishLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "FamilyFinishLessonByTrainer",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2,
            "noTrial");

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

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testTrainerFinishedLessonWithStudentWas_Project() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();
    Comments commentsBefore = app.dbschedules().comments();
    MaterialChilds materialChildsBefore = app.dbmaterial().materialChilds();

    app.trainer().finishedLessonWithWas_giveProject("ScheduleFinishLessonByTrainer", "Жуки", "Лабиринт");
    app.trainer().gotoTask();

    Schedules after = app.dbschedules().schedules();
    FinishedChildLessons finishChildAfter = app.dbschedules().finishedChildLessons();
    FinishedLessons finishAfter = app.dbschedules().finishedLessons();
    Comments commentsAfter = app.dbschedules().comments();
    MaterialChilds materialChildsAfter = app.dbmaterial().materialChilds();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(finishChildAfter.size(), equalTo(finishChildBefore.size() + 1));
    assertThat(finishAfter.size(), equalTo(finishBefore.size() + 1));
    assertThat(commentsAfter.size(), equalTo(commentsBefore.size() + 1));
    assertThat(materialChildsAfter.size(), equalTo(materialChildsBefore.size() + 2));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("ScheduleFinishLessonByTrainer");
    familyService.DeleteById("FamilyFinishLessonByTrainer");
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
    materialService.drop();
    materialChildsService.drop();
    materialBranchService.drop();
    studentService.DeleteById("StudentFinishLessonByTrainer");
  }

  private void check(Schedules after) {
    scheduleService.DeleteById("ScheduleFinishLessonByTrainer");
    app.trScheduleToday()
        .FinishedSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "ScheduleFinishLessonByTrainer",
            "23",
            "StudentFinishLessonByTrainer",
            "1",
            "ru",
            "finished");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
