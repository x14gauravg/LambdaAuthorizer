package com.gauravg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//
//{
//        "principalId": "yyyyyyyy", // The principal user identification associated with the token sent by the client.
//        "policyDocument": {
//        "Version": "2012-10-17",
//        "Statement": [
//        {
//        "Action": "execute-api:Invoke",
//        "Effect": "Allow|Deny",
//        "Resource": "arn:aws:execute-api:{regionId}:{accountId}:{apiId}/{stage}/{httpVerb}/[{resource}/[{child-resources}]]"
//        }
//        ]
//        },
//        "context": {
//        "stringKey": "value",
//        "numberKey": "1",
//        "booleanKey": "true"
//        },
//        "usageIdentifierKey": "{api-key}"
//        }
public class Policy {

    public String principalId ="123";
    public Map<String,Object> policyDocument = new HashMap<>();

    public void addStatement(String res){
        policyDocument.put("Version","2012-10-17");
        Statement stmt = new Statement();
        stmt.Action="execute-api:Invoke";
        stmt.Effect="Deny";
        stmt.Resource=res;

        List<Statement> list = new ArrayList<>();
        list.add(stmt);

        policyDocument.put("Statement",list);
    }

}
