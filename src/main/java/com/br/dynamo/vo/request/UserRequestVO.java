package com.br.dynamo.vo.request;

import lombok.Data;

@Data
public class UserRequestVO {

    private final String gender;
    private final String name;
    private final String email;
    private final String age;
    private final String cpf;
    private final String status;

}
