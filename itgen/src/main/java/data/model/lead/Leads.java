package data.model.lead;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Leads extends ForwardingSet<LeadData> {
  private final Set<LeadData> delegate;

  public Leads(Leads leads) {
    this.delegate = new HashSet<LeadData>(leads.delegate);
  }

  public Leads() { // конструктор без параметров
    this.delegate = new HashSet<LeadData>();
  }

  public Leads(Collection<LeadData> leads) {
    this.delegate = new HashSet<LeadData>(leads);
  }

  public Leads withAdded(LeadData lead) { // объект в который добавлена группа
    Leads leads = new Leads(this);
    leads.add(lead);
    return leads;
  }

  public Leads without(LeadData lead) { // объекта, из которго удалена группа
    Leads leads = new Leads(this);
    leads.remove(lead);
    return leads;
  }

  @Override
  protected Set<LeadData> delegate() {
    return delegate;
  }
}
