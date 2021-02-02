package data.precondition;

import app.appmanager.HelperBase;

public class NewFamilyWithoutLessons extends TransactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * новая семья с учеником и одним родителе
   */
  public void set1_FamilyWithStudentAndParent() {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
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
            base.DateWithCorrectionDays(-2460),
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
            base.DateWithCorrectionDays(-2460),
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
            base.DateWithCorrectionDays(-2460),
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
}
