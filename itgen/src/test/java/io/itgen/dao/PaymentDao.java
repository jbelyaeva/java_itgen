package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.PaymentData;
import io.itgen.model.WorkerData;

public class PaymentDao {

  public void save(PaymentData payment) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(payment);
  }

  public PaymentData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class).filter("id", id);
    PaymentData payment = datastore.findAndDelete(query);
    return payment;
  }

}

