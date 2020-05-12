package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
  @Column(name = "startWorkAt")
  @Temporal(TemporalType.DATE)
  private Date startWork;

  @Expose
  @Column(name = "createdAt")
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @Expose
  private String startWorkUi;

  @Expose
  @Column(name = "birthday")
  @Temporal(TemporalType.DATE)
  private Date birthday;

  @Expose
  private String birthdayUi;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "langs")
  private List<TrainerData.Langs> langs = new ArrayList<TrainerData.Langs>();

  @Embeddable
  public static class Langs {
    private String langs;

    public TrainerData.Langs withLangs(String type) {
      this.langs = type;
      return this;
    }

    public String getLangs() {
      return langs;
    }

    @Override
    public String toString() {
      return "" +
              langs;
    }
  }

  @Expose
  @Column(name = "gender")
  private Integer gender;

  @Expose
  @Column(name = "maxSlots")
  private Integer maxSlots;

  @Expose
  private String country;

  @Expose
  @Column(name = "tz")
  private String timeZone;

  @Expose
  @Column(name = "locale")
  private String locate;

  @Expose
  private String city;


  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "roles")
  private List<TrainerData.Roles> roles = new ArrayList<TrainerData.Roles>();

  @Embeddable
  public static class Roles {
    private String roles;

    public TrainerData.Roles withRoles(String type) {
      this.roles = type;
      return this;
    }

    public String getRoles() {
      return roles;
    }

    @Override
    public String toString() {
      return "" +
              roles;
    }
  }


  @Expose
  private String roleUi;


  @Expose
  @Column(name = "payBase")
  private Integer payBase;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "contacts")
  private List<TrainerData.Contacts> contacts = new ArrayList<TrainerData.Contacts>();

  @Embeddable
  public static class Contacts {
    private String type;
    private String val;

    public TrainerData.Contacts withType(String type) {
      this.type = type;
      return this;
    }

    public TrainerData.Contacts withVal(String val) {
      this.val = val;
      return this;
    }

    public String getType() {
      return type;
    }

    public String getVal() {
      return val;
    }

    @Override
    public String toString() {
      return "Contacts{" +
              "type='" + type + '\'' +
              ", val='" + val + '\'' +
              '}';
    }
  }

  @Expose
  private String emailUI;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "emails")
  private List<TrainerData.Emails> emails = new ArrayList<TrainerData.Emails>();

  @Embeddable
  public static class Emails {
    private String address;
    private Boolean verified;

    public TrainerData.Emails withAddress(String address) {
      this.address = address;
      return this;
    }

    public TrainerData.Emails withVerified(Boolean verified) {
      this.verified = verified;
      return this;
    }

    public String getAddress() {
      return address;
    }

    public Boolean getVerified() {
      return verified;
    }

    @Override
    public String toString() {
      return "Emails{" +
              "address='" + address + '\'' +
              ", verified='" + verified + '\'' +
              '}';
    }
  }


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

  @Expose
  private String note;

  @Expose
  private String info;

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

  public TrainerData withCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public TrainerData withStartWorkUi(String startWorkUi) {
    this.startWorkUi = startWorkUi;
    return this;
  }

  public TrainerData withLangs(List<TrainerData.Langs> langs) {
    this.langs = langs;
    return this;
  }


  public TrainerData withGender(Integer gender) {
    this.gender = gender;
    return this;
  }

  public TrainerData withRoles(List<TrainerData.Roles> roles) {
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

  public TrainerData withPayBase(Integer payBase) {
    this.payBase = payBase;
    return this;
  }
  public TrainerData withContacts(List<TrainerData.Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public TrainerData withEmailUI(String emailUI) {
    this.emailUI = emailUI;
    return this;
  }

  public TrainerData withEmails (List<TrainerData.Emails> emails) {
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

  public List<TrainerData.Langs> getLangs() {
    return langs;
  }

  public Integer getGender() {
    return gender;
  }

  public List<TrainerData.Roles> getRoles() {
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

  public Integer getPayBase() {
    return payBase;
  }

  public List<TrainerData.Contacts> getContacts() {
    return contacts;
  }

  public String getEmailUI() {
    return emailUI;
  }

  public List<TrainerData.Emails> getEmails() {
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


  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "TrainerData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", birthdayUi='" + birthdayUi + '\'' +
            ", gender=" + gender +
            ", maxSlots=" + maxSlots +
            ", country='" + country + '\'' +
            ", timeZone='" + timeZone + '\'' +
            ", locate='" + locate + '\'' +
            ", city='" + city + '\'' +
            ", payBase=" + payBase +
            ", note='" + note + '\'' +
            ", info='" + info + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TrainerData that = (TrainerData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(maxSlots, that.maxSlots) &&
            Objects.equals(country, that.country) &&
            Objects.equals(timeZone, that.timeZone) &&
            Objects.equals(locate, that.locate) &&
            Objects.equals(city, that.city) &&
            Objects.equals(payBase, that.payBase) &&
            Objects.equals(note, that.note) &&
            Objects.equals(info, that.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, gender, maxSlots, country, timeZone, locate, city, payBase, note, info);
  }
}
