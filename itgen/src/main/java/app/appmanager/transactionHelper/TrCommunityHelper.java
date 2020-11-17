package app.appmanager.transactionHelper;

import app.appmanager.dbHelpers.DbHelper;
import data.model.communities.CommunityData;
import data.model.communities.Managers;
import data.model.communities.Subscribers;
import data.services.CommunitiesService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrCommunityHelper {

  private final CommunitiesService communitiesService = new CommunitiesService();
  private final DbHelper dbHelper = new DbHelper();

  public void newCommunity(
      String idCommunity,
      Date createAt,
      String idCreator,
      String desc,
      String[] idManagers,
      String[] idSubscribers,
      Date[] dateSubscribers,
      int subsCount,
      String title,
      String[] idTags) {
    CommunityData community =
        new CommunityData()
            .withId(idCommunity)
            .withCreateAt(createAt)
            .withCreatorId(idCreator)
            .withDescription(desc)
            .withSubscribersCount(subsCount)
            .withTitle(title)
            .withTagIds(Arrays.asList(idTags))
            .withManagers(getManagers(idManagers))
            .withSubscribers(getSubscribers(idSubscribers, dateSubscribers));
    communitiesService.saveCommunity(community);
  }

  private List<Managers> getManagers(String[] idManagers) {
    List<Managers> mas = new ArrayList<Managers>();
    for (String idManager : idManagers) {
      mas.add(new Managers()
          .withId(idManager)
          .withRoles(dbHelper.roles(idManager)));
    }
    return mas;
  }

  private List<Subscribers> getSubscribers(String[] idSubscribers, Date[] dateSubscribers) {
    List<Subscribers> mas = new ArrayList<Subscribers>();
    for (int i = 0; i < idSubscribers.length; i++) {
      mas.add(new Subscribers()
          .withId(idSubscribers[i])
          .withSubAt(dateSubscribers[i]));
    }
    return mas;
  }


}
