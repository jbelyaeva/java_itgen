package io.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.itgen.model.StudentData;
import io.itgen.model.Students;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeMethod;

public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnAddStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void btnFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void select() {
    click(By.cssSelector("a.btn-link"));
  }

  public void btnDeleteStudent() {
    click(By.xpath("//button[contains(@class, 'remove')]"));
  }

  public void btnAddStudentInFamily() {
    click(By.xpath("//div[contains(@class,'child')]/span[contains(@class,'sign')]"));
  }

  public void assertDeleteSelectedStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void selectModifyStudent() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void btnSaveModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void openFiltr() {
    click(By.xpath("//button[@class='close']"));
  }

  public void changePol(int pol) {
    dropDownList_Integer(By.xpath("//select[@id='filter-gender']"), pol);
  }

  public void btnApply() {
    click(By.xpath("//button[contains(@class,'accept')]"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    dropDownList_Integer(By.cssSelector("#profile-gender"), studentData.getGender());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthdayUi());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), studentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getInst());
  }

  public void fillAddStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    dropDownList_Integer(By.cssSelector("#profile-gender"), studentData.getGender());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthdayUi());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), studentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getInst());
  }

  public void ModifyStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthdayUi());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList_Integer(By.cssSelector("#profile-gender"), studentData.getGender());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), studentData.getLocate());
    dropDownList(By.cssSelector("#profile-study-lang"), studentData.getStudyLang());
    dropDownList_Integer(By.cssSelector("#profile-duration"), studentData.getDuration());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), studentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getInst());
    type(By.cssSelector("input[name=\"profile-family-id\"]"), studentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), studentData.getNote());
  }

  public int getStudentCount() {
    return countingWithPaginated();
  }

  // студенты с пагинацией
  public List<StudentData> list() {
    List<StudentData> students = new ArrayList<StudentData>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//ul[@class='pagination']//li[2]"))); // ждать пока не появится элемент
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(students, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(students, elements);
    return students;
  }

  // из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из
  // ссылки в атрибуте, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<StudentData> students, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; // достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); // разрезали Имя Фамилия
      StudentData student =
          new StudentData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      students.add(student);
    }
  }

  public void btnCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void btnCreationBad() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    thereAreErrorMessages(); // проверка появления сообщения об ошибке
  }

  public void btnCreateFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void create(StudentData student) {
    btnCreateFamily();
    btnAddStudent();
    fillStudentForm(student);
    btnCreation();
  }

  public void createBad(StudentData student) {
    btnCreateFamily();
    btnAddStudent();
    fillStudentForm(student);
    btnCreationBad();
  }

  public void selectedStudentAfterCreate() {
    click(By.xpath("(//div[@class='gena-panel-body'])[1]//a"));
  }

  public void modify(StudentData student) {
    selectModifyStudent();
    ModifyStudentForm(student);
    btnSaveModify();
  }

  public StudentData getNewStudentDB(Students before, Students after) {
    StudentData studentNew=null;
    for (StudentData studentListAfter : after) {
      if (!before.contains(studentListAfter)) {
        studentNew=studentListAfter;
        break;}
    }
    return studentNew;
  }

  public void delete() {
    btnDeleteStudent();
    assertDeleteSelectedStudent();
  }

  public void selectStudentInListUIById(String id) {
    // находим пагинатор
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    // есть ли на первой странице наш студент
    List<WebElement> list = wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
    if (list.size() > 0) {
      wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
    } else {
      // если студентк не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin =
            wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        }
      }
    }
  }

}
