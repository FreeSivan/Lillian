package lillian.neural.build.buildXml.impl;

import lillian.common.unit.CConst;
import lillian.neural.build.buildBean.impl.ReqBean;
import lillian.neural.build.buildBean.impl.ResBean;
import lillian.neural.build.buildBean.impl.SecBean;
import lillian.neural.build.buildXml.IXmlParse;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public class SecParse implements IXmlParse<SecBean> {

    private IXmlParse request;

    private IXmlParse response;

    @Override
    public SecBean parse(Element element) {
        SecBean secBean = new SecBean();
        List<Element> lst = element.elements();
        for (Element item : lst) {
            if (CConst.REQUEST.equals(item.getName())) {
                ReqBean reqBean = (ReqBean)request.parse(item);
                secBean.setReqBean(reqBean);
            }
            else if(CConst.RESPONSE.equals(item.getName())) {
                ResBean resBean = (ResBean)response.parse(item);
                secBean.setResBean(resBean);
            }
        }
        return secBean;
    }


    public void setRequest(IXmlParse request) {
        this.request = request;
    }

    public void setResponse(IXmlParse response) {
        this.response = response;
    }
}
