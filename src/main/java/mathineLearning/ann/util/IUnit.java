package mathineLearning.ann.util;

/**
 * Created by xiwen.yxw on 2016/12/6.
 */
public interface IUnit {
    public Integer count(double[] x);
    public void train(double[] x, int t);
    public void setWeight(double[] weight);

    public void setyIta(double yIta);
}
