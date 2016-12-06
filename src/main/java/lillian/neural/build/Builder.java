package lillian.neural.build;

import lillian.common.spring.BaseTest;
import lillian.common.unit.CConst;
import lillian.neural.build.buildBean.impl.ConBean;
import lillian.neural.build.buildData.ITableBuild;
import lillian.neural.build.buildXml.impl.ConParse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public class Builder {

    /**
     *
     */
    private ConParse conParse;

    /**
     *
     */
    private ITableBuild invertedBuild;

    private ITableBuild positiveBuild;

    /**
     *
     * @param filePath
     * @return
     */
    public ConBean buildMemery(String filePath) {
        ConBean conBean = null;
        File file = new File(filePath);
        if (file == null) {
            return conBean;
        }
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element root = document.getRootElement();
            conBean = getConParse().parse(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return conBean;
    }

    /**
     *
     * @param conBean
     * @param dumpPath
     */
    public void buildInvertedTable(ConBean conBean, String dumpPath) {
        invertedBuild.buildTable(conBean, dumpPath);
    }

    public void buildPositiveTable(ConBean conBean, String dumpPath) {
        positiveBuild.buildTable(conBean, dumpPath);
    }

    public void setConParse(ConParse conParse) {
        this.conParse = conParse;
    }

    public ConParse getConParse() {
        return conParse;
    }


    public void setInvertedBuild(ITableBuild invertedBuild) {
        this.invertedBuild = invertedBuild;
    }

    public void setPositiveBuild(ITableBuild positiveBuild) {
        this.positiveBuild = positiveBuild;
    }
}
