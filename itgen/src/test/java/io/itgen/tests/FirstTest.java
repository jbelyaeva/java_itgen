package io.itgen.tests;

import org.testng.annotations.Test;

public class FirstTest extends TestBase {

//пилотный тест, т.к. после git pull первый тест открывает кривую страницу после залогирования
  @Test()
  public void testLeadCreation() {
    app.goTo().menuLeads();
   }

 }
