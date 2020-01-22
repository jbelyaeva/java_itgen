package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.itgen.model.WorkerData;

public class WorkerHelper extends HelperBase {

  public WorkerHelper(WebDriver wd) {
    super(wd);
  }

  public void submitAdminCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    click(By.linkText("Основное")); // проверка, что покинули страницу создания сотрудника
  }

  public void fillWorkerForm(WorkerData workerData) {
    type(By.name("user-firstName"), workerData.getFirstname());
    type(By.name("user-lastName"), workerData.getLastname());
    type(By.name("user-email"), workerData.getEmail());
    type(By.name("user-phone"), workerData.getPhone());
    dropDownList(By.name("role"), workerData.getRole());
  }

  public void addWorker() {
    click(By.linkText("Добавить сотрудника"));
  }

}
