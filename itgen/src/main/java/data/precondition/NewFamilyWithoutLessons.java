package data.precondition;

import static app.appmanager.HelperBase.DateWithCorrectionDays;

import data.model.users.ParentData;
import data.model.users.StudentData;
import java.util.Date;

public class NewFamilyWithoutLessons extends TransactionManager {

  protected static final DataManager data = new DataManager();

  /**
   * новая семья с учеником и одним родителе
   */
  public void set1_FamilyWithStudentAndParent() {
    trFamily().newFamily("newFamily", false, "txb");
    trStudent()
        .newStudent(
            "student",
            "Олег",
            "Олегов",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent(
        "newParent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "newFamily",
        "+97895554433",
        "julja@tt.ru",
        true
    );
  }

  /**
   * английская семья с учеником и одним родителе
   */
  public void set2_EngFamilyWithStudentAndParent() {
    trFamily().newFamily("family", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-2460),
            "en",
            "ru",
            "78787878i",
            "en",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent(
        "parent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "family",
        "+97895554433",
        "julja22@tt.ru",
        true
    );
  }

  /**
   * французская семья с учеником и одним родителе
   */
  public void set3_FrFamilyWithStudentAndParent() {
    trFamily().newFamily("family", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-2460),
            "fr",
            "ru",
            "78787878i",
            "fr",
            new String[]{"1"},
            2,
            "noTrial"
        );
    trParent().newParent(
        "parent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "family",
        "+97895554433",
        "julja22@tt.ru",
        true
    );
  }

  /**
   * семья с бесплатым учеником и одним родителе
   */
  public void set4_FamilyWithFreeStudentAndParent() {
    trFamily().newFamily("family", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudentFree(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "family"
        );
    trParent().newParent(
        "parent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "family",
        "+97895554433",
        "julja22@tt.ru",
        true
    );
  }

  //ученик
  public void set5_NewStudentFromObject(String idStudent, String idFamily, StudentData student) {
    trStudent()
        .newStudent(
            idStudent,
            student.getFirstname(),
            student.getLastname(),
            "expert",
            "BL",
            idFamily,
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }

  //родитель
  public void set6_NewParent(String idParent, String idFamily) {
    trParent()
        .newParent(
            idParent,
            "Лид",
            "Лидов",
            "BY",
            "Europe/Minsk",
            "ru",
            idFamily,
            "123456789",
            "mail@list.ru",
            true);
  }

  //родитель
  public void set6_1_NewParentFromObject(String idParent, String idFamily, ParentData parent) {
    trParent()
        .newParent(
            idParent,
            parent.getFirstName(),
            parent.getLastName(),
            parent.getCountry(),
            parent.getTimeZone(),
            parent.getLocate(),
            idFamily,
            "123456789",
            "mail@list.ru",
            true);
  }

  /**
   * семья с учеником
   */
  public void set7_FamilyWithStudent() {
    trFamily().newFamily("family", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "family",
            "Europe/Minsk",
            2,
            DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "123456789",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }

  //ученик
  public void set8_NewStudent() {
    trStudent()
        .newStudent(
            "newStudent",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "newFamily",
            "Europe/Minsk",
            1,
            new Date(1556726891000L),
            "newFamily",
            "ru",
            "123456789",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }
}
