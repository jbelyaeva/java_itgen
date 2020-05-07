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
  public WebDriver wd;

  private TrainerHelper trainerHelper;
  private StudentHelper studentHelper;
  private FamilyHelper familyHelper;
  private ParentHelper parentHelper;
  private WorkerHelper workerHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private String browser;
  private DBHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DBHelper();
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
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public WorkerHelper workers() {
    return workerHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public DBHelper db() {
    return dbHelper;
  }

  public StudentHelper student() {
    return studentHelper;
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

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

  public Screenshot getScreenShert(String locator) {
    Screenshot actualScreenshot;
    // return actualScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(wd);
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    return new AShot().addIgnoredElement(By.xpath("//tbody")).takeScreenshot(wd);
  }
}
