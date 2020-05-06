package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class TrainerData {
  @Id
  @Column(name = "_id")
  private String id;
  @Expose
  @Column(name = "firstName")
  private String firstName;
  @Expose
  @Column(name = "lastName")
  private String lastName;
  @Expose
  @Transient
  @Column(name = "startWork")
  @Temporal(TemporalType.DATE)
  private Date startWork;
  @Expose
  private String startWorkUi;
  @Expose
  @Transient
  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;
  @Expose
  private String birthdayUi;
  @Expose
  @Transient
  private String gender;
  @Expose
  private String maxSlots;
  @Expose
  private String country;
  @Expose
  private String timeZone;
  @Expose
  private String locate;
  @Expose
  private String city;
  @Expose
  private String role;
  @Expose
  private String payBase;
  @Expose
  private String phone;
  @Expose
  private String skype;
  @Expose
  private String viber;
  @Expose
  private String whatsapp;
  @Expose
  private String tg;
  @Expose
  private String fb;
  @Expose
  private String vk;
  @Expose
  private String ok;
  @Expose
  private String inst;
  @Expose
  private String note;

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

  public TrainerData withStartWork(Date startWork) {
    this.startWork = startWork;
    return this;
  }

  public TrainerData withStartWorkUi(String startWorkUi) {
    this.startWorkUi = startWorkUi;
    return this;
  }

  public TrainerData withGender(String gender) {
    this.gender = gender;
    return this;
  }
  public TrainerData withRole(String role) {
    this.role = role;
    return this;
  }

  public TrainerData withMaxSlots(String maxSlots) {
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

  public TrainerData withPayBase(String payBase) {
    this.payBase = payBase;
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

  public TrainerData withTg(String tg) {
    this.tg = tg;
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
    return startWork;
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

  public String getGender() {
    return gender;
  }

  public String getRole() {
    return role;
  }

  public String getMaxSlots() {
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

  public String getPayBase() {
    return payBase;
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

  public String getTg() {
    return tg;
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

  /* toString(), hashCode() & equals() */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TrainerData that = (TrainerData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "TrainerData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

}
