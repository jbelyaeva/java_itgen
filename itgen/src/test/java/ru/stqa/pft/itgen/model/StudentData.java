package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class StudentData {
  @Expose
  @Id
  @Column(name="_id")
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
  @Transient
  @Column(name="birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;
  @Expose
  @Transient
  private String birthdayUi;
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
  @Transient
  private String locate;
  @Expose
  @Transient
  private String studyLang;
  @Expose
  @Transient
  private String duration;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column( name = "contacts" )
  private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embeddable
          public static class Contacts {
          private String type;
          private String val;

          public void setType(String type) {
            this.type = type;
          }
          public void setVal(String val) {
            this.val = val;
          }
          public String getType() {return type;}
          public String getVal() {return val;}

          @Override
                   public String toString() {
                   return "Contacts{" +
                   "type='" + type + '\'' +
                   ", val='" + val + '\'' +
                   '}';
                    }
          }

  @Expose
  @Transient
  private String phone;
  @Expose
  @Transient
  private String skype;
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
  @Transient
  @Column(name="familyId")
  private String familyId;

  @Expose
  @Transient
  private String note;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name="roles")
  private List<Roles> roles = new ArrayList<Roles>();
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
  @JoinTable (name = "users", joinColumns = @JoinColumn (name = "familyId"))
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

  public StudentData withDuration(String duration) {
    this.duration = duration;
    return this;
  }

  public StudentData withContacts(List<Contacts> contacts) {
    this.contacts=contacts;
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

  public StudentData withRoles (List<Roles> roles) {
    this.roles = roles;
    return this;
  }


  /** геттеры */

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

  public String getDuration() {
    return duration;
  }

  public  List<Contacts> getContacts() {return contacts;}

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

  public List<Roles> getRoles() {
    return roles;
  }


  /**
   * хэш код и иквелс
   */
  @Override
  public String toString() {
    return "StudentData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", gender=" + gender +
            ", birthdayUi='" + birthdayUi + '\'' +
            ", pclevel='" + pclevel + '\'' +
            ", country='" + country + '\'' +
            ", city='" + city + '\'' +
            ", timezone='" + timezone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentData that = (StudentData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(pclevel, that.pclevel) &&
            Objects.equals(country, that.country) &&
            Objects.equals(city, that.city) &&
            Objects.equals(timezone, that.timezone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, gender, pclevel, country, city, timezone);
  }
}
