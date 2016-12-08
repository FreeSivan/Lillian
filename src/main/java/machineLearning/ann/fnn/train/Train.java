package machineLearning.ann.fnn.train;

import machineLearning.ann.fnn.frame.FnnLayer;
import machineLearning.ann.fnn.frame.FnnNet;
import machineLearning.ann.fnn.frame.FnnNode;
import machineLearning.ann.neuron.IThreshold;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class Train {
    /**
     * 神经网络
     */
    private FnnNet fnnNet;

    private double tiny;

    /**
     *
     * @param input
     * @param output
     */
    public void trainSingle(List<Double> input, List<Double> output) {
        // 将输入通过神经网络
        fnnNet.inputToOutput(input);
        int flag = 0;
        List<FnnLayer> fnnLayers = fnnNet.getLayerList();
        FnnLayer outputLayer = fnnLayers.get(fnnLayers.size()-1);
        trainOutLayer(outputLayer, output);
        FnnLayer nextLayer = outputLayer;
        for (int i = fnnLayers.size()-2; i >= 0; --i) {
            FnnLayer curLayer = fnnLayers.get(i);
            trainNormal(curLayer, nextLayer);
        }
    }

    private void trainNormal(FnnLayer curLayer, FnnLayer nextLayer) {
        List<FnnNode> curLst = curLayer.getNodeList();
        List<FnnNode> nxtLst = nextLayer.getNodeList();
        for (int i = 0; i < curLst.size(); ++i) {
            FnnNode fnnNode = curLst.get(i);
            List<Double> input = fnnNode.getCurInput();
            List<Double> weight = fnnNode.getWeightLst();
            Double curOutput = fnnNode.getCurOutput();
            IThreshold thrFunc = fnnNode.getThrFunc();
            Double derOutput = thrFunc.derivative(curOutput);
            double nxtDlt = 0;
            for (int j = 0; j < nxtLst.size(); ++j) {
                FnnNode nxtNode = nxtLst.get(i);
                List<Double> nxtWeight = nxtNode.getWeightLst();
                Double curDlt = nxtNode.getDltValue();
                nxtDlt += curDlt * nxtWeight.get(i);
            }
            double dlt = derOutput * nxtDlt;
            fnnNode.setDltValue(dlt);
            for (int j = 0; j < weight.size(); ++j) {
                double proWeight = weight.get(j);
                double curWeight = dlt * input.get(j) * tiny + proWeight;
                weight.set(j, curWeight);
            }
            fnnNode.setWeightLst(weight);
        }
    }

    private void trainOutLayer(FnnLayer outputLayer, List<Double> output) {
        List<FnnNode> nodeList = outputLayer.getNodeList();
        for (int i = 0; i < nodeList.size(); ++i) {
            FnnNode fnnNode = nodeList.get(i);
            List<Double> input = fnnNode.getCurInput();
            List<Double> weight = fnnNode.getWeightLst();
            Double curOutput = fnnNode.getCurOutput();
            IThreshold thrFunc = fnnNode.getThrFunc();
            Double derOutput = thrFunc.derivative(curOutput);
            // 计算并保存误差项
            double dlt = derOutput * (output.get(i)-curOutput);
            fnnNode.setDltValue(dlt);
            for (int j = 0; j < weight.size(); ++j) {
                double proWeight = weight.get(j);
                double curWeight = dlt * input.get(j) * tiny + proWeight;
                weight.set(j, curWeight);
            }
            fnnNode.setWeightLst(weight);
        }
    }

    public FnnNet getFnnNet() {
        return fnnNet;
    }

    public void setFnnNet(FnnNet fnnNet) {
        this.fnnNet = fnnNet;
    }

    public void setTiny(double tiny) {
        this.tiny = tiny;
    }
}
