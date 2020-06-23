package io.itgen.appmanager;

import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.model.StudentData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LKParentHelper extends HelperBase {

  public LKParentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnRecordOnTrail() {
    click(By.xpath("(//button[contains(@id-qa,'trial')])[2]"));
    noErrorMessage();
  }

  public void RecordOnTrail() {
    btnRecordOnTrail();
    btnSelectScratch();
    selectLesson();
    btnSignUp();
  }

  public void btnLogo() {
    click(By.xpath("//img[@alt='ITGEN.IO']"));
    noErrorMessage();
  }

  private void btnSignUp() {
    click(By.xpath("//button[contains(@id-qa,'signup')]"));
    noErrorMessage();
  }

  public void selectLesson() {
    click(By.className("list-of-times"));
    noErrorMessage();
  }

  public void btnSelectScratch() {
    click(By.xpath("//button[@id-qa='btn-1']"));
    noErrorMessage();
  }

  public void create(StudentData student) {
   btnAddNewStudent();
   fillStudentForm1(student);

   btnNextFirst();
   btnNextSecond();
  }

  private void btnNextSecond() {
    click(By.className("//button[contains(@class,'button-next')]"));
  }

  public void fillStudentForm1(StudentData studentData) {
  /*  type(By.xpath("//div[@id-qa='name']//input"), studentData.getFirstname());
    type(By.xpath("//div[@id-qa='surname']//input"), studentData.getLastname());*/
  //  click(By.xpath("//button[1]"));
  //  click(By.xpath("//div[@class='MuiPickersCalendar-week'][4]//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorInherit' and contains(text(),27)]"));
   // click(By.xpath("//div[contains(@class,'body')]"));

    click(By.xpath("//button[1]"));
    click(By.xpath("//input[@placeholder='Введите имя ученика']"));
    //div[contains(@class,'body')]
    //enterADate(By.xpath("//span[1]"), studentData.getBirthdayUi());
    /*
    dropDownList_Integer(By.xpath("//div[@id-qa='gender']//input"), studentData.getGender());
    dropDownList(By.xpath("//div[@id-qa='language']//input"), "ru");
    dropDownList(By.xpath("//div[@id-qa='pclevel']//input"), studentData.getPclevel());*/

  }

  private void btnNextFirst() {
    click(By.className("button-next"));
  }

  private void btnAddNewStudent() {
    click(By.xpath("//a[contains(@href,'addChild')]"));
    noErrorMessage();
  }
}
