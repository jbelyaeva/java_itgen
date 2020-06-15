package io.itgen.appmanager;

import io.itgen.model.RequestData;
import io.itgen.model.Requests;
import org.openqa.selenium.WebDriver;

public class RequestHelper extends HelperBase {

  public RequestHelper(WebDriver wd) {
    super(wd);
  }

  public String getIdNewRequestDB(Requests before, Requests after) {
    int a = 0;
    String getIdAfter = "";
    for (RequestData request : after) {
      getIdAfter = request.getId();
      for (RequestData request_before : before) {
        String getIdBefore = request_before.getId();
        if (getIdAfter.equals(getIdBefore)) {
          a = 1;
          break;
        } else {
          a = 2;
        }
      }
      if (a == 2) {
        break;
      }
    }
    return getIdAfter;
  }

}
