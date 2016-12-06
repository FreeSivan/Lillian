package lillian.neural.build.buildXml.impl;

import lillian.common.unit.CConst;
import lillian.neural.build.buildBean.impl.ResBean;
import lillian.neural.build.buildXml.IXmlParse;
import org.dom4j.Element;
import org.dom4j.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public class ResParse implements IXmlParse<ResBean> {

    @Override
    public ResBean parse(Element element) {
        Attribute attribute = element.attribute(CConst.STYLE);
        String style = attribute.getValue().trim();
        if (CConst.FUNCTION.equals(style)) {
            return buildFunction(element);
        }
        else if (CConst.RANDOM.equals(style)) {
            return buildRandom(element);
        }
        else {
            return buildSingle(element);
        }
    }

    private ResBean buildSingle(Element element) {
        ResBean resBean = new ResBean();
        Element single = element.element(CConst.SINGLE);
        resBean.setStyle(ResBean.Style.SINGLE);
        resBean.setSingleRes(single.getTextTrim());
        return resBean;
    }

    private ResBean buildRandom(Element element) {
        ResBean resBean = new ResBean();
        List<Element> elements = element.elements();
        List<String> randoms = new ArrayList<String>();
        for (Element item : elements) {
            randoms.add(item.getTextTrim());
        }
        resBean.setStyle(ResBean.Style.RANDOM);
        resBean.setRandomRes(randoms);
        return resBean;
    }

    private ResBean buildFunction(Element element) {
        ResBean resBean = new ResBean();
        Element function = element.element(CConst.FUNCTION);
        resBean.setFunctionName(function.getTextTrim());
        resBean.setStyle(ResBean.Style.FUNCTION);
        return resBean;
    }

}
