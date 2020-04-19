package ru.stqa.pft.itgen.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDataGenerator extends TestBase {
  // опции командной строки
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    StudentDataGenerator generator = new StudentDataGenerator();
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
    List<StudentData> students = generateContacts(count);
    if (format.equals("json")) {
      saveAsJson(students, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  //сохранение этих данных в файл
  private void saveAsJson(List<StudentData> students, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(students);
    try (Writer writer = new FileWriter(file);) {
      writer.write(json);
    }
  }

  private List<StudentData> generateContacts(int count) {
    List<StudentData> students = new ArrayList<StudentData>();
    for (int i = 0; i < count; i++) {
      students.add(new StudentData()
              .withFirstName(String.format("Вася-%s", i))
              .withLastName(String.format("Васин-%s", i))
              .withGender(Integer.valueOf(String.format("2", i)))
//              .withBirthday(String.format("01.01.2001", i))
              .withBirthdayUi(String.format("01.01.1978", i))
              .withPclevel(String.format("expert", i))
              .withCountry(String.format("AL", i))
              .withCity(String.format("Сватково %s", i))
              .withTimeZone(String.format("Pacific/Honolulu", i))
              .withPhone(String.format("89035540414", i))
              .withSkype(String.format("sk123", i))
              .withC2d(String.format("http:/88888", i))
              .withViber(String.format("4444444444", i))
              .withWhatsapp(String.format("89629861121", i))
              .withTelegram(String.format("tg45", i)).withFb(String.format("fb99", i))
              .withVk(String.format("vk89", i))
              .withOk(String.format("ok78", i))
              .withInst(String.format("inst567", i)));
    }
    return students;
  }
}