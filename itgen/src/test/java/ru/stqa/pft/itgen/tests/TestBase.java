package ru.stqa.pft.itgen.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.itgen.appmanager.ApplicationManager;
import ru.stqa.pft.itgen.model.*;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.exists;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Listeners(MyTestListener.class)
public class TestBase {
 String path=" C:/Devel/Projects/java_itgen/itgen/src/test/testsScreenshot/markedImages/";
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
   }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
   }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyStudentsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuStudents();
      Students dbStudents = app.dbstudents().students();
      List<StudentData> uiStudents = app.student().list();
      assertThat(new HashSet<Object>(uiStudents), equalTo(dbStudents
              .stream().map((s) -> new StudentData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstname())
                      .withLastName(s.getLastname()))
              .collect(Collectors.toSet())));
    }
  }

  public static void etalon(String expected, String name, Screenshot actualScreenshot) throws IOException {
    if (Boolean.getBoolean("etalon")) {
      File expectedFile = new File(expected + name + ".png"); //закоммитить после создания эталонного снимка
      ImageIO.write(actualScreenshot.getImage(), "png", expectedFile);//закоммитить после создания эталонного снимка
    }
  }

  public void verifyWorkerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuWorkers();
      Workers dbWorkers = app.db().workers();
      List<WorkerData> uiWorkers = app.worker().listWithoutPaginator();
      assertThat(new HashSet<Object>(uiWorkers), equalTo(dbWorkers
              .stream().map((s) -> new WorkerData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstName())
                      .withLastName(s.getLastName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyTrainerListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuTrainers();
      Trainers dbTrainers = app.db().trainers();
      List<TrainerData> uiTrainers = app.trainer().list();
      assertThat(new HashSet<Object>(uiTrainers), equalTo(dbTrainers
              .stream().map((s) -> new TrainerData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstName())
                      .withLastName(s.getLastName()))
              .collect(Collectors.toSet())));
    }
  }
  public void verifyLeadsListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      app.goTo().menuLeads();
      Leads dbLeads = app.db().leads();
      List<LeadData> uiLeads = app.lead().list();
      assertThat(new HashSet<Object>(uiLeads), equalTo(dbLeads
              .stream().map((s) -> new LeadData()
                      .withId(s.getId())
                      .withFirstName(s.getFirstname())
                      .withLastName(s.getLastname()))
              .collect(Collectors.toSet())));
    }
  }


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
