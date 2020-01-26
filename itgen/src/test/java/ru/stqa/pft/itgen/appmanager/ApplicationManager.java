package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;

  private StudentHelper studentHelper;
  private WorkerHelper workerHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private String browser;

  public ApplicationManager (String browser) {
    this.browser=browser;
  }
  public void init() {

   if (browser.equals(BrowserType.FIREFOX)){
     wd = new FirefoxDriver();
   }
   else if (browser.equals(BrowserType.CHROME)){
     wd = new ChromeDriver();
   }
   else if (browser.equals(BrowserType.IE)){
     wd = new InternetExplorerDriver();
   }

   wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   wd.get("http://localhost:3000/login");
   workerHelper = new WorkerHelper(wd);
   sessionHelper = new SessionHelper(wd);
   navigationHelper = new NavigationHelper(wd);
   studentHelper = new StudentHelper(wd);
   sessionHelper.login("superadmin", "111111");
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
}
