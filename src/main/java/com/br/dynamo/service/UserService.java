package com.br.dynamo.service;

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

}
