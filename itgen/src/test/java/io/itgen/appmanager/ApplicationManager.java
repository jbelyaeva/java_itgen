package io.itgen.appmanager;

import io.itgen.appmanager.dbHelpers.DbHelper;
import io.itgen.appmanager.dbHelpers.DbHelperCandidaies;
import io.itgen.appmanager.dbHelpers.DbHelperMaterials;
import io.itgen.appmanager.dbHelpers.DbHelperRequest;
import io.itgen.appmanager.dbHelpers.DbHelperSchedule;
import io.itgen.appmanager.dbHelpers.DbHelperStudents;
import io.itgen.appmanager.dbHelpers.DbHelperTasks;
import io.itgen.appmanager.dbHelpers.DbHelperTest;
import io.itgen.appmanager.transactionHelper.TrCandidateHelper;
import io.itgen.appmanager.transactionHelper.TrFamilyHelper;
import io.itgen.appmanager.transactionHelper.TrLeadHelper;
import io.itgen.appmanager.transactionHelper.TrMaterialHelper;
import io.itgen.appmanager.transactionHelper.TrParentHelper;
import io.itgen.appmanager.transactionHelper.TrPaymentHelper;
import io.itgen.appmanager.transactionHelper.TrStudentHelper;
import io.itgen.appmanager.transactionHelper.TrTaskHelper;
import io.itgen.appmanager.transactionHelper.TrTestHelper;
import io.itgen.appmanager.transactionHelper.schedule.TrScheduleTodayHelper;
import io.itgen.appmanager.transactionHelper.schedule.TrScheduleTomorrowHelper;
import io.itgen.appmanager.transactionHelper.schedule.TrScheduleYesterdayHelper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ApplicationManager {

  public static Properties properties;
  public static String address;
  public WebDriver wd;
  private TrainerHelper trainerHelper;
  private StudentHelper studentHelper;
  private FamilyHelper familyHelper;
  private ParentHelper parentHelper;
  private WorkerHelper workerHelper;
  private LeadHelper leadHelper;
  private HelperBase helperBase;
  private ScheduleHelper scheduleHelper;
  private SessionHelper sessionHelper;
  private TestHelper testHelper;
  private NavigationHelper navigationHelper;
  private String browser;
  private DbHelper dbHelper;
  private SShotHelper sShotHelper;
  private DbHelperStudents dbHelperStudents;
  private DbHelperSchedule dbHelperSchedule;
  private DbHelperRequest dbHelperRequest;
  private DbHelperMaterials dbHelperMaterials;
  private DbHelperTasks dbHelperTasks;
  private DbHelperTest dbHelperTest;
  private DbHelperCandidaies dbHelperCandidaies;
  private WindowScheduleHelper windowScheduleHelper;
  private RequestHelper requestHelper;
  private LKParentHelper lkParentHelper;
  private PaymentHelper paymentHelper;
  private MaterialHelper materialHelper;
  private TaskHelper taskHelper;
  private CandidateHelper candidateHelper;
  private TrScheduleTomorrowHelper trScheduleTomorrowHelper;
  private TrScheduleYesterdayHelper trScheduleYesterdayHelper;
  private TrScheduleTodayHelper trScheduleTodayHelper;
  private TrStudentHelper transactionStudentHelper;
  private TrLeadHelper transactionLeadHelper;
  private TrParentHelper transactionParentHelper;
  private TrPaymentHelper transactionPaymentHelper;
  private TrFamilyHelper transactionFamilyHelper;
  private TrMaterialHelper transactionMaterialHelper;
  private TrTaskHelper transactionTaskHelper;
  private TrTestHelper transactionTestHelper;
  private TrCandidateHelper transactionCandidateHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(
        new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
    dbHelperStudents = new DbHelperStudents();
    dbHelperSchedule = new DbHelperSchedule();
    dbHelperRequest = new DbHelperRequest();
    dbHelperMaterials = new DbHelperMaterials();
    dbHelperTasks = new DbHelperTasks();
    dbHelperTest = new DbHelperTest();
    dbHelperCandidaies = new DbHelperCandidaies();
    trScheduleTomorrowHelper = new TrScheduleTomorrowHelper();
    trScheduleYesterdayHelper = new TrScheduleYesterdayHelper();
    trScheduleTodayHelper = new TrScheduleTodayHelper();
    transactionStudentHelper = new TrStudentHelper();
    transactionLeadHelper = new TrLeadHelper();
    transactionParentHelper = new TrParentHelper();
    transactionPaymentHelper = new TrPaymentHelper();
    transactionFamilyHelper = new TrFamilyHelper();
    transactionMaterialHelper = new TrMaterialHelper();
    transactionTaskHelper = new TrTaskHelper();
    transactionTestHelper = new TrTestHelper();
    transactionCandidateHelper = new TrCandidateHelper();
    if ("".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setVersion("85");
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", true);
      capabilities.setCapability("videoName", System.getProperty("videoName", "selenoid.mp4"));
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "windows")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
      wd.manage().window().setSize(new Dimension(1920, 1080));
    }
    wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    workerHelper = new WorkerHelper(wd);
    sessionHelper = new SessionHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    studentHelper = new StudentHelper(wd);
    parentHelper = new ParentHelper(wd);
    familyHelper = new FamilyHelper(wd);
    trainerHelper = new TrainerHelper(wd);
    scheduleHelper = new ScheduleHelper(wd);
    sShotHelper = new SShotHelper(wd);
    leadHelper = new LeadHelper(wd);
    windowScheduleHelper = new WindowScheduleHelper(wd);
    requestHelper = new RequestHelper(wd);
    lkParentHelper = new LKParentHelper(wd);
    paymentHelper = new PaymentHelper(wd);
    helperBase = new HelperBase(wd);
    materialHelper = new MaterialHelper(wd);
    taskHelper = new TaskHelper(wd);
    testHelper = new TestHelper(wd);
    candidateHelper = new CandidateHelper(wd);
    sessionHelper.login(
        properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    // проверить, есть ли папки для скриншотов, если нет - создать
    Path requiredMainDir =
        Paths.get(properties.getProperty("testsScreenshot"));
    if (!Files.exists(requiredMainDir)) Files.createDirectory(requiredMainDir);

    Path[] requiredDirs = {
        Paths.get(properties.getProperty("actual")),
        Paths.get(properties.getProperty("expected")),
        Paths.get(properties.getProperty("markedImages"))
    };
    for (Path dir : requiredDirs) {
      if (Files.exists(dir)) {
        continue;
      }
      Files.createDirectory(dir);
    }
  }

  public void stop() {
    wd.quit();
  }

  public WorkerHelper worker() {
    return workerHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  public SessionHelper session() {
    return sessionHelper;
  }

  public DbHelperStudents dbstudents() {
    return dbHelperStudents;
  }

  public DbHelperSchedule dbschedules() {
    return dbHelperSchedule;
  }

  public DbHelperRequest dbrequest() {
    return dbHelperRequest;
  }

  public DbHelperMaterials dbmaterial() {
    return dbHelperMaterials;
  }

  public DbHelperTasks dbtasks() {
    return dbHelperTasks;
  }

  public DbHelperTest dbtest() {
    return dbHelperTest;
  }

  public DbHelperCandidaies dbcandidates() {
    return dbHelperCandidaies;
  }

  public StudentHelper student() {
    return studentHelper;
  }

  public SShotHelper sshot() {
    return sShotHelper;
  }

  public ParentHelper parent() {
    return parentHelper;
  }

  public FamilyHelper family() {
    return familyHelper;
  }

  public TrainerHelper trainer() {
    return trainerHelper;
  }

  public ScheduleHelper schedule() {
    return scheduleHelper;
  }

  public LeadHelper lead() {
    return leadHelper;
  }

  public WindowScheduleHelper windowSchedule() {
    return windowScheduleHelper;
  }

  public RequestHelper request() {
    return requestHelper;
  }

  public LKParentHelper lkParent() {
    return lkParentHelper;
  }

  public PaymentHelper payment() {
    return paymentHelper;
  }

  public MaterialHelper material() {
    return materialHelper;
  }

  public TaskHelper task() {
    return taskHelper;
  }

  public HelperBase base() {
    return helperBase;
  }

  public TestHelper test() {
    return testHelper;
  }

  public CandidateHelper cantidate() {
    return candidateHelper;
  }

  public TrScheduleTomorrowHelper trScheduleTomorrow() {
    return trScheduleTomorrowHelper;
  }

  public TrScheduleYesterdayHelper trScheduleYesterday() {
    return trScheduleYesterdayHelper;
  }

  public TrScheduleTodayHelper trScheduleToday() {
    return trScheduleTodayHelper;
  }

  public TrStudentHelper trStudent() {
    return transactionStudentHelper;
  }

  public TrLeadHelper trLead() {
    return transactionLeadHelper;
  }

  public TrParentHelper trParent() {
    return transactionParentHelper;
  }

  public TrPaymentHelper trPayment() {
    return transactionPaymentHelper;
  }

  public TrFamilyHelper trFamily() {
    return transactionFamilyHelper;
  }

  public TrMaterialHelper trMaterial() {
    return transactionMaterialHelper;
  }

  public TrTaskHelper trTask() {
    return transactionTaskHelper;
  }

  public TrTestHelper trTest() {
    return transactionTestHelper;
  }

  public TrCandidateHelper trCandidate() {
    return transactionCandidateHelper;
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

}
