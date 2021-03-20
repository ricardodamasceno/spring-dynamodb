package com.br.dynamo.repository;

import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.br.dynamo.vo.request.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Iterator;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDB dynamoDB;

    public void getUsers(UserRequestVO request){

        Table table = dynamoDB.getTable("USER_TABLE");

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression(buildQuery(request))
                .withValueMap(buildParams(request));

        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();

        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toJSONPretty());
        }

    }

    private ValueMap buildParams(UserRequestVO request){
        ValueMap map = new ValueMap();

        if(StringUtils.hasText(request.getEmail())){
            map.withString(":key_email", request.getEmail());
        }
        if(StringUtils.hasText(request.getName())){
            map.withString(":key_name", request.getName());
        }
        return map;
    }

    private String buildQuery(UserRequestVO request){

        StringBuilder query = new StringBuilder();

        if(StringUtils.hasText(request.getEmail())){
            query.append("user_email = :key_email ");
        }
        if(StringUtils.hasText(request.getName())){
            query.append("and user_name = :key_name");
        }
        return query.toString();
    }

}
