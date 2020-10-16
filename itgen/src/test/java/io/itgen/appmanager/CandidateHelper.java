package io.itgen.appmanager;

import io.itgen.model.CandidateData;
import io.itgen.model.TrainerData;
import io.itgen.services.TrainerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CandidateHelper extends HelperBase {

  TrainerService trainerService = new TrainerService();

  public CandidateHelper(WebDriver wd) {
    super(wd);
  }


  public void create(CandidateData candidate, String vacancy) {
    btnAddCandidate();
    fillFormCandidate(candidate, vacancy);
    btnCreate();
  }

  private void btnCreate() {
    click(By.xpath("//button[contains(@class,'create')]"));
  }

  private void fillFormCandidate(CandidateData candidate, String vacancy) {
    type(By.xpath("//input[@name='profile-firstName']"), candidate.getFirstName());
    type(By.xpath("//input[@name='profile-lastName']"), candidate.getLastName());
    type(By.name("profile-english-firstName"), candidate.getEngFirstName());
    type(By.name("profile-english-lastName"), candidate.getEngLastName());
    enterADate(By.cssSelector("input[name=profile-birthday]"),
        DateISOToUsualDataString(candidate.getBirthday()));

    dropDownList_Integer(By.cssSelector("#profile-gender"), candidate.getGender());

    click(By.xpath("//select[@id='profile-vacancy']"));
    click(By.xpath("//option[@value='" + vacancy + "']"));

    click(By.xpath("//select[@id='profile-country']"));
    click(By.xpath("//option[@value='" + candidate.getCountry() + "']"));

    type(By.cssSelector("input[name=profile-city]"), candidate.getCity());

    click(By.xpath("//select[@id='profile-timezone']"));
    click(By.xpath("//option[@value='" + candidate.getTimezone() + "']"));

    click(By.xpath("//select[@id='profile-locale']"));
    click(By.xpath("//option[@value='" + candidate.getLocale() + "']"));

    wd.findElement(By.xpath("//textarea[@name='profile-note']"))
        .sendKeys(candidate.getNote());

    wd.findElement(By.xpath("//textarea[@name='profile-info']"))
        .sendKeys(candidate.getInfo());

    TrainerData trainer = trainerService.findById(candidate.getReferId());
    type(By.xpath("//div[@class='input-group']//input[@name='user-name']"), trainer.getLastName());
    click(By.xpath("//a[@class='search-result']"));

    type(By.xpath("//input[@name='profile-resume-link']"), candidate.getResume());

    type(By.xpath("//input[@name='profile-test-task-link']"), candidate.getTestTask());

    type(By.cssSelector("input[name=profile-contact-phone]"),
        candidate.getContacts().get(0).getVal());
    type(By.cssSelector("input[name=profile-contact-email]"),
        candidate.getContacts().get(1).getVal());
    type(By.cssSelector("input[name=profile-contact-telegram]"),
        candidate.getContacts().get(2).getVal());
    type(By.cssSelector("input[name=profile-contact-viber]"),
        candidate.getContacts().get(3).getVal());
    type(By.cssSelector("input[name=profile-contact-c2d]"),
        candidate.getContacts().get(4).getVal());

    click(By.cssSelector("a.btn-link.btn-show"));

    type(By.cssSelector("input[name=profile-contact-skype]"),
        candidate.getContacts().get(5).getVal());
    type(By.cssSelector("input[name=profile-contact-whatsapp]"),
        candidate.getContacts().get(6).getVal());
    type(By.cssSelector("input[name=profile-contact-facebook]"),
        candidate.getContacts().get(7).getVal());
    type(By.cssSelector("input[name=profile-contact-vk]"), candidate.getContacts().get(8).getVal());
    type(By.cssSelector("input[name=profile-contact-ok]"), candidate.getContacts().get(9).getVal());
    type(By.cssSelector("input[name=profile-contact-instagram]"),
        candidate.getContacts().get(10).getVal());

    type(By.cssSelector("input[name=candidate-utm-source]"), candidate.getUtm().getSource());
    type(By.cssSelector("input[name=candidate-utm-medium]"), candidate.getUtm().getMedium());
    type(By.cssSelector("input[name=candidate-utm-campaign]"), candidate.getUtm().getCampaing());
    type(By.cssSelector("input[name=candidate-utm-term]"), candidate.getUtm().getTerm());
    type(By.cssSelector("input[name=candidate-utm-content]"), candidate.getUtm().getContent());
  }

  private void btnAddCandidate() {
    clickWaitElementToBeClicable(5, By.xpath("//button[contains(@class,'create-candidate')]"));
  }

  public void modify(String idCandidate, CandidateData candidate) {
    selectModifyCandidate(idCandidate);
    btnPencil();
    fillModifyForm(candidate);
    btnSave();
  }

  private void fillModifyForm(CandidateData candidate) {
    type(By.xpath("//input[@name='profile-firstName']"), candidate.getFirstName());
    type(By.xpath("//input[@name='profile-lastName']"), candidate.getLastName());
    type(By.name("profile-english-firstName"), candidate.getEngFirstName());
    type(By.name("profile-english-lastName"), candidate.getEngLastName());

    clear(8, By.cssSelector("input[name=profile-birthday]"));
    click(By.name("profile-english-lastName"));//кликнуть в др место
    enterADate(By.cssSelector("input[name=profile-birthday]"),
        DateISOToUsualDataString(candidate.getBirthday()));

    dropDownList_Integer(By.cssSelector("#profile-gender"), candidate.getGender());

    click(By.xpath("//select[@id='profile-country']"));
    click(By.xpath("//option[@value='" + candidate.getCountry() + "']"));

    type(By.cssSelector("input[name=profile-city]"), candidate.getCity());

    click(By.xpath("//select[@id='profile-timezone']"));
    click(By.xpath("//option[@value='" + candidate.getTimezone() + "']"));

    click(By.xpath("//select[@id='profile-locale']"));
    click(By.xpath("//option[@value='" + candidate.getLocale() + "']"));

    clear(35, By.xpath("//textarea[@name='profile-note']"));
    wd.findElement(By.xpath("//textarea[@name='profile-note']"))
        .sendKeys(candidate.getNote());

    clear(30, By.xpath("//textarea[@name='profile-info']"));
    wd.findElement(By.xpath("//textarea[@name='profile-info']"))
        .sendKeys(candidate.getInfo());

    TrainerData trainer = trainerService.findById(candidate.getReferId());
    clear(30, By.xpath("//div[@class='input-group']//input[@name='user-name']"));
    type(By.xpath("//div[@class='input-group']//input[@name='user-name']"), trainer.getLastName());
    click(By.xpath("//a[@class='search-result']"));

    type(By.xpath("//input[@name='profile-resume-link']"), candidate.getResume());

    type(By.xpath("//input[@name='profile-test-task-link']"), candidate.getTestTask());

    type(By.cssSelector("input[name=profile-contact-phone]"),
        candidate.getContacts().get(0).getVal());
    type(By.cssSelector("input[name=profile-contact-email]"),
        candidate.getContacts().get(1).getVal());
    type(By.cssSelector("input[name=profile-contact-telegram]"),
        candidate.getContacts().get(2).getVal());
    type(By.cssSelector("input[name=profile-contact-viber]"),
        candidate.getContacts().get(3).getVal());
    type(By.cssSelector("input[name=profile-contact-c2d]"),
        candidate.getContacts().get(4).getVal());

    click(By.cssSelector("a.btn-link.btn-show"));

    type(By.cssSelector("input[name=profile-contact-skype]"),
        candidate.getContacts().get(5).getVal());
    type(By.cssSelector("input[name=profile-contact-whatsapp]"),
        candidate.getContacts().get(6).getVal());
    type(By.cssSelector("input[name=profile-contact-facebook]"),
        candidate.getContacts().get(7).getVal());
    type(By.cssSelector("input[name=profile-contact-vk]"), candidate.getContacts().get(8).getVal());
    type(By.cssSelector("input[name=profile-contact-ok]"), candidate.getContacts().get(9).getVal());
    type(By.cssSelector("input[name=profile-contact-instagram]"),
        candidate.getContacts().get(10).getVal());

    type(By.cssSelector("input[name=candidate-utm-source]"), candidate.getUtm().getSource());
    type(By.cssSelector("input[name=candidate-utm-medium]"), candidate.getUtm().getMedium());
    type(By.cssSelector("input[name=candidate-utm-campaign]"), candidate.getUtm().getCampaing());
    type(By.cssSelector("input[name=candidate-utm-term]"), candidate.getUtm().getTerm());
    type(By.cssSelector("input[name=candidate-utm-content]"), candidate.getUtm().getContent());
  }

  private void btnSave() {
    click(By.xpath("//button[contains(@class,'save')]"));
  }

  private void btnPencil() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
  }

  private void selectModifyCandidate(String idCandidate) {
    click(By.xpath("//a[@href='/candidates/" + idCandidate + "']"));
  }

  public void delete(String idCandidte) {
    selectModifyCandidate(idCandidte);
    btnPoints();
    btnDelete();
    noErrorMessage();
  }

  private void btnDelete() {
    clickWithMoveToElementAndWait(5, By.xpath("//a[contains(@class,'remove')]"));
    click(By.xpath("//button[contains(@class,'danger')]"));
  }

  private void btnPoints() {
    clickWithMoveToElementAndWait(5, By.xpath("//button[contains(@class,'dropdown')]"));
  }
}
