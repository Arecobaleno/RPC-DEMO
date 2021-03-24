package org.example.medium;

import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.spi.activation.Server;
import org.example.Server.ServerRequest;
import org.example.util.Response;

import javax.jws.Oneway;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Media {
    public static Map<String, BeanMethod> beanMap;
    static {
        beanMap = new HashMap<>();
    }
    private static Media m=null;

    private Media(){
    }

    public static Media newInstance(){
        if(m==null){
            m = new Media();
        }
        return m;
    }

    // 反射处理业务
    public Response process(ServerRequest request) throws InvocationTargetException, IllegalAccessException {
        Response result = null;
        try {
            String command = request.getCommand();
            BeanMethod beanMethod = beanMap.get(command);
            if(beanMethod==null){
                return null;
            }

            Object bean = beanMethod.getBean();
            Method m = beanMethod.getMethod();
            Class paramType = m.getParameterTypes()[0];
            Object content = request.getContent();
            Object args = JSONObject.parseObject(JSONObject.toJSONString(content),paramType);
            result = (Response) m.invoke(bean,args);
            result.setId(request.getId());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

}
