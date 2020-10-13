package io.itgen.appmanager;

import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LeadHelper extends HelperBase {

  public LeadHelper(WebDriver wd) {
    super(wd);
  }

  public void btnCreateLead() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  public void btnAddLead() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  public void btnSaveLead() {
    click(By.xpath("//button[contains(@class,'save')]"));
  }

  public void btnLotPoint() {
    click(By.xpath("//button[contains(@class,'dropdown')]"));
  }

  public void btnDeleteLead() {
    click(By.xpath("//a[contains(@class,'remove')]"));
  }

  public void assertDeleteSelectedLead() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(
        isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void btnPencil() {
   clickWithMoveToElementAndWait(5, By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void fillLeadForm(LeadData leadData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), leadData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), leadData.getLastname());
    dropDownList(By.id("lead-role"), leadData.getRoleUi());
    dropDownList(By.cssSelector("#profile-country"), leadData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), leadData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), leadData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), leadData.getLocate());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), leadData.getPhone());
    type(
        By.cssSelector("input[name=\"profile-contact-email\"]"),
        "julja+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), leadData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), leadData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), leadData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), leadData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), leadData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), leadData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), leadData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), leadData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), leadData.getInst());
  }

  public void modifyLeadForm(LeadData leadData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), leadData.getFirstname());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), leadData.getLastname());
    dropDownList(By.id("lead-role"), leadData.getRoleUi());
    dropDownList(By.cssSelector("#profile-country"), leadData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), leadData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), leadData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), leadData.getLocate());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), leadData.getPhone());
    type(
        By.cssSelector("input[name=\"profile-contact-email\"]"),
        "julja+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), leadData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), leadData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), leadData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), leadData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), leadData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), leadData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), leadData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), leadData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), leadData.getInst());
  }

  public void selectLeadInListUIById(String id) {
    // находим пагинатор
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    // есть ли на первой странице наш студент
    List<WebElement> list = wd.findElements(By.cssSelector("a[href='/leads/" + id + "'"));
    if (list.size() > 0) {
      wd.findElement(By.cssSelector("a[href='/leads/" + id + "'")).click();
    } else {
      // если студентк не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin = wd.findElements(By.cssSelector("a[href='/leads/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/leads/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        }
      }
    }
  }

  public void delete() {
    btnLotPoint();
    btnDeleteLead();
    assertDeleteSelectedLead();
    noErrorMessage();
  }

  // работник с пагинацией
  public List<LeadData> list() {
    List<LeadData> leads = new ArrayList<LeadData>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//ul[@class='pagination']//li[2]"))); // ждать пока не появится элемент
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.xpath("//a[contains(@href,'/leads/')]"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(leads, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.xpath("//a[contains(@href,'/leads/')]"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(leads, elements);
    return leads;
  }

  private void includeInListBaseWebElement(List<LeadData> leads, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; // достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); // разрезали Имя Фамилия
      LeadData lead =
          new LeadData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      leads.add(lead);
    }
  }

  public LeadData getNewLeadDB(Leads before, Leads after) {
    LeadData leadNew = null;
    for (LeadData leadListAfter : after) {
      if (!before.contains(leadListAfter)) {
        leadNew = leadListAfter;
        break;
      }
    }
    return leadNew;
  }

  public void modify(LeadData lead) {
    btnPencil();
    modifyLeadForm(lead);
    btnSaveLead();
    noErrorMessage();
  }

  public void create(LeadData lead) {
    btnCreateLead();
    fillLeadForm(lead);
    btnAddLead();
    noErrorMessage();
  }

  public void waitForLoadHeader() {
    new WebDriverWait(wd, 10)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2")));
  }
}
