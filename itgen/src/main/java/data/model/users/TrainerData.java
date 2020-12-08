package data.model.users;

import com.google.gson.annotations.Expose;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity("users")
public class TrainerData {
  @Id
  @Property("_id")
  private String id;

  @Expose
  @Property("firstName")
  private String firstName;

  @Expose
  @Property("lastName")
  private String lastName;

  @Expose
  @Property("startWorkAt")
  private Date startWorkAt;

  @Expose
  @Property("createAt")
  private Date createdAt;

  @Expose private String startWorkUi;

  @Expose
  @Property("birthday")
  private Date birthday;

  @Expose
  private String birthdayUi;

  @Property("langs")
  private List<String> langs = new ArrayList<>();

  @Property("skills")
  private List<String> skills = new ArrayList<>();

  @Expose
  @Property("gender")
  private Integer gender;

  @Expose
  @Property("maxSlots")
  private Integer maxSlots;

  @Expose
  private String country;

  @Expose
  @Property("tz")
  private String timeZone;

  @Expose
  @Property("locale")
  private String locate;

  @Expose private String city;

  @Property("roles")
  private List<String> roles = new ArrayList<>();

  @Expose private String roleUi;

  @Expose
  @Property("payBase")
  private Double payBase;

  @Embedded private List<Contacts> contacts = new ArrayList<Contacts>();

  @Expose private String emailUI;

  @Embedded("emails")
  private List<Emails> emails = new ArrayList<Emails>();

  @Expose private String phone;

  @Expose private String skype;

  @Expose private String viber;

  @Expose private String whatsapp;

  @Expose private String telegram;

  @Expose private String fb;

  @Expose
  private String vk;

  @Expose
  private String ok;

  @Expose
  private String inst;

  @Expose
  private String note;

  @Expose
  private String info;

  @Expose
  private String slack;

  @Property("workloadLevel")
  private String workloadLevel;

  @Property("engFirstName")
  private String engFirstName;

  @Property("engLastName")
  private String engLastName;

  /* setters */

  public TrainerData withId(String id) {
    this.id = id;
    return this;
  }

  public TrainerData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public TrainerData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public TrainerData withBirthday(Date birthday) {
    this.birthday = birthday;
    return this;
  }

  public TrainerData withBirthdayUi(String birthdayUi) {
    this.birthdayUi = birthdayUi;
    return this;
  }

  public TrainerData withStartWorkAt(Date startWorkAt) {
    this.startWorkAt = startWorkAt;
    return this;
  }

  public TrainerData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public TrainerData withStartWorkUi(String startWorkUi) {
    this.startWorkUi = startWorkUi;
    return this;
  }

  public TrainerData withLangs(List<String> langs) {
    this.langs = langs;
    return this;
  }

  public TrainerData withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public TrainerData withRoles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public TrainerData withRoleUi(String roleUi) {
    this.roleUi = roleUi;
    return this;
  }

  public TrainerData withMaxSlots(Integer maxSlots) {
    this.maxSlots = maxSlots;
    return this;
  }

  public TrainerData withCountry(String country) {
    this.country = country;
    return this;
  }

  public TrainerData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public TrainerData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public TrainerData withCity(String city) {
    this.city = city;
    return this;
  }

  public TrainerData withPayBase(Double payBase) {
    this.payBase = payBase;
    return this;
  }

  public TrainerData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public TrainerData withEmailUI(String emailUI) {
    this.emailUI = emailUI;
    return this;
  }

  public TrainerData withEmails(List<Emails> emails) {
    this.emails = emails;
    return this;
  }

  public TrainerData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public TrainerData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public TrainerData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public TrainerData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public TrainerData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public TrainerData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public TrainerData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public TrainerData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public TrainerData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public TrainerData withNote(String note) {
    this.note = note;
    return this;
  }

  public TrainerData withInfo(String info) {
    this.info = info;
    return this;
  }

  public TrainerData withWorkloadLevel(String workloadLevel) {
    this.workloadLevel = workloadLevel;
    return this;
  }

  public TrainerData withEngFirstName(String engFirstName) {
    this.engFirstName = engFirstName;
    return this;
  }

  public TrainerData withEngLastName(String engLastName) {
    this.engLastName = engLastName;
    return this;
  }

  public TrainerData withSkills(List<String> skills) {
    this.skills = skills;
    return this;
  }
  public TrainerData withSlack(String slack) {
    this.slack = slack;
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

  public Date getStartWork() {
    return startWorkAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getStartWorkUi() {
    return startWorkUi;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getBirthdayUi() {
    return birthdayUi;
  }

  public List<String> getLangs() {
    return langs;
  }

  public Integer getGender() {
    return gender;
  }

  public List<String> getRoles() {
    return roles;
  }

  public String getRoleUi() {
    return roleUi;
  }

  public Integer getMaxSlots() {
    return maxSlots;
  }

  public String getCountry() {
    return country;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getLocate() {
    return locate;
  }

  public String getCity() {
    return city;
  }

  public Double getPayBase() {
    return payBase;
  }

  public List<Contacts> getContacts() {
    return contacts;
  }

  public String getEmailUI() {
    return emailUI;
  }

  public List<Emails> getEmails() {
    return emails;
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

  public String getNote() {
    return note;
  }

  public String getInfo() {
    return info;
  }

  public String getWorkloadLevel() {
    return workloadLevel;
  }

  public String getEngFirstName() {
    return engFirstName;
  }

  public String getEngLastName() {
    return engLastName;
  }

  public List<String> getSkills() {
    return skills;
  }

  public String getSlack() {
    return slack;
  }

  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "TrainerData{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", startWorkAt=" + startWorkAt +
        ", createdAt=" + createdAt +
        ", startWorkUi='" + startWorkUi + '\'' +
        ", birthday=" + birthday +
        ", birthdayUi='" + birthdayUi + '\'' +
        ", langs=" + langs +
        ", skills=" + skills +
        ", gender=" + gender +
        ", maxSlots=" + maxSlots +
        ", country='" + country + '\'' +
        ", timeZone='" + timeZone + '\'' +
        ", locate='" + locate + '\'' +
        ", city='" + city + '\'' +
        ", roles=" + roles +
        ", roleUi='" + roleUi + '\'' +
        ", payBase=" + payBase +
        ", contacts=" + contacts +
        ", emailUI='" + emailUI + '\'' +
        ", emails=" + emails +
        ", phone='" + phone + '\'' +
        ", skype='" + skype + '\'' +
        ", viber='" + viber + '\'' +
        ", whatsapp='" + whatsapp + '\'' +
        ", telegram='" + telegram + '\'' +
        ", fb='" + fb + '\'' +
        ", vk='" + vk + '\'' +
        ", ok='" + ok + '\'' +
        ", inst='" + inst + '\'' +
        ", note='" + note + '\'' +
        ", info='" + info + '\'' +
        ", workloadLevel='" + workloadLevel + '\'' +
        ", engFirstName='" + engFirstName + '\'' +
        ", engLastName='" + engLastName + '\'' +
        '}';

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TrainerData that = (TrainerData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(gender, that.gender)
        && Objects.equals(maxSlots, that.maxSlots)
        && Objects.equals(country, that.country)
        && Objects.equals(timeZone, that.timeZone)
        && Objects.equals(locate, that.locate)
        && Objects.equals(city, that.city)
        && Objects.equals(payBase, that.payBase)
        && Objects.equals(note, that.note)
        && Objects.equals(workloadLevel, that.workloadLevel)
        && Objects.equals(info, that.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, gender, maxSlots, country, timeZone, locate, city,
        payBase, note,
        workloadLevel, info);
  }
}
