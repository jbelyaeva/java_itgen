package data.services;

import data.dao.CommentDao;
import data.model.schedule.CommentData;

public class CommentService {

  private final CommentDao commentDao = new CommentDao();

  public CommentService() {}

  public void drop() {
    commentDao.drop();
  }

  public void save(CommentData comment) {
    commentDao.save(comment);
  }
}
