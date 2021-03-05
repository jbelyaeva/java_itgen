package data.model.tier;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;

@Entity("tier-countries")
public class TierCountryData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("defaultTierId")
  private String defaultTierId;

  @Property("defaultEngTierId")
  private String defaultEngTierId;

  @Embedded("groups")
  private ArrayList<Group> groups;

  public TierCountryData() {
  }

  public String getId() {
    return id;
  }
}
