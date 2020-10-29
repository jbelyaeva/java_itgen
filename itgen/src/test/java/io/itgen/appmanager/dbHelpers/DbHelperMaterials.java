package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialBranchs;
import io.itgen.model.materials.MaterialChildData;
import io.itgen.model.materials.MaterialChilds;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.MaterialPerms;
import io.itgen.model.materials.MaterialPermsData;
import io.itgen.model.materials.Materials;
import java.util.List;

public class DbHelperMaterials {
  public MaterialData lastMaterial() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialData> q = datastore.createQuery(MaterialData.class);
    long count = q.count();
    List<MaterialData> material = q.find().toList();
    MaterialData lastMaterial = material.get(Math.toIntExact(count - 1));
    return lastMaterial;
  }

  public MaterialBranchData lastBranchMaterial() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialBranchData> q = datastore.createQuery(MaterialBranchData.class);
    long count = q.count();
    List<MaterialBranchData> branch = q.find().toList();
    MaterialBranchData lastMaterialBranch = branch.get(Math.toIntExact(count - 1));
    return lastMaterialBranch;
  }

  public Materials materials() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialData> q = datastore.createQuery(MaterialData.class);
    List<MaterialData> materials = q.find().toList();
    return new Materials(materials);
  }

  public MaterialBranchs materialBranchs() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialBranchData> q = datastore.createQuery(MaterialBranchData.class);
    List<MaterialBranchData> materialBranchs = q.find().toList();
    return new MaterialBranchs(materialBranchs);
  }

  public MaterialPerms materialPerms() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialPermsData> q = datastore.createQuery(MaterialPermsData.class);
    List<MaterialPermsData> materialPerms = q.find().toList();
    return new MaterialPerms(materialPerms);
  }

  public MaterialChilds materialChilds() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<MaterialChildData> q = datastore.createQuery(MaterialChildData.class);
    List<MaterialChildData> materialChildData = q.find().toList();
    return new MaterialChilds(materialChildData);
  }
}
