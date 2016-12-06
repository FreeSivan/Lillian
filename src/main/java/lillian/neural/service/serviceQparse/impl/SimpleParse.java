package lillian.neural.service.serviceQparse.impl;

import lillian.neural.service.serviceQparse.IParse;

import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/26.
 */
public class SimpleParse implements IParse{
    @Override
    public String parseQuery(String str, Map<String, String> context) {
        String[] strArr = str.split(":");
        System.out.println("length = " + strArr.length);
        if (strArr.length > 1) {
            context.put("input", strArr[1]);
        }
        return strArr[0];
    }
}
