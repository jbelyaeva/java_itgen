package io.itgen.appmanager.transactionHelper;

import io.itgen.model.ParentData;
import io.itgen.model.StudentData;
import io.itgen.model.TrainerData;
import io.itgen.model.WorkerData;
import io.itgen.model.chat.ChatMessageData;
import io.itgen.model.chat.ChatRoomData;
import io.itgen.model.chat.ChatSubscriptionData;
import io.itgen.model.chat.LastMessage;
import io.itgen.model.chat.User;
import io.itgen.model.chat.Users;
import io.itgen.services.ChatMessageService;
import io.itgen.services.ChatRoomService;
import io.itgen.services.ChatSubscriptionService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TrChatHelper {

  private final ChatMessageService chatMessageService = new ChatMessageService();
  private final ChatRoomService chatRoomService = new ChatRoomService();
  private final ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();

  public void saveChatRoom(
      String id,
      Date createdAt,
      String type,
      String ownerId,
      Object[] arr,
      String users
  ) {
    ChatRoomData room = new ChatRoomData().withId(id).withCreatedAt(createdAt)
        .withType(type)
        .withOwnerId(ownerId)
        .withUsers(getUsers(arr,users));
    chatRoomService.save(room);
  }

  private List<Users> getUsers(Object[] arr, String users) {
    switch (users) {
      case ("Admin_Trainer"):
        return Arrays.asList(
            new Users()
                .withId(((WorkerData) arr[0]).getId())
                .withFirstName(((WorkerData) arr[0]).getFirstName())
                .withLastName(((WorkerData) arr[0]).getLastName())
                .withR(((WorkerData) arr[0]).getRoles().get(2)),
            new Users()
                .withId(((TrainerData) arr[1]).getId())
                .withFirstName(((TrainerData) arr[1]).getFirstName())
                .withLastName(((TrainerData) arr[1]).getLastName())
                .withR(((TrainerData) arr[1]).getRoles().get(1)));
      case ("Admin_Parent"):
        return Arrays.asList(
            new Users()
                .withId(((WorkerData) arr[0]).getId())
                .withFirstName(((WorkerData) arr[0]).getFirstName())
                .withLastName(((WorkerData) arr[0]).getLastName())
                .withR(((WorkerData) arr[0]).getRoles().get(2)),
            new Users()
                .withId(((ParentData) arr[1]).getId())
                .withFirstName(((ParentData) arr[1]).getFirstName())
                .withLastName(((ParentData) arr[1]).getLastName())
                .withR(((ParentData) arr[1]).getRoles().get(0)));
      case ("Student_Trainer"):
        return Arrays.asList(
            new Users()
                .withId(((StudentData) arr[0]).getId())
                .withFirstName(((StudentData) arr[0]).getFirstname())
                .withLastName(((StudentData) arr[0]).getLastname())
                .withR(((StudentData) arr[0]).getRoles().get(0)),
            new Users()
                .withId(((TrainerData) arr[1]).getId())
                .withFirstName(((TrainerData) arr[1]).getFirstName())
                .withLastName(((TrainerData) arr[1]).getLastName())
                .withR(((TrainerData) arr[1]).getRoles().get(0)));
      case ("Parent_Trainer"):
        return Arrays.asList(
            new Users()
                .withId(((ParentData) arr[0]).getId())
                .withFirstName(((ParentData) arr[0]).getFirstName())
                .withLastName(((ParentData) arr[0]).getLastName())
                .withR(((ParentData) arr[0]).getRoles().get(0)),
            new Users()
                .withId(((TrainerData) arr[1]).getId())
                .withFirstName(((TrainerData) arr[1]).getFirstName())
                .withLastName(((TrainerData) arr[1]).getLastName())
                .withR(((TrainerData) arr[1]).getRoles().get(0)));
      default: return null;
    }
  }

  public void saveChatMessage(
      String id,
      String idRoom,
      Date date,
      String text,
      String idUser) {
    ChatMessageData message = new ChatMessageData()
        .withId(id)
        .withrId(idRoom)
        .withTs(date)
        .withText(text)
        .withUser(new User().withId(idUser));
    chatMessageService.save(message);
  }

  public void saveChatSubscription(
      String idDescr,
      Date date,
      String type,
      String idRoom,
      String getUser,
      int msgs,
      boolean alert,
      String postUser,
      String message) {
    ChatSubscriptionData subscr = new ChatSubscriptionData()
        .withId(idDescr)
        .withUpdatedAt(date)
        .withType(type)
        .withRoomId(idRoom)
        .withUserId(getUser)
        .withMsgs(msgs)
        .withAlert(alert)
        .withLastMessage(new LastMessage().withId("123").withTs(date).withrId(idRoom)
            .withText(message).withUser(new User().withId(postUser)));
    chatSubscriptionService.save(subscr);
  }
}