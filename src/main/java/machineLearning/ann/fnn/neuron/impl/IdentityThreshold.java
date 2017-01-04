package machineLearning.ann.fnn.neuron.impl;

import machineLearning.ann.fnn.neuron.IThreshold;

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
