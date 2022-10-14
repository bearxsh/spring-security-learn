package com.bearxsh.springsecuritylearn.jose4j;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

/**
 * 生成一对rsa密钥
 */
public class Test_02 {
    public static void main(String[] args) throws JoseException {
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("authServer");
        final String publicKeyString = rsaJsonWebKey.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY);
        final String privateKeyString = rsaJsonWebKey.toJson(JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
        System.out.println(publicKeyString);
        System.out.println(privateKeyString);
    }
}
