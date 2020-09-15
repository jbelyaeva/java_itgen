package io.itgen.appmanager.dbHelpers;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.schedule.CommentData;
import io.itgen.model.schedule.Comments;
import io.itgen.model.schedule.FinishedChildLessonData;
import io.itgen.model.schedule.FinishedChildLessons;
import io.itgen.model.schedule.FinishedLessonData;
import io.itgen.model.schedule.FinishedLessons;
import io.itgen.model.schedule.ScheduleData;
import io.itgen.model.schedule.Schedules;
import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;

import io.itgen.services.FinishedChildLessonService;
import java.util.List;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

public class DbHelperSchedule {
  // вся коллекция расписания
  public Schedules schedules() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class);
    List<ScheduleData> schedules = query.find().toList();
    return new Schedules(schedules);
  }

  public FinishedChildLessons finishedChildLessons() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedChildLessonData> query = datastore.createQuery(FinishedChildLessonData.class);
    List<FinishedChildLessonData> finishedChildLessons = query.find().toList();
    return new FinishedChildLessons(finishedChildLessons);
  }

  public FinishedLessons finishedLessons() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FinishedLessonData> query = datastore.createQuery(FinishedLessonData.class);
    List<FinishedLessonData> finishedLessons = query.find().toList();
    return new FinishedLessons(finishedLessons);
  }

  public Comments comments() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<CommentData> query = datastore.createQuery(CommentData.class);
    List<CommentData> comments = query.find().toList();
    return new Comments(comments);
  }

  public ScheduleData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    ScheduleData schedule = query.find().next();
    return schedule;
  }

  public Schedules findByIdList(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ScheduleData> query = datastore.createQuery(ScheduleData.class).filter("id", id);
    List<ScheduleData> schedules = query.find().toList();
    return new Schedules(schedules);
  }

  public Tasks tasksComposition(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> q = datastore.createQuery(TaskData.class).filter("linkUser", id);
    List<TaskData> tasks = q.find().toList();
    return new Tasks(tasks);
  }

}
