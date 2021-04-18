package ws.microservices.insurance.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DBConfigProperties {
    private String jdbcUrl;
    private String username;
    private String password;

}
