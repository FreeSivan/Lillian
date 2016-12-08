package machineLearning.ann.fnn.frame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/8.
 *
 */
public class FnnLayer {
    /**
     *
     */
    private List<FnnNode> nodeList;

    public List<Double> inputToOutput(List<Double> lst) {
        List<Double> result = new ArrayList<Double>();
        for (FnnNode fnnNode : nodeList) {
            Double tmp = fnnNode.inputToOutput(lst);
            if (tmp != null) {
                result.add(tmp);
            }
        }
        return result;
    }

    public List<FnnNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<FnnNode> nodeList) {
        this.nodeList = nodeList;
    }
}
