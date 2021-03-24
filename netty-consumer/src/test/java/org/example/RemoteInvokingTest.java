//package org.example;
//
//import com.alibaba.fastjson.JSONObject;
//import org.example.client.annotation.RemoteInvoke;
//import org.example.client.param.Response;
//import org.example.user.bean.User;
//import org.example.user.remote.UserRemote;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RemoteInvokingTest.class)
//@ComponentScan("org.example")
//public class RemoteInvokingTest {
//    @RemoteInvoke
//    private UserRemote userRemote;
//
//    @Test
//    public void testSaveUser(){
//        User u = new User();
//        u.setId(1);
//        u.setName("张三");
//        Response response = userRemote.saveUser(u);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//}
