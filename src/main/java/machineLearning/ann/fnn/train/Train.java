package machineLearning.ann.fnn.train;

import machineLearning.ann.fnn.frame.FnnLayer;
import machineLearning.ann.fnn.frame.FnnNet;
import machineLearning.ann.fnn.frame.FnnNode;
import machineLearning.ann.fnn.neuron.IThreshold;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class Train {
    /**
     * 神经网络
     */
    private FnnNet fnnNet;

    /**
     * 学习速率
     */
    private double tiny;

    /**
     * 取单条样本训练神经网络
     * @param input 单条样本的输入
     * @param output 单条样本的输出
     */
    public void trainSingle(List<Double> input, List<Double> output) {
        // 将输入通过神经网络
        fnnNet.inputToOutput(input);
        List<FnnLayer> fnnLayers = fnnNet.getLayerList();
        // 获取输出层
        FnnLayer outputLayer = fnnLayers.get(fnnLayers.size()-1);
        // 训练输出层的权重
        trainOutLayer(outputLayer, output);
        FnnLayer nextLayer = outputLayer;
        for (int i = fnnLayers.size()-2; i >= 0; --i) {
            FnnLayer curLayer = fnnLayers.get(i);
            trainNormal(curLayer, nextLayer);
            nextLayer = curLayer;
        }
    }

    /**
     * 训练非输出层
     * @param curLayer 当前层次
     * @param nextLayer 下一个层次
     */
    private void trainNormal(FnnLayer curLayer, FnnLayer nextLayer) {
        // 获取当前层次的所有节点
        List<FnnNode> curLst = curLayer.getNodeList();
        // 获取上一层次的所有节点
        List<FnnNode> nxtLst = nextLayer.getNodeList();
        // 迭代当前层的每一个节点
        for (int i = 0; i < curLst.size(); ++i) {
            // 取出当前节点
            FnnNode fnnNode = curLst.get(i);
            // 取出当前节点的输入
            List<Double> input = fnnNode.getCurInput();
            // 取出当前节点的权重
            List<Double> weight = fnnNode.getWeightLst();
            // 取出当前节点权重的加和
            Double curSum = fnnNode.getSumAdd();
            // 取出当前节点的阈值函数
            IThreshold thrFunc = fnnNode.getThrFunc();
            // 计算偏导数
            Double derOutput = thrFunc.derivative(curSum);
            double nxtDlt = 0;
            // 迭代每个上层节点
            for (int j = 0; j < nxtLst.size(); ++j) {
                FnnNode nxtNode = nxtLst.get(j);
                List<Double> nxtWeight = nxtNode.getWeightLst();
                Double curDlt = nxtNode.getDltValue();
                nxtDlt += curDlt * nxtWeight.get(i);
            }
            double dlt = derOutput * nxtDlt;
            fnnNode.setDltValue(dlt);
            for (int j = 0; j < weight.size(); ++j) {
                weight.set(j, dlt * input.get(j) * tiny + weight.get(j));
            }
            fnnNode.setWeightLst(weight);
        }
    }

    /**
     * 训练输出层
     * @param outputLayer 输出层
     * @param output 样本输出结果
     */
    private void trainOutLayer(FnnLayer outputLayer, List<Double> output) {
        // 获取输出层所有节点
        List<FnnNode> nodeList = outputLayer.getNodeList();
        // 迭代输出层的每个节点
        for (int i = 0; i < nodeList.size(); ++i) {
            // 取出当前输出节点
            FnnNode fnnNode = nodeList.get(i);
            // 取出当前节点的输入
            List<Double> input = fnnNode.getCurInput();
            // 取出节点当前的权值
            List<Double> weight = fnnNode.getWeightLst();
            // 取出节点对于当前输入的输出
            Double curOutput = fnnNode.getCurOutput();
            // 取出节点对于当前输入的加和
            Double curSum = fnnNode.getSumAdd();
            // 获取当前阈值函数
            IThreshold thrFunc = fnnNode.getThrFunc();
            // 计算阈值函数在curSum处的导数
            Double derOutput = thrFunc.derivative(curSum);
            // 计算并保存误差项
            double dlt = derOutput * (output.get(i)-curOutput);
            fnnNode.setDltValue(dlt);
            for (int j = 0; j < weight.size(); ++j) {
                weight.set(j, dlt * input.get(j) * tiny + weight.get(j));
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
