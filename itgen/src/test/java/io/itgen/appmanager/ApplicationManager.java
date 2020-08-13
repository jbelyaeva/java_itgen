package io.itgen.appmanager;

import io.itgen.appmanager.dbHelpers.DbHelper;
import io.itgen.appmanager.dbHelpers.DbHelperRequest;
import io.itgen.appmanager.dbHelpers.DbHelperSchedule;
import io.itgen.appmanager.dbHelpers.DbHelperStudents;
import io.itgen.appmanager.transactionHelper.TrFamilyHelper;
import io.itgen.appmanager.transactionHelper.TrLeadHelper;
import io.itgen.appmanager.transactionHelper.TrParentHelper;
import io.itgen.appmanager.transactionHelper.TrPaymentHelper;
import io.itgen.appmanager.transactionHelper.TrStudentHelper;
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
  public WebDriver wd;
  private TrainerHelper trainerHelper;
  private StudentHelper studentHelper;
  private FamilyHelper familyHelper;
  private ParentHelper parentHelper;
  private WorkerHelper workerHelper;
  private LeadHelper leadHelper;
  private ScheduleHelper scheduleHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private String browser;
  private DbHelper dbHelper;
  private SShotHelper sShotHelper;
  private DbHelperStudents dbHelperStudents;
  private DbHelperSchedule dbHelperSchedule;
  private DbHelperRequest dbHelperRequest;
  private WindowScheduleHelper windowScheduleHalper;
  private RequestHelper requestHalper;
  private LKParentHelper lkParentHelper;
  private PaymentHelper paymentHelper;
  private TrScheduleTomorrowHelper trScheduleTomorrowHelper;
  private TrScheduleYesterdayHelper trScheduleYesterdayHelper;
  private TrScheduleTodayHelper trScheduleTodayHelper;
  private TrStudentHelper transactionStudentHelper;
  private TrLeadHelper transactionLeadHelper;
  private TrParentHelper transactionParentHelper;
  private TrPaymentHelper transactionPaymentHelper;
  private TrFamilyHelper transactionFamilyHelper;

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
    trScheduleTomorrowHelper = new TrScheduleTomorrowHelper();
    trScheduleYesterdayHelper = new TrScheduleYesterdayHelper();
    trScheduleTodayHelper = new TrScheduleTodayHelper();
    transactionStudentHelper = new TrStudentHelper();
    transactionLeadHelper = new TrLeadHelper();
    transactionParentHelper = new TrParentHelper();
    transactionPaymentHelper = new TrPaymentHelper();
    transactionFamilyHelper = new TrFamilyHelper();
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
      capabilities.setVersion("83");
      capabilities.setCapability("enableVNC", true);
      capabilities.setCapability("enableVideo", false);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "windows")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
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
    windowScheduleHalper = new WindowScheduleHelper(wd);
    requestHalper = new RequestHelper(wd);
    lkParentHelper = new LKParentHelper(wd);
    paymentHelper = new PaymentHelper(wd);
    sessionHelper.login(
        properties.getProperty("web.Login"), properties.getProperty("web.Password"));
    // проверить, есть ли папки для скриншотов, если нет - создать
    Path[] requiredDirs = {
      Paths.get(properties.getProperty("actual")), Paths.get(properties.getProperty("markedImages"))
    };
    for (Path dir : requiredDirs) {
      if (Files.exists(dir)) continue;
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
    return windowScheduleHalper;
  }

  public RequestHelper request() {
    return requestHalper;
  }

  public LKParentHelper lkParent() {
    return lkParentHelper;
  }

  public PaymentHelper payment() {
    return paymentHelper;
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

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
