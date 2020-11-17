package data.model.users;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Transient;
import data.model.usersGeneral.Contacts;
import data.model.usersGeneral.Emails;
import data.model.usersGeneral.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("users")
public class ParentData {
  @Expose
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
  @Property("country")
  private String country;

  @Expose
  @Property("city")
  private String city;

  @Property("tz")
  @Expose
  private String timeZone;

  @Expose
  @Property("locale")
  private String locate;

  @Embedded private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embedded private List<Emails> emails = new ArrayList<Emails>();

  @Expose @Transient private String phone;

  @Expose @Transient private String skype;

  @Expose @Transient private String email;

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

  @Embedded("services")
  private Services services;

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

  public ParentData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  public ParentData withEmails(List<Emails> emails) {
    this.emails = emails;
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

  public ParentData withRoles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public ParentData withServices(Services services) {
    this.services = services;
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

  public List<Emails> getEmails() {
    return emails;
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

  public List<String> getRoles() {
    return roles;
  }

  public Services getServices() {
    return services;
  }

  /* toString(), hashCode() & equals() */

  @Override
  public String toString() {
    return "ParentData{"
        + "id='"
        + id
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", country='"
        + country
        + '\''
        + ", city='"
        + city
        + '\''
        + ", timeZone='"
        + timeZone
        + '\''
        + ", locate='"
        + locate
        + '\''
        + ", note='"
        + note
        + '\''
        + ", services='"
        + services
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParentData that = (ParentData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(country, that.country)
        && Objects.equals(city, that.city)
        && Objects.equals(timeZone, that.timeZone)
        && Objects.equals(locate, that.locate)
        && Objects.equals(note, that.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, country, city, timeZone, locate, note);
  }
}
