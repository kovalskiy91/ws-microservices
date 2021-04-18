package ws.microservices.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class InsuranceGatewayInboundApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceGatewayInboundApplication.class, args);
    }

}
