package lillian.neural.service.servicePositive;

import lillian.neural.service.serviceBean.ReqMeta;
import lillian.neural.service.serviceBean.ResMeta;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public interface IPositive {
    /**
     *
     * @param docId
     * @return
     */
    public ReqMeta[] searchReq(Integer[] docId);

    /**
     *
     * @param docId
     * @return
     */
    public ResMeta searchResp(Integer docId);

    /**
     *
     * @param path
     */
    public void load(String path);

}
