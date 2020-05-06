package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.TrainerData;
import ru.stqa.pft.itgen.model.WorkerData;

import java.util.ArrayList;
import java.util.List;

public class TrainerHelper extends HelperBase {
  public TrainerHelper(WebDriver wd) {
    super(wd);
  }

  public void selectedTrainer() {
    //click(By.xpath("//tr[9]/td/a"));
    click(By.xpath("//a[contains(@href,'profile')]"));
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
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void submitTrainerModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifiTrainerForm(TrainerData trainerData) {
    type(By.name("profile-firstName"), trainerData.getFirstName());
    type(By.name("profile-lastName"), trainerData.getLastName());
    enterADate(By.name("profile-startWorkAt"), trainerData.getStartWorkUi());
    enterADate(By.name("profile-birthday"), trainerData.getBirthdayUi());
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
//    click(By.name("skill_16"));
//    click(By.name("skill_8"));
//    click(By.name("skill_9"));
//    click(By.name("skill_10"));
//    click(By.name("skill_11"));
//    click(By.name("skill_12"));
//    click(By.name("skill_13"));
//    click(By.name("skill_14"));
//    click(By.name("skill_18"));
//    click(By.name("skill_15"));
//    click(By.name("skill_22"));
//    click(By.name("skill_20"));
    // закрывает выпадающий список с чек-боксами
    Actions builder = new Actions(wd);
    wd.findElement(By.cssSelector("button.btn.btn-default.dropdown-toggle"));
    builder.click().perform();
    //
    type(By.xpath("//input[@name='profile-maxSlots']"), trainerData.getMaxSlots());
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
    type(By.name("profile-contact-telegram"), trainerData.getTg());
    type(By.name("profile-contact-viber"), trainerData.getViber());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-skype"), trainerData.getSkype());
    type(By.name("profile-contact-whatsapp"), trainerData.getWhatsapp());
    type(By.name("profile-contact-fb"), trainerData.getFb());
    type(By.name("profile-contact-vk"), trainerData.getVk());
    type(By.name("profile-contact-ok"), trainerData.getOk());
    type(By.name("profile-contact-instagram"), trainerData.getInst());
    type(By.name("profile-note"), trainerData.getNote());
  }

  public int getTrainerCount() {
    return countingWithPaginated();
  }
  public String createTrainer(TrainerData trainer) {
    addTrainer();
    fillTrainerForm(trainer);
    submitTrainerCreation();
    String url = getURL();
    return url;
  }
  public void addTrainer() {
    click(By.cssSelector("a.btn.btn-default"));
  }
  public void fillTrainerForm(TrainerData trainerData) {
    type(By.name("user-firstName"), trainerData.getFirstName());
    type(By.name("user-lastName"), trainerData.getLastName());
    type(By.name("user-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.name("user-phone"), trainerData.getPhone());
    dropDownList(By.name("role"), trainerData.getRole());
  }
  public void submitTrainerCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }
  //работник с пагинацией
  public List<TrainerData> list() {
    List<TrainerData> trainers = new ArrayList<TrainerData>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='pagination']//li[2]")));//ждать пока не появится элемент
    String next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(trainers, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(trainers, elements);
    return trainers;
  }

  //из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из ссылки в атрибуте
  //, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<TrainerData> trainers, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; //достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); //разрезали Имя Фамилия
      TrainerData trainer = new TrainerData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      trainers.add(trainer);
    }
  }

}
