package data.precondition;

public class Skills extends TransactionManager {

  protected static final DataManager data = new DataManager();

  //В админке направлений у Minecraft стоит галочка АЗ.
  public void set1_MinecraftWithAt(String id) {
    String descrition = "Ученик будет конструировать 3D миры, "
            + "строить логические цепочки, получит базовые знание по решению задач на программирование "
            + "и разработке проектов";
    int[] age = {5, 7};
    String[] state = {"hidden", "visible"};
    trSkill()
        .updateSkill(
            id,
            "ru", "Minecraft",
            "visible",
            descrition,
            7,
            age,
            state,
            "21",
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy",
            true,
            1,
            2);
  }

  //В админке направлений у Minecraft стоит галочка АЗ.
  public void set2_MinecraftWithTest(String id, String testId) {
    String descrition = "Ученик будет конструировать 3D миры, "
        + "строить логические цепочки, получит базовые знание по решению задач на программирование "
        + "и разработке проектов";
    int[] age = {5, 7, 8};
    String[] state = {"hidden", "hiddenByTest", "hiddenByTest"};
    trSkill()
        .updateSkillWithTest(
            id,
            "ru", "Minecraft",
            "visible",
            descrition,
            7,
            age,
            state,
            "21",
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy",
            true,
            1,
            2,
            testId);
  }

  //В админке направлений у Minecraft стоит галочка АЗ, направление с 9 лет.
  public void set3_MinecraftWithTestAfter9Years(String id, String testId) {
    String descrition = "Ученик будет конструировать 3D миры, "
        + "строить логические цепочки, получит базовые знание по решению задач на программирование "
        + "и разработке проектов";
    int[] age = {7, 9, 10};
    String[] state = {"hidden", "hiddenByTest", "hiddenByTest"};
    trSkill()
        .updateSkillWithTest(
            id,
            "ru", "Minecraft",
            "visible",
            descrition,
            9,
            age,
            state,
            "21",
            "https://drive.google.com/drive/folders/1FntRoW1PkC_Qay4pGj_NNZ55KmDv00zy",
            true,
            1,
            2,
            testId);
  }
}
