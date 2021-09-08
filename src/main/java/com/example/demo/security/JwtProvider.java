package com.example.demo.security;

import com.example.demo.exception.SpringRedditException;
import com.example.demo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init(){
        try{
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/my-release-key.keystore");
            keyStore.load(resourceAsStream, "123qwe".toCharArray());
        }catch (KeyStoreException| CertificateException | NoSuchAlgorithmException | IOException e){
            throw new SecurityException("Exception occured while loading keystroke");
        }
    }


    public String generateToken(Authentication authentication){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("alias_name", "123qwe".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Exception occured while retrieving public key from keystore");
        }
    }
    public String validateToken(String jwt) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(getPublicKey())
                .requireAudience("string")
                .build()
                .parseClaimsJws(jwt);

        return jwt;
    }
        private PublicKey getPublicKey(){
          try {
              return keyStore.getCertificate("springblog").getPublicKey();
          }catch (KeyStoreException e){
              throw new SecurityException("Exception occured while" +
                      "retrieving public key from keystore");
          }
        }

        public String getUsernameFromJWT(String token){
            Claims claims =Jwts.parserBuilder()
                .requireAudience("string")
                .build()
                .parseClaimsJws(token)
                    .getBody();


        return claims.getSubject();

        }

}
