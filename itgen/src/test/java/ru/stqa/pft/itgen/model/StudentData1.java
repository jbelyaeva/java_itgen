package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class StudentData1 {

  @Expose
  @Id
  private String id;
  @Expose
  @Column(name="firstName")
  private String firstname;
  @Expose
  @Column(name="lastName")
  private String lastname;
  @Expose
  @Column(name="gender")
  private Integer gender;
  @Expose
  @Column(name="birthday")
  @Temporal(TemporalType.DATE)
  private java.util.Date birthday;
  @Expose
  @Column(name="pcLevel")
  private String pclevel;
  @Expose
  @Column(name="country")
  private String country;
  @Expose
  @Column(name="city")
  private String city;
  @Expose
  @Column(name="tz")
  private String timezone;
  @Expose
  @Column(name="locale")
  private String locate;
 /* @Expose
  @Column(name="langs")
  private String[] studyLang;/*
  @Expose
  private String duration;
  @Expose
  private String phone;
  @Expose
  private String skype;
  @Expose
  private String c2d;
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
  @Expose
  private String familyId;
  @Expose
  private String note;

*/
  public StudentData1 withId(String id) {
    this.id = id;
    return this;
  }
  public StudentData1 withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public StudentData1 withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public StudentData1 withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public StudentData1 withBirthday(java.util.Date birthday) {
    this.birthday = birthday;
    return this;
  }

  public StudentData1 withPclevel(String pclevel) {
    this.pclevel = pclevel;
    return this;
  }

  public StudentData1 withCountry(String country) {
    this.country = country;
    return this;
  }

  public StudentData1 withCity(String city) {
    this.city = city;
    return this;
  }

  public StudentData1 withTimeZone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  public StudentData1 withLocate(String locate) {
    this.locate = locate;
    return this;
  }

 /* public StudentData1 withStudyLang(String[] studyLang) {
    this.studyLang = studyLang;
    return this;
  }
/*
  public StudentData1 withDuration(String duration) {
    this.duration = duration;
    return this;
  }

  public StudentData1 withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public StudentData1 withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public StudentData1 withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public StudentData1 withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public StudentData1 withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public StudentData1 withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public StudentData1 withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public StudentData1 withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public StudentData1 withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public StudentData1 withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public StudentData1 withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public StudentData1 withNote(String note) {
    this.note = note;
    return this;
  }
*/
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

  /*public String[] getStudyLang() {
    return studyLang;
  }*/


  @Override
  public String toString() {
    return "StudentData1{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", gender='" + gender + '\'' +
            ", birthday='" + birthday + '\'' +
            ", pclevel='" + pclevel + '\'' +
            ", country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", timezone='" + timezone + '\'' +
            ", locate='" + locate + '\'' +
          //  ", studyLang='" + studyLang + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentData1 that = (StudentData1) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
