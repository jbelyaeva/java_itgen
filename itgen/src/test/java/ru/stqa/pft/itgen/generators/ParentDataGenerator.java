package ru.stqa.pft.itgen.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;
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
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private List<ParentData> generateParents(int count) {
    List<ParentData> parents = new ArrayList<ParentData>();
    for (int i = 0; i < count; i++) {
      parents.add(new ParentData()
              .withFirstName(String.format("Алеша %s", i))
              .withLastName(String.format("Зайцев %s", i))
              .withCountry(String.format("Армения", i))
              .withCity(String.format("Пересвет %s", i))
              .withTimeZone(String.format("(GMT+05:00) Азия/Ташкент", i))
              .withPhone(String.format("89035540414", i))
              .withSkype(String.format("sk128", i))
              .withEmail(String.format("eee+" + Math.round(Math.random() * 1000) + "@gmail.com", i))
              .withC2d(String.format("http:/777", i))
              .withViber(String.format("4444444444", i))
              .withWhatsapp(String.format("89629861121", i))
              .withTelegram(String.format("tg896", i))
              .withFb(String.format("fb56", i))
              .withVk(String.format("vk894", i))
              .withOk(String.format("ok256", i))
              .withInst(String.format("inst000", i)));
    }
    return parents;
  }
}