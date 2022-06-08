package org.example.ioc.application.order;

import lombok.RequiredArgsConstructor;
import org.example.ioc.application.model.User;
import org.example.ioc.application.notification.Notifier;
import org.example.ioc.application.notification.NotifierSelector;
import org.example.ioc.framework.annotation.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final NotifierSelector notifierSelector;

    public void makeOrder(User user, List<String> products){
        products.stream().map(p->new Order(user.getLogin(),p))
                .forEach(ordersRepository::insertOrderItem);

        Notifier notifier = notifierSelector.getNotifier(user);

        notifier.sendMessage(user,"order created");
    }
}
