package data.services;

import data.dao.PaymentDao;
import data.model.payment.PaymentData;

public class PaymentService {

  private final PaymentDao paymentDao = new PaymentDao();

  public PaymentService() {}

  public PaymentData DeleteById(String id) {
    return paymentDao.deleteById(id);
  }

  public void create(PaymentData payment) {
    paymentDao.save(payment);
  }

  public void save(PaymentData payment) {
    paymentDao.save(payment);
  }

  public PaymentData findById(String id) {
    return paymentDao.findById(id);
  }

  public void drop() {
    paymentDao.drop();
  }
}
