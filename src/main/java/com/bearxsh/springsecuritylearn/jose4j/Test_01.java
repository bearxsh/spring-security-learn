package com.bearxsh.springsecuritylearn.jose4j;

import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.lang.JoseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Test_01 {

    public static void main(String[] args) throws JoseException, MalformedClaimException {
        // 通过私钥对生成jwk
        RsaJsonWebKey jwk = new RsaJsonWebKey(JsonUtil.parseJson("{"
                + "    \"kty\": \"RSA\","
                + "    \"alg\": \"RS256\","
                + "    \"n\": \"mrX5ROEw4kCYDXR94FQsm33gr5o5dQXvuOoe-eG_yvdNW83MMt9GgG_eBBq_1b7HgyP9lo15BfKX3GH1igCjEKoXJxcHC5xox4xC0tvbBNCDwG_987ZsqlgCb4f7X66DCcHh17AyAHYa8JhO2kXvtD1OIQxSajSgmk1C1sxI5kqJXfvJwRLcCEK87P6Bs9YNLnnJeouSkYce04AhspmyQKKax4GllbMjcrUUMRoqpCBMklh5Pgl9sOGRLo-6uzzowtI_SyF03YsE2ejh9m-YWqTYsx7PIg6SdWrNRIprKtjnhc9nk-QBzWbOTFH3bpMoXl0T9KPndaLpi1pXtaej9Q\","
                + "    \"e\": \"AQAB\","
                + "    \"d\": \"lL9vqdUl7fMS_qTJPf1QYjPV6qBKrAQIJ28aV0DA6YF6pFCrCyJ3I5frC2E4nmbuZl0dPTpKaPiFIAQjUwsnvSb8Wb4fLP-2El3-BcQSwX9FnalPrpnvwpwZw2gnvSgJn0EFRh6HBMCJSFf4QI7LWC01SDsTpj9xRsoQAHurf5YZ8YRpi_-XEWBaL-4R_RxpoeAnSgSbJkcGoJNcxqwWbCun37KYBS_71sd155VWycMd-uHtTRnW6SenVG49pexXq-tIQxOwmatpTj0XF7hhshKgdTF1xXPbMSX6XOvxiy929jPMercBL_-OUu0PPUZTTVp0gNziGdevzufHkEfHxQ\","
                + "    \"p\": \"5dOs8Q0SHIuCq-gAOx2c2JaqXzPRsmmZ7nXx1P1jbddDIenVPA6q5zUVqXIRRQgMA7AdD9x7anJ2_kaKSFoE2D8peuObvmjrbmJeYE4F4138pNESOHlBmhUH93Oo4i5TvmNZ5hxu3CmonGKMafeDoMpopN15yeDGkTVFKoOfuys\","
                + "    \"q\": \"rFRhmJIIj3o__CbjVOlUgiVzrk-ZgK9jGXHhyt6LELQae1nUiZNZuwZeHwgzTonsTMJ2JHnmCuDpwuhjf_kha5KHeuczE7gmnlaGd3s6kaKDyB9bXbMTs122SnNiB-lJcwm4wRNWI-irSh8PyHSQVnjvKkQCCEPi4i0Ky1KgDV8\","
                + "    \"dp\": \"qBUJNDn09v9pD8Ra9uEPZq-55mqFgFAPDgEgXj76yshWBqV3F7c6cmG2d_g-fRgHgWL5vjHn6M_SCuEYHRYI2QZIleGEc9tT46T5lME7OS_xp7Bn_PlhawjajLT_3Hs5L9KFWu-MfGPTNpw0SQOGNsARjBGWEnjbgDNPZGpjFYU\","
                + "    \"dq\": \"F_0rFNUHUgm_jHdRYAmXFQLnppU4FhzUG7-podb23t1jblZj6r7TV-CcC4_VrJIwjcLoNU2uw0bp45L7_t2MVHAyYd57Urxoy9PZphpGXe2UkLAkxNdf37Ek5hpHxDgqXFQ3HtF1RUxnQ8stJEdtrEvrZyPOcJ4aoEeK4CDhXNs\","
                + "    \"qi\": \"QdwkKs9n6jswVsbYKprvpNr2Mbg-RBPp5xx1p-ypVJnFOd_lnCA6P3gRJ-pe6tSCIB6AYViEhZpzKMSu47f27_38VHkH9qOOL38ZVeVFo8Yt8lkBwMEKQdDOghfF74L5Fczo9bH7QX679dC847cDEa1oaV2Cdv6XcSKGywwvq3Y\""
                + "}"));

        // 创建Claims，放在JWT payload位置
        // Create the Claims, which will be the content of the JWT
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Issuer");  // who creates the token and signs it
        claims.setAudience("Audience"); // to whom the token is intended to be sent
        claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
        claims.setSubject("subject"); // the subject/principal is whom the token is about
        claims.setClaim("email","mail@example.com"); // additional claims/attributes about the subject can be added
        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

        JsonWebSignature jws = new JsonWebSignature();

        // The payload of the JWS is JSON content of the JWT Claims
        jws.setPayload(claims.toJson());

        // The JWT is signed using the private key
        jws.setKey(jwk.getPrivateKey());

        // Set the Key ID (kid) header because it's just the polite thing to do.
        // We only have one key in this example but a using a Key ID helps
        // facilitate a smooth key rollover process
        jws.setKeyIdHeaderValue("kid");

        // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
        jws.setAlgorithmHeaderValue(jwk.getAlgorithm());

        // 生成JWT 令牌
        // Sign the JWS and produce the compact serialization or the complete JWT/JWS
        // representation, which is a string consisting of three dot ('.') separated
        // base64url-encoded parts in the form Header.Payload.Signature
        // If you wanted to encrypt it, you can simply set this jwt as the payload
        // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
        String jwt = jws.getCompactSerialization();

        long expirationTime = claims.getExpirationTime().getValueInMillis();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.SIMPLIFIED_CHINESE);
        String msg = String.format("JWT expired at (%d -> %s)", expirationTime, dateFormat.format(expirationTime));

        // 打印JWT 令牌过期时间
        System.out.println(msg);
        // 打印JWT 令牌
        System.out.println(jwt);
    }
}
