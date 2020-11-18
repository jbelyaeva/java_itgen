package app.appmanager.transactionHelper;

import data.model.typeform.Answers;
import data.model.typeform.TestData;
import data.model.typeform.TestResultsData;
import data.services.TestResultsService;
import data.services.TestService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TrTestHelper {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();

  public void saveTest(
      String id,
      String title,
      String rootFormId,
      String language,
      String description,
      int minScore,
      int maxScore,
      int timeForPassing,
      String[] skills,
      Date createAt,
      Date removedAt) {
    TestData test =
        new TestData()
            .withId(id)
            .withTitle(title)
            .withRootFormId(rootFormId)
            .withLanguage(language)
            .withDescription(description)
            .withSkillIds(Arrays.asList(skills))
            .withMinScore(minScore)
            .withMaxScore(maxScore)
            .withTimeForPassing(timeForPassing)
            .withCreatedAt(createAt)
            .withEntityTestId("123456")
            .withRemovedAt(removedAt);
    testService.create(test);
  }

  public void saveResultTestInProcess(
      String id,
      String childId,
      String testId,
      String title,
      String rootFormId,
      String[] skills,
      String language,
      int minScore,
      int maxScore,
      Date createAt,
      String rawFormData) {
    TestResultsData testResult =
        new TestResultsData()
            .withId(id)
            .withChildId(childId)
            .withTestId(testId)
            .withTitle(title)
            .withRootFormId(rootFormId)
            .withSkillIds(Arrays.asList(skills))
            .withLanguage(language)
            .withMinScore(minScore)
            .withMaxScore(maxScore)
            .withCreatedAt(createAt)
            .withEntityTestId("123456")
            .withRawFormData(rawFormData);
    testResultsService.create(testResult);
  }

  public void saveResultTest(
      String id,
      String childId,
      String testId,
      String title,
      String rootFormId,
      String[] skills,
      String language,
      int minScore,
      int maxScore,
      Date date,
      String rawFormData,
      Boolean pass) {
    TestResultsData testResult =
        new TestResultsData()
            .withId(id)
            .withChildId(childId)
            .withTestId(testId)
            .withTitle(title)
            .withRootFormId(rootFormId)
            .withSkillIds(Arrays.asList(skills))
            .withLanguage(language)
            .withMinScore(minScore)
            .withMaxScore(maxScore)
            .withCreatedAt(date)
            .withEntityTestId("123456")
            .withRawFormData(rawFormData)
            .withAnswers(
                Collections.singletonList(
                    new Answers()
                        .withTitle("Как тебя зовут")
                        .withLearnerAnswers("1")
                        .withTrueAnswers("-")
                        .withIsAnswerTrue(false)))
            .withFinishedAt(date)
            .withFinishedSuccess(pass)
            .withRawFormAnswers("")
            .withScores(1);
    testResultsService.create(testResult);
  }
}
