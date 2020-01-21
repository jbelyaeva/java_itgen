package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.itgen.model.AdminData;

public class AdminHelper extends HelperBase {

  public AdminHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAdminCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
  }

  public void fillAdminForm(AdminData adminData) {
    type(By.name("user-firstName"), adminData.getFirstname());
    type(By.name("user-lastName"), adminData.getLastname());
    type(By.name("user-email"), adminData.getEmail());
    type(By.name("user-phone"), adminData.getPhone());
//    click(By.name("role")); //без этого тоже работает
    new Select(wd.findElement(By.name("role"))).selectByVisibleText(adminData.getRole());
//    click(By.name("role")); //без этого тоже работает
  }

  public void addWorker() {
    click(By.linkText("Добавить сотрудника"));
  }
}
