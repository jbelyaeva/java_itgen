package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  public static Properties propertiesAshot;
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

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
    propertiesAshot = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    String targetAshot = System.getProperty("target", "ashot");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    propertiesAshot.load(new FileReader(new File(String.format("src/test/resources/%s.properties", targetAshot))));

    dbHelper = new DbHelper();
    dbHelperStudents = new DbHelperStudents();
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
    leadHelper=new LeadHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  //  SShotHelper.(propertiesAshot.getProperty("expected"), propertiesAshot.getProperty("actual"), propertiesAshot.getProperty("markedImages"));
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

  public DbHelperStudents dbstudents() {
    return dbHelperStudents;
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

  public LeadHelper lead(){return leadHelper;}

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

 }
