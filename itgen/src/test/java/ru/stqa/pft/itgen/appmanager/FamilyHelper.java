package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.FamilyDataUI;
import ru.stqa.pft.itgen.model.StudentData;

public class FamilyHelper extends HelperBase {

  public FamilyHelper(WebDriver wd) {
    super(wd);
  }

  public void fillFamilyForm(FamilyDataUI familyDataUI) {
    // заполнение формы ученика
    type(By.cssSelector("input[name=\"profile-firstName\"]"), familyDataUI.getFirstnameStudent());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), familyDataUI.getLastnameStudent());
    dropDownList_Integer(By.cssSelector("#profile-gender"), familyDataUI.getGenderStudent());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), familyDataUI.getBirthdayUiStudent());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), familyDataUI.getPclevelStudent());
    dropDownList(By.cssSelector("#profile-country"), familyDataUI.getCountryStudent());
    type(By.cssSelector("input[name=\"profile-city\"]"), familyDataUI.getCityStudent());
    dropDownList(By.cssSelector("#profile-timezone"), familyDataUI.getTimezoneStudent());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), familyDataUI.getPhoneStudent());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), familyDataUI.getTelegramStudent());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), familyDataUI.getViberStudent());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), familyDataUI.getC2dStudent());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), familyDataUI.getSkypeStudent());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), familyDataUI.getWhatsappStudent());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), familyDataUI.getFbStudent());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), familyDataUI.getVkStudent());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), familyDataUI.getOkStudent());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), familyDataUI.getInstStudent());
    // заполнение формы родителя
    Actions actions = new Actions(wd);
    WebElement element = wd.findElement(By.cssSelector(
            "li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"));
    actions.moveToElement(element, 1, 1).build().perform();
    element.click();
    type(By.xpath("(//input[@name='profile-firstName'])[2]"), familyDataUI.getFirstnameParent());
    type(By.xpath("(//input[@name='profile-lastName'])[2]"), familyDataUI.getLastnameParent());
    dropDownList(By.xpath("(//select[@id='profile-country'])[2]"), familyDataUI.getCountryParent());
    type(By.xpath("(//input[@name='profile-city'])[2]"), familyDataUI.getCityParent());
    dropDownList(By.xpath("(//select[@id='profile-timezone'])[2]"), familyDataUI.getTimezoneParent());
    type(By.xpath("(//input[@name='profile-contact-phone'])[2]"), familyDataUI.getPhoneParent());
    type(By.name("profile-contact-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.xpath("(//input[@name='profile-contact-telegram'])[2]"), familyDataUI.getTelegramParent());
    type(By.xpath("(//input[@name='profile-contact-viber'])[2]"), familyDataUI.getViberParent());
    type(By.xpath("(//input[@name='profile-contact-c2d'])[2]"), familyDataUI.getC2dParent());
    click(By.xpath("//div[6]/a"));
    type(By.xpath("(//input[@name='profile-contact-skype'])[2]"), familyDataUI.getSkypeParent());
    type(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]"), familyDataUI.getWhatsappParent());
    type(By.xpath("(//input[@name='profile-contact-fb'])[2]"), familyDataUI.getFbParent());
    type(By.xpath("(//input[@name='profile-contact-vk'])[2]"), familyDataUI.getVkParent());
    type(By.xpath("(//input[@name='profile-contact-ok'])[2]"), familyDataUI.getOkParent());
    type(By.xpath("(//input[@name='profile-contact-instagram'])[2]"), familyDataUI.getInstParent());
  }
  public String getIdFamily(String url) {
    String[] getIdSplit = url.split("/");
    String id = getIdSplit[4]; //достали id
    return id;
  }
  public void createFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }
  public void submitFamilyCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }
  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }
  public void addParent() {
    click(By.xpath("//button[@class='close btn-add-parent']"));
  }
  public void selectedStudent() {
    click(By.cssSelector("a.btn-link"));
  }
  public void selectedFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }
  public void deleteFamily() {
    click(By.xpath("//button[contains(@class, 'btn-remove-family')]"));
  }
  public void alertDeleteSelectedFamily() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }
  public void createFamily(FamilyDataUI family) {
    createFamily();
    addStudent();
    addParent();
    fillFamilyForm(family);
    submitFamilyCreation();
  }
  public String deletionFamily(StudentData deletedStudent) {
    SelectStudentById(deletedStudent);
    selectedFamily();
    String url = getURL();
    deleteFamily();
    alertDeleteSelectedFamily();
    return url;
  }

  private void SelectStudentById(StudentData deletedStudent) {
     wd.findElement(By.cssSelector("a[href='/profile/"+deletedStudent.getId()+"'")).click();
    }
 }
