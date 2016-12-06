package lillian;

import lillian.common.spring.BaseTest;
import lillian.common.unit.CConst;
import lillian.neural.build.Builder;
import lillian.neural.build.buildBean.impl.ConBean;
import lillian.neural.service.Service;


/**
 * Created by xiwen.yxw on 2016/11/6.
 */
public class Lillian {
    public static void main(String[] args) {
        Builder builder = (Builder) BaseTest.getAc().getBean("builder");
        Service service = (Service) BaseTest.getAc().getBean("service");
        ConBean conBean = builder.buildMemery(CConst.PATH_CONFIGURE);
        String invertedPath = "D:\\code\\myself\\lillian\\data\\inverted.dat";
        String positivePath = "D:\\code\\myself\\lillian\\data\\positive.dat";
        String query = "推荐:1111";
        builder.buildInvertedTable(conBean, invertedPath);
        builder.buildPositiveTable(conBean, positivePath);
        service.load(invertedPath, positivePath);
        String response = service.query(query);
        System.out.println("response = " + response);
    }
}
