package io.itgen.services;

import io.itgen.dao.PaymentDao;
import io.itgen.model.PaymentData;
import io.itgen.model.ScheduleData;
import io.itgen.model.StudentData;

public class PaymentService {
  private PaymentDao paymentDao = new PaymentDao();

  public PaymentService() {
  }

  public PaymentData findByIdAndDelete(String id) {
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
}
