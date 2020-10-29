package io.itgen.model;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Utm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("candidates")
public class CandidateData {

  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("createAt")
  private Date createAt;

  @Expose
  @Property("status")
  private String status;

  @Expose
  @Property("firstName")
  private String firstname;

  @Expose
  @Property("lastName")
  private String lastname;

  @Expose
  @Property("engFirstName")
  private String engFirstName;

  @Expose
  @Property("engLastName")
  private String engLastName;

  @Expose
  @Property("gender")
  private Integer gender;

  @Expose
  @Property("vacancyId")
  private String vacancyId;

  @Expose
  @Property("locale")
  private String locale;

  @Property("birthday")
  private Date birthday;

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
  @Property("note")
  private String note;

  @Expose
  @Property("info")
  private String info;

  @Expose
  @Property("referId")
  private String referId;

  @Expose
  @Property("resume")
  private String resume;

  @Expose
  @Property("testTask")
  private String testTask;

  @Embedded private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embedded private Utm utm;

  public CandidateData() {}

  public CandidateData withId(String id) {
    this.id = id;
    return this;
  }

  public CandidateData withCreateAt(Date createAt) {
    this.createAt = createAt;
    return this;
  }

  public CandidateData withStatus(String status) {
    this.status = status;
    return this;
  }

  public CandidateData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public CandidateData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public CandidateData withEngFirstname(String engFirstName) {
    this.engFirstName = engFirstName;
    return this;
  }

  public CandidateData withEngLastname(String engLastName) {
    this.engLastName = engLastName;
    return this;
  }

  public CandidateData withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public CandidateData withVacancyId(String vacancyId) {
    this.vacancyId = vacancyId;
    return this;
  }

  public CandidateData withLocale(String locale) {
    this.locale = locale;
    return this;
  }

  public CandidateData withBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }

  public CandidateData withCountry(String country) {
    this.country = country;
    return this;
  }

  public CandidateData withCity(String city) {
    this.city = city;
    return this;
  }

  public CandidateData withTimezone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  public CandidateData withNote(String note) {
    this.note = note;
    return this;
  }

  public CandidateData withInfo(String info) {
    this.info = info;
    return this;
  }

  public CandidateData withReferId(String referId) {
    this.referId = referId;
    return this;
  }

  public CandidateData withResume(String resume) {
    this.resume = resume;
    return this;
  }

  public CandidateData withTestTask(String testTask) {
    this.testTask = testTask;
    return this;
  }

  public CandidateData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public CandidateData withUtm(Utm utm) {
    this.utm = utm;
    return this;
  }

  public String getId() {
    return id;
  }

  public Date getCreateAt() {
    return createAt;
  }

  public String getStatus() {
    return status;
  }

  public String getFirstName() {
    return firstname;
  }

  public String getLastName() {
    return lastname;
  }

  public String getEngFirstName() {
    return engFirstName;
  }

  public String getEngLastName() {
    return engLastName;
  }

  public Integer getGender() {
    return gender;
  }

  public String getVacancyId() {
    return vacancyId;
  }

  public String getLocale() {
    return locale;
  }

  public Date getBirthday() {
    return birthday;
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

  public String getNote() {
    return note;
  }

  public String getInfo() {
    return info;
  }

  public String getReferId() {
    return referId;
  }

  public String getResume() {
    return resume;
  }

  public String getTestTask() {
    return testTask;
  }

  public List<Contacts> getContacts() {
    return contacts;
  }

  public Utm getUtm() {
    return utm;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CandidateData that = (CandidateData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(status, that.status)
        && Objects.equals(firstname, that.firstname)
        && Objects.equals(lastname, that.lastname)
        && Objects.equals(gender, that.gender)
        && Objects.equals(vacancyId, that.vacancyId)
        && Objects.equals(locale, that.locale)
        && Objects.equals(country, that.country)
        && Objects.equals(city, that.city)
        && Objects.equals(timezone, that.timezone)
        && Objects.equals(note, that.note)
        && Objects.equals(info, that.info)
        && Objects.equals(referId, that.referId)
        && Objects.equals(resume, that.resume)
        && Objects.equals(testTask, that.testTask);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, status, firstname, lastname, gender, vacancyId, locale, country, city, timezone, note,
        info, referId, resume, testTask);
  }

  @Override
  public String toString() {
    return "CandidateData{"
        + "id='"
        + id
        + '\''
        + ", createAt="
        + createAt
        + ", status='"
        + status
        + '\''
        + ", firstname='"
        + firstname
        + '\''
        + ", lastname='"
        + lastname
        + '\''
        + ", gender="
        + gender
        + ", vacancyId='"
        + vacancyId
        + '\''
        + ", locale='"
        + locale
        + '\''
        + ", birthday="
        + birthday
        + ", country='"
        + country
        + '\''
        + ", city='"
        + city
        + '\''
        + ", timezone='"
        + timezone
        + '\''
        + ", note='"
        + note
        + '\''
        + ", info='"
        + info
        + '\''
        + ", referId='"
        + referId
        + '\''
        + ", resume='"
        + resume
        + '\''
        + ", testTask='"
        + testTask
        + '\''
        + ", contacts="
        + contacts
        + ", utm="
        + utm
        + '}';
  }
}
