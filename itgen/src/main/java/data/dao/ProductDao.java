package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.products.ProductData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class ProductDao implements Dao<ProductData> {

  public void save(ProductData productData) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(productData);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(ProductData productData) {

  }

  public ProductData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(ProductData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ProductData> query = datastore.createQuery(ProductData.class);
    datastore.delete(query);
  }

  public void deleteField(String idPerm, String nameField) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ProductData> query = datastore.createQuery(ProductData.class).field("id").equal(idPerm);
    datastore.update(query, datastore.createUpdateOperations(ProductData.class).unset(nameField));
  }

  @Override
  public ProductData deleteById(String id) {
    return null;
  }
}
