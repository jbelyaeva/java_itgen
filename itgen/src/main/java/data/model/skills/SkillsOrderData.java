package data.model.skills;

import com.google.gson.annotations.Expose;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;

@Entity("skillsOrder")
public class SkillsOrderData {

  @Expose
  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("order")
  private ArrayList<String> order;

  public String getId() {
    return id;
  }

  public ArrayList<String> getOrder() {
    return order;
  }
}
