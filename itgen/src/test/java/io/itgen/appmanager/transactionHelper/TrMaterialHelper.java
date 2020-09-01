package io.itgen.appmanager.transactionHelper;

import io.itgen.model.materials.Branches;
import io.itgen.model.materials.LinkedMaterials;
import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.MaterialPerms;
import io.itgen.model.materials.MaterialPermsData;
import io.itgen.model.tasks.Activity;
import io.itgen.model.tasks.Comments;
import io.itgen.model.tasks.D;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialPermsService;
import io.itgen.services.MaterialService;
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
  private final MaterialPermsService materialPermsService = new MaterialPermsService();

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

  public MaterialData materialBase(String idMaterial,
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
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
                new Activity().withUId(verifier).withTs(new Date()).withT("statusChanged")
                    .withD(new D().withNewData("published").withOldData("checking"))))
        .withVerifier(verifier);
    materialService.save(material);
  }

  public void checkingMaterialDalete(
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
        .withOriginality(originality)
        .withMaterialLink(materialLink)
        .withProjectLink(projectLink)
        .withSourceLink(sourceLink)
        .withTags(tags)
        .withLimkedMaterials(
            Arrays.asList(
                new LinkedMaterials().withId(idLinkMaterial).withLang(linkLang).withStatus("published")))
        .withComments(comments)
        .withDesc(desc)
        .withActivity(
            Arrays.asList(
                new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview"),
                new Activity().withUId(verifier).withTs(new Date()).withT("statusChanged")
                    .withD(
                        new D().withNewData("published").withOldData("checking")),
                new Activity().withUId(verifier).withTs(new Date()).withT("linkMaterialCreated")
                    .withD(
                        new D().withLangs(Arrays.asList(linkLang))),
                new Activity().withUId(verifier).withTs(new Date()).withT("descUpdated")
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
    MaterialData material = materialBase(idMaterial, creator, title, status, skill, idBranch, type,
        level, lang)
        .withOriginality(originality)
        .withMaterialLink(materialLink)
        .withProjectLink(projectLink)
        .withSourceLink(sourceLink)
        .withTags(tags)
        .withLimkedMaterials(linkedMaterials)
        .withComments(Arrays.asList(
            new Comments().withId("different").withOwner("666").withText("Комментарий")))
        .withDesc(desc)
        .withActivity(
            Arrays.asList(
                new Activity().withUId(verifier).withTs(new Date()).withT("tookForReview"),
                new Activity().withUId(verifier).withTs(new Date()).withT("statusChanged")
                    .withD(
                        new D().withNewData("published").withOldData("checking")),
                new Activity().withUId(verifier).withTs(new Date()).withT("commentAdded")
                    .withD(
                        new D().withNewData(text))))
        .withVerifier(verifier)
        .withRemoved(true);
    materialService.save(material);
  }

  public void addMaterialPerms(String id, String[] perms, String[] reviews ) {
    MaterialPermsData materialPerms =
        new MaterialPermsData()
        .withId(id)
        .withPerms(Arrays.asList(perms))
        .withReviewPerms(Arrays.asList(reviews));
    materialPermsService.save(materialPerms);
  }
}
