package lillian.neural.service.serviceMatcher;

import lillian.neural.service.serviceBean.ReqMeta;

/**
 * Created by xiwen.yxw on 2016/11/14.
 * 不同短文本相似算法的实现
 */
public interface IMatcher {
    public Integer isMatch(ReqMeta[] postMetaArr, String query);
}
