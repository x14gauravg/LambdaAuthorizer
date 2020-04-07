package com.gauravg;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.LambdaRuntime;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HelloWorldLambda {

//    public void handler(List<Integer> input){
//        String message = String.format("hello , %s",input);
//        System.out.println("Updated Handle");
//
//        input.forEach(System.out::println);
//
//        System.out.println(message);
//    }

//    public void handler(Map<String,List<Integer>> input){
//        String message = String.format("hello , %s",input);
//
//
//        input.forEach((s, integers) -> {
//            System.out.println("key : " + s);
//            integers.forEach(System.out::println);
//        });
//
//        System.out.println(message);
//    }

    public Map handler(Map<String,Object> input, Context ctx){

        LambdaLogger logger =  ctx.getLogger();

        convertMaptoString(input,logger);
//        System.out.println("Message from S3 : "+ input.getHttpMethod());
        APIGatewayProxyResponseEvent res = new APIGatewayProxyResponseEvent();
        Map output = new HashMap();
        output.put("body","Message from Gaurav's Lambda "+ ctx.getLogGroupName());
        output.put("statusCode",200);

        return output;
    }

    private void convertMaptoString(Map<String,Object> map,LambdaLogger logger){

        map.forEach((s, o) -> {
            logger.log( " { " + s + "- > "+ o);
        });

    }
}
