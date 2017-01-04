package machineLearning.DT.sampleNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/12/21.
 */
public class Sample {

    private Map<String, Integer> attributeMap;

    private Integer result;

    public Sample() {
        attributeMap = new HashMap<String, Integer>();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Map<String, Integer> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, Integer> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public Integer getAttribute(String tag) {
        return this.attributeMap.get(tag);
    }
}
