package ru.stqa.pft.itgen;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WorkerCreationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWorkerCreation() throws Exception {
    driver.get("https://test3.portal.itgen.io/login");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("superadmin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("111111");
    driver.findElement(By.xpath("//button[@class='btn btn-block btn-primary center-block btn-login']")).click();
    driver.findElement(By.xpath("//a[@href='/admins']")).click();
    driver.findElement(By.linkText("Добавить сотрудника")).click();
    driver.findElement(By.name("user-firstName")).click();
    driver.findElement(By.name("user-firstName")).clear();
    driver.findElement(By.name("user-firstName")).sendKeys("Иван");
    driver.findElement(By.name("user-lastName")).click();
    driver.findElement(By.name("user-lastName")).clear();
    driver.findElement(By.name("user-lastName")).sendKeys("0Иванов");
    driver.findElement(By.name("user-email")).click();
    driver.findElement(By.name("user-email")).clear();
    driver.findElement(By.name("user-email")).sendKeys("eee+"+Math.round(Math.random()*10)+"@mail.ru");
    driver.findElement(By.name("user-phone")).click();
    driver.findElement(By.name("user-phone")).sendKeys("1111111111111");
    driver.findElement(By.name("role")).click();
    new Select(driver.findElement(By.name("role"))).selectByVisibleText("Администратор");
    driver.findElement(By.name("role")).click();
    driver.findElement(By.xpath("//button[@class='btn btn-primary btn-create']")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
