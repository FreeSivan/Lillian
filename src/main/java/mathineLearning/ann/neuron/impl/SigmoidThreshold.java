package mathineLearning.ann.neuron.impl;


import mathineLearning.ann.neuron.IThreshold;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class SigmoidThreshold implements IThreshold {
    @Override
    public double activate(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    @Override
    public double derivative(double x) {
        double act = activate(x);
        return act * (1 - act);
    }
}
