package io.itgen.appmanager.transactionHelper;

import io.itgen.model.typeform.TestData;
import io.itgen.model.typeform.TestResultsData;
import io.itgen.services.TestResultsService;
import io.itgen.services.TestService;
import java.util.Arrays;
import java.util.Date;

public class TrTestHelper {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();

  public void saveTest(String id,
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
    TestData test = new TestData().withId(id).withTitle(title)
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
      Date createAt,
      String rawFormData) {
      TestResultsData testResult = new TestResultsData().withId(id).withChildId(childId)
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
}