package io.itgen.appmanager.transactionHelper;

import io.itgen.model.materials.Branches;
import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.tasks.Activity;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TrMaterialHelper {

  ArrayList<String> list = new ArrayList();
  ArrayList<String> tags = new ArrayList();
  ArrayList<String> comments = new ArrayList();
  ArrayList<String> linkedMaterials = new ArrayList();
  ArrayList<Activity> activity = new ArrayList();
  private final MaterialBranchService materialBranchService = new MaterialBranchService();
  private final MaterialService materialService = new MaterialService();

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
            .withLang(lang)
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
            .withLang(lang)
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
            .withLang(lang)
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
}
