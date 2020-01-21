package ru.stqa.pft.itgen.tests.in_devel;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StudentDeletionTest {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testStudentDeletion() throws Exception {
    wd.get("https://test3.portal.itgen.io/login");
    wd.findElement(By.name("username")).click();
    wd.findElement(By.name("username")).clear();
    wd.findElement(By.name("username")).sendKeys("superadmin");
    wd.findElement(By.name("password")).clear();
    wd.findElement(By.name("password")).sendKeys("111111");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пароль'])[1]/following::button[1]")).click();
    wd.findElement(By.linkText("Ученики")).click();
    wd.findElement(By.linkText("0мишин Миша")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Установить пароль 111111'])[1]/following::button[1]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/following::button[1]")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
