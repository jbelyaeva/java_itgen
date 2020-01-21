package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public void createFamaly() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.name("profile-firstName"), studentData.getFirsname());
    type(By.name("profile-lastName"), studentData.getLastname());
    click(By.id("profile-gender"));
    new Select(wd.findElement(By.id("profile-gender"))).selectByVisibleText(studentData.getGender());
    Actions builder = new Actions(wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
    click(By.name("profile-birthday")); // клик по нужному элементу
    builder.sendKeys(studentData.getBirthday()).perform(); // исполнить нужную последовательность действий
    click(By.name("profile-city"));
    new Select(wd.findElement(By.id("profile-country"))).selectByVisibleText(studentData.getCountry());
    click(By.id("profile-country"));
    type(By.name("profile-city"), studentData.getSity());
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[1]/following::div[1]"));
    new Select(wd.findElement(By.id("profile-timezone"))).selectByVisibleText(studentData.getTimezone());
    click(By.id("profile-timezone"));
    type(By.name("profile-contact-phone"), studentData.getPhone());
    type(By.name("profile-contact-skype"), studentData.getSkype());
    type(By.name("profile-contact-c2d"), studentData.getC2d());
    click(By.linkText("Показать еще"));
    type(By.name("profile-contact-viber"), studentData.getViber());
    type(By.name("profile-contact-whatsapp"), studentData.getWhatsapp());
    type(By.name("profile-contact-telegram"), studentData.getTelegram());
    type(By.name("profile-contact-fb"), studentData.getFb());
    type(By.name("profile-contact-vk"), studentData.getVk());
    type(By.name("profile-contact-ok"), studentData.getOk());
    type(By.name("profile-contact-instagram"), studentData.getInst());
  }

  public void submitFamalyCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create-family']"));
  }
}
