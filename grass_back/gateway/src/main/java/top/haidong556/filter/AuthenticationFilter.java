package top.haidong556.filter;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@Component
@NacosConfigurationProperties(groupId = "${nacos-config.oauth.public-key.group-id}", dataId = "${nacos-config.oauth.public-key.data-id}")
public class AuthenticationFilter implements GatewayFilter, Ordered {
    @Value("${modulus:23}")
    private BigInteger modulus;
    @Value("${exponent:2}")
    private BigInteger exponent;
    @Value("${algorithm:f}")
    private String algorithm;
    @Value("${expired:432}")
    private long expired;
    @Value("${createTime:54}")
    private long createTime;
    @Value("${format:f}")
    private String format;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Mono<String> body=readRequestBody(exchange);
        // 这里获取 JWT，具体获取方式取决于你的请求体格式和内容
        String jwt = ...; // 从请求体中获取 JWT
        if (verify(jwt)) {
            // 验证成功，继续处理请求
            return chain.filter(exchange);
        } else {
            // 验证失败，返回错误响应
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }


    @Override
    public int getOrder() {
        return 0;
    }
    public  boolean verify(String jwt) {
        DecodedJWT verify = null;
        try {
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);

            // 使用 KeyFactory 生成 RSAPublicKey 对象
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(rsaPublicKeySpec);
            verify = JWT.require(Algorithm.RSA256(publicKey, null))
                    .build()
                    .verify(jwt);
            // 验证成功，处理验证通过后的逻辑
            return true;
        } catch (JWTVerificationException exception) {
            // 验证失败，处理验证失败后的逻辑
            System.out.println("验证失败");
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    public static Mono<String> readRequestBody(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Flux<DataBuffer> body = request.getBody();

        // 使用 DataBufferUtils.join 将 Flux<DataBuffer> 中的数据合并为一个 DataBuffer
        Mono<DataBuffer> join = DataBufferUtils.join(body);

        // 使用 DataBufferUtils.release 来释放 DataBuffer 资源
        return join.flatMap(buffer -> {
            try {
                // 从 DataBuffer 中读取字节数组并转换为字符串
                byte[] bytes = new byte[buffer.readableByteCount()];
                buffer.read(bytes);
                String bodyString = new String(bytes, StandardCharsets.UTF_8);

                return Mono.just(bodyString);
            } finally {
                // 释放 DataBuffer 资源
                DataBufferUtils.release(buffer);
            }
        });
    }
}

