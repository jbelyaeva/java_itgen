package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.payment.PaymentData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class PaymentDao implements Dao<PaymentData> {

  @Override
  public void save(PaymentData payment) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(payment);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(PaymentData paymentData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public PaymentData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class).filter("id", id);
    PaymentData payment = datastore.findAndDelete(query);
    return payment;
  }

  @Override
  public PaymentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(PaymentData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class);
    datastore.delete(query);
  }
}
