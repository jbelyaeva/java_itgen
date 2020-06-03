package ru.stqa.pft.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ru.stqa.pft.itgen.model.WorkerData;

import static ru.stqa.pft.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class WorkerDao {

  public void save(WorkerData worker) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(worker);
  }

  public WorkerData findByIdAndDelete(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<WorkerData> query = datastore.createQuery(WorkerData.class).filter("id", id);
    WorkerData worker = datastore.findAndDelete(query);
    return worker;
  }

}

