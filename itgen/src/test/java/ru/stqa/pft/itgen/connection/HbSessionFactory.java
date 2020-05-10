package ru.stqa.pft.itgen.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HbSessionFactory {

  public static EntityManagerFactory entityManagerFactory;

  public HbSessionFactory() {
  }

  public static EntityManagerFactory hibernateSessionFactoryUtil() {
    entityManagerFactory = Persistence.createEntityManagerFactory("ogm-mongoDB");
    return entityManagerFactory;
  }
}
