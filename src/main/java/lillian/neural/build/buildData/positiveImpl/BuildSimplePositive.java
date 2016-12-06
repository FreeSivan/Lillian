package lillian.neural.build.buildData.positiveImpl;

import lillian.neural.build.buildBean.impl.ConBean;
import lillian.neural.build.buildBean.impl.ReqBean;
import lillian.neural.build.buildBean.impl.ResBean;
import lillian.neural.build.buildBean.impl.SecBean;
import lillian.neural.build.buildData.ITableBuild;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class BuildSimplePositive implements ITableBuild{
    @Override
    public void buildTable(ConBean data, String dumpPath) {
        try {
            List<SecBean> secList = data.getSections();
            FileWriter writer = new FileWriter(dumpPath);
            BufferedWriter bw = new BufferedWriter(writer);
            for (SecBean secBean : secList) {
                dump(secBean, bw);
            }
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dump(SecBean secBean, BufferedWriter bw) {
        try {
            bw.write(""+secBean.getDocId());
            bw.write("|");
            ReqBean reqBean = secBean.getReqBean();
            bw.write(reqBean.getRequest());
            bw.write("|");
            ResBean resBean = secBean.getResBean();
            if (resBean.getStyle() == ResBean.Style.SINGLE) {
                bw.write("0");
                bw.write("#");
                bw.write(resBean.getSingleRes());
            }
            else if (resBean.getStyle() == ResBean.Style.RANDOM) {
                bw.write("1");
                bw.write("#");
                for (String str : resBean.getRandomRes()) {
                    bw.write(str);
                    bw.write("^");
                }
            }
            else if (resBean.getStyle() == ResBean.Style.FUNCTION){
                bw.write("2");
                bw.write("#");
                bw.write(resBean.getFunctionName());
            }
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
