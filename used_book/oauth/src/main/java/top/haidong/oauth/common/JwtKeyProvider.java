package top.haidong.oauth.common;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
@PropertySource("classpath:key-config.properties")
public class JwtKeyProvider implements RSAKeyProvider {
    private static final JwtKeyProvider instance=new JwtKeyProvider();
    @Value("${server-key-expired-millis}")
    private static long EXPIRE_TIME_MILLIS;
    private static volatile MyKeyPair keyPair;
    private static volatile   Date keyCreateTime;
    private static  volatile Date keyExpiredTime;



    private JwtKeyProvider(){
        keyPair=new MyKeyPair();
        Date current=new Date();
        keyCreateTime=current;
        keyExpiredTime=current;
    }

    public static JwtKeyProvider getInstance(){
        return instance;
    }

    @Override
    public  RSAPublicKey getPublicKeyById(String keyId) {
        Date current=new Date();
        if(current.after(keyExpiredTime)){
            keyPair=new MyKeyPair();
            keyCreateTime=current;
            keyExpiredTime=new Date(current.getTime()+EXPIRE_TIME_MILLIS);
        }

        return (RSAPublicKey) keyPair.getPublicKey();
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        Date current=new Date();
        if(current.after(keyExpiredTime)){
            keyPair=new MyKeyPair();
            keyCreateTime=current;
            keyExpiredTime=new Date(current.getTime()+EXPIRE_TIME_MILLIS);
        }

        return (RSAPrivateKey) keyPair.getPrivateKey();
    }

    @Override
    public String getPrivateKeyId() {
        return "fd";
    }
}
