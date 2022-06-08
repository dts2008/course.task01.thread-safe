package org.example.ioc.application.order;

import lombok.RequiredArgsConstructor;
import org.example.ioc.application.persistance.JdbcTemplate;
import org.example.ioc.framework.annotation.Component;

@Component
@RequiredArgsConstructor
public class DefaultOrdersRepository implements OrdersRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertOrderItem(Order order){
        jdbcTemplate.update("INSERT INTO `orders` (login, product) VALUES (?,?)",new Object[]{
                order.getLogin(),
                order.getProduct()
        });
    }
}
