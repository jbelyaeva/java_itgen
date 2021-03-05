package data.dao;

public interface Dao<T> {

  void save(T t);

  <T> void updateField(String id, String nameField, T data);

  <T> void updateArrayField(String id, String nameField, T[] data);

  void delete(T t);

  void deleteField(String id, String nameField);

  T deleteById(String id);

  T findById(String id);

  void drop();
}
