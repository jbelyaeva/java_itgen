package io.itgen.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.itgen.model.WorkerData;
import io.itgen.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class WorkerAdminDataGenerator extends TestBase {
  // опции командной строки
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    WorkerAdminDataGenerator generator = new WorkerAdminDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  //генерация тестовых данных
  private void run() throws IOException {
    List<WorkerData> workers = generateWorkers(count);
    if (format.equals("json")) {
      saveAsJson(workers, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  //сохранение этих данных в файл
  private void saveAsJson(List<WorkerData> workers, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(workers);
    try (Writer writer = new FileWriter(file);) {
      writer.write(json);
    }
  }

  private List<WorkerData> generateWorkers(int count) {
    List<WorkerData> workers = new ArrayList<WorkerData>();
    for (int i = 0; i < count; i++) {
      workers.add(new WorkerData()
              .withFirstName(String.format("Алеша-%s", i))
              .withLastName(String.format("Зайцев-%s", i))
              .withEmailUI(String.format("eee+" + Math.round(Math.random() * 1000) + "@gmail.com", i))
              .withPhone(String.format("89032566987", i))
              .withRoleUi("Администратор"));
    }
    return workers;
  }
}