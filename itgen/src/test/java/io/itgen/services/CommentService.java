package io.itgen.services;

import io.itgen.dao.CommentDao;

public class CommentService {

  private final CommentDao commentDao = new CommentDao();

  public CommentService() {}

  public void drop() {
    commentDao.drop();
  }
}
