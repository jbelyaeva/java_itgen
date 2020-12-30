package app.testbase;

import app.appmanager.ApplicationManager;
import core.listeners.MyTestListener;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import data.model.users.*;
import data.precondition.DataManager;
import data.precondition.TransactionManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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

  protected static final DataManager data = new DataManager();

  protected static final TransactionManager tr = new TransactionManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
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

  public static void etalon(String expected, String name, Screenshot actualScreenshot)
      throws IOException {
    if (Boolean.getBoolean("etalon")) {
      File expectedFile =
          new File(expected + name + ".png");
      ImageIO.write(
          actualScreenshot.getImage(),
          "png",
          expectedFile);
    }
  }

  public void verifyStudentsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuStudents();
      Students dbStudents = app.dbstudents().students();
      List<StudentData> uiStudents = app.student().list();
      assertThat(
          new HashSet<Object>(uiStudents),
          equalTo(
              dbStudents.stream()
                  .map(
                      (s) ->
                          new StudentData()
                              .withId(s.getId())
                              .withFirstName(s.getFirstname())
                              .withLastName(s.getLastname()))
                  .collect(Collectors.toSet())));
    }
  }

  public void verifyWorkerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuWorkers();
      Workers dbWorkers = app.db().workers();
      List<WorkerData> uiWorkers = app.worker().list();
      assertThat(
          new HashSet<Object>(uiWorkers),
          equalTo(
              dbWorkers.stream()
                  .map(
                      (s) ->
                          new WorkerData()
                              .withId(s.getId())
                              .withFirstName(s.getFirstName())
                              .withLastName(s.getLastName()))
                  .collect(Collectors.toSet())));
    }
  }

  public void verifyTrainerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuTrainers();
      Trainers dbTrainers = app.db().trainers();
      List<TrainerData> uiTrainers = app.trainer().list();
      assertThat(
          new HashSet<Object>(uiTrainers),
          equalTo(
              dbTrainers.stream()
                  .map(
                      (s) ->
                          new TrainerData()
                              .withId(s.getId())
                              .withFirstName(s.getFirstName())
                              .withLastName(s.getLastName()))
                  .collect(Collectors.toSet())));
    }
  }

  public void verifyLeadsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuLeads();
      Leads dbLeads = app.db().leads();
      List<LeadData> uiLeads = app.lead().list();
      assertThat(
          new HashSet<Object>(uiLeads),
          equalTo(
              dbLeads.stream()
                  .map(
                      (s) ->
                          new LeadData()
                              .withId(s.getId())
                              .withFirstName(s.getFirstname())
                              .withLastName(s.getLastname()))
                  .collect(Collectors.toSet())));
    }
  }
}
