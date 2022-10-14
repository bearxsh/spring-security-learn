package com.bearxsh.springsecuritylearn.nimbus;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import io.jsonwebtoken.JwtException;

import java.text.ParseException;

/**
 * RS256
 */
public class Test_02 {
    public static void main(String[] args) throws ParseException, JOSEException {
        verify("eyJraWQiOiJraWQiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJJc3N1ZXIiLCJhdWQiOiJBdWRpZW5jZSIsImV4cCI6MTY2NTc0MDU5NywianRpIjoiYXNYbHFkU1VIOVpaRjh4VEgtcXlTUSIsImlhdCI6MTY2NTczOTk5NywibmJmIjoxNjY1NzM5ODc3LCJzdWIiOiJzdWJqZWN0IiwiZW1haWwiOiJtYWlsQGV4YW1wbGUuY29tIiwiZ3JvdXBzIjpbImdyb3VwLW9uZSIsIm90aGVyLWdyb3VwIiwiZ3JvdXAtdGhyZWUiXX0.FcibBmnuSez6PPLoanmkLcuVuJ5giM-RjnsGgcF2RLeHUPOnyKQ8Z93yNZuycXUFVOX5gOsUacm5BdAGkyN5hqasZVZEoR4GepxMdYQ_LtaYCcw5qF_1Po7ysLOuU1MExcKv5u9GkOvkYXFcsGKuv_SPcMTPtCnx_wao0c6Z0k6o8PoIQ9FfDAFT7mohYLXhTlscR-QR4p1Yf_RAsnq7YV60lX__8t7aualnWkmdB8y-sP30qOsSifUhbFzZdMgyKd5eIFlq2kUQ9QuruhIrv8CC1uhQS7jgIncAOcIVmaoOsRemLOeXaKxssnW0j8hcxtMrKWu_RjzmrGd_DDTwHg");
    }


    public static String generateToken() throws JOSEException {
        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        //将负载信息封装到Payload中
        Payload payload = new Payload("{\"sub\":\"Joe\"}");
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建RSA签名器
        RSAKey rsaKey = null;
        JWSSigner jwsSigner = new RSASSASigner(rsaKey, true);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    public static void verify(String token) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey publicRsaKey = null;
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = null;
        if (!jwsObject.verify(jwsVerifier)) {
            throw new IllegalStateException("token 不合法");
        }


    }
}
