package lillian.neural.service.serviceMatcher;

import lillian.neural.service.serviceBean.ReqMeta;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public interface IMatcher {
    public Integer isMatch(ReqMeta[] postMetaArr, String query);
}
