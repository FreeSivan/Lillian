package lillian.neural.service.serviceChoicer.impl;

import lillian.neural.service.serviceBean.ResMeta;
import lillian.neural.service.serviceChoicer.IChoicer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/26.
 */
public class FunctionChoicer implements IChoicer{
    @Override
    public String choiceResp(ResMeta resp, Map<String, String> context) {
        String funcName = resp.getFunctionName();
        int pos = funcName.lastIndexOf(".");
        String className = funcName.substring(0, pos);
        String methodName = funcName.substring(pos+1);
        System.out.println("class = " + className);
        System.out.println("method = " + methodName);
        String input = context.get("input");
        try {
            Class<?> clazz = Class.forName(className);
            Object object = clazz.newInstance();
            Method method = clazz.getMethod(methodName, String.class);
            String response = (String)method.invoke(object, input);
            return response;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
