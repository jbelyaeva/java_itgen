package app.appmanager.transactionHelper;

import data.model.general.Activity;
import data.model.general.Comments;
import data.model.general.D;
import data.model.materials.Branches;
import data.model.materials.LinkedMaterials;
import data.model.materials.MaterialBranchData;
import data.model.materials.MaterialChildData;
import data.model.materials.MaterialData;
import data.model.materials.MaterialPermsData;
import data.model.schedule.CommentData;
import data.model.schedule.DoneMaterials;
import data.model.schedule.HwMaterials;
import data.model.schedule.Grades;
import data.services.CommentService;
import data.services.MaterialBranchService;
import data.services.MaterialChildsService;
import data.services.MaterialPermsService;
import data.services.MaterialService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TrMaterialHelper {

  ArrayList<String> list = new ArrayList();
  ArrayList<String> tags = new ArrayList();
  ArrayList<Comments> comments = new ArrayList();
  ArrayList<LinkedMaterials> linkedMaterials = new ArrayList();
  ArrayList<Activity> activity = new ArrayList();
  private final MaterialBranchService materialBranchService = new MaterialBranchService();
  private final MaterialService materialService = new MaterialService();
  private final MaterialChildsService materialChildsService = new MaterialChildsService();
  private final MaterialPermsService materialPermsService = new MaterialPermsService();
  private final CommentService commentService = new CommentService();

  public void newMaterialBranch(String idRecord, String idMaterialBranch, String name) {
    MaterialBranchData materialBranch =
        new MaterialBranchData()
            .withId(idRecord)
            .withBranches(
                Arrays.asList(
                    new Branches()
                        .withId(idMaterialBranch)
                        .withName(name)
                        .withMaterialsOrder(list)));
    materialBranchService.save(materialBranch);
  }

  public MaterialData materialBase(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang) {
    MaterialData material =
        new MaterialData()
            .withId(idMaterial)
            .withCreateAt(new Date())
            .withCreator(creator)
            .withTitle(title)
            .withStatus(status)
            .withSkill(skill)
            .withBranch(idBranch)
            .withType(type)
            .withLevel(level)
            .withLang(lang);
    return material;
  }

  public void newMaterial(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(linkedMaterials)
            .withComments(comments)
            .withDesc(desc)
            .withActivity(activity);
    materialService.save(material);
  }

  public void checkingMaterial(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc,
      String verifier) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(linkedMaterials)
            .withComments(comments)
            .withDesc(desc)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview")))
            .withVerifier(verifier);
    materialService.save(material);
  }

  public void publishedMaterial(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc,
      String verifier) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(linkedMaterials)
            .withComments(comments)
            .withDesc(desc)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview"),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("statusChanged")
                        .withD(new D().withNewData("published").withOldData("checking"))))
            .withVerifier(verifier);
    materialService.save(material);
  }

  public void checkingMaterialDelete(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc,
      String verifier) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(linkedMaterials)
            .withComments(comments)
            .withDesc(desc)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview")))
            .withVerifier(verifier)
            .withRemoved(true);
    materialService.save(material);
  }

  public void linkedMaterial(
      String idMaterial,
      String idLinkMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String linkLang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc,
      String verifier) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(
                Arrays.asList(
                    new LinkedMaterials()
                        .withId(idLinkMaterial)
                        .withLang(linkLang)
                        .withStatus("published")))
            .withComments(comments)
            .withDesc(desc)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview"),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("statusChanged")
                        .withD(new D().withNewData("published").withOldData("checking")),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("linkMaterialCreated")
                        .withD(new D().withLangs(Arrays.asList(linkLang))),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("descUpdated")
                        .withD(new D().withNewData(desc + " " + desc))))
            .withVerifier(verifier)
            .withRemoved(true);
    materialService.save(material);
  }

  public void publishedMaterialWithComment(
      String idMaterial,
      String creator,
      String title,
      String status,
      String skill,
      String idBranch,
      String type,
      String level,
      String lang,
      String originality,
      String materialLink,
      String projectLink,
      String sourceLink,
      String desc,
      String verifier,
      String text) {
    MaterialData material =
        materialBase(idMaterial, creator, title, status, skill, idBranch, type, level, lang)
            .withOriginality(originality)
            .withMaterialLink(materialLink)
            .withProjectLink(projectLink)
            .withSourceLink(sourceLink)
            .withTags(tags)
            .withLimkedMaterials(linkedMaterials)
            .withComments(
                Arrays.asList(
                    new Comments().withId("different").withOwner("666").withText("Комментарий")))
            .withDesc(desc)
            .withActivity(
                Arrays.asList(
                    new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview"),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("statusChanged")
                        .withD(new D().withNewData("published").withOldData("checking")),
                    new Activity()
                        .withUId(verifier)
                        .withTs(new Date())
                        .withT("commentAdded")
                        .withD(new D().withNewData(text))))
            .withVerifier(verifier)
            .withRemoved(true);
    materialService.save(material);
  }

  public void addMaterialPerms(String id, String[] perms, String[] reviews) {
    MaterialPermsData materialPerms =
        new MaterialPermsData()
            .withId(id)
            .withPerms(Arrays.asList(perms))
            .withReviewPerms(Arrays.asList(reviews));
    materialPermsService.save(materialPerms);
  }

  public void materialsOnLesson(
      String id,
      String childId,
      String materialId,
      Boolean hw,
      String status,
      String idTrainer,
      String idSchedule,
      Double dateFinish,
      String mark,
      Boolean f,
      Boolean from,
      Boolean to,
      String fromString,
      String toString
      ) {
    MaterialChildData materialChild =
        new MaterialChildData()
            .withId(id)
            .withChildId(childId)
            .withMaterialId(materialId)
            .withHw(hw)
            .withStatus(status)
            .withActivity(Arrays.asList(new Activity().withUId(idTrainer)
            .withTs(new Date())
            .withS(idSchedule)
            .withW(dateFinish)
            .withT(mark)
            .withF(f)
            .withD(new D().withFrom(from).withTo(to).withFromString(fromString).withToString(toString))));
    materialChildsService.save(materialChild);
  }

  public void addComment(
      String id,
      String owner,
      String target,
      String idSchedule,
      Double w,
      Date date,
      String[] hwMaterials,
      String[] doneMaterials,
      String done,
      Double eTime,
      Double sTime,
      Integer[] grades,
      String hw,
      String skillId,
      String t,
      String[] text
  ) {
    CommentData newComment = new CommentData()
        .withId(id)
        .withOwner(owner)
        .withTarget(target)
        .withS(idSchedule)
        .withW(w)
        .withCreateAt(date)
        .withHwMaterials(Arrays.asList(
            new HwMaterials()
            .withId("111111")
            .withTitle(hwMaterials[0])
            .withType(hwMaterials[1])
            .withMaterialLink(hwMaterials[2])
                .withStatus(hwMaterials[3])))
        .withDoneMaterials(Arrays.asList(new DoneMaterials()
            .withId("222222")
            .withTitle(doneMaterials[0])
            .withType(doneMaterials[1])
            .withMaterialLink(doneMaterials[2])
            .withStatus(doneMaterials[3])))
        .withDone(done)
        .withETime(eTime)
        .withGrades(new Grades()
            .withCompleteHw(grades[0])
            .withCompleteTasks(grades[1])
            .withLearnedNewTopic(grades[2])
            .withMadeNoMistakes(grades[3])
            .withWasInattentive(grades[4])
            .withHasDesireToLearn(grades[5]))
        .withHw(hw)
        .withSTime(sTime)
        .withSkillId(skillId)
        .withT(t)
        .withText(text[0])
        .withTextForParents(text[1])
        .withTopics(text[2]);
    commentService.save(newComment);
  }
}
