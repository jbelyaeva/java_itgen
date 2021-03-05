package data.precondition;

import core.general.TimeGeneral;
import data.precondition.transactionHelper.TrAchievementHelper;
import data.precondition.transactionHelper.TrCandidateHelper;
import data.precondition.transactionHelper.TrChatHelper;
import data.precondition.transactionHelper.TrCommunityHelper;
import data.precondition.transactionHelper.TrFamilyHelper;
import data.precondition.transactionHelper.TrFinishedLessonHelper;
import data.precondition.transactionHelper.TrLeadHelper;
import data.precondition.transactionHelper.TrMaterialHelper;
import data.precondition.transactionHelper.TrParentHelper;
import data.precondition.transactionHelper.TrPaymentHelper;
import data.precondition.transactionHelper.TrRequestHelper;
import data.precondition.transactionHelper.TrSkillHelper;
import data.precondition.transactionHelper.TrStudentHelper;
import data.precondition.transactionHelper.TrTaskHelper;
import data.precondition.transactionHelper.TrTestHelper;
import data.precondition.transactionHelper.TrWorkerHelper;
import data.precondition.transactionHelper.schedule.TrScheduleTodayHelper;
import data.precondition.transactionHelper.schedule.TrScheduleTomorrowHelper;
import data.precondition.transactionHelper.schedule.TrScheduleYesterdayHelper;

public class TransactionManager {

  private final TrScheduleYesterdayHelper trScheduleYesterdayHelper = new TrScheduleYesterdayHelper();
  private final TrScheduleTodayHelper trScheduleTodayHelper = new TrScheduleTodayHelper();
  private final TrScheduleTomorrowHelper trScheduleTomorrowHelper = new TrScheduleTomorrowHelper();
  private final TrStudentHelper trStudentHelper = new TrStudentHelper();
  private final TrChatHelper transactionChatHelper = new TrChatHelper();
  private final TrLeadHelper transactionLeadHelper = new TrLeadHelper();
  private final TrFamilyHelper transactionFamilyHelper = new TrFamilyHelper();
  private final TrParentHelper transactionParentHelper = new TrParentHelper();
  private final TrTaskHelper transactionTaskHelper = new TrTaskHelper();
  private final TrWorkerHelper transactionWorkerHelper = new TrWorkerHelper();
  private final TrPaymentHelper transactionPaymentHelper = new TrPaymentHelper();
  private final TrMaterialHelper transactionMaterialHelper = new TrMaterialHelper();
  private final TrFinishedLessonHelper transactionFinishedLessonHelper = new TrFinishedLessonHelper();
  private final TrSkillHelper transactionSkillHelper = new TrSkillHelper();
  private final TrTestHelper transactionTestHelper = new TrTestHelper();
  private final TrCommunityHelper transactionCommunityHelper = new TrCommunityHelper();
  private final TrAchievementHelper transactionAchievementHelper = new TrAchievementHelper();
  private final TrRequestHelper transactionRequestHelper = new TrRequestHelper();
  private final TrCandidateHelper trCandidateHelper = new TrCandidateHelper();
  private final TimeGeneral time = new TimeGeneral();

  public TransactionManager() {
  }

  public TrScheduleYesterdayHelper trScheduleYesterday() {
    return trScheduleYesterdayHelper;
  }

  public TrScheduleTomorrowHelper trScheduleTomorrow() {
    return trScheduleTomorrowHelper;
  }

  public TrScheduleTodayHelper trScheduleToday() {
    return trScheduleTodayHelper;
  }

  public TrStudentHelper trStudent() {
    return trStudentHelper;
  }

  public TrChatHelper trChat() {
    return transactionChatHelper;
  }

  public TrCommunityHelper trCommunity() {
    return transactionCommunityHelper;
  }

  public TrFamilyHelper trFamily() {
    return transactionFamilyHelper;
  }

  public TrCandidateHelper trCandidate() {
    return trCandidateHelper;
  }

  public TrParentHelper trParent() {
    return transactionParentHelper;
  }

  public TrWorkerHelper trWorker() {
    return transactionWorkerHelper;
  }

  public TrPaymentHelper trPayment() {
    return transactionPaymentHelper;
  }

  public TrMaterialHelper trMaterial() {
    return transactionMaterialHelper;
  }

  public TrFinishedLessonHelper trFinishedLesson() {
    return transactionFinishedLessonHelper;
  }

  public TrSkillHelper trSkill() {
    return transactionSkillHelper;
  }

  public TrLeadHelper trLead() {
    return transactionLeadHelper;
  }

  public TrTestHelper trTest() {
    return transactionTestHelper;
  }

  public TrTaskHelper trTask() {
    return transactionTaskHelper;
  }

  public TrAchievementHelper trAchievement() {
    return transactionAchievementHelper;
  }

  public TrRequestHelper trRequest() {
    return transactionRequestHelper;
  }

  public TimeGeneral time() {
    return time;
  }
}
