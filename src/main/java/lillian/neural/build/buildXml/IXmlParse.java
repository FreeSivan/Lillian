package lillian.neural.build.buildXml;

import org.dom4j.Element;

/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public interface IXmlParse<T> {
    public T parse(Element element);
}
