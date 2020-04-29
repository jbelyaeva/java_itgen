package ru.stqa.pft.itgen.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ParentDataGenerator extends TestBase {
  // опции командной строки
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ParentDataGenerator generator = new ParentDataGenerator();
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
    List<ParentData> parents = generateParents(count);
    if (format.equals("json")) {
      saveAsJson(parents, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  //сохранение этих данных в файл
  private void saveAsJson(List<ParentData> parents, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(parents);
    try (Writer writer = new FileWriter(file);) {
      writer.write(json);
    }
  }

  private List<ParentData> generateParents(int count) {
    List<ParentData> parents = new ArrayList<ParentData>();
    for (int i = 0; i < count; i++) {
      parents.add(new ParentData()
              .withFirstName(String.format("Витя %s", i))
              .withLastName(String.format("Васильев %s", i))
              .withCountry(String.format("RU", i))
              .withCity(String.format("Пересвет %s", i))
              .withTimeZone(String.format("Pacific/Honolulu", i))
              .withLocate(String.format("ru", i))
              .withPhone(String.format("11111111111", i))
              .withSkype(String.format("sk %s", i))
              .withEmail(String.format("eee+" + Math.round(Math.random() * 1000) + "@gmail.com", i))
              .withC2d(String.format("http:/%s", i))
              .withViber(String.format("22222222222", i))
              .withWhatsapp(String.format("33333333333", i))
              .withTelegram(String.format("tg %s", i))
              .withFb(String.format("fb %s", i))
              .withVk(String.format("vk %s", i))
              .withOk(String.format("ok %s", i))
              .withInst(String.format("inst %s", i))
              .withNote(String.format("Привет %s", i)));
    }
    return parents;
  }
}