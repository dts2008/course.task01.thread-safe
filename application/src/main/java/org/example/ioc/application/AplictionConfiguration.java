package org.example.ioc.application;

import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.notification.EmailNotifier;
import org.example.ioc.application.notification.Notifier;
import org.example.ioc.application.notification.NotifierSelector;
import org.example.ioc.application.notification.PhoneNotifier;
import org.example.ioc.application.order.DefaultOrdersRepository;
import org.example.ioc.application.order.OrderService;
import org.example.ioc.application.order.OrdersRepository;
import org.example.ioc.application.persistance.JdbcTemplate;
import org.example.ioc.framework.annotation.Bean;
import org.example.ioc.framework.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AplictionConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate();
    }

    @Bean
    public NotifierSelector notifierSelector(List<Notifier> notifiers){
        Map<MessagingType,Notifier> notifierMap = notifiers.stream()
                .collect(Collectors.toMap(Notifier::getSupportedType, n->n));
        return new NotifierSelector(notifierMap);
    }
}
