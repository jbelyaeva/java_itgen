package data.model.tier;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.Date;

@Entity("tiers")
public class TierData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("description")
  private String description;

  @Property("shopId")
  private String shopId;

  @Property("createdAt")
  private Date createdAt;

  @Embedded("products")
  private ArrayList<Product> products;

  public TierData() {
  }

  public String getId() {
    return id;
  }
}
