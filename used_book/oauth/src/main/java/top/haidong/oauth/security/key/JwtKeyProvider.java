package top.haidong.oauth.security.key;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * 单例模式
 * 在每次get密钥时候会判断密钥在服务器的过期时间是否到了，如果到了，就创建新的密钥取代原先的。
 */
@PropertySource("classpath:key-config.properties")
public class JwtKeyProvider implements RSAKeyProvider {
    private static final JwtKeyProvider instance=new JwtKeyProvider();
    private static volatile MyKeyPair keyPair;

    private JwtKeyProvider(){
        keyPair=new MyKeyPair();
    }

    public static JwtKeyProvider getInstance(){
        return instance;
    }
    //只维持一个密钥对，所以keyId参数为空
    @Override
    public  RSAPublicKey getPublicKeyById(String keyId) {
        return (RSAPublicKey) keyPair.getPublicKey();
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        Date current=new Date();
        if(current.after(keyPair.getKeyExpiredTime())){
            keyPair=new MyKeyPair();
            System.out.println("--------------------------new-key_------------------------");
        }

        return (RSAPrivateKey) keyPair.getPrivateKey();
    }

    @Override
    public String getPrivateKeyId() {
        return "";
    }

}
