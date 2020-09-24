package io.itgen.dao;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.TrainerData;
import io.itgen.model.WorkerData;

public class WorkerDao {

  public void save(WorkerData worker) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(worker);
  }

  public WorkerData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<WorkerData> query = datastore.createQuery(WorkerData.class).filter("id", id);
    WorkerData worker = datastore.findAndDelete(query);
    return worker;
  }

  public WorkerData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(WorkerData.class).field("id").equal(id).first();
  }

}

