package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.WorkerData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.services.WorkerService;
import io.itgen.tests.TestBase;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/*В бд есть Дефолтный ребенок и Дефолтный родитель*/
public class TrainerChatSearchDialogWithNewFamilyTest extends TestBase {

  @Test
  public void testTrainerChatSearchDialogWithNewStudent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("ребенок"),equalTo(true));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithNewParent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("родитель"),equalTo(true));
    app.chat().btnCloseChat();
  }

}
