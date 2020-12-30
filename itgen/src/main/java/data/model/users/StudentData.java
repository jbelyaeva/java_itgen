package data.model.users;

import com.google.gson.annotations.Expose;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.model.usersGeneral.FinishedLessonsCountBySkill;
import data.model.usersGeneral.Services;
import data.model.usersGeneral.Status;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("users")
public class StudentData {

  @Id
  @Property("_id")
  private String id;

  @Property("username")
  private String username;

  @Property("skills")
  private List<String> skills = new ArrayList<>();

  @Expose
  @Property("firstName")
  private String firstname;

  @Expose
  @Property("lastName")
  private String lastname;

  @Expose
  @Property("gender")
  private Integer gender;

  @Property("birthday")
  private Date birthday;

  @Expose
  @Property("pcLevel")
  private String pclevel;

  @Expose
  @Property("country")
  private String country;

  @Expose
  @Property("city")
  private String city;

  @Expose
  @Property("tz")
  private String timezone;

  @Expose
  @Property("locale")
  private String locate;

  @Expose
  private String studyLang;

  @Property("langs")
  private List<String> langs = new ArrayList<>();

  @Expose
  @Property("duration")
  private Integer duration;

  @Embedded
  private List<Contacts> contacts = new ArrayList<>();

  @Property("familyId")
  private String familyId;

  @Expose
  @Property("note")
  private String note;

  @Property("roles")
  private List<String> roles = new ArrayList<>();

  @Embedded("status")
  private Status status;

  @Embedded("services")
  private Services services;

  @Property("lastSeen")
  private Date lastSeen;

  @Property("lessonCount")
  private int lessonCount;

  @Embedded("emails")
  private final List<Emails> emails = new ArrayList<>();

  @Property("lastSubjs")
  private List<String> lastSubjs = new ArrayList<>();

  @Property("usedSubjs")
  private List<String> usedSubjs = new ArrayList<>();

  @Property("finishedLessonsCount")
  private int finishedLessonsCount;

  @Embedded("finishedLessonsCountBySkill")
  private FinishedLessonsCountBySkill finishedLessonsCountBySkill;

  public StudentData() {
  }

  public StudentData withId(String id) {
    this.id = id;
    return this;
  }

  public StudentData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public StudentData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public StudentData withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public StudentData withBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }

  public StudentData withPclevel(String pclevel) {
    this.pclevel = pclevel;
    return this;
  }

  public StudentData withCountry(String country) {
    this.country = country;
    return this;
  }

  public StudentData withCity(String city) {
    this.city = city;
    return this;
  }

  public StudentData withTimeZone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  public StudentData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public StudentData withStudyLang(String studyLang) {
    this.studyLang = studyLang;
    return this;
  }

  public StudentData withDuration(Integer duration) {
    this.duration = duration;
    return this;
  }

  public StudentData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public StudentData withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public StudentData withNote(String note) {
    this.note = note;
    return this;
  }

  public StudentData withRoles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public StudentData withStatus(Status status) {
    this.status = status;
    return this;
  }

  public StudentData withLangs(List<String> langs) {
    this.langs = langs;
    return this;
  }

  public StudentData withSkills(List<String> skills) {
    this.skills = skills;
    return this;
  }

  public StudentData withLastSubjs(List<String> lastSubjs) {
    this.lastSubjs = lastSubjs;
    return this;
  }

  public StudentData withUsedSubjs(List<String> usedSubjs) {
    this.usedSubjs = usedSubjs;
    return this;
  }

  public StudentData withFinishedLessonsCount(int finishedLessonsCount) {
    this.finishedLessonsCount = finishedLessonsCount;
    return this;
  }

  public StudentData withFinishedLessonsCountBySkill(
      FinishedLessonsCountBySkill finishedLessonsCountBySkill) {
    this.finishedLessonsCountBySkill = finishedLessonsCountBySkill;
    return this;
  }

  public StudentData withServices(Services services) {
    this.services = services;
    return this;
  }

  public StudentData withUsername(String username) {
    this.username = username;
    return this;
  }

  public StudentData withLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public Integer getGender() {
    return gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getPclevel() {
    return pclevel;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getLocate() {
    return locate;
  }

  public String getStudyLang() {
    return studyLang;
  }

  public Integer getDuration() {
    return duration;
  }

  public List<Contacts> getContacts() {
    return contacts;
  }

  public String getFamilyId() {
    return familyId;
  }

  public String getNote() {
    return note;
  }

  public List<String> getRoles() {
    return roles;
  }

  public Status getStatus() {
    return status;
  }

  public List<String> getLangs() {
    return langs;
  }

  public List<String> getSkills() {
    return skills;
  }

  public List<String> getLastSubjs() {
    return lastSubjs;
  }

  public List<String> getUsedSubjs() {
    return usedSubjs;
  }

  public int getFinishedLessonsCount() {
    return finishedLessonsCount;
  }

  public FinishedLessonsCountBySkill getFinishedLessonsCountBySkill() {
    return finishedLessonsCountBySkill;
  }

  public String getUsername() {
    return username;
  }

  public Services getServices() {
    return services;
  }

  public Date getLastSeen() {
    return lastSeen;
  }

  public int getLessonCount() {
    return lessonCount;
  }

  public List<Emails> getEmails() {
    return emails;
  }

  @Override
  public String toString() {
    return "StudentData{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        ", skills=" + skills +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", gender=" + gender +
        ", birthday=" + birthday +
        ", pclevel='" + pclevel + '\'' +
        ", country='" + country + '\'' +
        ", city='" + city + '\'' +
        ", timezone='" + timezone + '\'' +
        ", locate='" + locate + '\'' +
        ", studyLang='" + studyLang + '\'' +
        ", langs=" + langs +
        ", duration=" + duration +
        ", contacts=" + contacts +
        ", familyId='" + familyId + '\'' +
        ", note='" + note + '\'' +
        ", roles=" + roles +
        ", status=" + status +
        ", services=" + services +
        ", lastSeen='" + lastSeen + '\'' +
        ", lessonCount=" + lessonCount +
        ", emails=" + emails +
        ", lastSubjs=" + lastSubjs +
        ", usedSubjs=" + usedSubjs +
        ", finishedLessonsCount=" + finishedLessonsCount +
        ", finishedLessonsCountBySkill=" + finishedLessonsCountBySkill +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentData that = (StudentData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(firstname, that.firstname)
        && Objects.equals(lastname, that.lastname)
        && Objects.equals(gender, that.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, gender);
  }
}
