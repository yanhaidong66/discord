package top.haidong556.oauth.security.key;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;

/**
 * 单例模式
 * 可以获得jwtToken
 * 可以获得当前jwt的RSA公钥
 * 可以验证jwtToken的合法性
 * 可以获得jwtToken的信息
 */
@PropertySource("classpath:key-config.properties")
public class JwtFactory {
    private static final String ALGORITHM="RSA256";
    private static final String TYPE="jwt";
    private static final String ISSUER="grass_oauth";
    @Value("${client-key-expired-millis}")
    private static long EXPIRED=100000;
    private static final String AUDIENCE="-1";
    private static final String ISSUE_AT="-1";
    private static final String JWT_ID="-1";
    private static final String USER_ID="-1";
    private static final String USER_NAME="-1";
    private static final String AUTHORITY="-1";
    private static final  JwtKeyProvider keyProvider= JwtKeyProvider.getInstance();


    public static String getJwt(Authentication authentication){
        HashMap<String, Object> head=new HashMap<>();
        head.put("type",TYPE);
        head.put("algorithm",ALGORITHM);

        HashMap<String,String> payload=new HashMap<>();
        String a=new String();
        for(GrantedAuthority s:authentication.getAuthorities()){
            a+=s.toString()+";";
        }
        payload.put("authority",a);

        Date issuedAt=new Date();
        Date expiredAt=new Date(issuedAt.getTime()+EXPIRED);


        JWTCreator.Builder jwtBuilder=JWT.create();
        String jwt=jwtBuilder.withExpiresAt(expiredAt)
                .withAudience(authentication.getName())
                .withIssuedAt(issuedAt)
                .withIssuer(ISSUER)
                .withHeader(head)
                .withPayload(payload)
                .sign(Algorithm.RSA256(keyProvider));

        return jwt;

    }
    public static PublicKey getPublicKey(){
        return keyProvider.getPublicKeyById("");
    }

    public static String getPublicKeyJson(){return keyProvider.getPublicKeyJson();}
    public static String getPublicKeyProperties(){return keyProvider.getPublicKeyProperties();}

    /**
     * 验证token  合法性
     */
    public static boolean verify(String token) {
        DecodedJWT verify=null;
        try {
            verify = JWT.require(Algorithm.RSA256(keyProvider.getPublicKeyById(""), null))
                    .build()
                    .verify(token);
            // 验证成功，处理验证通过后的逻辑
            return true;
        } catch (JWTVerificationException exception) {
            // 验证失败，处理验证失败后的逻辑
            System.out.println("验证失败");
            return false;
        }

    }

    /**
     * 获取token信息方法
     */
    public static String getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.RSA256(keyProvider.getPublicKeyById(""),null)).build().verify(token);
        return verify.getClaims().toString();
    }
    public static boolean updateJwt(){
        keyProvider.createNewKeyPair();
        return true;
    }

}
