package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;


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
    Actions actions = new Actions(wd);
    WebElement element = wd.findElement(By.cssSelector("li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"));
    actions.moveToElement(element, 1, 1).build().perform();
    element.click();
    type(By.xpath("(//input[@name='profile-firstName'])[2]"), parentData.getFirstName());
    type(By.xpath("(//input[@name='profile-lastName'])[2]"), parentData.getLastName());
    dropDownList(By.xpath("(//select[@id='profile-country'])[2]"), parentData.getCountry());
    type(By.xpath("(//input[@name='profile-city'])[2]"), parentData.getCity());
    dropDownList(By.xpath("(//select[@id='profile-timezone'])[2]"), parentData.getTimeZone());
    type(By.xpath("(//input[@name='profile-contact-phone'])[2]"), parentData.getPhone());
    type(By.xpath("(//input[@name='profile-contact-skype'])[2]"), parentData.getSkype());
    type(By.name("profile-contact-email"), parentData.getEmail());
    type(By.xpath("(//input[@name='profile-contact-c2d'])[2]"), parentData.getC2d());
    click(By.xpath("//div[5]/a"));
    type(By.xpath("(//input[@name='profile-contact-viber'])[2]"), parentData.getViber());
    type(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]"), parentData.getWhatsapp());
    type(By.xpath("(//input[@name='profile-contact-telegram'])[2]"), parentData.getTelegram());
    type(By.xpath("(//input[@name='profile-contact-fb'])[2]"), parentData.getFb());
    type(By.xpath("(//input[@name='profile-contact-vk'])[2]"), parentData.getVk());
    type(By.xpath("(//input[@name='profile-contact-ok'])[2]"), parentData.getOk());
    type(By.xpath("(//input[@name='profile-contact-instagram'])[2]"), parentData.getInst());

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

  public void assertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void deleteFamily() {
    click(By.cssSelector("button.btn.btn-danger.btn-remove-family"));
  }

  public void submitFamilyCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void selectedParent() {
    click(By.xpath("//div[2]/div[2]/ul/li/div/a"));
    //click(By.xpath("//a[contains(@href, '/profile/wNJ8LLQafbx659czN')]"));
  }

  public void deleteParent() {
    click(By.cssSelector("button.btn.btn-danger.btn-sm.btn-remove-user"));
  }

  public void assertDeleteSelectedFamily() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void assertDeleteSelectedStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifyStudent() {
    click(By.cssSelector("span.small.glyphicon.glyphicon-pencil"));
  }

  public void submitStudentModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void ModifyStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirsname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthday());
    dropDownList(By.cssSelector("#profile-gender"), studentData.getGender());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), studentData.getLocate());
    dropDownList(By.cssSelector("#profile-study-lang"), studentData.getStudyLang());
    dropDownList(By.cssSelector("#profile-duration"), studentData.getDuration());
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
    type(By.cssSelector("input[name=\"profile-family-id\"]"), studentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), studentData.getNote());
  }
}
