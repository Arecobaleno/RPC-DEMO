package org.example.client.proxy;

import org.example.client.annotation.RemoteInvoke;
import org.example.client.core.TcpClient;
import org.example.client.param.ClientRequest;
import org.example.client.param.Response;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理
 */
@Component
public class InvokeProxy implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(RemoteInvoke.class)){
                field.setAccessible(true);

                final Map<Method, Class> methodClassMap = new HashMap<>();
                putMethodClass(methodClassMap,field);
                Enhancer enhancer = new Enhancer();
                enhancer.setInterfaces(new Class[]{field.getType()});
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        //采用netty客户端调用服务器
                        ClientRequest request = new ClientRequest();
                        request.setCommand(methodClassMap.get(method).getName()+"."+method.getName());
                        request.setContent(args[0]);
                        Response resp = TcpClient.send(request);

                        return resp;
                    }
                });

                try {
                    field.set(bean,enhancer.create());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 将属性的所有方法和属性的Class匹配起来
     * @param methodClassMap
     * @param field
     */
    private void putMethodClass(Map<Method,Class> methodClassMap, Field field){
        Method[] methods=field.getType().getDeclaredMethods();
        for(Method m:methods){
            methodClassMap.put(m,field.getType());
        }
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
