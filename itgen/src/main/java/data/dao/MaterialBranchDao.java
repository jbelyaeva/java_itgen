package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import data.connection.MFSessionFactory;
import data.model.materials.MaterialBranchData;
import dev.morphia.Datastore;
import dev.morphia.query.Query;

public class MaterialBranchDao implements Dao<MaterialBranchData> {

  @Override
  public void save(MaterialBranchData materialBranch) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialBranch);
  }

  @Override
  public <T> void updateField(String id, String nameField, T data) {

  }

  @Override
  public <T> void updateArrayField(String id, String nameField, T[] data) {

  }

  @Override
  public void delete(MaterialBranchData materialBranchData) {

  }

  @Override
  public void deleteField(String id, String nameField) {

  }

  @Override
  public MaterialBranchData deleteById(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<MaterialBranchData> query =
        datastore.createQuery(MaterialBranchData.class).filter("id", id);
    MaterialBranchData materialBranch = datastore.findAndDelete(query);
    return materialBranch;
  }

  @Override
  public MaterialBranchData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialBranchData.class).field("id").equal(id).first();
  }

  @Override
  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialBranchData> query = datastore.createQuery(MaterialBranchData.class);
    datastore.delete(query);
  }
}
