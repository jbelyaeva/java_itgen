package ru.stqa.pft.itgen;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ParentCreationTest {
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
  public void testParentCreation() throws Exception {
    driver.get("https://test3.portal.itgen.io/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("superadmin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("111111");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пароль'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("Ученики")).click();
    driver.findElement(By.linkText("0мишин Миша")).click();
    driver.findElement(By.linkText("Семья")).click();
    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/main/div/div/div/div/div[2]/div/div/span")).click();
    // добавление родителя
    driver.findElement(By.name("profile-firstName")).click();
    driver.findElement(By.name("profile-firstName")).sendKeys("Витя");
    driver.findElement(By.name("profile-lastName")).click();
    driver.findElement(By.name("profile-lastName")).sendKeys("Витин");
    driver.findElement(By.name("profile-contact-phone")).click();
    driver.findElement(By.name("profile-contact-phone")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-skype")).click();
    driver.findElement(By.name("profile-contact-skype")).sendKeys("skype");
    driver.findElement(By.name("profile-contact-email")).click();
    driver.findElement(By.name("profile-contact-email")).sendKeys("10@test.ru");
    driver.findElement(By.name("profile-contact-c2d")).click();
    driver.findElement(By.name("profile-contact-c2d")).sendKeys("https://web.chat2desk.com/chat/my?dialogID=9346556");
    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/main/div/div/div/div[3]/div[5]/a/span")).click();//ссылка ещё??
    driver.findElement(By.name("profile-contact-viber")).click();
    driver.findElement(By.name("profile-contact-viber")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-whatsapp")).click();
    driver.findElement(By.name("profile-contact-whatsapp")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-telegram")).click();
    driver.findElement(By.name("profile-contact-telegram")).sendKeys("telegram");
    driver.findElement(By.name("profile-contact-fb")).click();
    driver.findElement(By.name("profile-contact-fb")).sendKeys("fb");
    driver.findElement(By.name("profile-contact-vk")).click();
    driver.findElement(By.name("profile-contact-vk")).sendKeys("vk");
    driver.findElement(By.name("profile-contact-ok")).click();
    driver.findElement(By.name("profile-contact-ok")).sendKeys("ok");
    driver.findElement(By.name("profile-contact-instagram")).click();
    driver.findElement(By.name("profile-contact-instagram")).sendKeys("inst");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проверьте правильность введенных данных'])[7]/following::button[1]")).click();

    //    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/nav/div/div[4]/a/img")).click();  //кнопка logout

    // хуй знает что
//    driver.findElement(By.name("username")).clear();
//    driver.findElement(By.name("username")).sendKeys("superadmin");
//    driver.findElement(By.name("password")).clear();
//    driver.findElement(By.name("password")).sendKeys("111111");
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
