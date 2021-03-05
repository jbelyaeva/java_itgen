package data.model.products;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.Date;

@Entity("products")
public class ProductData {

  @Id
  @Property("_id")
  private String id;

  @Property("className")
  private String className;

  @Property("duration")
  private int duration;

  @Property("amount")
  private int amount;

  @Property("createdAt")
  private Date createdAt;

  public ProductData() {
  }

  public String getId() {
    return id;
  }
}
