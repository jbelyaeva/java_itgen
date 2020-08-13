package io.itgen.appmanager.transactionHelper;

import io.itgen.model.ParentData;
import io.itgen.model.PaymentData;
import io.itgen.model.StudentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Emails;
import io.itgen.model.users.FinishedLessonsCountBySkill;
import io.itgen.model.users.Status;
import io.itgen.services.ParentService;
import io.itgen.services.PaymentService;
import io.itgen.services.StudentService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrPaymentHelper {

  private final PaymentService paymentService = new PaymentService();

  public void newPayment(
      String idPayment,
      String fId,
      String creator,
      Integer val,
      Integer t,
      String desc,
      Boolean approved,
      Integer money) {

    PaymentData payment =
        new PaymentData()
            .withId(idPayment)
            .withfId(fId)
            .withCreator(creator)
            .withVal(val)
            .withT(t)
            .withDesc(desc)
            .withApproved(approved)
            .withMoney(money);

    paymentService.save(payment);
  }
}
