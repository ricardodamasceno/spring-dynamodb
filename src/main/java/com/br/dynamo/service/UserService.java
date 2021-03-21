package com.br.dynamo.service;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.br.dynamo.repository.UserRepository;
import com.br.dynamo.vo.request.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void getUsers(UserRequestVO request){
        userRepository.getUsers(request);
    }

    public void saveUser(UserRequestVO request){

        Item item = new Item().withPrimaryKey("user_gender", request.getGender())
                .withString("user_name", request.getName())
                .withString("user_age", request.getAge())
                .withString("user_cpf", request.getCpf())
                .withString("user_email", request.getEmail());

        userRepository.saveUser(item);
    }

    public void deleteUser(UserRequestVO request){
        userRepository.deleteUser(request);
    }

}
