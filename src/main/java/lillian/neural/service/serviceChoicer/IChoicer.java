package lillian.neural.service.serviceChoicer;

import lillian.neural.service.serviceBean.ResMeta;

import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public interface IChoicer {
    public String choiceResp(ResMeta resp, Map<String, String> context);
}
