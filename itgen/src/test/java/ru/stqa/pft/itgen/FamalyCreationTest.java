package ru.stqa.pft.itgen;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class FamalyCreationTest {
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
  public void testFamalyCreation() throws Exception {
    driver.get("https://test3.portal.itgen.io/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("superadmin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("111111");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пароль'])[1]/following::button[1]")).click();
    driver.findElement(By.linkText("Ученики")).click();
    driver.findElement(By.linkText("Создать семью")).click();

    // Создание ученика, заполнение полей
    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/main/div/div/div/div/div/button/span")).click();
    driver.findElement(By.name("profile-firstName")).click();
    driver.findElement(By.name("profile-firstName")).sendKeys("Вася");
    driver.findElement(By.name("profile-lastName")).click();
    driver.findElement(By.name("profile-lastName")).sendKeys("0Васечкин");
    driver.findElement(By.id("profile-gender")).click();
    new Select(driver.findElement(By.id("profile-gender"))).selectByVisibleText("Ж");
    /*
    //Импортируем Actions и Action классы;
    //import org.openqa.selenium.interactions.Action;
    //import org.openqa.selenium.interactions.Actions;
    // Создаем объект класса Actions, с помощью которого будем генерировать действия:
    Actions builder = new Actions(driver);
    //Инициализируем объект класса Action с помощью builder:
    Action keyXPressed = builder.sendKeys("11112000").build();
    //Теперь в нужном месте тестирующего кода мы можем исполнить эту последовательность действий следующим образом
    driver.findElement(By.name("profile-birthday")).click();
    keyXPressed.perform();
    */
    Actions builder = new Actions(driver); // Создаем объект класса Actions, с помощью которого будем генерировать действия
    driver.findElement(By.name("profile-birthday")).click(); // клик по нужному элементу
    builder.sendKeys("11112022").perform(); // исполнить нужную последовательность действий

    driver.findElement(By.id("profile-country")).click();
    new Select(driver.findElement(By.id("profile-country"))).selectByVisibleText("Бахрейн");
    driver.findElement(By.id("profile-country")).click();
    driver.findElement(By.name("profile-city")).click();
    driver.findElement(By.name("profile-city")).sendKeys("Высоково");
    driver.findElement(By.id("profile-timezone")).click();
    new Select(driver.findElement(By.id("profile-timezone"))).selectByVisibleText("(GMT+02:00) Европа/Запорожье");
    driver.findElement(By.id("profile-timezone")).click();
    driver.findElement(By.name("profile-contact-phone")).click();
    driver.findElement(By.name("profile-contact-phone")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-skype")).click();
    driver.findElement(By.name("profile-contact-skype")).sendKeys("skype");
    driver.findElement(By.name("profile-contact-c2d")).click();
    driver.findElement(By.name("profile-contact-c2d")).sendKeys("https://web.chat2desk.com/chat/my?dialogID=9346556");
    driver.findElement(By.linkText("Показать еще")).click();
    driver.findElement(By.name("profile-contact-viber")).click();
    driver.findElement(By.name("profile-contact-viber")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-whatsapp")).click();
    driver.findElement(By.name("profile-contact-whatsapp")).sendKeys("111111111111");
    driver.findElement(By.name("profile-contact-telegram")).click();
    driver.findElement(By.name("profile-contact-telegram")).sendKeys("telega");
    driver.findElement(By.name("profile-contact-fb")).click();
    driver.findElement(By.name("profile-contact-fb")).sendKeys("fb");
    driver.findElement(By.name("profile-contact-vk")).click();
    driver.findElement(By.name("profile-contact-vk")).sendKeys("vk");
    driver.findElement(By.name("profile-contact-ok")).click();
    driver.findElement(By.name("profile-contact-ok")).sendKeys("ok");
    driver.findElement(By.name("profile-contact-instagram")).click();
    driver.findElement(By.name("profile-contact-instagram")).sendKeys("inst");

    //создание родителя
    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/main/div/div/div[2]/div/div/button/span")).click();
    driver.findElement(By.xpath("(//input[@name='profile-firstName'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-firstName'])[2]")).sendKeys("Петя");
    driver.findElement(By.xpath("(//input[@name='profile-lastName'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-lastName'])[2]")).sendKeys("Петечкин");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[2]/following::select[1]")).click();
    new Select(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[2]/following::select[1]"))).selectByVisibleText("Австралия");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[2]/following::select[1]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-city'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-city'])[2]")).sendKeys("Загорск");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Часовой пояс'])[2]/following::select[1]")).click();
    new Select(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Часовой пояс'])[2]/following::select[1]"))).selectByVisibleText("(GMT-08:00) Америка/Лос-Анджелес");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Часовой пояс'])[2]/following::select[1]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-phone'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-phone'])[2]")).sendKeys("89629861123");
    driver.findElement(By.xpath("(//input[@name='profile-contact-skype'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-skype'])[2]")).sendKeys("скайп");
    driver.findElement(By.name("profile-contact-email")).click();
    driver.findElement(By.name("profile-contact-email")).sendKeys("12@test.ru");
    driver.findElement(By.xpath("(//input[@name='profile-contact-c2d'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-c2d'])[2]")).sendKeys("https://web.chat2desk.com/chat/all?dialogID=9346556");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проверьте правильность введенных данных'])[9]/following::a[1]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-viber'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-viber'])[2]")).sendKeys("84994031017");
    driver.findElement(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]")).sendKeys("84994031017");
    driver.findElement(By.xpath("(//input[@name='profile-contact-telegram'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-telegram'])[2]")).sendKeys("telega");
    driver.findElement(By.xpath("(//input[@name='profile-contact-fb'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-fb'])[2]")).sendKeys("fb");
    driver.findElement(By.xpath("(//input[@name='profile-contact-vk'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-vk'])[2]")).sendKeys("vk");
    driver.findElement(By.xpath("(//input[@name='profile-contact-ok'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-ok'])[2]")).sendKeys("ok");
    driver.findElement(By.xpath("(//input[@name='profile-contact-instagram'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='profile-contact-instagram'])[2]")).sendKeys("inst");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Код приглашения'])[1]/following::button[1]")).click();

    /* Логаут
    driver.findElement(By.xpath("//div[@id='__blaze-root']/div/div[2]/nav/div/div[4]/a/img")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("superadmin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("111111");
    */
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
