package org.example.ioc.application.persistance;

import java.util.function.Function;

public class JdbcTemplate {
    public int update(String query, Object[] params){
        System.out.println("Meke sql request "+query);
        return 0;
    }
}
