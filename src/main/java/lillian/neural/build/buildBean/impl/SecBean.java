package lillian.neural.build.buildBean.impl;

import lillian.neural.build.buildBean.IBaseBean;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class SecBean implements IBaseBean{

    /**
     * 唯一标识section的ID
     */
    private Integer docId;

    /**
     * 请求相关数据
     */
    private ReqBean reqBean;

    /**
     * 相应相关数据
     */
    private ResBean resBean;

    public ReqBean getReqBean() {
        return reqBean;
    }

    public ResBean getResBean() {
        return resBean;
    }

    public void setReqBean(ReqBean reqBean) {
        this.reqBean = reqBean;
    }

    public void setResBean(ResBean resBean) {
        this.resBean = resBean;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    @Override
    public void showSelf() {
        System.out.println("docId = " + getDocId());
        getReqBean().showSelf();
        getResBean().showSelf();
    }
}
