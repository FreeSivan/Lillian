package lillian.neural.service.serviceQparse;

import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/26.
 */
public interface IParse {
    public String parseQuery(String str, Map<String, String> context);
}
