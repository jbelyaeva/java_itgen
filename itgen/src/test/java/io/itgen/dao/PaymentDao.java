package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.PaymentData;
import io.itgen.model.ScheduleData;
import io.itgen.model.TaskData;
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

  public PaymentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(PaymentData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class);
    datastore.delete(query);
  }
}

