package lillian.neural.build.buildBean.impl;

import lillian.neural.build.buildBean.IBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class ConBean implements IBaseBean{

    private List<SecBean> sections = new ArrayList<SecBean>();

    public List<SecBean> getSections() {
        return sections;
    }

    public void addSection(SecBean secBean) {
        this.sections.add(secBean);
    }

    @Override
    public void showSelf() {
        System.out.println("===============in the container=============");
        for (SecBean secBean : sections) {
            secBean.showSelf();
        }
    }
}
