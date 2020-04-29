package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.Parents;

public class ParentHelper extends HelperBase {

  public ParentHelper(WebDriver wd) {
    super(wd);
  }

  public void selectedStudent() {
    click(By.cssSelector("a.btn-link"));
  }

  public void selectedParent() {
    click(By.xpath("(//div[@class='gena-panel-body'])[2]//a"));
  }

  public void selectedFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void addParentInFamily() {
    click(By.xpath("//div[contains(@class, 'add-parent')]/span"));
  }

  public void deleteParent() {
    click(By.xpath("//button[contains(@class,'remove-user')]"));
  }

  public void alertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void submitParentModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void submitParentCreation() {
    click(By.xpath("//button[contains(@class, 'family')]"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void modificationParent() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void creationParent(ParentData parent) {
    selectedStudent();
    selectedFamily();
    addParentInFamily();
    fillParentForm(parent);
    submitParentCreation();
  }

  public String deletionParent() {
    selectedStudent();
    selectedFamily();
    selectedParent();
    String url = getURL();
    deleteParent();
    alertDeleteSelectedParent();
    return url;
  }

  public String modifyParent(ParentData parent) {
    selectedStudent();
    selectedFamily();
    selectedParent();
    String url = getURL();
    modificationParent();
    ModifyParentForm(parent);
    submitParentModify();
    return url;
  }

  public void ModifyParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    dropDownList(By.cssSelector("#profile-country"), parentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), parentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), parentData.getTimeZone());
    dropDownList(By.cssSelector("#profile-locale"), parentData.getLocate());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
    type(By.cssSelector("input[name=\"profile-family-id\"]"), parentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), parentData.getNote());
  }

  public void fillParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-email\"]"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
  }

  public String getIdNewParentDB(Parents before, Parents after) {
    int a = 0;
    String getIdAfter = "";
    for (ParentData parent : after) {
      getIdAfter = parent.getId();
      for (ParentData parent_before : before) {
        String getIdBefore = parent_before.getId();
        if (getIdAfter.equals(getIdBefore)) {
          a = 1;
          break;
        } else {
          a = 2;
        }
      }
      if (a == 2) {
        break;
      }
    }
    return getIdAfter;
  }
}
