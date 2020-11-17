package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import org.testng.annotations.Test;

/*В бд есть Дефолтный ребенок и Дефолтный родитель*/
public class TrainerChatSearchDialogWithNewFamilyTest extends TestBase {

  @Test
  public void testTrainerChatSearchDialogWithNewStudent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("ребенок"), equalTo(true));
    app.chat().btnCloseChat();
  }

  @Test
  public void testTrainerChatSearchDialogWithNewParent() {
    app.chat().btnOpenChat();
    assertThat(app.chat().searchPerson("родитель"), equalTo(true));
    app.chat().btnCloseChat();
  }
}
