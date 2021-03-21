package com.br.dynamo.controller;

import com.br.dynamo.service.UserService;
import com.br.dynamo.vo.request.UserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public void getUsers(@RequestHeader String name, @RequestHeader String email,
                         @RequestHeader String age, @RequestHeader String cpf,
                         @RequestHeader String gender){
        userService.getUsers(new UserRequestVO(gender, name, email, age, cpf));
    }

    @PostMapping
    public void saveUser(@RequestBody UserRequestVO request){
        userService.saveUser(request);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserRequestVO request){
        userService.deleteUser(request);
    }

}
