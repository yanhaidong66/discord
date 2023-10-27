package top.haidong.oauth.common;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;

@Component
@PropertySource("classpath:key-config.properties")
public class JwtFactory {
    private static final String ALGORITHM="RSA256";
    private static final String TYPE="jwt";
    private static final String ISSUER="grass_oauth";
    @Value("${client-key-expired-millis}")
    private static long EXPIRED;
    private static final String AUDIENCE="-1";
    private static final String ISSUE_AT="-1";
    private static final String JWT_ID="-1";
    private static final String USER_ID="-1";
    private static final String USER_NAME="-1";
    private static final String AUTHORITY="-1";
    @Autowired
    private static volatile RSAKeyProvider keyProvider;


    public static String getJwt(String audience,String userId,String authority){
        HashMap<String, Object> head=new HashMap<>();
        head.put("type",TYPE);
        head.put("algorithm",ALGORITHM);

        HashMap<String,String> payload=new HashMap<>();
        payload.put("user_id",userId);
        payload.put("authority",authority);


        Date issuedAt=new Date();
        Date expiredAt=new Date(issuedAt.getTime()+EXPIRED);



        JWTCreator.Builder jwtBuilder=JWT.create();
        String jwtToken=jwtBuilder.withExpiresAt(expiredAt)
                .withAudience(audience)
                .withIssuedAt(issuedAt)
                .withJWTId(JWT_ID)
                .withIssuer(ISSUER)
                .withHeader(head)
                .withPayload(payload)
                .sign(Algorithm.RSA256(JwtKeyProvider.getInstance()));

        return jwtToken;

    }


    /**
     * 验证token  合法性
     */
//    public static DecodedJWT verify(String token) {
//        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
//    }

    /**
     * 获取token信息方法
     */
    /*public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }*/

}
