package app.appmanager.dbHelpers;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.model.schedule.CommentData;
import data.model.schedule.Comments;
import data.model.schedule.FinishedChildLessonData;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessonData;
import data.model.schedule.FinishedLessons;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import java.util.List;

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

  public Tasks tasksComposition(String idUser) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TaskData> q = datastore.createQuery(TaskData.class).filter("linkUser", idUser);
    List<TaskData> tasks = q.find().toList();
    return new Tasks(tasks);
  }
}
