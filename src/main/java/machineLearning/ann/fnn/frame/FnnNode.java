package machineLearning.ann.fnn.frame;

import machineLearning.ann.neuron.IThreshold;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/8.
 *
 * 神经网络的单元，内含阈值函数和权重向量
 */
public class FnnNode {
    /**
     * 阈值函数
     */
    private IThreshold thrFunc;

    /**
     * 权重向量
     */
    private List<Double> weightLst;

    /**
     * 当前的输入向量
     */
    private List<Double> curInput;

    /**
     * 当前输出值
     */
    private double curOutput;

    /**
     * 保存当前误差项
     */
    private double dltValue;

    /**
     * 保存当前加合
     */
    private double sumAdd;

    /**
     * 由输入值计算输出结果
     * @param input 输入向量
     * @return 输出结果
     */
    public Double inputToOutput(List<Double> input) {
        if (thrFunc== null || weightLst == null) {
            return null;
        }
        if (input.size() != weightLst.size()) {
            return null;
        }
        double result = 0;
        for (int i = 0; i < input.size(); ++i) {
            result += input.get(i) * weightLst.get(i);
        }
        setSumAdd(result);
        result = thrFunc.activate(result);
        // 保存当前输出
        setCurOutput(result);
        // 保存当前输入
        setCurInput(input);
        return result;
    }

    public Double getIndexWeight(int index) {
        if (index > weightLst.size()) {
            return null;
        }
        return weightLst.get(index);
    }

    public IThreshold getThrFunc() {
        return thrFunc;
    }

    public void setThrFunc(IThreshold thrFunc) {
        this.thrFunc = thrFunc;
    }

    public List<Double> getWeightLst() {
        return weightLst;
    }

    public void setWeightLst(List<Double> weightLst) {
        this.weightLst = weightLst;
    }

    public List<Double> getCurInput() {
        return curInput;
    }

    public void setCurInput(List<Double> curInput) {
        this.curInput = curInput;
    }

    public double getCurOutput() {
        return curOutput;
    }

    public void setCurOutput(double curOutput) {
        this.curOutput = curOutput;
    }

    public double getDltValue() {
        return dltValue;
    }

    public void setDltValue(double dltValue) {
        this.dltValue = dltValue;
    }

    public double getSumAdd() {
        return sumAdd;
    }

    public void setSumAdd(double sumAdd) {
        this.sumAdd = sumAdd;
    }
}
