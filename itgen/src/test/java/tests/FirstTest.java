package tests;

import app.testbase.TestBase;
import org.testng.annotations.Test;

public class FirstTest extends TestBase {

  // пилотный тест, т.к. после git pull первый тест открывает кривую страницу после залогирования
  //для локального запуска
  @Test()
  public void testLeadCreation() {
    app.goTo().menuLeads();
  }
}
