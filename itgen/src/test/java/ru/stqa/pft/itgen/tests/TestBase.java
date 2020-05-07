package ru.stqa.pft.itgen.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.itgen.appmanager.ApplicationManager;
import ru.stqa.pft.itgen.model.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(MyTestListener.class)
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyStudentsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().students();
      Students dbStudents = app.db().students();
      List<StudentData> uiStudents = app.student().list();
      assertThat(new HashSet<Object>(uiStudents), equalTo(dbStudents
              .stream().map((s) -> new StudentData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstname())
                      .withLastName(s.getLastname()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyWorkerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().worker();
      Workers dbWorkers = app.db().workers();
      List<WorkerData> uiWorkers = app.worker().listWithoutPaginator();
      assertThat(new HashSet<Object>(uiWorkers), equalTo(dbWorkers
              .stream().map((s) -> new WorkerData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstName())
                      .withLastName(s.getLastName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyTrainerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().gotoTrainer();
      Trainers dbTrainers = app.db().trainers();
      List<TrainerData> uiTrainers = app.trainer().list();
      assertThat(new HashSet<Object>(uiTrainers), equalTo(dbTrainers
              .stream().map((s) -> new TrainerData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstName())
                      .withLastName(s.getLastName()))
              .collect(Collectors.toSet())));
    }
  }
}
