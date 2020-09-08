package io.itgen.tests.testing;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.TrainerData;
import io.itgen.tests.TestBase;
import org.testng.annotations.Test;

import java.util.List;


public class DbMorphiaTest extends TestBase {

  @Test
  public void testDbMorphia()   {

    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<TrainerData> q = datastore.createQuery(TrainerData.class).filter("roles", "trainer");
    List<TrainerData> trainers = q.find().toList();
    for (TrainerData trainer : trainers) {
      System.out.println(trainer);
    }
/*
    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("studentDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentDelete").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);*/
  }
}