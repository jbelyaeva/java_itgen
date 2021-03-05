package data.precondition;

import data.model.materials.MaterialData;

public class Materials extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //новая ветка Скретч
  public void newBranchScratch() {
    trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
  }

  public void set1_newMaterial(String idCreator) {
    newBranchScratch();
    trMaterial()
        .newMaterial(
            "material",
            idCreator,
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность");
  }

  public void set1_1_checkingTrainerMaterial() {
    newBranchScratch();
    trMaterial()
        .checkingMaterial(
            "material",
            "14",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  public void perms(String idUser) {
    String[] perms = {"1.ru", "1.en", "1.fr", "1.he", "1.de", "1.es", "2.ru"};
    String[] reviews = {"1.ru", "1.en", "1.fr", "1.he", "1.de", "1.es", "2.ru"};
    trMaterial().addMaterialPerms(idUser, perms, reviews);
  }

  public void set2_MaterialTakeChecking(String idUser, String idCreator) {
    newBranchScratch();
    trMaterial()
        .checkingMaterial(
            "material",
            idCreator,
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);
  }

  public void set3_MaterialPublished(String idUser) {
    newBranchScratch();
    trMaterial()
        .publishedMaterial(
            "material",
            "666",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);
  }

  public void set3_1_MaterialPublishedCreatorTrainer(String idUser) {
    newBranchScratch();
    trMaterial()
        .publishedMaterial(
            "material",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);
  }

  public void set4_MaterialRevision(String idUser) {
    newBranchScratch();
    trMaterial()
        .revisionMaterial(
            "material",
            "666",
            "Жуки",
            "rework",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);
  }

  public void set5_MaterialNew(String idUser) {
    newBranchScratch();
    set3_MaterialPublished("666");
    trMaterial().materialNew(idUser);
  }

  public void set6_MaterialEngPublished(String idUser) {
    newBranchScratch();
    trMaterial()
        .publishedMaterial(
            "materialEngPublished",
            "14",
            "Beetles",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);
  }

  public void set7_MaterialPublishedLinkedWithMaterialChecking(String idUserCreator,
      String idUserReviwer) {

    newBranchScratch();

    trMaterial()
        .linkedMaterial(
            "materialEngPublished",
            "material",
            "checking",
            "666",
            "Beetles",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUserCreator);

    newBranchScratch();
    trMaterial()
        .linkedMaterial(
            "material",
            "materialEngPublished",
            "published",
            "666",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "en",
            "translation",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUserReviwer);
  }

  public void set7_1_MaterialPublishedLinked() {
    newBranchScratch();
    trMaterial()
        .linkedMaterial(
            "materialEngPublished",
            "material",
            "published",
            "14",
            "Beetles",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    newBranchScratch();
    trMaterial()
        .linkedMaterial(
            "material",
            "materialEngPublished",
            "published",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "en",
            "translation",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  //материал роверяет деф тренер. Он оставил коммент
  public void set8_MaterialWithCommentOnChecking() {
    newBranchScratch();
    trMaterial()
        .checkingMaterialWithComment(
            "material",
            "14",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "23",
            "23",
            "Комментарий");
  }

  //два материала, ожид проверки
  public void set9_newTwoMaterials() {
    newBranchScratch();
    trMaterial()
        .newMaterial(
            "material",
            "666",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность");

    trMaterial()
        .newMaterial(
            "materialSecond",
            "666",
            "Пчелы",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает реакцию");
  }

  public void set10_TwoMaterialTakeChecking(String idUser) {
    newBranchScratch();
    trMaterial()
        .checkingMaterial(
            "material",
            "666",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            idUser);

    trMaterial()
        .checkingMaterial(
            "materialSecond",
            "666",
            "Пчелы",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает реакцию",
            idUser);
  }

  public void set11_checkingMaterialFromObject(String id, MaterialData material) {
    newBranchScratch();
    trMaterial()
        .newMaterial(id,
            "666",
            material.getTitle().trim(),
            "checking",
            material.getSkill(),
            "CreateNewMaterial",
            material.getType(),
            material.getLevel(),
            material.getLang(),
            material.getOriginality(),
            material.getMaterialLink(),
            material.getProjectLink(),
            material.getSourceLink(),
            material.getDesc().trim());
  }

  public void set12_checkingMaterialDelete() {
    newBranchScratch();
    trMaterial().
        checkingMaterialDelete(
            "material",
            "14",
            "Жуки",
            "checking",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }

  public void set13_publishedMaterialWithComment(String comment) {
    newBranchScratch();
    trMaterial().
        publishedMaterialWithComment(
            "material",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666",
            comment);
  }

  //два материала, ожид проверки
  public void set14_twoMaterialsEngAndRuForLink() {
    newBranchScratch();
    trMaterial()
        .publishedMaterial(
            "materialRU",
            "14",
            "Жуки",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "ru",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");

    trMaterial()
        .publishedMaterial(
            "materialEn",
            "14",
            "Beetles",
            "published",
            "1",
            "CreateNewMaterial",
            "video",
            "easy",
            "en",
            "original",
            "https://docs.google.com",
            "https://docs.google.com",
            "https://docs.google.com",
            "Развивает внимательность",
            "666");
  }
}
