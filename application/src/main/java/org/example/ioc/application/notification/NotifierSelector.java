package org.example.ioc.application.notification;

import lombok.RequiredArgsConstructor;
import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.model.User;

import java.util.Map;

@RequiredArgsConstructor
public class NotifierSelector {
    private final Map<MessagingType, Notifier> notifierMap;
    public static final MessagingType DEFAULT_NOTIFIER = MessagingType.EMAIL;

    public Notifier getNotifier(User user) {
        Notifier notifier = notifierMap.get(user.getMessagingType());
        return notifier == null ? notifierMap.get(DEFAULT_NOTIFIER) : notifier;
    }
}
