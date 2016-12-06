package mathineLearning.ann.fnn;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/30.
 */
public class DataNode {

    private List<Float> attributeList;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Float> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Float> attributeList) {
        this.attributeList = attributeList;
    }

}
