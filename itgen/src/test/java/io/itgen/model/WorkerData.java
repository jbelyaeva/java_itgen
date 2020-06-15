package io.itgen.model;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.*;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("users")
public class WorkerData {
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("firstName")
  private String firstName;

  @Expose
  @Property("lastName")
  private String lastName;

  @Property("langs")
  private List<String> langs = new ArrayList<>();

  @Expose
  private String emailUI;

  @Embedded("emails")
  private List<Emails> emails = new ArrayList<Emails>();

  @Property("roles")
  private List<String> roles = new ArrayList<>();

  @Expose
  private String roleUi;

  @Expose
  @Property("startWorkAt")
  private Date startDay;

  @Expose
  private String startDayUi;

  @Expose
  @Transient
  @Property("birthday")
  private Date birthDay;

  @Expose
  private String birthDayUi;

  @Expose
  private Integer gender;

  @Expose
  private String country;

  @Expose
  private String city;

  @Expose
  @Property("tz")
  private String timeZone;

  @Expose
  @Property("locale")
  private String locate;

  @Embedded
  private List<Contacts> contacts = new ArrayList<Contacts>();

  @Expose
  private String phone;

  @Expose
  private String skype;

  @Expose
  private String viber;

  @Expose
  private String whatsapp;

  @Expose
  private String telegram;

  @Expose
  private String fb;

  @Expose
  private String vk;

  @Expose
  private String ok;

  @Expose
  private String inst;

  /* setters */

  public WorkerData withId(String id) {
    this.id = id;
    return this;
  }

  public WorkerData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public WorkerData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public WorkerData withLangs(List<String> langs) {
    this.langs = langs;
    return this;
  }

  public WorkerData withEmailUI(String emailUI) {
    this.emailUI = emailUI;
    return this;
  }

  public WorkerData withEmails(List<Emails> emails) {
    this.emails = emails;
    return this;
  }

  public WorkerData withRoles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public WorkerData withRoleUi(String roleUi) {
    this.roleUi = roleUi;
    return this;
  }

  public WorkerData withStartWork(Date startDay) {
    this.startDay = startDay;
    return this;
  }

  public WorkerData withStartWorkUi(String startDayUi) {
    this.startDayUi = startDayUi;
    return this;
  }

  public WorkerData withBirthday(Date birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public WorkerData withBirthdayUi(String birthDayUi) {
    this.birthDayUi = birthDayUi;
    return this;
  }

  public WorkerData withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public WorkerData withCountry(String country) {
    this.country = country;
    return this;
  }

  public WorkerData withCity(String city) {
    this.city = city;
    return this;
  }

  public WorkerData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public WorkerData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public WorkerData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public WorkerData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public WorkerData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public WorkerData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public WorkerData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public WorkerData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public WorkerData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public WorkerData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public WorkerData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public WorkerData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  /* getters */

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public List<String> getLangs() {
    return langs;
  }

  public String getEmailUI() {
    return emailUI;
  }

  public List<Emails> getEmails() {
    return emails;
  }

  public List<String> getRoles() {
    return roles;
  }

  public String getRoleUi() {
    return roleUi;
  }

  public Date getStartDay() {
    return startDay;
  }

  public String getStartDayUi() {
    return startDayUi;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public String getBirthDayUi() {
    return birthDayUi;
  }

  public Integer getGender() {
    return gender;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getLocate() {
    return locate;
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

  public String getViber() {
    return viber;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getTelegramg() {
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

  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "WorkerData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", startDayUi='" + startDayUi + '\'' +
            ", birthDayUi='" + birthDayUi + '\'' +
            ", gender=" + gender +
            ", country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", timeZone='" + timeZone + '\'' +
            ", locate='" + locate + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkerData that = (WorkerData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(country, that.country) &&
            Objects.equals(city, that.city) &&
            Objects.equals(timeZone, that.timeZone) &&
            Objects.equals(locate, that.locate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, gender, country, city, timeZone, locate);
  }
}
