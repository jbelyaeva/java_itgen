package io.itgen.model;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Transient;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Services;
import io.itgen.model.users.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("users")
public class StudentData {
  @Id
  @Property("_id")
  private String id;

  @Transient
  @Property("username")
  private String username;

  @Property("skills")
  private final List<String> skills = new ArrayList<String>();

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

  @Expose @Transient private String birthdayUi;

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

  @Expose @Transient private String studyLang;

  @Property("langs")
  private List<String> langs = new ArrayList<>();

  @Expose
  @Property("duration")
  private Integer duration;

  @Embedded private List<Contacts> contacts = new ArrayList<Contacts>();

  @Expose @Transient private String phone;

  @Expose @Transient private String skype;

  @Expose @Transient private String c2d;

  @Expose @Transient private String viber;

  @Expose @Transient private String whatsapp;

  @Expose @Transient private String telegram;

  @Expose @Transient private String fb;

  @Expose @Transient private String vk;

  @Expose @Transient private String ok;

  @Expose @Transient private String inst;

  @Property("familyId")
  private String familyId;

  @Expose
  @Property("note")
  private String note;

  @Property("roles")
  private List<String> roles = new ArrayList<>();

  @Embedded private Status status;

  @Transient
  @Property("startWorkAt")
  private Date startWorkAt;

  @Transient
  @Property("createAt")
  private Date createAt;

  @Transient
  @Property("maxSlots")
  private int maxSlots;

  @Transient
  @Property("payBase")
  private int payBase;

  @Transient
  @Embedded("services")
  private Services services;

  @Transient
  @Property("lastSeen")
  private String lastSeen;

  @Transient
  @Property("lessonCount")
  private int lessonCount;

  @Transient
  @Embedded("emails")
  private final List<Emails> emails = new ArrayList<Emails>();

  @Property("lastSubjs")
  private List<String> lastSubjs = new ArrayList<>();

  @Property("usedSubjs")
  private List<String> usedSubjs = new ArrayList<>();

  @Property("finishedLessonsCount")
  private int finishedLessonsCount;

  @Embedded private FinishedLessonsCountBySkill finishedLessonsCountBySkill;

  // getters and setters

  public Status getStatus() {
    return status;
  }

  public StudentData withStatus(Status status) {
    this.status = status;
    return this;
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

  public StudentData withBirthdayUi(String birthdayUi) {
    this.birthdayUi = birthdayUi;
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

  public StudentData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public StudentData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public StudentData withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public StudentData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public StudentData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public StudentData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public StudentData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public StudentData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public StudentData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public StudentData withInst(String inst) {
    this.inst = inst;
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

  public StudentData withLangs(List<String> langs) {
    this.langs = langs;
    return this;
  }

  public StudentData withSkills(List<String> skills) {
    this.langs = langs;
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
      FinishedLessonsCountBySkill finishedLessonsCpontBySkill) {
    this.finishedLessonsCountBySkill = finishedLessonsCountBySkill;
    return this;
  }

  /* getters */

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

  public String getBirthdayUi() {
    return birthdayUi;
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

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getC2d() {
    return c2d;
  }

  public String getViber() {
    return viber;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getTelegram() {
    return telegram;
  }

  public String getFb() {
    return fb;
  }

  public String getVk() {
    return vk;
  }

  public String getOk() {
    return ok;
  }

  public String getInst() {
    return inst;
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

  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "StudentData{"
        + "id='"
        + id
        + '\''
        + ", firstname='"
        + firstname
        + '\''
        + ", lastname='"
        + lastname
        + '\''
        + ", gender="
        + gender
        + ", birthdayUi='"
        + birthdayUi
        + '\''
        + ", pclevel='"
        + pclevel
        + '\''
        + ", country='"
        + country
        + '\''
        + ", city='"
        + city
        + '\''
        + ", timezone='"
        + timezone
        + '\''
        + ", locate='"
        + locate
        + '\''
        + ", note='"
        + note
        + '\''
        + ", duration='"
        + duration
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
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
