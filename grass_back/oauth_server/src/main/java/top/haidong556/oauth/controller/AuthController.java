package top.haidong556.oauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.haidong556.oauth.entity.Jwt;
import top.haidong556.oauth.security.key.JwtFactory;


@RestController
public class AuthController {

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyToken(@RequestBody Jwt jwt) {

             if(JwtFactory.verify(jwt.getJwt())==true){
                 return ResponseEntity.ok(true);

             }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

    }
}
