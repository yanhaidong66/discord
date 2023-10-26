package top.haidong.oauth.common;

import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class JwtKeyProvider implements RSAKeyProvider {

    @Override
    public RSAPublicKey getPublicKeyById(String keyId) {
        return new MyPublicKey();
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return new MyPrivateKey();
    }

    @Override
    public String getPrivateKeyId() {
        return "fd";
    }
}
