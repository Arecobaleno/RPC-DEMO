package org.example.service;

import com.alibaba.fastjson.JSONObject;
import org.example.client.annotation.RemoteInvoke;
import org.example.user.model.User;
import org.example.user.remote.UserRemote;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    @RemoteInvoke
    private UserRemote userRemote;

    public void testServerUser(){
        User u = new User();
        u.setId(1);
        u.setName("czy");
        Object response = userRemote.saveUser(u);
        System.out.println(JSONObject.toJSONString(response));
    }
}
