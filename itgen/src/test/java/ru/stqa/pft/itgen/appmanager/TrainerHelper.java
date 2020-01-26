package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }
}
