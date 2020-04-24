package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.pft.itgen.model.FamilyDataUi;

public class FamilyHelper extends HelperBase {

  public FamilyHelper(WebDriver wd) {
    super(wd);
  }

  public void fillFamilyForm(FamilyDataUi familyDataUi) {
    // заполнение формы ученика
    type(By.cssSelector("input[name=\"profile-firstName\"]"), familyDataUi.getFirstnameStudent());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), familyDataUi.getLastnameStudent());
    dropDownList_Integer(By.cssSelector("#profile-gender"), familyDataUi.getGenderStudent());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), familyDataUi.getBirthdayUiStudent());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), familyDataUi.getPclevelStudent());
    dropDownList(By.cssSelector("#profile-country"), familyDataUi.getCountryStudent());
    type(By.cssSelector("input[name=\"profile-city\"]"), familyDataUi.getCityStudent());
    dropDownList(By.cssSelector("#profile-timezone"), familyDataUi.getTimezoneStudent());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), familyDataUi.getPhoneStudent());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), familyDataUi.getTelegramStudent());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), familyDataUi.getViberStudent());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), familyDataUi.getC2dStudent());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), familyDataUi.getSkypeStudent());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), familyDataUi.getWhatsappStudent());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), familyDataUi.getFbStudent());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), familyDataUi.getVkStudent());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), familyDataUi.getOkStudent());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), familyDataUi.getInstStudent());
    // заполнение формы родителя
    Actions actions = new Actions(wd);
    WebElement element = wd.findElement(By.cssSelector(
            "li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"));
    actions.moveToElement(element, 1, 1).build().perform();
    element.click();
    type(By.xpath("(//input[@name='profile-firstName'])[2]"), familyDataUi.getFirstnameParent());
    type(By.xpath("(//input[@name='profile-lastName'])[2]"), familyDataUi.getLastnameParent());
    dropDownList(By.xpath("(//select[@id='profile-country'])[2]"), familyDataUi.getCountryParent());
    type(By.xpath("(//input[@name='profile-city'])[2]"), familyDataUi.getCityParent());
    dropDownList(By.xpath("(//select[@id='profile-timezone'])[2]"), familyDataUi.getTimezoneParent());
    type(By.xpath("(//input[@name='profile-contact-phone'])[2]"), familyDataUi.getPhoneParent());
    type(By.name("profile-contact-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.xpath("(//input[@name='profile-contact-telegram'])[2]"), familyDataUi.getTelegramParent());
    type(By.xpath("(//input[@name='profile-contact-viber'])[2]"), familyDataUi.getViberParent());
    type(By.xpath("(//input[@name='profile-contact-c2d'])[2]"), familyDataUi.getC2dParent());
    click(By.xpath("//div[6]/a"));
    type(By.xpath("(//input[@name='profile-contact-skype'])[2]"), familyDataUi.getSkypeParent());
    type(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]"), familyDataUi.getWhatsappParent());
    type(By.xpath("(//input[@name='profile-contact-fb'])[2]"), familyDataUi.getFbParent());
    type(By.xpath("(//input[@name='profile-contact-vk'])[2]"), familyDataUi.getVkParent());
    type(By.xpath("(//input[@name='profile-contact-ok'])[2]"), familyDataUi.getOkParent());
    type(By.xpath("(//input[@name='profile-contact-instagram'])[2]"), familyDataUi.getInstParent());
  }
}
