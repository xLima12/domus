package br.com.codenoir.domus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.codenoir.domus")
public class DomusGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomusGatewayApplication.class, args);
    }

}
