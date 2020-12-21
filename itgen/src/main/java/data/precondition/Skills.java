package data.precondition;

public class Skills extends TranzactionManager {

  protected static final DataManager data = new DataManager();

  //В админке направлений у Minecraft стоит галочка АЗ.
  public void set1_MinecraftWithAt() {
    String descrition = "Ученик будет конструировать 3D миры, "
        + "строить логические цепочки, получит базовые знание по решению задач на программирование "
        + "и разработке проектов";
    int[] age = {5, 7};
    String[] state = {"hidden", "visible"};
    trSkill()
        .updateSkill(
            "SHnv3uDTCS9orGGkM",
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
}
