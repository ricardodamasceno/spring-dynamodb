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

    public void saveUser(Item item){
        Table table = dynamoDB.getTable("USER_TABLE");
        table.putItem(item);
    }

    public void getUsers(UserRequestVO request){

        Table table = dynamoDB.getTable("USER_TABLE");

        ValueMap params = new ValueMap();

        QueryFilter queryFilter = new QueryFilter("user_age");
        queryFilter.eq(request.getAge());

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression(buildQuery(request, params))
                .withFilterExpression(buildFilter(request, params))
                .withValueMap(params);

        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();

        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toJSONPretty());
        }

    }

    private String buildQuery(UserRequestVO request, ValueMap params){

        StringBuilder query = new StringBuilder();

        if(StringUtils.hasText(request.getGender())){
            query.append("user_gender = :key_gender");
            params.withString(":key_gender", request.getGender());
        }
        if(StringUtils.hasText(request.getName())){
            query.append(" and user_name = :key_name");
            params.withString(":key_name", request.getName());
        }
        return StringUtils.hasText(query) ? query.toString() : null;
    }

    private String buildFilter(UserRequestVO request, ValueMap params){
        StringBuilder filter = new StringBuilder();

        if(StringUtils.hasText(request.getEmail())){
            filter.append("user_email = :key_email");
            params.withString(":key_email", request.getEmail());
        }
        if(StringUtils.hasText(request.getAge())){
            insertAnd(filter);
            filter.append("user_age = :key_age");
            params.withString(":key_age", request.getAge());
        }
        if(StringUtils.hasText(request.getCpf())){
            insertAnd(filter);
            filter.append("user_cpf = :key_cpf");
            params.withString(":key_cpf", request.getCpf());
        }
        return StringUtils.hasText(filter) ? filter.toString() : null;
    }

    private void insertAnd(StringBuilder value){
        if (StringUtils.hasText(value)){
            value.append(" and ");
        }
    }

}
