package lillian.neural.service.serviceChoicer.impl;

import lillian.neural.service.serviceBean.ResMeta;
import lillian.neural.service.serviceChoicer.IChoicer;

import java.util.Map;
import java.util.Random;

/**
 * Created by xiwen.yxw on 2016/11/25.
 */
public class SimpleRChoicer implements IChoicer{
    @Override
    public String choiceResp(ResMeta resp, Map<String, String> context) {
        if (resp.getStyle() != 1) {
            return null;
        }
        long t = System.currentTimeMillis();
        Random rd = new Random(t);
        int index = Math.abs(rd.nextInt()) % resp.getRandomResponse().size();
        return resp.getRandomResponse().get(index);
    }
}
