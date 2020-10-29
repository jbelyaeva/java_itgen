package io.itgen.model.chat;

import com.google.common.collect.ForwardingSet;
import io.itgen.model.schedule.ScheduleData;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChatMessages extends ForwardingSet<ChatMessageData> {
  private Set<ChatMessageData> delegate;

  public ChatMessages(ChatMessages chatMessages) {
    this.delegate = new HashSet<ChatMessageData>(chatMessages.delegate);
  }

  public ChatMessages() { //конструктор без параметров
    this.delegate = new HashSet<ChatMessageData>();
  }

  public ChatMessages(Collection<ChatMessageData> chatMessages) {
    this.delegate = new HashSet<ChatMessageData>(chatMessages);
  }

  public ChatMessages withAdded(ChatMessageData chatMessage) {
    ChatMessages chatMessages = new ChatMessages(this);
    chatMessages.add(chatMessage);
    return chatMessages;
  }

  public ChatMessages without(ChatMessageData chatMessage) {
    ChatMessages chatMessages = new ChatMessages(this);
    chatMessages.remove(chatMessage);
    return chatMessages;
  }

  @Override
  protected Set<ChatMessageData> delegate() {
    return delegate;
  }
}

