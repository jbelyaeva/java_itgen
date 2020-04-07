package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
  private WorkerHelper workerHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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
    trainerHelper = new TrainerHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.quit();
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public WorkerHelper getWorkerHelper() {
    return workerHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public StudentHelper getStudentHelper() {
    return studentHelper;
  }

  public TrainerHelper getTrainerHelper() {
    return trainerHelper;
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
