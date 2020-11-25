package app.appmanager;

import app.appmanager.dbHelpers.DbHelper;
import app.appmanager.dbHelpers.DbHelperBase;
import app.appmanager.dbHelpers.DbHelperCandidaies;
import app.appmanager.dbHelpers.DbHelperChat;
import app.appmanager.dbHelpers.DbHelperCommunity;
import app.appmanager.dbHelpers.DbHelperMaterials;
import app.appmanager.dbHelpers.DbHelperRequest;
import app.appmanager.dbHelpers.DbHelperSchedule;
import app.appmanager.dbHelpers.DbHelperStudents;
import app.appmanager.dbHelpers.DbHelperTasks;
import app.appmanager.dbHelpers.DbHelperTest;
import app.appmanager.transactionHelper.TrCandidateHelper;
import app.appmanager.transactionHelper.TrChatHelper;
import app.appmanager.transactionHelper.TrCommunityHelper;
import app.appmanager.transactionHelper.TrFamilyHelper;
import app.appmanager.transactionHelper.TrFinishedLessonHelper;
import app.appmanager.transactionHelper.TrLeadHelper;
import app.appmanager.transactionHelper.TrMaterialHelper;
import app.appmanager.transactionHelper.TrParentHelper;
import app.appmanager.transactionHelper.TrPaymentHelper;
import app.appmanager.transactionHelper.TrStudentHelper;
import app.appmanager.transactionHelper.TrTaskHelper;
import app.appmanager.transactionHelper.TrTestHelper;
import app.appmanager.transactionHelper.TrWorkerHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleTodayHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleTomorrowHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleYesterdayHelper;
import core.general.Assertions;
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
  private final String browser;
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
  private ChatHelper chatHelper;
  private NavigationHelper navigationHelper;
  private SShotHelper sShotHelper;
  private WindowScheduleHelper windowScheduleHelper;
  private RequestHelper requestHelper;
  private LKParentHelper lkParentHelper;
  private PaymentHelper paymentHelper;
  private MaterialHelper materialHelper;
  private TaskHelper taskHelper;
  private CandidateHelper candidateHelper;
  private CommunityHelper communityHelper;
  private Assertions assertions;
  private DbHelper dbHelper;
  private DbHelperBase dbHelperBase;
  private DbHelperStudents dbHelperStudents;
  private DbHelperSchedule dbHelperSchedule;
  private DbHelperRequest dbHelperRequest;
  private DbHelperMaterials dbHelperMaterials;
  private DbHelperTasks dbHelperTasks;
  private DbHelperTest dbHelperTest;
  private DbHelperChat dbHelperChat;
  private DbHelperCandidaies dbHelperCandidaies;
  private DbHelperCommunity dbHelperCommunity;
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
  private TrChatHelper transactionChatHelper;
  private TrCandidateHelper transactionCandidateHelper;
  private TrWorkerHelper transactionWorkerHelper;
  private TrFinishedLessonHelper transactionFinishedLessonHelper;
  private TrCommunityHelper transactionCommunityHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(
        new FileReader(
            new File(String.format("src/test/resources/properties/%s.properties", target))));

    dbHelper = new DbHelper();
    dbHelperBase = new DbHelperBase();
    dbHelperStudents = new DbHelperStudents();
    dbHelperSchedule = new DbHelperSchedule();
    dbHelperRequest = new DbHelperRequest();
    dbHelperMaterials = new DbHelperMaterials();
    dbHelperTasks = new DbHelperTasks();
    dbHelperTest = new DbHelperTest();
    dbHelperChat = new DbHelperChat();
    dbHelperCandidaies = new DbHelperCandidaies();
    dbHelperCommunity = new DbHelperCommunity();

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
    transactionChatHelper = new TrChatHelper();
    transactionCandidateHelper = new TrCandidateHelper();
    transactionWorkerHelper = new TrWorkerHelper();
    transactionFinishedLessonHelper = new TrFinishedLessonHelper();
    transactionCommunityHelper = new TrCommunityHelper();

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
      capabilities.setVersion("86");
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
    chatHelper = new ChatHelper(wd);
    candidateHelper = new CandidateHelper(wd);
    communityHelper = new CommunityHelper(wd);
    assertions = new Assertions(wd);

    sessionHelper.login(
        properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    // проверить, есть ли папки для скриншотов, если нет - создать
    Path requiredMainDir = Paths.get(properties.getProperty("testsScreenshot"));
    if (!Files.exists(requiredMainDir)) {
      Files.createDirectory(requiredMainDir);
    }

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

  public DbHelperBase dbbase() {
    return dbHelperBase;
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

  public DbHelperChat dbchat() {
    return dbHelperChat;
  }

  public DbHelperCandidaies dbcandidates() {
    return dbHelperCandidaies;
  }

  public DbHelperCommunity dbcommunity() {
    return dbHelperCommunity;
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

  public ChatHelper chat() {
    return chatHelper;
  }

  public CandidateHelper cantidate() {
    return candidateHelper;
  }

  public CommunityHelper community() {
    return communityHelper;
  }

  public Assertions check() {
    return assertions;
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

  public TrChatHelper trChat() {
    return transactionChatHelper;
  }

  public TrCandidateHelper trCandidate() {
    return transactionCandidateHelper;
  }

  public TrWorkerHelper trWorker() {
    return transactionWorkerHelper;
  }

  public TrFinishedLessonHelper trFinishedLesson() {
    return transactionFinishedLessonHelper;
  }

  public TrCommunityHelper trCommunity() {
    return transactionCommunityHelper;
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

}
