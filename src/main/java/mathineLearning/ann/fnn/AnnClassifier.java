package mathineLearning.ann.fnn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/30.
 */
public class AnnClassifier {

    private int inputCount;

    private int hiddenCount;

    private int outputCount;

    private List<BpNode> inputNodes;

    private List<BpNode> hiddenNodes;

    private List<BpNode> outputNodes;

    private float[][] inputHiddenWeight;
    private float[][] hiddenOutputWeight;

    private List<DataNode> trainNodes;

    public AnnClassifier(int inputCount, int hiddenCount, int outputCount) {
        trainNodes = new ArrayList<DataNode>();
        this.inputCount = inputCount;
        this.hiddenCount = hiddenCount;
        this.outputCount = outputCount;
        this.inputNodes = new ArrayList<BpNode>();
        this.outputNodes = new ArrayList<BpNode>();
        this.hiddenNodes = new ArrayList<BpNode>();
        inputHiddenWeight = new float[inputCount][hiddenCount];
        hiddenOutputWeight = new float[hiddenCount][outputCount];
    }

    public void train(float eta, int n) {
        reset();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < trainNodes.size(); ++j) {
                forward(trainNodes.get(j).getAttributeList());
                backward(trainNodes.get(j).getType());
                updateWeight(eta);
            }
            System.out.println("n = " + i);
        }
    }

    private void updateWeight(float eta) {
        // 更新输入到隐藏层的权重矩阵
        for (int i = 0; i < inputCount; ++i) {
            for (int j = 0; j < hiddenCount; ++j) {
                float inputVal = inputNodes.get(i).getForwardOutputValue();
                float hiddenVal = hiddenNodes.get(i).getBackwardOutputValue();
                inputHiddenWeight[i][j] -= eta * inputVal * hiddenVal;
            }
        }
        // 更新隐藏层到输出层的权重矩阵
        for (int i = 0; i < hiddenCount; ++i) {
            for (int j = 0; j < outputCount; ++j) {
                float hiddenVal = hiddenNodes.get(i).getForwardOutputValue();
                float outputVal = outputNodes.get(j).getBackwardOutputValue();
                hiddenOutputWeight[i][j] -= eta * hiddenVal * outputVal;
            }
        }
    }

    private void backward(int type) {
        // 输出层
        for(int j = 0; j < outputCount; ++j) {
            float result = -1;
            if (j == type) {
                result = 1;
            }
            float tmp = outputNodes.get(j).getForwardOutputValue() - result;
            outputNodes.get(j).setBackwardInputValue(tmp);
        }

        // 隐藏层
        for (int j = 0; j < hiddenCount; ++j) {
            float tmp = 0;
            for (int k = 0; k < outputCount; ++k) {
                tmp += hiddenOutputWeight[j][k]*outputNodes.get(k).getBackwardOutputValue();
            }
            hiddenNodes.get(j).setBackwardInputValue(tmp);
        }
    }

    private void forward(List<Float> attributeList) {
        // 输入层
        for (int k = 0; k < attributeList.size(); ++k) {
            inputNodes.get(k).setForwardInputValue(attributeList.get(k));
        }
        // 隐藏层
        for (int j = 0; j < hiddenCount; ++j) {
            float tmp = 0;
            for (int k = 0; k < inputCount; ++k) {
                tmp += inputHiddenWeight[k][j] * inputNodes.get(k).getForwardOutputValue();
            }
            hiddenNodes.get(j).setForwardInputValue(tmp);
        }
        // 输出层
        for (int j = 0; j < outputCount; ++j) {
            float tmp = 0;
            for (int k = 0; k < hiddenCount; ++k) {
                tmp += hiddenOutputWeight[k][j]*hiddenNodes.get(k).getForwardOutputValue();
            }
            outputNodes.get(j).setForwardInputValue(tmp);
        }
    }

    private void reset() {
        inputNodes.clear();
        hiddenNodes.clear();
        outputNodes.clear();

        for (int i = 0; i < inputCount; ++i) {
            inputNodes.add(new BpNode(BpNode.TYPE_INPUT));
        }
        for (int i = 0; i < outputCount; ++i) {
            outputNodes.add(new BpNode(BpNode.TYPE_OUTPUT));
        }
        for (int i = 0; i < hiddenCount; ++i) {
            outputNodes.add(new BpNode(BpNode.TYPE_OUTPUT));
        }

        for (int i = 0; i < inputCount; ++i) {
            for (int j = 0; j < hiddenCount; ++j) {
                inputHiddenWeight[i][j] = (float)(Math.random()*0.1);
            }
        }
        for (int i = 0;  i < hiddenCount; ++i) {
            for (int j = 0; j < outputCount; ++j) {
                hiddenOutputWeight[i][j] = (float)(Math.random()*0.1);
            }
        }
    }

    public int getInputCount() {
        return inputCount;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public int getHiddenCount() {
        return hiddenCount;
    }

    public void setHiddenCount(int hiddenCount) {
        this.hiddenCount = hiddenCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(int outputCount) {
        this.outputCount = outputCount;
    }

    public List<BpNode> getInputNodes() {
        return inputNodes;
    }

    public void setInputNodes(List<BpNode> inputNodes) {
        this.inputNodes = inputNodes;
    }

    public List<BpNode> getHiddenNodes() {
        return hiddenNodes;
    }

    public void setHiddenNodes(List<BpNode> hiddenNodes) {
        this.hiddenNodes = hiddenNodes;
    }

    public List<BpNode> getOutputNodes() {
        return outputNodes;
    }

    public void setOutputNodes(List<BpNode> outputNodes) {
        this.outputNodes = outputNodes;
    }

    public List<DataNode> getTrainNodes() {
        return trainNodes;
    }

    public void setTrainNodes(List<DataNode> trainNodes) {
        this.trainNodes = trainNodes;
    }
}
