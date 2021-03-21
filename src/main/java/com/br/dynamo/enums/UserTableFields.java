package com.br.dynamo.enums;

import lombok.Getter;

@Getter
public enum UserTableFields {

    USER_TABLE_NAME("USER_TABLE"),
    USER_GENDER("user_gender"),
    USER_NAME("user_name"),
    USER_AGE("user_age"),
    USER_CPF("user_cpf"),
    USER_EMAIL("user_email");

    UserTableFields(String value){
        this.value = value;
    }

    private String value;

}
