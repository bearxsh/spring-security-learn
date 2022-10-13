package com.bearxsh.springsecuritylearn.test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class Test_01 {
    public static void main(String[] args) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bellfsdafafwrqwerqwerqwerweqrwerwerwqrweqrwe");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setHeaderParam("typ", "JWT").setSubject("Joe").signWith(signatureAlgorithm, signingKey).compact();
        System.out.println(jws);

    }
}
