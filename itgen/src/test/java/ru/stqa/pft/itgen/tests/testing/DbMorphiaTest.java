package ru.stqa.pft.itgen.tests.testing;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.users.Contacts;
import ru.stqa.pft.itgen.model.users.Status;
import ru.stqa.pft.itgen.services.StudentService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;


public class DbMorphiaTest {

  @Test
  public void testDbMorphia() throws Exception {


    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> query = datastore.createQuery(StudentData.class);
    List<StudentData> students = query.find().toList();
    for (StudentData student : students) {
      System.out.println(student);
    }

    StudentService studentService = new StudentService();
    StudentData student = new StudentData().withId("studentDelete").withFirstName("Маша").withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
            .withFamilyId("studentDelete").withStudyLang("ru").withLocate("ru")
            .withBirthday(new Date(1556726891000L))
            .withLangs(Arrays.asList("ru"))
            .withContacts(Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2).withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }
}