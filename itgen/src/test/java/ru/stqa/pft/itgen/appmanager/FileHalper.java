package ru.stqa.pft.itgen.appmanager;

import java.io.*;
import java.nio.file.Paths;

import static java.nio.file.Files.exists;

public class FileHalper {

  public static void SampleFileWriter(String PATH, String text) {
    //создаём объект File, который привязываем к пути PATH.
    File file = new File(PATH);
    try (FileWriter writer = new FileWriter(file, true)) {
      writer.write(text);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  public static String SampleFileReader(String PATH) {
    File file = new File(PATH);
    //Этот спец. объект для построения строки
    StringBuilder sb = new StringBuilder();
    exists(Paths.get(PATH));
    try {
      //Объект для чтения файла в буфер
      BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
      try {
        //В цикле построчно считываем файл
        String s;
        while ((s = in.readLine()) != null) {
          sb.append(s);
        }
      } finally {
        in.close();
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
    //Возвращаем полученный текст с файла
    return sb.toString();
  }
  public static void SampleFileCleaner(String PATH) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(PATH);
    writer.print("");
    writer.close();
  }
  public static void deleteAllFilesFolder(String path) {
    for (File myFile : new File(path).listFiles())
      if (myFile.isFile()) myFile.delete();
  }


}
