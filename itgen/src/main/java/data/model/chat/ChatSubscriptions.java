package data.model.chat;

import com.google.common.collect.ForwardingSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ChatSubscriptions extends ForwardingSet<ChatSubscriptionData> {

  private final Set<ChatSubscriptionData> delegate;

  public ChatSubscriptions(ChatSubscriptions chatSubscribtions) {
    this.delegate = new HashSet<ChatSubscriptionData>(chatSubscribtions.delegate);
  }

  public ChatSubscriptions() { // конструктор без параметров
    this.delegate = new HashSet<ChatSubscriptionData>();
  }

  public ChatSubscriptions(Collection<ChatSubscriptionData> chatSubscribtions) {
    this.delegate = new HashSet<ChatSubscriptionData>(chatSubscribtions);
  }

  public ChatSubscriptions withAdded(ChatSubscriptionData chatSubscribtion) {
    ChatSubscriptions chatSubscribtions = new ChatSubscriptions(this);
    chatSubscribtions.add(chatSubscribtion);
    return chatSubscribtions;
  }

  public ChatSubscriptions without(ChatSubscriptionData chatSubscribtion) {
    ChatSubscriptions chatSubscribtions = new ChatSubscriptions(this);
    chatSubscribtions.remove(chatSubscribtion);
    return chatSubscribtions;
  }

  @Override
  protected Set<ChatSubscriptionData> delegate() {
    return delegate;
  }
}
