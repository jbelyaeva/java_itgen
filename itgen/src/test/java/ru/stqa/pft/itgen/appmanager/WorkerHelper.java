package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerHelper extends HelperBase {

  public WorkerHelper(WebDriver wd) {
    super(wd);
  }

  public void submitWorkerCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void fillWorkerForm(WorkerData workerData) {
    type(By.name("user-firstName"), workerData.getFirstName());
    type(By.name("user-lastName"), workerData.getLastName());
    type(By.name("user-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.name("user-phone"), workerData.getPhone());
    dropDownList(By.name("role"), workerData.getRole());
  }

  public void addWorker() {
    click(By.cssSelector("a.btn.btn-default"));
  }

  public void selectedWorker() {
    click(By.cssSelector("a.btn-link"));
  }

  public void deleteWorker() {
    click(By.cssSelector("button.btn.btn-danger.btn-sm.btn-remove-user"));
  }

  public void assertDeleteSelectedWorker() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifyWorker() {
    click(By.cssSelector("span.small.glyphicon.glyphicon-pencil"));
  }

  public void submitWorkerModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifiWorkerForm(WorkerData workerData) {
    type(By.name("profile-firstName"), workerData.getFirstName());
    type(By.name("profile-lastName"), workerData.getLastName());
    enterADate(By.name("profile-startWorkAt"), workerData.getStartDay());
    enterADate(By.name("profile-birthday"), workerData.getBirthDay());
    dropDownList(By.id("profile-gender"), workerData.getGender());
    dropDownList(By.id("profile-country"), workerData.getCountry());
    type(By.name("profile-city"), workerData.getCity());
    dropDownList(By.id("profile-timezone"), workerData.getTimeZone());
    dropDownList(By.id("profile-locale"), workerData.getLocate());
    type(By.name("profile-contact-phone"), workerData.getPhone());
    type(By.name("profile-contact-skype"), workerData.getSkype());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-viber"), workerData.getViber());
    type(By.name("profile-contact-whatsapp"), workerData.getWhatsapp());
    type(By.name("profile-contact-telegram"), workerData.getTg());
    type(By.name("profile-contact-fb"), workerData.getFb());
    type(By.name("profile-contact-vk"), workerData.getVk());
    type(By.name("profile-contact-ok"), workerData.getOk());
    type(By.name("profile-contact-instagram"), workerData.getInst());
  }

  public int getWorkerCount() {
    int count = 0;
    String next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        count = count + wd.findElements(By.cssSelector("a.btn-link")).size();
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    return count + wd.findElements(By.cssSelector("a.btn-link")).size();
  }
}
