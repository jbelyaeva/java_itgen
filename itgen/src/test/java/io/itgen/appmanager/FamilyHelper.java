package io.itgen.appmanager;

import io.itgen.model.FamilyDataUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FamilyHelper extends HelperBase {

  public FamilyHelper(WebDriver wd) {
    super(wd);
  }

  public void fillFamilyForm(FamilyDataUI familyDataUI) {

    if(isElementPresent(By.name("profile-firstName"))){
    type(By.name("profile-firstName"), familyDataUI.getFirstnameStudent());}
    else refresh();

    type(By.name("profile-lastName"), familyDataUI.getLastnameStudent());
    dropDownList_Integer(By.cssSelector("#profile-gender"), familyDataUI.getGenderStudent());
    enterADate(
        By.name("profile-birthday"), familyDataUI.getBirthdayUiStudent());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), familyDataUI.getPclevelStudent());
    dropDownList(By.cssSelector("#profile-country"), familyDataUI.getCountryStudent());
    type(By.name("profile-city"), familyDataUI.getCityStudent());
    dropDownList(By.cssSelector("#profile-timezone"), familyDataUI.getTimezoneStudent());
    type(By.name("profile-contact-phone"), familyDataUI.getPhoneStudent());
    type(
        By.name("profile-contact-telegram"),
        familyDataUI.getTelegramStudent());
    type(By.name("profile-contact-viber"), familyDataUI.getViberStudent());
    type(By.name("profile-contact-c2d"), familyDataUI.getC2dStudent());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-skype"), familyDataUI.getSkypeStudent());
    type(
        By.name("profile-contact-whatsapp"),
        familyDataUI.getWhatsappStudent());
    type(By.name("profile-contact-facebook"), familyDataUI.getFbStudent());
    type(By.name("profile-contact-vk"), familyDataUI.getVkStudent());
    type(By.name("profile-contact-ok"), familyDataUI.getOkStudent());
    type(
        By.name("profile-contact-instagram"), familyDataUI.getInstStudent());
    // заполнение формы родителя
    Actions actions = new Actions(wd);
    WebElement element =
        wd.findElement(
            By.cssSelector(
                "li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"));
    actions.moveToElement(element, 1, 1).build().perform();
    element.click();
    type(By.xpath("(//input[@name='profile-firstName'])[2]"), familyDataUI.getFirstnameParent());
    type(By.xpath("(//input[@name='profile-lastName'])[2]"), familyDataUI.getLastnameParent());
    dropDownList(By.xpath("(//select[@id='profile-country'])[2]"), familyDataUI.getCountryParent());
    type(By.xpath("(//input[@name='profile-city'])[2]"), familyDataUI.getCityParent());
    dropDownList(
        By.xpath("(//select[@id='profile-timezone'])[2]"), familyDataUI.getTimezoneParent());
    type(By.xpath("(//input[@name='profile-contact-phone'])[2]"), familyDataUI.getPhoneParent());
    type(
        By.name("profile-contact-email"),
        "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(
        By.xpath("(//input[@name='profile-contact-telegram'])[2]"),
        familyDataUI.getTelegramParent());
    type(By.xpath("(//input[@name='profile-contact-viber'])[2]"), familyDataUI.getViberParent());
    type(By.xpath("(//input[@name='profile-contact-c2d'])[2]"), familyDataUI.getC2dParent());
    click(By.xpath("//div[6]/a"));
    type(By.xpath("(//input[@name='profile-contact-skype'])[2]"), familyDataUI.getSkypeParent());
    type(
        By.xpath("(//input[@name='profile-contact-whatsapp'])[2]"),
        familyDataUI.getWhatsappParent());
    type(By.xpath("(//input[@name='profile-contact-facebook'])[2]"), familyDataUI.getFbParent());
    type(By.xpath("(//input[@name='profile-contact-vk'])[2]"), familyDataUI.getVkParent());
    type(By.xpath("(//input[@name='profile-contact-ok'])[2]"), familyDataUI.getOkParent());
    type(By.xpath("(//input[@name='profile-contact-instagram'])[2]"), familyDataUI.getInstParent());
  }

  public void createFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void submitFamilyCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    Assert.assertFalse(
        isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void addParent() {
    click(By.xpath("//button[@class='close btn-add-parent']"));
  }

  public void bntModifyFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void bntDeleteFamily() {
    click(By.xpath("//button[contains(@class, 'btn-remove-family')]"));
  }

  public void alertDeleteSelectedFamily() {

    // подождали, навели курсор, нажали на подтверждение
    WebElement dynamicElement1 =
        (new WebDriverWait(wd, 5))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='modal-content']//button[contains(@class,'danger')]")));
    Actions actions1 = new Actions(wd);
    actions1.moveToElement(dynamicElement1).build().perform();
    dynamicElement1.click();
    Assert.assertFalse(
        isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void alertDeleteFamily() {
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(
        isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void create(FamilyDataUI family) {
    createFamily();
    addStudent();
    addParent();
    fillFamilyForm(family);
    submitFamilyCreation();
    noErrorMessage();
  }

  public void btnFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void delete() {
    btnFamily();
    bntDeleteFamily();
    alertDeleteSelectedFamily();
    noErrorMessage();
  }
}
