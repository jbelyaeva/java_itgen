package ru.stqa.pft.itgen.model;

public class WorkerProfileData {
  private final String firstName;
  private final String lastName;
  private final String startDay;
  private final String birthDay;
  private final String gender;
  private final String country;
  private final String city;
  private final String timeZone;
  private final String locate;
  private final String phone;
  private final String skype;
  private final String viber;
  private final String whatsapp;
  private final String tg;
  private final String fb;
  private final String vk;
  private final String ok;
  private final String inst;

  public WorkerProfileData(String firstName, String lastName, String startDay, String birthDay, String gender, String country, String city, String timeZone, String locate, String phone, String skype, String viber, String whatsapp, String tg, String fb, String vk, String ok, String inst) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.startDay = startDay;
    this.birthDay = birthDay;
    this.gender = gender;
    this.country = country;
    this.city = city;
    this.timeZone = timeZone;
    this.locate = locate;
    this.phone = phone;
    this.skype = skype;
    this.viber = viber;
    this.whatsapp = whatsapp;
    this.tg = tg;
    this.fb = fb;
    this.vk = vk;
    this.ok = ok;
    this.inst = inst;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getStartDay() {
    return startDay;
  }

  public String getBirthDay() {
    return birthDay;
  }

  public String getGender() {
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
}
