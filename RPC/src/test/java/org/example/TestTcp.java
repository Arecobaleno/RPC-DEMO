//package org.example;
//
//import org.example.client.ClientRequest;
//import org.example.util.Response;
//import org.example.client.TcpClient;
//import org.example.user.bean.User;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestTcp {
//    @Test
//    public void testGetResponse() {
//        ClientRequest request = new ClientRequest();
//        request.setContent("测试Tcp长连接");
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//
//    @Test
//    public void testGetSaveUser() {
//        ClientRequest request = new ClientRequest();
//        User u = new User();
//        u.setId(1);
//        u.setName("张三");
//        request.setCommand("org.example.user.controller.UserController.saveUser");
//        request.setContent(u);
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//
//    @Test
//    public void testGetSaveUsers() {
//        ClientRequest request = new ClientRequest();
//        List<User> users = new ArrayList<>();
//        User u = new User();
//        u.setId(2);
//        u.setName("李三");
//        users.add(u);
//        request.setCommand("org.example.user.controller.UserController.saveUsers");
//        request.setContent(users);
//        Response resp = TcpClient.send(request);
//        System.out.println(resp.getResult());
//    }
//}
