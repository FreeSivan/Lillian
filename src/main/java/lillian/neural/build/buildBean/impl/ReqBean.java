package lillian.neural.build.buildBean.impl;

import lillian.neural.build.buildBean.IBaseBean;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class ReqBean implements IBaseBean{

    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public void showSelf() {
        System.out.println("request = " + getRequest());
    }
}
