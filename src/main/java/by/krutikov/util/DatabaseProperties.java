package by.krutikov.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@PropertySource("classpath:db.properties")
public class DatabaseProperties {
    @Value("${POSTGRES_DRIVER_NAME}")
    private String driver_name;
    @Value("${DATABASE_URL}")
    private String url;
    @Value("${DATABASE_PORT}")
    private String port;
    @Value("${DATABASE_NAME}")
    private String name;
    @Value("${DATABASE_LOGIN}")
    private String login;
    @Value("${DATABASE_PASSWORD}")
    private String password;
}
