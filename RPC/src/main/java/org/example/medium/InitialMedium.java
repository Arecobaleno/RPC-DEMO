package org.example.medium;

import org.example.annotation.Remote;
import org.example.annotation.RemoteInvoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

@Component
public class InitialMedium implements BeanPostProcessor { //所有的 Bean 初始化后执行方法
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("进来");
        if (bean.getClass().isAnnotationPresent(Remote.class)){ //判断类是否有controller注解
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method m:methods){
                String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
                System.out.println(key);
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(m);
                Media.beanMap.put(key, beanMethod);
            }

        }
        return bean;
    }
}
