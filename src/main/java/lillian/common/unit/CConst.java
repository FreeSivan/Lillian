package lillian.common.unit;

import java.net.URLDecoder;

/**
 * Created by xiwen.yxw on 2016/11/7.
 */
public class CConst {
    public final static String PATH_CONFIGURE = URLDecoder.decode(ClassLoader.getSystemResource("configure/example.xml").getPath());

    public final static String REQUEST = "request";

    public final static String RESPONSE = "response";

    public final static String STYLE = "style";

    public final static String RANDOM = "random";

    public final static String SINGLE = "single";

    public final static String FUNCTION = "function";

    public final static String FUNC_NAME = "name";
}
