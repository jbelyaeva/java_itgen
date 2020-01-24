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
//    Assert.assertFalse(isElementPresent(By.cssSelector(".help-block.help-block-error"))); // проверка валидации email
//    Assert.assertTrue(isElementPresent(By.cssSelector("li.active > a"))); // проверка перехода на др.страницу после нажатия на кнопку
  }

  public void fillWorkerForm(WorkerData workerData) {
    type(By.name("user-firstName"), workerData.getFirstname());
    type(By.name("user-lastName"), workerData.getLastname());
    type(By.name("user-email"), workerData.getEmail());
    type(By.name("user-phone"), workerData.getPhone());
    dropDownList(By.name("role"), workerData.getRole());
  }

  public void addWorker() {
    click(By.cssSelector("a.btn.btn-default"));
  }

}
