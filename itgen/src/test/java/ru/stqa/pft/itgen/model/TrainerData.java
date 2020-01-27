package ru.stqa.pft.itgen.model;

public class TrainerData {
  private final String firstName;
  private final String lastName;
  private final String startWork;
  private final String birthday;
  private final String gender;
  private final String maxSlots;
  private final String country;
  private final String timeZone;
  private final String locate;
  private final String city;
  private final String payBase;
  private final String phone;
  private final String skype;
  private final String viber;
  private final String whatsapp;
  private final String tg;
  private final String fb;
  private final String vk;
  private final String ok;
  private final String inst;
  private final String note;

  public TrainerData(String firstName, String lastName, String startWork, String birthday, String gender, String maxSlots, String country, String timeZone, String locate, String city, String payBase, String phone, String skype, String viber, String whatsapp, String tg, String fb, String vk, String ok, String inst, String note) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.startWork = startWork;
    this.birthday = birthday;
    this.gender = gender;
    this.maxSlots = maxSlots;
    this.country = country;
    this.timeZone = timeZone;
    this.locate = locate;
    this.city = city;
    this.payBase = payBase;
    this.phone = phone;
    this.skype = skype;
    this.viber = viber;
    this.whatsapp = whatsapp;
    this.tg = tg;
    this.fb = fb;
    this.vk = vk;
    this.ok = ok;
    this.inst = inst;
    this.note = note;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getStartWork() {
    return startWork;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
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
}
