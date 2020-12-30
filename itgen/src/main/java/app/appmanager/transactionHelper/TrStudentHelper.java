package app.appmanager.transactionHelper;

import data.model.users.StudentData;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.FinishedLessonsCountBySkill;
import data.model.usersGeneral.One;
import data.model.usersGeneral.Password;
import data.model.usersGeneral.Services;
import data.model.usersGeneral.Status;
import data.model.usersGeneral.TwentyOne;
import data.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrStudentHelper {

  private final StudentService studentService = new StudentService();

  public void studentAddDefaultFamilyAfterLesson(
      String idStudent,
      String name,
      String surname,
      String pclevel,
      String country,
      String tz,
      int gender,
      Date birthday,
      String studyLang,
      String locate,
      String phone,
      String lang,
      String[] skill,
      int duration,
      int finishLessens,
      String status,
      String[] lastSubj,
      String[] usedSubj,
      int[] countSkill) {
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
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(birthday)
            .withLangs(Arrays.asList(lang))
            .withSkills(Arrays.asList(skill))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withDuration(duration)
            .withStatus(new Status().withState(status))
            .withLastSubjs(Arrays.asList(lastSubj))
            .withUsedSubjs(Arrays.asList(usedSubj))
            .withFinishedLessonsCount(finishLessens)
            .withFinishedLessonsCountBySkill(
                new FinishedLessonsCountBySkill().withCount(countSkill[0])
                    .withMinutes(countSkill[1]));
    studentService.save(student);
  }

  public void studentAddDefaultFamilyAfterScratchMinecraft(
      String idStudent,
      String name,
      String surname,
      String pclevel,
      String country,
      String tz,
      int gender,
      Date birthday,
      String studyLang,
      String locate,
      String phone,
      String lang,
      String[] skill,
      int duration,
      int finishLessens,
      String status,
      String[] lastSubj,
      String[] usedSubj) {
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
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(birthday)
            .withLangs(Arrays.asList(lang))
            .withSkills(Arrays.asList(skill))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withDuration(duration)
            .withStatus(new Status().withState(status))
            .withLastSubjs(Arrays.asList(lastSubj))
            .withUsedSubjs(Arrays.asList(usedSubj))
            .withFinishedLessonsCount(finishLessens)
            /*.withFinishedLessonsCountBySkill(
                new FinishedLessonsCountBySkill().withCount(1)
                    .withMinutes(120)*/
            .withFinishedLessonsCountBySkill(new FinishedLessonsCountBySkill()
                .withOnes(new One().withCount(1).withMinutes(120))
                .withTwentyOnes(new TwentyOne().withCount(1).withMinutes(120)));
    studentService.save(student);

  }

  public void newStudent(
      String idStudent,
      String name,
      String surname,
      String pclevel,
      String country,
      String idFamily,
      String tz,
      int gender,
      Date birthday,
      String studyLang,
      String locate,
      String phone,
      String lang,
      String[] skill,
      int duration,
      String status) {
    StudentData student =
        new StudentData()
            .withId(idStudent)
            .withUsername("newUser")
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
            .withBirthday(birthday)
            .withLangs(Arrays.asList(lang))
            .withSkills(Arrays.asList(skill))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withServices(new Services()
                .withPassword(new Password().withBcrypt(
                    "$2b$10$tA7gJVhEt/NPcfldqC1AD.JQMPvXFt.zaK7y82y2uIUoB4PJWaon6")))
            .withDuration(duration)
            .withStatus(new Status().withState(status));
    studentService.save(student);
  }

  public void newStudentFree(
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
            .withRoles(Arrays.asList("child", "donator"))
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

  public void changeDefaultStudent(String idStudent, String name, String surname, String[] roles,
      String pclevel, String country, String tz, int gender, String studyLang, String locate,
      String langs, String skills, String phone, int duration,
      String status, int finishLessens, String lastSubj, String usedSubj, int countSkill) {

    StudentData oldStudent = studentService.findById("21");
    StudentData newStudent =
        new StudentData()
            .withId("21")
            .withFirstName(name)
            .withLastName(surname)
            .withRoles(Arrays.asList(roles))
            .withPclevel(pclevel)
            .withCountry(country)
            .withTimeZone(tz)
            .withGender(gender)
            .withFamilyId("111")
            .withStudyLang(studyLang)
            .withLocate(locate)
            .withBirthday(oldStudent.getBirthday())
            .withLangs(Arrays.asList(langs))
            .withStudyLang("ru")
            .withSkills(Arrays.asList(skills))
            .withServices(oldStudent.getServices())
            .withUsername(oldStudent.getUsername())
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal(phone)))
            .withDuration(duration)
            .withStatus(new Status().withState(status))
            .withServices(new Services().withPassword(
                new Password().withBcrypt(
                    "$2b$10$tA7gJVhEt/NPcfldqC1AD.JQMPvXFt.zaK7y82y2uIUoB4PJWaon6")))
            .withLastSubjs(Arrays.asList(lastSubj))
            .withUsedSubjs(Arrays.asList(usedSubj))
            .withFinishedLessonsCountBySkill(
                new FinishedLessonsCountBySkill().withOne(countSkill))
            .withFinishedLessonsCount(finishLessens)
            .withLastSeen(new Date());
    studentService.save(newStudent);
  }
}
