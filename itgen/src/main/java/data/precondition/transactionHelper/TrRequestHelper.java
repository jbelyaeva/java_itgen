package data.precondition.transactionHelper;

import data.model.general.Activity;
import data.model.general.Comments;
import data.model.requests.RequestData;
import data.model.requests.Times;
import data.services.RequestService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TrRequestHelper {

  private final RequestService requestService = new RequestService();
  private ArrayList<Comments> listcomment = new ArrayList<>();

  public void newRequest(
      String idRequest,
      String idCreator,
      String status,
      String idStudent,
      String skill,
      int duration,
      boolean permanent,
      boolean trial
  ) {
    RequestData request =
        new RequestData()
            .withId(idRequest)
            .withCreator(idCreator)
            .withCreatedAt(new Date())
            .withStatus(status)
            .withChildId(idStudent)
            .withComments(listcomment)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill(skill)
            .withDuration(duration)
            .withPermanent(permanent)
            .withTrial(trial)
            .withTimes(
                Arrays.asList(
                    new Times().withMin(1611464400000.0).withMax(1611511200000.0),
                    new Times().withMin(1611550800000.0).withMax(1611597600000.0),
                    new Times().withMin(1611637200000.0).withMax(1611684000000.0),
                    new Times().withMin(1611723600000.0).withMax(1611770400000.0),
                    new Times().withMin(1611810000000.0).withMax(1611856800000.0),
                    new Times().withMin(1611896400000.0).withMax(1611943200000.0),
                    new Times().withMin(1611982800000.0).withMax(1612029600000.0)));
    requestService.save(request);
  }

  public void newRequestToTrainer(
      String idCreator,
      String status,
      String idStudent,
      String skill,
      int duration,
      boolean permanent,
      boolean trial,
      String idTrainer
  ) {
    RequestData request =
        new RequestData()
            .withId("newRequest")
            .withCreator(idCreator)
            .withCreatedAt(new Date())
            .withStatus(status)
            .withChildId(idStudent)
            .withComments(listcomment)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill(skill)
            .withDuration(duration)
            .withPermanent(permanent)
            .withTrial(trial)
            .withTimes(
                Arrays.asList(
                    new Times().withMin(1611464400000.0).withMax(1611511200000.0),
                    new Times().withMin(1611550800000.0).withMax(1611597600000.0),
                    new Times().withMin(1611637200000.0).withMax(1611684000000.0),
                    new Times().withMin(1611723600000.0).withMax(1611770400000.0),
                    new Times().withMin(1611810000000.0).withMax(1611856800000.0),
                    new Times().withMin(1611896400000.0).withMax(1611943200000.0),
                    new Times().withMin(1611982800000.0).withMax(1612029600000.0)))
            .withLessonFormat(0)
            .withTrainerId(idTrainer);
    requestService.save(request);
  }

  public void newRequestToGender(
      String idRequest,
      String idCreator,
      String status,
      String idStudent,
      String skill,
      int duration,
      boolean permanent,
      boolean trial,
      int gender
  ) {
    RequestData request =
        new RequestData()
            .withId(idRequest)
            .withCreator(idCreator)
            .withCreatedAt(new Date())
            .withStatus(status)
            .withChildId(idStudent)
            .withComments(listcomment)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill(skill)
            .withDuration(duration)
            .withPermanent(permanent)
            .withTrial(trial)
            .withTimes(
                Arrays.asList(
                    new Times().withMin(1611464400000.0).withMax(1611511200000.0),
                    new Times().withMin(1611550800000.0).withMax(1611597600000.0),
                    new Times().withMin(1611637200000.0).withMax(1611684000000.0),
                    new Times().withMin(1611723600000.0).withMax(1611770400000.0),
                    new Times().withMin(1611810000000.0).withMax(1611856800000.0),
                    new Times().withMin(1611896400000.0).withMax(1611943200000.0),
                    new Times().withMin(1611982800000.0).withMax(1612029600000.0)))
            .withLessonFormat(0)
            .withTrainerGender(gender);
    requestService.save(request);
  }

  public void newRequestOnIF(String idRequest,
      String idCreator,
      String status,
      String idStudent,
      String skill,
      int duration,
      boolean permanent,
      boolean trial
  ) {
    RequestData request =
        new RequestData()
            .withId(idRequest)
            .withCreator(idCreator)
            .withCreatedAt(new Date())
            .withStatus(status)
            .withChildId(idStudent)
            .withComments(listcomment)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId("666").withTs(new Date()).withT("requestCreated")))
            .withSkill(skill)
            .withDuration(duration)
            .withPermanent(permanent)
            .withTrial(trial)
            .withTimes(
                Arrays.asList(
                    new Times().withMin(1611464400000.0).withMax(1611511200000.0),
                    new Times().withMin(1611550800000.0).withMax(1611597600000.0),
                    new Times().withMin(1611637200000.0).withMax(1611684000000.0),
                    new Times().withMin(1611723600000.0).withMax(1611770400000.0),
                    new Times().withMin(1611810000000.0).withMax(1611856800000.0),
                    new Times().withMin(1611896400000.0).withMax(1611943200000.0),
                    new Times().withMin(1611982800000.0).withMax(1612029600000.0)))
            .withLessonFormat(1);
    requestService.save(request);
  }
}
