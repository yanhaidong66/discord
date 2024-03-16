package top.haidong556.oauth.security.key;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;

class JwtFactoryTest {

    @Test
    void getJwt() {

        LinkedList<GrantedAuthority> l=new LinkedList<>();
        l.add(new SimpleGrantedAuthority("read"));
        l.add(new SimpleGrantedAuthority("write"));
        String jwt = JwtFactory.getJwt(new UsernamePasswordAuthenticationToken("username", "password",l));
        System.out.println(jwt);
        System.out.println(JwtFactory.getTokenInfo(jwt));
        System.out.println(JwtFactory.getTokenInfo(jwt));

    }

    @Test
    void getPublicKey() {
        while(true){
            LinkedList l=new LinkedList<>();
            l.add("read");
            String token=JwtFactory.getJwt(new UsernamePasswordAuthenticationToken("username","password",l));
            PublicKey key=JwtFactory.getPublicKey();
            System.out.println(key.toString());
            System.out.println("------------");
            System.out.println(JwtFactory.getPublicKeyJson());
        }

    }

    @Test
    void verify() {

        LinkedList<GrantedAuthority> l=new LinkedList<>();
        l.add(new SimpleGrantedAuthority("read"));
        l.add(new SimpleGrantedAuthority("write"));
        String token=JwtFactory.getJwt(new UsernamePasswordAuthenticationToken("username","password",l));
        System.out.println(JwtFactory.verify("dskfj"));
        System.out.println(JwtFactory.getTokenInfo(token));
        System.out.println(JwtFactory.getTokenInfo(token));
    }

    @Test
    void getTokenInfo() {
        LinkedList l=new LinkedList<>();
        l.add("read");
        String token=JwtFactory.getJwt(new UsernamePasswordAuthenticationToken("username","password",l));
        System.out.println(JwtFactory.getTokenInfo(token));


    }


}