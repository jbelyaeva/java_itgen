package data.services;

import data.dao.ProductDao;
import data.model.products.ProductData;

public class ProductService {

  private final ProductDao productDao = new ProductDao();

  public ProductService() {
  }

  public void save(ProductData productData) {
    productDao.save(productData);
  }

  public void drop() {
    productDao.drop();
  }

  public ProductData findById(String id) {
    return productDao.findById(id);
  }

  public void deleteField(String idProd, String nameField) {
    productDao.deleteField(idProd, nameField);
  }
}
