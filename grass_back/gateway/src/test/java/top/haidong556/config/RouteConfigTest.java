package top.haidong556.config;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RouteConfigTest {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void myRestTemplate() {
        String forObject = restTemplate.getForObject("http://deal-server/config/get", String.class);
        System.out.println(forObject);

    }
}