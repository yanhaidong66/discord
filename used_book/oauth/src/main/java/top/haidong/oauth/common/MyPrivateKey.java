package top.haidong.oauth.common;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

public class MyPrivateKey implements RSAPrivateKey {
    private  BigInteger exponent ;
    private  BigInteger modulus;
    public MyPrivateKey(BigInteger exponent,BigInteger modulus){
        this.exponent=exponent;
        this.modulus=modulus;
    }
    @Override
    public BigInteger getPrivateExponent() {
        return exponent;
    }

    @Override
    public String getAlgorithm() {
        return "SHA256withRSA";
    }

    @Override
    public String getFormat() {
        return "PKCS#8";
    }

    @Override
    public byte[] getEncoded() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateCrtKeySpec privateKeySpec = new RSAPrivateCrtKeySpec(
                    modulus,
                    new BigInteger("65537"), // Public Exponent
                    exponent,
                    new BigInteger("1234567890"), // PrimeP
                    new BigInteger("9876543210"), // PrimeQ
                    new BigInteger("1357924680"), // PrimeExponentP
                    new BigInteger("8642097531"), // PrimeExponentQ
                    new BigInteger("1234567890") // CRT Coefficient
            );

            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
            return privateKey.getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BigInteger getModulus() {
        return modulus;
    }
}
