//package org.example;
//
//import org.example.annotation.RemoteInvoke;
//import org.example.user.bean.User;
//import org.example.user.remote.UserRemote;
//import org.example.util.Response;
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
//        userRemote.saveUser(u);
//    }
//}
