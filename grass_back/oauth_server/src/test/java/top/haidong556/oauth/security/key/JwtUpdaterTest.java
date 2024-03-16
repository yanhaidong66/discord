package top.haidong556.oauth.security.key;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUpdaterTest {
    static JwtUpdater jwtUpdater;
    @BeforeAll
    static void init(){
        jwtUpdater=new JwtUpdater();
    }

    @Test
    void testPush(){
        jwtUpdater.pushJwtToNacos();
    }

}