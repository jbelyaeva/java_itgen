package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class ParentData {
  @Expose
  @Id
  @Column(name="_id")
  private String id;
  @Expose
  @Column(name="firstName")
  private String firstName;
  @Expose
  @Column(name="lastName")
  private String lastName;
  @Expose
  @Column(name="country")
  private String country;
  @Column(name="city")
  @Expose
  private String city;
  @Column(name="tz")
  @Expose
  private String timeZone;
  @Expose
  @Transient
  private String locate;
  @Expose
  @Transient
  private String phone;
  @Expose
  @Transient
  private String skype;
  @Expose
  @Transient
  private String email;
  @Expose
  @Transient
  private String c2d;
  @Expose
  @Transient
  private String viber;
  @Expose
  @Transient
  private String whatsapp;
  @Expose
  @Transient
  private String telegram;
  @Expose
  @Transient
  private String fb;
  @Expose
  @Transient
  private String vk;
  @Expose
  @Transient
  private String ok;
  @Expose
  @Transient
  private String inst;
  @Expose
  @Transient
  private String familyId;
  @Expose
  @Transient
  private String note;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name="roles")
  private List<ParentData.Roles> roles = new ArrayList<ParentData.Roles>();
  @Embeddable
  static class Roles {
    private String roles;
    public void setRoles(String type) {
      this.roles = type;
    }
    public String getRoles() {return roles;}

    @Override
    public String toString() {
      return "" +
              roles  ;
    }
  }
  /*
  @ManyToOne
  private FamilyData family;

  public FamilyData getFamily() {
    return family;
  }
  */

  @OneToOne
  @JoinColumn(name = "familyId")
  private FamilyData family;

  public FamilyData getFamily() {
    return family;
  }

  /** сеттеры */

  public ParentData withId(String id) {
    this.id = id;
    return this;
  }

  public ParentData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ParentData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ParentData withCountry(String country) {
    this.country = country;
    return this;
  }

  public ParentData withCity(String city) {
    this.city = city;
    return this;
  }

  public ParentData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public ParentData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public ParentData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ParentData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public ParentData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ParentData withC2d(String c2d) {
    this.c2d = c2d;
    return this;
  }

  public ParentData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public ParentData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public ParentData withTelegram(String telegram) {
    this.telegram = telegram;
    return this;
  }

  public ParentData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public ParentData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public ParentData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public ParentData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public ParentData withFamilyId(String familyId) {
    this.familyId = familyId;
    return this;
  }

  public ParentData withNote(String note) {
    this.note = note;
    return this;
  }

  public ParentData withRoles (List<ParentData.Roles> roles) {
    this.roles = roles;
    return this;
  }

  /** геттеры */

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
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

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getEmail() {
    return email;
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

  public List<ParentData.Roles> getRoles() {
    return roles;
  }


  /**
   * ту стринг хэшкод и иквелс
   */
  @Override
  public String toString() {
    return "ParentData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", timeZone='" + timeZone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParentData that = (ParentData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(country, that.country) &&
            Objects.equals(city, that.city) &&
            Objects.equals(timeZone, that.timeZone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, country, city, timeZone);
  }
}
