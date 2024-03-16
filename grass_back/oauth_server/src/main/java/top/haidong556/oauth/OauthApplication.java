package top.haidong556.oauth;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import top.haidong556.oauth.security.key.JwtUpdater;


@EnableWebSecurity
@SpringBootApplication
@EnableDiscoveryClient
public class OauthApplication {

    public static void main(String[] args) {
        Thread jwtUpdater=new Thread(new JwtUpdater());
        jwtUpdater.start();
        SpringApplication.run(OauthApplication.class, args);
    }

}
