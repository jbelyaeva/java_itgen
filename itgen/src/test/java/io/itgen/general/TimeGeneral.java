package io.itgen.general;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class TimeGeneral {

  public TimeGeneral() {
    super();
  }

  int twentyFourHours = 172800000; //86400000;

  private long getMsLocalTime() {
    LocalDate date = LocalDate.now();
    return date
            .atStartOfDay()
            .toInstant(OffsetDateTime
                    .now()
                    .getOffset())
            .toEpochMilli();
  }

  public Double date() {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000) * 1.0;
  }

  public Double dateYesterday() {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000 - twentyFourHours) * 1.0;
  }

  public Double Stime(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000 + start(period)) * 1.0;
  }

  public Double Etime(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000 + finish(period)) * 1.0;
  }

  public Double StimeYesterday(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000 + start(period) - twentyFourHours) * 1.0;
  }

  public Double EtimeYesterday(String period) {
    long nowTime = getMsLocalTime();
    return (nowTime + 10800000 + finish(period) - twentyFourHours) * 1.0;
  }

  //продумать остальные периоды т.к. если создаем в 01:00, а сейчас 10.00, то start=0
  public int start(String period) {
    int startValue = 0;
    if (period.equals("21:00 - 23:00")) {
      startValue = 64800000;
    }
    if (period.equals("18:00 - 20:00")) {
      startValue = 54000000;
    }
    if (period.equals("01:00 - 03:00")) {
      startValue = 79200000;
    }
    return startValue;
  }

  public int finish(String period) {
    int finishValue = 0;
    if (period.equals("21:00 - 23:00")) {
      finishValue = 72000000;
    }
    if (period.equals("18:00 - 20:00")) {
      finishValue = 61200000;
    }
    if (period.equals("01:00 - 03:00")) {
      finishValue = 86400000;
    }
    return finishValue;
  }


}
