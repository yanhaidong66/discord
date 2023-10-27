package top.haidong.oauth.common;

import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.RSAKeyGenParameterSpec;
@Component
public class MyKeyPair {
    private static  PrivateKey privateKey;
    private static  PublicKey publicKey;

    public MyKeyPair(){
        // 使用安全随机数生成器
        SecureRandom secureRandom = new SecureRandom();

        // 创建RSA密钥对生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // 设置密钥大小（通常为2048位或更多位）
        int keySize = 2048;
        try {
            keyPairGenerator.initialize(new RSAKeyGenParameterSpec(keySize, RSAKeyGenParameterSpec.F4), secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 获取公钥和私钥
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
    public PublicKey getPublicKey(){
        return publicKey;
    }
}
