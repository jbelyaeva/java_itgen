package tests;

import app.testbase.TestBase;
import org.testng.annotations.Test;

public class AFirstTest extends TestBase {

  // пилотный тест, т.к. после git pull первый тест открывает кривую страницу после залогирования
  @Test()
  public void testAFirst() {

    app.goTo().menuLeads();
  }
}
