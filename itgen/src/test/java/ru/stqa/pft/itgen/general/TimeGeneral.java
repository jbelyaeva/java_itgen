package ru.stqa.pft.itgen.general;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class TimeGeneral {

  public TimeGeneral() {
    super();
  }

  public Double time(String period) {
    LocalDate date = LocalDate.now();
    long millisLocalDate = date
            .atStartOfDay()
            .toInstant(OffsetDateTime
                    .now()
                    .getOffset())
            .toEpochMilli();
    double time = (millisLocalDate + 10800000) * 1.0; //  текущая дата
    return time;
  }

  public Double Stime(String period) {
    LocalDate date = LocalDate.now();
    long millisLocalDate = date
            .atStartOfDay()
            .toInstant(OffsetDateTime
                    .now()
                    .getOffset())
            .toEpochMilli();
    double sValue = (millisLocalDate + 10800000 + start(period)) * 1.0; //c 21:00
    return sValue;
  }

  public Double Etime(String period) {
    LocalDate date = LocalDate.now();
    long millisLocalDate = date
            .atStartOfDay()
            .toInstant(OffsetDateTime
                    .now()
                    .getOffset())
            .toEpochMilli();
    double eValue = (millisLocalDate + 10800000 + finish(period)) * 1.0; //по 23:00
    return eValue;
  }

  //продумать остальные периоды т.к. если создаем в 01:00, а сейчас 10.00, то start=0
  public int start(String period) {
    int startValue = 0;
    if (period.equals("21:00 - 23:00")) {
      startValue = 64800000;
    }
    return startValue;
  }

  public int finish(String period) {
    int finishValue = 0;
    if (period.equals("21:00 - 23:00")) {
      finishValue = 72000000;
    }
    return finishValue;
  }
}
