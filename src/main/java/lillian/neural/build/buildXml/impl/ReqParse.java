package lillian.neural.build.buildXml.impl;

import lillian.neural.build.buildBean.impl.ReqBean;
import lillian.neural.build.buildXml.IXmlParse;
import org.dom4j.Element;

/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public class ReqParse implements IXmlParse<ReqBean> {

    @Override
    public ReqBean parse(Element element) {
        ReqBean reqBean = new ReqBean();
        reqBean.setRequest(element.getTextTrim());
        return reqBean;
    }

}
