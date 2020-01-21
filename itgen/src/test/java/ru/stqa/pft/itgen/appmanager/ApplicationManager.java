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

  private AdminHelper adminHelper;
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

   wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   wd.get("https://test3.portal.itgen.io/login");
   adminHelper = new AdminHelper(wd);
   sessionHelper=new SessionHelper(wd);
   navigationHelper = new NavigationHelper(wd);
   sessionHelper.login("admin", "111111");
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

  public AdminHelper getAdminHelper() {
    return adminHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
