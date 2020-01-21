package ru.stqa.pft.itgen.tests.in_devel;

import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class  StudentCreationTest {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("https://test3.portal.itgen.io/login");
    wd.findElement(By.name("username")).clear();
    wd.findElement(By.name("username")).sendKeys("superadmin");
    wd.findElement(By.name("password")).clear();
    wd.findElement(By.name("password")).sendKeys("111111");
    wd.findElement(By.xpath("//button[@class='btn btn-block btn-primary center-block btn-login']")).click();
  }

  @Test
  public void testStudentCreation() throws Exception {
    wd.findElement(By.xpath("//a[@href='/childs']")).click();
    wd.findElement(By.xpath("//a[@href='/createFamily']")).click();
    wd.findElement(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']")).click();
    //Создание ребенка, заполнение полей
    wd.findElement(By.name("profile-firstName")).click();
    wd.findElement(By.name("profile-firstName")).sendKeys("Миша");
    wd.findElement(By.name("profile-lastName")).click();
    wd.findElement(By.name("profile-lastName")).sendKeys("0Мишин");
    wd.findElement(By.id("profile-gender")).click();
    new Select(wd.findElement(By.id("profile-gender"))).selectByVisibleText("Ж");
    Actions builder = new Actions(wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
    wd.findElement(By.name("profile-birthday")).click(); // клик по нужному элементу
    builder.sendKeys("11112022").perform(); // исполнить нужную последовательность действий
    wd.findElement(By.name("profile-city")).click();
    new Select(wd.findElement(By.id("profile-country"))).selectByVisibleText("Бангладеш");
    wd.findElement(By.id("profile-country")).click();
    wd.findElement(By.name("profile-city")).click();
    wd.findElement(By.name("profile-city")).sendKeys("Пересвет");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[1]/following::div[1]")).click();
    new Select(wd.findElement(By.id("profile-timezone"))).selectByVisibleText("(GMT+05:00) Азия/Ташкент");
    wd.findElement(By.id("profile-timezone")).click();
    wd.findElement(By.name("profile-contact-phone")).click();
    wd.findElement(By.name("profile-contact-phone")).sendKeys("111111111111");
    wd.findElement(By.name("profile-contact-skype")).click();
    wd.findElement(By.name("profile-contact-skype")).sendKeys("skype");
    wd.findElement(By.name("profile-contact-c2d")).click();
    wd.findElement(By.name("profile-contact-c2d")).sendKeys("https://web.chat2desk.com/chat/my?dialogID=9346556");
    wd.findElement(By.linkText("Показать еще")).click();
    wd.findElement(By.name("profile-contact-viber")).click();
    wd.findElement(By.name("profile-contact-viber")).sendKeys("111111111111");
    wd.findElement(By.name("profile-contact-whatsapp")).click();
    wd.findElement(By.name("profile-contact-whatsapp")).sendKeys("111111111111");
    wd.findElement(By.name("profile-contact-telegram")).click();
    wd.findElement(By.name("profile-contact-telegram")).sendKeys("telega");
    wd.findElement(By.name("profile-contact-fb")).click();
    wd.findElement(By.name("profile-contact-fb")).sendKeys("fb");
    wd.findElement(By.name("profile-contact-vk")).click();
    wd.findElement(By.name("profile-contact-vk")).sendKeys("vk");
    wd.findElement(By.name("profile-contact-ok")).click();
    wd.findElement(By.name("profile-contact-ok")).sendKeys("ok");
    wd.findElement(By.name("profile-contact-instagram")).click();
    wd.findElement(By.name("profile-contact-instagram")).sendKeys("inst");
    wd.findElement(By.xpath("//button[@class='btn btn-primary btn-create-family']")).click(); // кнопка создать??
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
