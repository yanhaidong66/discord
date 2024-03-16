package top.haidong556.oauth.security.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import top.haidong556.config.RestTemplateConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class JwtUpdater implements Runnable{
    static {
        InputStream resourceAsStream;
        Properties properties=new Properties();
        try {
            resourceAsStream = JwtUpdater.class.getClassLoader().getResourceAsStream("key-config.properties");
            properties.load(resourceAsStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EXPIRED= Long.parseLong(properties.getProperty("server-key-expired-millis","-1"));
    }

    private static final long EXPIRED;
    RestTemplate restTemplate= new RestTemplate();
    String url = "http://127.0.0.1:8848/nacos/v1/cs/configs";
    String dataId = "publicKey";
    String group = "oauth";
    String content = "content";
    @Override
    public void run() {

        if(EXPIRED==-1)
            return ;

        while (true){
            try {
                sleep(EXPIRED);
                updateJwt();
                pushJwtToNacos();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean pushJwtToNacos() {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        content=JwtFactory.getPublicKeyProperties();
        String requestBody = "dataId=" + dataId + "&group=" + group + "&content=" + content;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        restTemplate.postForLocation(url, requestEntity);
        System.out.println("Request sent successfully.");
        return true;
    }

    private boolean updateJwt() {

        System.out.println("update");
        boolean r=JwtFactory.updateJwt();
        System.out.println(JwtFactory.getPublicKey().toString());
        return r;
    }
}
