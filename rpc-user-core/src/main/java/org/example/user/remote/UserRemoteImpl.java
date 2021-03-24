package org.example.user.remote;


import org.example.annotation.Remote;
import org.example.user.model.User;
import org.example.user.service.UserService;
import org.example.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.List;

@Remote
public class UserRemoteImpl implements UserRemote {
    @Resource
    private UserService userService;

    public Object saveUser(User user){
        userService.saveUser(user);
        return ResponseUtil.createSuccessResult(user);
    }

    public Object saveUsers(List<User> users){
        userService.saveList(users);
        return ResponseUtil.createSuccessResult(users);
    }
}
