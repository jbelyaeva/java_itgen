package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoWorker() {
    click(By.xpath("//a[@href='/admins']"));
  }

  public void gotoStudents() {
    click(By.xpath("//div[@class='left-bar-body']//div[2]"));
  }
}
