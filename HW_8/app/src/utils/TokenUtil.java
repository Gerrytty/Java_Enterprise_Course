package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

public class TokenUtil {

    public static String createToken(int user_id, boolean isAdmin) {
        String token = "";

        try {

            Algorithm algorithm = Algorithm.HMAC256("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");

            token = JWT.create().withSubject(String.valueOf(user_id)).withClaim("role", isAdmin)
                    .sign(algorithm);

        } catch (UnsupportedEncodingException e) {
            System.out.println("Fail with creating token");
            e.printStackTrace();

        }
        return token;
    }

    public static boolean verifyToken(String token, boolean isAdmin) {

        try {

            Algorithm algorithm = Algorithm.HMAC256("QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            if (!isAdmin) {
                return true;
            }

            if (jwt.getClaim("role").asBoolean()) {
                return true;
            }

            else {
                return false;
            }

        } catch (JWTVerificationException | UnsupportedEncodingException exception) {
            System.out.println("Exception in verifyToken method");
            exception.printStackTrace();
            return false;
        }
    }
}