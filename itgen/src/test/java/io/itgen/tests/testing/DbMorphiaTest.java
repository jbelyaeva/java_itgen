package io.itgen.tests.testing;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.RequestData;
import org.testng.annotations.Test;

import java.util.List;


public class DbMorphiaTest {

  @Test
  public void testDbMorphia() throws Exception {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<RequestData> query = datastore.createQuery(RequestData.class);
    List<RequestData> students = query.find().toList();
    for (RequestData student : students) {
      System.out.println(student);
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