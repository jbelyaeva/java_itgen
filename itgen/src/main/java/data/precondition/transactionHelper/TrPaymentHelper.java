package data.precondition.transactionHelper;

import data.model.payment.PaymentData;
import data.services.PaymentService;

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
