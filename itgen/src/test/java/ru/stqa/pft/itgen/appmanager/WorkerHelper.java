package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.services.WorkerService;

import java.util.ArrayList;
import java.util.List;

public class WorkerHelper extends HelperBase {

  public WorkerHelper(WebDriver wd) {
    super(wd);
  }

  public void bntCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void fillWorkerForm(WorkerData workerData) {
    type(By.name("user-firstName"), workerData.getFirstName());
    type(By.name("user-lastName"), workerData.getLastName());
    type(By.name("user-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.name("user-phone"), workerData.getPhone());
    dropDownList(By.name("role"), workerData.getRoleUi());
  }

  public void btnAddWorker() {
    click(By.cssSelector("a.btn.btn-default"));
  }

  public void selectedWorker() {
    click(By.cssSelector("a.btn-link"));
  }

  public void deleteWorker() {
    click(By.xpath("//button[contains(@class,'remove')]"));
  }

  public void alertDeleteSelectedWorker() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void modifyWorker() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void submitWorkerModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void modifiWorkerForm(WorkerData workerData) {
    type(By.name("profile-firstName"), workerData.getFirstName());
    type(By.name("profile-lastName"), workerData.getLastName());
    enterADate(By.name("profile-startWorkAt"), workerData.getStartDayUi());
    enterADate(By.name("profile-birthday"), workerData.getBirthDayUi());
    dropDownList_Integer(By.id("profile-gender"), workerData.getGender());
    dropDownList(By.id("profile-country"), workerData.getCountry());
    type(By.name("profile-city"), workerData.getCity());
    dropDownList(By.id("profile-timezone"), workerData.getTimeZone());
    dropDownList(By.id("profile-locale"), workerData.getLocate());
    type(By.name("profile-contact-phone"), workerData.getPhone());
    type(By.name("profile-contact-telegram"), workerData.getTelegramg());
    type(By.name("profile-contact-viber"), workerData.getViber());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-skype"), workerData.getSkype());
    type(By.name("profile-contact-whatsapp"), workerData.getWhatsapp());
    type(By.name("profile-contact-facebook"), workerData.getFb());
    type(By.name("profile-contact-vk"), workerData.getVk());
    type(By.name("profile-contact-ok"), workerData.getOk());
    type(By.name("profile-contact-instagram"), workerData.getInst());
  }

  public int getWorkerCount() {
    return countingWithPaginated();
  }

  public String createWorker(WorkerData worker) {
    btnAddWorker();
    fillWorkerForm(worker);
    bntCreation();
    String url = getURL();
    return url;
  }

  public void createFirstWorker(WorkerData worker) {
    btnAddWorker();
    fillWorkerForm(worker);
    bntCreation();
  }

  public void deletionWorker(String id) {
    selectedWorkerById(id);
    deleteWorker();
    alertDeleteSelectedWorker();
  }

  public void modificationWorker(WorkerData worker, String id) {
    selectedWorkerById(id);
    modifyWorker();
    modifiWorkerForm(worker);
    submitWorkerModify();
  }

  public void selectedWorkerById(String id) {
    //находим пагинатор
    String next = wd.findElement(By.xpath("//button[3]")).getAttribute("class");
    //  List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    //есть ли на первой странице наш работник
    List<WebElement> list = wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
    if (list.size() > 0) {
      wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
    } else {
      //если работник не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin = wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        }
      }
    }
  }


  //работник с пагинацией
  public List<WorkerData> list() {
    // selectListAll();
    List<WorkerData> workers = new ArrayList<WorkerData>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@aria-label,'next')]")));//ждать пока не появится элемент
    String next = wd.findElement(By.xpath("//button[contains(@aria-label,'next')]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(workers, elements);
        wd.findElement(By.xpath("//button[contains(@aria-label,'next')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//button[contains(@aria-label,'next')]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(workers, elements);
    return workers;
  }


  //из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из ссылки в атрибуте
  //, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<WorkerData> workers, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; //достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); //разрезали Имя Фамилия
      WorkerData worker = new WorkerData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      workers.add(worker);
    }
  }

}
