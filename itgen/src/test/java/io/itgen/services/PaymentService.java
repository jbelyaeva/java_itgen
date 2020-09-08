package io.itgen.services;

import io.itgen.dao.PaymentDao;
import io.itgen.model.PaymentData;

public class PaymentService {

  private final PaymentDao paymentDao = new PaymentDao();

  public PaymentService() {
  }

  public PaymentData DeleteById(String id) {
    return paymentDao.findByIdAndDelete(id);
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
