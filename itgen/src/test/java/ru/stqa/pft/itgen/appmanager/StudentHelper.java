package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.StudentData1;
import ru.stqa.pft.itgen.model.StudentData2;

import java.util.ArrayList;
import java.util.List;


public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public  void createFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    dropDownList(By.cssSelector("#profile-gender"), studentData.getGender());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthday());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), studentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getInst());

  }
/*  public void fillStudentForm1(StudentData1 studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    dropDownList(By.cssSelector("#profile-gender"), studentData.getGender());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthday());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getContacts());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), studentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getInst());

  }*/

  public void fillParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-email\"]"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
  }

  public void fillFamilyParentForm(ParentData parentData) {
    Actions actions = new Actions(wd);
    WebElement element = wd.findElement(By.cssSelector("li.list-group-item.create-family-parent-item > div.form-group > input[name=\"profile-firstName\"]"));
    actions.moveToElement(element, 1, 1).build().perform();
    element.click();
    type(By.xpath("(//input[@name='profile-firstName'])[2]"), parentData.getFirstName());
    type(By.xpath("(//input[@name='profile-lastName'])[2]"), parentData.getLastName());
    dropDownList(By.xpath("(//select[@id='profile-country'])[2]"), parentData.getCountry());
    type(By.xpath("(//input[@name='profile-city'])[2]"), parentData.getCity());
    dropDownList(By.xpath("(//select[@id='profile-timezone'])[2]"), parentData.getTimeZone());
    type(By.xpath("(//input[@name='profile-contact-phone'])[2]"), parentData.getPhone());
    type(By.xpath("(//input[@name='profile-contact-skype'])[2]"), parentData.getSkype());
    type(By.name("profile-contact-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.xpath("(//input[@name='profile-contact-c2d'])[2]"), parentData.getC2d());
    click(By.xpath("//div[5]/a"));
    type(By.xpath("(//input[@name='profile-contact-viber'])[2]"), parentData.getViber());
    type(By.xpath("(//input[@name='profile-contact-whatsapp'])[2]"), parentData.getWhatsapp());
    type(By.xpath("(//input[@name='profile-contact-telegram'])[2]"), parentData.getTelegram());
    type(By.xpath("(//input[@name='profile-contact-fb'])[2]"), parentData.getFb());
    type(By.xpath("(//input[@name='profile-contact-vk'])[2]"), parentData.getVk());
    type(By.xpath("(//input[@name='profile-contact-ok'])[2]"), parentData.getOk());
    type(By.xpath("(//input[@name='profile-contact-instagram'])[2]"), parentData.getInst());

  }

  public void submitStudentCreation() {
    click(By.xpath("//button[contains(@class,'create')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void selectedStudent() {
    click(By.cssSelector("a.btn-link"));
  }

  public void selectedFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void addParentInFamily() {
    click(By.xpath("//div[contains(@class, 'add-parent')]/span"));
  }

  public void submitParentCreation() {
    click(By.xpath("//button[contains(@class, 'family')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void addParent() {
    click(By.xpath("//button[@class='close btn-add-parent']"));
     }

  public void deleteStudent() {
    click(By.xpath("//button[contains(@class, 'remove')]"));
  }

  public void assertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void deleteFamily() {
    click(By.xpath("//button[contains(@class, 'btn-remove-family')]"));
  }

  public void submitFamilyCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void selectedParent() {
    click(By.xpath("(//div[@class='gena-panel-body'])[2]//a"));
  }

  public void deleteParent() {
    click(By.xpath("//button[contains(@class,'remove-user')]"));
  }

  public void assertDeleteSelectedFamily() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void assertDeleteSelectedStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modifyStudent() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void submitStudentModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void ModifyStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), studentData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), studentData.getLastname());
    enterADate(By.cssSelector("input[name=\"profile-birthday\"]"), studentData.getBirthday());
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList(By.cssSelector("#profile-gender"), studentData.getGender());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), studentData.getLocate());
    dropDownList(By.cssSelector("#profile-study-lang"), studentData.getStudyLang());
    dropDownList(By.cssSelector("#profile-duration"), studentData.getDuration());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), studentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), studentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), studentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), studentData.getViber());
    type(By.cssSelector("input[name=\"profile-family-id\"]"), studentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), studentData.getNote());
  }

  public void modifyParent() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
    }

  public void ModifyParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    dropDownList(By.cssSelector("#profile-country"), parentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), parentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), parentData.getTimeZone());
    dropDownList(By.cssSelector("#profile-locale"), parentData.getLocate());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-fb\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
    type(By.cssSelector("input[name=\"profile-family-id\"]"), parentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), parentData.getNote());
  }

  public void submitParentModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public int getStudentCount() {
   return countingWithPaginated();
  }

  //студенты с пагинацией
  public List<StudentData> getStudentList() {
    List<StudentData> students= new ArrayList<StudentData>();
    WebDriverWait wait = new WebDriverWait (wd, 2);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='pagination']//li[2]")));
    String next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements= wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(students, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements= wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(students, elements);
    return students;
  }
  //из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из ссылки в атрибуте
  //, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<StudentData> students, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; //достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); //разрезали Имя Фамилия
      StudentData student = new StudentData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      students.add(student);
    }
  }

  public String getIdNewStudent(List<StudentData> before, List<StudentData> after) {
    int a = 0;
    String  getIdAfter = "";
    for (int i = 0; i < after.size(); i++) {
               getIdAfter = after.get(i).getId();

         for (int j = 0; j < before.size(); j++) {
               String getIdBefore = before.get(j).getId();
                    if (getIdAfter.equals(getIdBefore)) { a = 1; break;}
                    else { a = 2;}
         }
                if (a == 2) { break;}
    }
    return getIdAfter;
  }

  public void createFamily(StudentData student) {
    createFamily();
    addStudent();
    addParent();
    fillStudentForm(student);
    fillFamilyParentForm(new ParentData().withFirstName("Костин").withLastName("Петя"));
    submitFamilyCreation();
    }
}

