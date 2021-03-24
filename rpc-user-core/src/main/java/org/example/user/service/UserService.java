package org.example.user.service;

import org.example.user.model.User;
import org.example.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public Object saveUser(User user){
        return new Response();
    }

    public Object saveList(List<User> users){
        return new Response();
    }
}
