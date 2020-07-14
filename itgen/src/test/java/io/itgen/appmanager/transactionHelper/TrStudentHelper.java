package io.itgen.appmanager.transactionHelper;

import io.itgen.model.StudentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrStudentHelper {

  private final StudentService studentService = new StudentService();

  // добавленный в дефолтную семью студент (русский, в скиллах Scratch, телефон), после прохождения
  // пробного и записанный на др занятие
  public void StudentAddDefaultFamily_FinishedTrailLesson_RecordSingle(
      String idStudent,
      String pclevel,
      String country,
      String tz,
      int gender,
      String studyLang,
      String locate) {
    StudentData student =
        new StudentData()
            .withId(idStudent)
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel(pclevel)
            .withCountry(country)
            .withTimeZone(tz)
            .withGender(gender)
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("learning"))
            .withLastSubjs(Arrays.asList("1"))
            .withUsedSubjs(Arrays.asList("1"))
            .withFinishedLessonsCount(1)
            .withFinishedLessonsCountBySkill(new FinishedLessonsCountBySkill().withOne(1));
    studentService.save(student);
  }

  // добавленный в дефолтную семью студент(русский, в скиллах Scratch, телефон), после прохождения
  // пробного
  public void StudentAddDefaultFamily_AfterTrial(
      String idStudent,
      String pclevel,
      String country,
      String tz,
      int gender,
      String studyLang,
      String locate) {

    StudentData student =
        new StudentData()
            .withId(idStudent)
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel(pclevel)
            .withCountry(country)
            .withTimeZone(tz)
            .withGender(gender)
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("trialFinished"))
            .withLastSubjs(Arrays.asList("1"))
            .withUsedSubjs(Arrays.asList("1"))
            .withFinishedLessonsCount(1)
            .withFinishedLessonsCountBySkill(new FinishedLessonsCountBySkill().withOne(1));
    studentService.save(student);
  }

  // добавленный в дефолтную семью студент(русский, в скиллах Scratch, телефон)
  public void StudentAddDefaultFamily(
      String idStudent,
      String pclevel,
      String country,
      String tz,
      int gender,
      String studyLang,
      String locate) {

    StudentData student =
        new StudentData()
            .withId(idStudent)
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel(pclevel)
            .withCountry(country)
            .withTimeZone(tz)
            .withGender(gender)
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }

  public void newStudent(
      String idStudent,
      String name,
      String surname,
      String pclevel,
      String country,
      String tz,
      int gender,
      String studyLang,
      String locate,
      String idFamily) {

    StudentData student =
        new StudentData()
            .withId(idStudent)
            .withFirstName(name)
            .withLastName(surname)
            .withRoles(Arrays.asList("child"))
            .withPclevel(pclevel)
            .withCountry(country)
            .withTimeZone(tz)
            .withGender(gender)
            .withFamilyId(idFamily)
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(new Date(1263502800L))
            .withLangs(Arrays.asList("ru"))
            .withSkills(Arrays.asList("1"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
  }
}
