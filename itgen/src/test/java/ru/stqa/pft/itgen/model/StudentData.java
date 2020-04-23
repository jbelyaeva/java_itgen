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
  @Id
  @Column(name = "_id")
  private String id;
  @Column(name = "firstName")
  private String firstname;
  @Column(name = "lastName")
  private String lastname;
  @Column(name = "gender")
  private Integer gender;
  @Transient
  private String birthdayUi;
  @Column(name = "pcLevel")
  private String pclevel;
  @Column(name = "country")
  private String country;
  @Column(name = "city")
  private String city;
  @Column(name = "tz")
  private String timezone;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column (name = "contacts")
  private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embeddable
  public static class Contacts {
    private String type;
    private String val;
    private boolean preferred;

    public void setType(String type) {
      this.type = type;
    }
    public void setVal(String val) {
      this.val = val;
    }
    public void setPreferred(boolean preferred) {
      this.preferred = preferred;
    }

    public String getType() {
      return type;
    }
    public String getVal() {
      return val;
    }
    public boolean isPreferred() {
      return preferred;
    }

    @Override
    public String toString() {
      return "Contacts{" +
              "type='" + type + '\'' +
              ", val='" + val + '\'' +
              ", preferred=" + preferred +
              '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Contacts contacts = (Contacts) o;
      return preferred == contacts.preferred &&
              Objects.equals(type, contacts.type) &&
              Objects.equals(val, contacts.val);
    }

    @Override
    public int hashCode() {
      return Objects.hash(type, val, preferred);
    }
  }


  /**
   * сеттеры
   */

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

  public StudentData withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;
  }

  /**
   * геттеры
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

  public List<Contacts> getContacts() {
    return contacts;
  }


  /**
   * хэш код и иквелс
   */
  @Override
  public String toString() {
    return "StudentData{" +
            "id='" + id + '\'' +
            ", contacts=" + contacts +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StudentData that = (StudentData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(contacts, that.contacts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contacts);
  }

  /** спасение */
  /*
  public boolean deepEquals(StudentData studentData) {
    if (this == studentData) return true;
    // проверяем размеры коллекций
    if (this.getContacts().size() != studentData.getContacts().size())
      return false;
    // сравниваем каждый i-ый элемент коллекции
    // с каждым i-ым элементом другой коллекции
    for (int i = 0; i < studentData.getContacts().size(); ++i) {
      // для сравнения студентов можно использовать equals
      if (!studentData.getContacts().get(i).equals(
              this.getContacts().get(i))) {
        return false;
      }
    }
    // сравниваем остальные поля комнаты
    return Objects.equals(id, studentData.id);
  }
  */

}