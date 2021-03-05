package data.model.tier;

import dev.morphia.annotations.Property;

public class Product {

  @Property("_id")
  private String id;
  private int price;
  private String currency;

  public Product() {
  }
}
