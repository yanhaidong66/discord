package top.haidong.oauth.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class JwtFactoryTest {
    @Test
    public void getJwt(){
        JwtFactory.getJwt("j","f","dfsd");
    }
}
