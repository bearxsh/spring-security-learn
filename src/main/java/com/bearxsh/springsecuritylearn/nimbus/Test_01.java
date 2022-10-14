package com.bearxsh.springsecuritylearn.nimbus;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import io.jsonwebtoken.JwtException;

import java.text.ParseException;

/**
 * HS256
 */
public class Test_01 {

    public static final String SECRET = "bellfsdafafwrqwerqwerqwerweqrwerwerwqrweqrwe";

    public static void main(String[] args) throws JOSEException, ParseException {

     /*   String token = generateToken();
        verify(token);*/
        verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2UifQ.5jdU0nOYd05qTGPUAtrUdI7FACxWY1WYXZtmEZeAZHY");


    }

    public static String generateToken() throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
        Payload payload = new Payload("{\"sub\":\"Joe\"}");
        JWSObject jwsObject = new JWSObject(header, payload);
        MACSigner signer = new MACSigner(SECRET);
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }

    public static void verify(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        MACVerifier verifier = new MACVerifier(SECRET);
        boolean result = jwsObject.verify(verifier);
        if (!result) {
            throw new JwtException("token 不合法");
        }
        // TODO 检查是否过期
        String payload = jwsObject.getPayload().toString();
        System.out.println(payload);

    }
}
