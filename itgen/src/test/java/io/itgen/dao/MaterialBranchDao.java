package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.materials.MaterialBranchData;

public class MaterialBranchDao {

  public void save(MaterialBranchData materialBranch) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(materialBranch);
  }

  public MaterialBranchData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<MaterialBranchData> query = datastore.createQuery(MaterialBranchData.class).filter("id", id);
    MaterialBranchData materialBranch = datastore.findAndDelete(query);
    return materialBranch;
  }

  public MaterialBranchData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(MaterialBranchData.class).field("id").equal(id).first();
  }
}
