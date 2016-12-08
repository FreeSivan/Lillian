package machineLearning.ann.neuron.impl;


import machineLearning.ann.neuron.IThreshold;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public class TangentThreshold implements IThreshold {
    @Override
    public double activate(double x) {
        return Math.tanh(x);
    }

    @Override
    public double derivative(double x) {
        double coshX = Math.cosh(x);
        double denOm = (Math.cosh(2*x) + 1);
        return 4*coshX*coshX / (denOm*denOm);
    }
}
