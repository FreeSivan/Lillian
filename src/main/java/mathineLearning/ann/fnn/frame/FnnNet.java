package mathineLearning.ann.fnn.frame;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class FnnNet {
    /**
     *
     */
    private List<FnnLayer> layerList;

    public List<Double> inputToOutput(List<Double> lst) {
        List<Double> input = lst;
        List<Double> output;
        for (FnnLayer fnnLayer : layerList) {
            output = fnnLayer.inputToOutput(input);
            input = output;
        }
        output = input;
        return output;
    }

    public List<FnnLayer> getLayerList() {
        return layerList;
    }

    public void setLayerList(List<FnnLayer> layerList) {
        this.layerList = layerList;
    }
}
