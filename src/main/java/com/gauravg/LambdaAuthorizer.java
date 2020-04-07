package com.gauravg;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class LambdaAuthorizer {
//
//    {
//        "type": "TOKEN",
//            "authorizationToken": "incoming-client-token",
//            "methodArn": "arn:aws:execute-api:us-east-2:123456789012:example/prod/POST/{proxy+}"
//    }


    public Policy handler(Map<String, Object> event, Context ctx) {


        LambdaLogger logger = ctx.getLogger();

        logger.log(" Input : Auth token " + (String) event.get("authorizationToken"));
        logger.log(" Input : Method Arn " + (String) event.get("methodArn"));

        logger.log("Hello from Lambda Auth");

        String token = (String) event.get("authorizationToken");
        DecodedJWT jwt = null;
        Claim sub = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("hello");
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            jwt = verifier.verify(token);
            event.put("Token Verified", "yes");
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            logger.log("exception " + exception);
        }


        try {
            jwt = JWT.decode(token);
            logger.log(jwt.getPayload());

            Map<String, Claim> claims = jwt.getClaims();

            sub = claims.get("sub");

        } catch (JWTDecodeException exception) {
            logger.log("exception " + exception);
        }

        Policy policy = new Policy();
        policy.addStatement((String) event.get("methodArn"));
//        event.put("Decoded JWT ", (jwt!=null) ? jwt.toString():null);

        logger.log(policy.toString());


        return policy;
    }
}


