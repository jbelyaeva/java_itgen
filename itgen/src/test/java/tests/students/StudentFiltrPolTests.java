package tests.students;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.users.StudentData;
import data.model.users.Students;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentFiltrPolTests extends TestBase {
  int pol = 1;

  @BeforeMethod
  public void ensurePreconditions() {
    data.newFamily().set7_FamilyWithStudent();
    data.newFamily().set8_NewStudent();
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testStudentFilterPol() {
    app.goTo().menuStudents();
    app.student().openFiltr();
    app.student().changePol(pol);
    app.student().btnApply();
    Students list = app.dbstudents().studentFiltrPol(pol);
    List<StudentData> uiStudents = app.student().list();
    assertThat(
        new HashSet<Object>(uiStudents),
        equalTo(
            list.stream()
                .map(
                    (s) ->
                        new StudentData()
                            .withId(s.getId())
                            .withFirstName(s.getFirstname())
                            .withLastName(s.getLastname()))
                .collect(Collectors.toSet())));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().family().student().parent();
  }
}
