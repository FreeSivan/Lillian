package lillian.neural.build.buildXml.impl;

import lillian.neural.build.buildBean.impl.ConBean;
import lillian.neural.build.buildBean.impl.SecBean;
import lillian.neural.build.buildXml.IXmlParse;
import org.dom4j.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public class ConParse implements IXmlParse<ConBean> {

    private IXmlParse secParse;

    @Override
    public ConBean parse(Element element) {
        int index = 0;
        ConBean conBean = new ConBean();
        List<Element> childElements = element.elements();
        for (Element item : childElements) {
            SecBean secBean = (SecBean)getSecParse().parse(item);
            secBean.setDocId(index++);
            conBean.addSection(secBean);
        }
        return conBean;
    }

    public void setSecParse(IXmlParse secParse) {
        this.secParse = secParse;
    }

    public IXmlParse getSecParse() {
        return this.secParse;
    }
}
