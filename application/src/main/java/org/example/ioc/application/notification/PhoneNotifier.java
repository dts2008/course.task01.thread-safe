package org.example.ioc.application.notification;

import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.model.User;
import org.example.ioc.framework.annotation.Component;

@Component
public class PhoneNotifier implements Notifier {
    @Override
    public MessagingType getSupportedType() {
        return MessagingType.PHONE;
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.printf("Semd viber message to %s: \"%s\"\n", user.getPhone(), message);
    }
}
