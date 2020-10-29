package io.itgen.model.chat;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChatRooms extends ForwardingSet<ChatRoomData> {

  private Set<ChatRoomData> delegate;

  public ChatRooms(ChatRooms chatRooms) {
    this.delegate = new HashSet<ChatRoomData>(chatRooms.delegate);
  }

  public ChatRooms() { //конструктор без параметров
    this.delegate = new HashSet<ChatRoomData>();
  }

  public ChatRooms(Collection<ChatRoomData> chatRooms) {
    this.delegate = new HashSet<ChatRoomData>(chatRooms);
  }

  public ChatRooms withAdded(ChatRoomData chatRoom) {
    ChatRooms chatRooms = new ChatRooms(this);
    chatRooms.add(chatRoom);
    return chatRooms;
  }

  public ChatRooms without(ChatRoomData chatRoom) {
    ChatRooms chatRooms = new ChatRooms(this);
    chatRooms.remove(chatRoom);
    return chatRooms;
  }

  @Override
  protected Set<ChatRoomData> delegate() {
    return delegate;
  }
}

