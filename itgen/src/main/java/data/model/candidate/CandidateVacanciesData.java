package data.model.candidate;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;

@Entity("candidates-vacancies")
public class CandidateVacanciesData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("statuses")
  private ArrayList<String> statuses;

  public CandidateVacanciesData() {
  }

  public String getId() {
    return id;
  }

  public ArrayList<String> getStatuses() {
    return statuses;
  }
}
