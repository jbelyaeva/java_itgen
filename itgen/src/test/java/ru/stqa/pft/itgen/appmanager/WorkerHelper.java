package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.WorkerProfileData;
import ru.stqa.pft.itgen.model.WorkerUserData;

public class WorkerHelper extends HelperBase {

  public WorkerHelper(WebDriver wd) {
    super(wd);
  }

  public void submitWorkerCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void fillWorkerForm(WorkerUserData workerUserData) {
    type(By.name("user-firstName"), workerUserData.getFirstname());
    type(By.name("user-lastName"), workerUserData.getLastname());
    type(By.name("user-email"), workerUserData.getEmail());
    type(By.name("user-phone"), workerUserData.getPhone());
    dropDownList(By.name("role"), workerUserData.getRole());
  }

  public void addWorker() {
    click(By.cssSelector("a.btn.btn-default"));
  }

  public void selectedWorker() {
    click(By.cssSelector("a.btn-link"));
  }

  public void deleteWorker() {
    click(By.xpath("(//button[@type='button'])"));
  }

  public void assertDeleteSelectedWorker() {
    click(By.xpath("(//button[@type='button'])[6]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifyWorker() {
    click(By.cssSelector("span.small.glyphicon.glyphicon-pencil"));
  }

  public void submitWorkerModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
  }

  public void modifiWorkerForm(WorkerProfileData workerProfileData) {
    type(By.name("profile-firstName"), workerProfileData.getFirstName());
    type(By.name("profile-lastName"), workerProfileData.getLastName());
    enterADate(By.name("profile-startWorkAt"), workerProfileData.getStartDay());
    enterADate(By.name("profile-birthday"), workerProfileData.getBirthDay());
    dropDownList(By.id("profile-gender"), workerProfileData.getGender());
    dropDownList(By.id("profile-country"), workerProfileData.getCountry());
    type(By.name("profile-city"), workerProfileData.getCity());
    dropDownList(By.id("profile-timezone"), workerProfileData.getTimeZone());
    dropDownList(By.id("profile-locale"), workerProfileData.getLocate());
    type(By.name("profile-contact-phone"), workerProfileData.getPhone());
    type(By.name("profile-contact-skype"), workerProfileData.getSkype());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-viber"), workerProfileData.getViber());
    type(By.name("profile-contact-whatsapp"), workerProfileData.getWhatsapp());
    type(By.name("profile-contact-telegram"), workerProfileData.getTg());
    type(By.name("profile-contact-fb"), workerProfileData.getFb());
    type(By.name("profile-contact-vk"), workerProfileData.getVk());
    type(By.name("profile-contact-ok"), workerProfileData.getOk());
    type(By.name("profile-contact-instagram"), workerProfileData.getInst());
  }
}
