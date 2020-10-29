package io.itgen.tests.testing;

import io.itgen.model.skills.Beginner;
import io.itgen.model.skills.Requirements;
import io.itgen.model.skills.SkillsData;
import io.itgen.services.SkillsService;
import io.itgen.tests.TestBase;
import java.util.Arrays;
import org.testng.annotations.Test;

public class DbMorphiaTest extends TestBase {

  @Test
  public void testDbMorphia() {

    /*  Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<TrainerData> q = datastore.createQuery(TrainerData.class).filter("roles", "trainer");
    List<TrainerData> trainers = q.find().toList();
    for (TrainerData trainer : trainers) {
      System.out.println(trainer);
    } */

    SkillsService skillsService = new SkillsService();
    SkillsData skills =
        new SkillsData()
            .withId("123")
            .withLang("ru")
            .withTitle("Маша")
            .withVisibility("visi")
            .withDesc("ttt")
            .withMinAge(5)
            .withSkillId("25")
            .withRequirements(
                new Requirements()
                    .withtBeginner(Arrays.asList(new Beginner().withAge(8).withState("vis"))));

    skillsService.save(skills);
  }
}
