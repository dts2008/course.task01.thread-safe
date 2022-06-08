package org.example.ioc.application.notification;

import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.model.User;
import org.example.ioc.framework.annotation.Component;

@Component
public class EmailNotifier implements Notifier {
    @Override
    public MessagingType getSupportedType() {
        return MessagingType.EMAIL;
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.printf("Semd email to %s: \"%s\"\n", user.getEmail(), message);
    }
}
