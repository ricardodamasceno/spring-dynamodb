package com.br.dynamo.controller;

import com.br.dynamo.service.UserService;
import com.br.dynamo.vo.request.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public void getUsers(@RequestHeader String name, @RequestHeader String email,
                         @RequestHeader String age, @RequestHeader String cpf){

        userService.getUsers(new UserRequestVO(name, email, age, cpf));
    }

}
