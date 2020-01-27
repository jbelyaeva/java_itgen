package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public void createFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirsname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    dropDownList(By.cssSelector("#profile-gender"), studentData.getGender());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthday());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getViber());


    // альтернативные локаторы
/*
    type(By.name("profile-firstName"), studentData.getFirsname());
    type(By.name("profile-lastName"), studentData.getLastname());
    dropDownList(By.id("profile-gender"), studentData.getGender());
    enterADate(By.name("profile-birthday"), studentData.getBirthday());
    dropDownList(By.id("profile-country"), studentData.getCountry());
    type(By.name("profile-city"), studentData.getCity());
    dropDownList(By.id("profile-timezone"), studentData.getTimezone());
    type(By.name("profile-contact-phone"), studentData.getPhone());
    type(By.name("profile-contact-skype"), studentData.getSkype());
    type(By.name("profile-contact-c2d"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-viber"), studentData.getViber());
    type(By.name("profile-contact-whatsapp"), studentData.getWhatsapp());
    type(By.name("profile-contact-telegram"), studentData.getTelegram());
    type(By.name("profile-contact-fb"), studentData.getFb());
    type(By.name("profile-contact-vk"), studentData.getVk());
    type(By.name("profile-contact-ok"), studentData.getOk());
    type(By.name("profile-contact-instagram"), studentData.getInst());
*/

  }

  public void fillParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-email\"]"), parentData.getEmail());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
  }

  public void fillFamilyParentForm(ParentData parentData) {
    type(By.cssSelector("li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"), parentData.getFirstName());

  }

  public void submitStudentCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create-family']"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void selectedStudent() {
    click(By.cssSelector("a.btn-link"));
  }

  public void selectedFamily() {
    click(By.xpath("//div[@class='links']//a[3]"));
  }

  public void addParentInFamily() {
    click(By.cssSelector("div.gena-panel-btn.btn-add-parent > span.glyphicon.glyphicon-plus-sign"));
  }

  public void submitParentCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family-member"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void addParent() {
    click(By.xpath("//button[@class='close btn-add-parent']"));
     }

  public void deleteStudent() {
    click(By.xpath("(//button[@type='button'])[2]"));
  }

  public void assertDeleteSelectedStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void deleteFamily() {
    click(By.cssSelector("button.btn.btn-danger.btn-remove-family"));
  }

  public void assertDeleteSelectedFamily() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

}
