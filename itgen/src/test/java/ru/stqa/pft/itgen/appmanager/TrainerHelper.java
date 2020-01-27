package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.TrainerData;

public class TrainerHelper extends HelperBase {
  public TrainerHelper(WebDriver wd) {
    super(wd);
  }


  public void selectedTrainer() {
    click(By.xpath("//tr[9]/td/a"));
  }

  public void deleteTrainer() {
    click(By.xpath("//button[@type='button']"));
  }

  public void assertDeleteSelectedTrainer() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifyTrainer() {
    click(By.cssSelector("span.small.glyphicon.glyphicon-pencil"));
  }

  public void submitTrainerModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
  }

  public void modifiTrainerForm(TrainerData trainerData) {
    type(By.name("profile-firstName"), trainerData.getFirstName());
    type(By.name("profile-lastName"), trainerData.getLastName());
    enterADate(By.name("profile-startWorkAt"), trainerData.getStartWork());
    enterADate(By.name("profile-birthday"), trainerData.getBirthday());
    dropDownList(By.id("profile-gender"), trainerData.getGender());
    // выпадающий список с чек-боксами
    click(By.cssSelector("button.btn.btn-default.dropdown-toggle"));
    click(By.name("skill_1"));
    click(By.name("skill_2"));
    click(By.name("skill_3"));
    click(By.name("skill_17"));
    click(By.name("skill_21"));
    click(By.name("skill_19"));
    click(By.name("skill_4"));
    click(By.name("skill_5"));
    click(By.name("skill_6"));
    click(By.name("skill_7"));
    click(By.name("skill_16"));
    click(By.name("skill_8"));
    click(By.name("skill_9"));
    click(By.name("skill_10"));
    click(By.name("skill_11"));
    click(By.name("skill_12"));
    click(By.name("skill_13"));
    click(By.name("skill_14"));
    click(By.name("skill_18"));
    click(By.name("skill_15"));
    click(By.name("skill_22"));
    click(By.name("skill_20"));
    // закрывает выпадающий список с чек-боксами
    Actions builder = new Actions(wd);
    wd.findElement(By.cssSelector("button.btn.btn-default.dropdown-toggle"));
    builder.click().perform();
    //
    type(By.name("profile-maxSlots"), trainerData.getMaxSlots());
    dropDownList(By.id("profile-country"), trainerData.getCountry());
    type(By.name("profile-city"), trainerData.getCity());
    dropDownList(By.id("profile-timezone"), trainerData.getTimeZone());
    dropDownList(By.id("profile-locale"), trainerData.getLocate());
    // выбор языка обучения -- чек-боксы
    click(By.xpath("//input[@value='ru']"));
    click(By.xpath("//input[@value='en']"));
    //
    dropDownList(By.id("profile-pay-base"), trainerData.getPayBase());
    type(By.name("profile-contact-phone"), trainerData.getPhone());
    type(By.name("profile-contact-skype"), trainerData.getSkype());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-viber"), trainerData.getViber());
    type(By.name("profile-contact-whatsapp"), trainerData.getWhatsapp());
    type(By.name("profile-contact-telegram"), trainerData.getTg());
    type(By.name("profile-contact-fb"), trainerData.getFb());
    type(By.name("profile-contact-vk"), trainerData.getVk());
    type(By.name("profile-contact-ok"), trainerData.getOk());
    type(By.name("profile-contact-instagram"), trainerData.getInst());
    type(By.name("profile-note"), trainerData.getNote());
  }
}
