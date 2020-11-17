package data.model.payment;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Payments extends ForwardingSet<PaymentData> {
  private final Set<PaymentData> delegate;

  public Payments(Payments payments) {
    this.delegate = new HashSet<PaymentData>(payments.delegate);
  }

  public Payments() { // конструктор без параметров
    this.delegate = new HashSet<PaymentData>();
  }

  public Payments(Collection<PaymentData> payments) {
    this.delegate = new HashSet<PaymentData>(payments);
  }

  public Payments withAdded(PaymentData payment) {
    Payments payments = new Payments(this);
    payments.add(payment);
    return payments;
  }

  public PaymentData withFamilyId(String id) {
    Payments payments = new Payments(this);
    PaymentData payment = payments.iterator().next().withfId(id);
    return payment;
  }

  public Payments without(PaymentData payment) {
    Payments payments = new Payments(this);
    payments.remove(payment);
    return payments;
  }

  @Override
  protected Set<PaymentData> delegate() {
    return delegate;
  }
}
