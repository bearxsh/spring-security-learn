package com.bearxsh.springsecuritylearn.test;

import com.nimbusds.jose.JOSEException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class Test_02 {
    public static void main(String[] args) throws NoSuchAlgorithmException, JOSEException {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        try {
            verifier.initVerify(publicKey);

        } catch (InvalidKeyException e) {
            throw new JOSEException("Invalid public RSA key: " + e.getMessage(), e);
        }

        try {
            verifier.update(signedContent);
            return verifier.verify(signature.decode());

        } catch (SignatureException e) {
            return false;
        }
    }
}
