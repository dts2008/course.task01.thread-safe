package org.example.ioc.application.notification;

import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.model.User;

public interface Notifier {
    MessagingType getSupportedType();
    void sendMessage(User user, String message);
}
