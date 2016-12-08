package mathineLearning.ann.neuron.impl;

import mathineLearning.ann.neuron.IThreshold;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class IdentityThreshold implements IThreshold {
    @Override
    public double activate(double x) {
        return x;
    }

    @Override
    public double derivative(double x) {
        return 1;
    }
}
