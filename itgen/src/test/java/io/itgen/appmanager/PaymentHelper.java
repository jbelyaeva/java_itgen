package io.itgen.appmanager;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentHelper extends HelperBase {
  StudentHelper student = new StudentHelper(wd);

  public PaymentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnPay() {
    click(By.xpath("//a[contains(@href,'/pay/')]"));
  }

  public void btnPay4Lessons() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='family-product-item'][1]//a")));
    dynamicElement.click();
  }

  public void fillTestCard() {
    type(By.id("request_credit_card_number_1"), "4200");
    type(By.id("request_credit_card_number_2"), "0000");
    type(By.id("request_credit_card_number_3"), "0000");
    type(By.id("request_credit_card_number_4"), "0000");
    type(By.id("request_credit_card_exp_month"), "01");
    type(By.id("request_credit_card_exp_year"), "25");
    type(By.xpath("//input[contains(@placeholder,'IVAN')]"), "IVAN IVANOV");
    type(By.xpath("//input[contains(@placeholder,'CVC')]"), "123");
    type(By.xpath("//input[contains(@type,'email')]"), "julja@list.ru");
   }

  public void paymentAdmin(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    btnPay4Lessons();
    fillTestCard();
    btnBay();
  }

  private void btnBay() {
    click(By.xpath("//input[contains(@name,'commit')]"));
  }

  public Boolean findPictureSuccessPay() {
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    if (isElementPresent(By.xpath("//img[contains(@alt,'Success')]"))) return true;

    return false;
  }

  public void paymentAdminShop(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  }

  public void paymentAdminCard(String id) {
    student.selectStudentInListUIById(id);
    student.btnFamily();
    btnPay();
    wd.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    btnPay4Lessons();
    fillTestCard();
    }

}
