package machineLearning.ann.neuron;

/**
 * Created by xiwen.yxw on 2016/12/8.
 */
public interface IThreshold {
    /**
     *
     * @param x
     * @return
     */
    public double activate(double x);

    /**
     *
     * @param x
     * @return
     */
    public double derivative(double x);
}
