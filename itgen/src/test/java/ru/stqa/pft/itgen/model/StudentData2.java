package ru.stqa.pft.itgen.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class StudentData2 {

  @Id
  private String id;

  @ElementCollection
  @Column(name="langs")
  private List<langs> langs = new ArrayList<langs>();

  @Embedded
  private Settings settings;

  @ElementCollection
  @Column( name = "emails" )
  private List<Emails> emails = new ArrayList<Emails>();

  @ElementCollection
  @Column( name = "contacts" )
  private List<Contacts> contacts = new ArrayList<Contacts>();

  @Embeddable
  public static class langs {
         private String langs;
          // getters, setters ...

    @Override
    public String toString() {
      return "{" +
              "'" + langs + '\'' +
              '}';
    }
  }

  @Embeddable
  public static class Settings {

    private boolean messengersBeforeStart;
    private boolean messengersAfterLessonSkipped;
    private boolean messengersMakePay;
    private boolean messengersAfterLessonAutoCanceled;

    // getters, setters ...

    @Override
    public String toString() {
      return "Settings{" +
              "messengersBeforeStart=" + messengersBeforeStart +
              ", messengersAfterLessonSkipped=" + messengersAfterLessonSkipped +
              ", messengersMakePay=" + messengersMakePay +
              ", messengersAfterLessonAutoCanceled=" + messengersAfterLessonAutoCanceled +
              '}';
    }
  }

  @Embeddable
  public static class Emails {

    private String address;
    private boolean verified;

    // getters, setters ...

    @Override
    public String toString() {
      return "Emails{" +
              "address='" + address + '\'' +
              ", verified=" + verified +
              '}';
    }
  }

  @Embeddable
  public static class Contacts {

    private String type;
    private String val;

    @Override
    public String toString() {
      return "{" +
              "'" + type + '\'' +
              "," + val + '\'' +
              '}';
    }
  }

  public String getId() {
    return id;
  }

  public StudentData2 withId(String id) {
    this.id = id;
    return this;
  }

  public List<StudentData2.langs> getLangs() {
    return langs;
  }

  public StudentData2 withLangs(List<StudentData2.langs> langs) {
    this.langs = langs;
    return this;
  }

  public Settings getSettings() {
    return settings;
  }

  public void setSettings(Settings settings) {
    this.settings = settings;
  }

  public List<Emails> getEmails() {
    return emails;
  }

  public void setEmails(List<Emails> emails) {
    this.emails = emails;
  }

  public List<Contacts> getContacts() {
    return contacts;
  }

  public StudentData2 withContacts(List<Contacts> contacts) {
    this.contacts = contacts;
    return this;

  }

  @Override
  public String toString() {
    return "StudentData2{" +
            "id='" + id + '\'' +
            ", langs=" + langs +
            ", settings=" + settings +
            ", emails=" + emails +
            ", contacts=" + contacts +
            '}';
  }
}
