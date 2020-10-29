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

public class WorkerDataGenerator extends TestBase {
  // опции командной строки
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    WorkerDataGenerator generator = new WorkerDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  // генерация тестовых данных
  private void run() throws IOException {
    List<WorkerData> workers = generateWorkers(count);
    if (format.equals("json")) {
      saveAsJson(workers, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  // сохранение этих данных в файл
  private void saveAsJson(List<WorkerData> workers, File file) throws IOException {
    Gson gson =
        new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(workers);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }
  /**
   * Инструкция: генератор настроен на создание данных для тестов на модификацию. при генерации
   * данных для тестов на создание необходимо установить значения по умолчанию для следующих полей:
   * gender -- 2, country -- "BY", city -- "Минск", timeZone -- "Europe/Minsk", locate -- "ru"
   */
  private List<WorkerData> generateWorkers(int count) {
    List<WorkerData> workers = new ArrayList<WorkerData>();
    for (int i = 0; i < count; i++) {
      workers.add(
          new WorkerData()
              .withFirstName(String.format("Алеша-%s", i))
              .withLastName(String.format("Зайцев-%s", i))
              //       .withRoleUi(String.format("employee", i))  // при создании сотрудника
              // раскоментить
              .withStartWorkUi(String.format("01.01.1998", i))
              .withBirthdayUi(String.format("25.03.1979", i))
              .withGender(Integer.valueOf(String.format("2", i)))
              .withCountry(String.format("AL", i))
              .withCity(String.format("Сватково %s", i))
              .withTimeZone(String.format("Pacific/Honolulu", i))
              .withLocate(String.format("ru", i))
              .withPhone(String.format("11111111111", i))
              .withTelegram(String.format("tg %s", i))
              .withViber(String.format("22222222222", i))
              .withSkype(String.format("sk %s", i))
              .withWhatsapp(String.format("33333333333", i))
              .withFb(String.format("fb %s", i))
              .withVk(String.format("vk %s", i))
              .withOk(String.format("ok %s", i))
              .withInst(String.format("inst %s", i)));
    }
    return workers;
  }
}
