package mathineLearning.ann.util;

/**
 * Created by xiwen.yxw on 2016/12/6.
 */
public class AbstractUnit implements IUnit{

    private double yIta;

    private double[] weight;

    public Integer perception(double[] x) {
        double sum = 0;
        if (weight.length != x.length) {
            return null;
        }
        for (int i = 0; i < weight.length; ++i)  {
            sum += weight[i] * x[i];
        }
        return sum > 0 ? 1 : -1;
    }

    private double sigmoid(double value) {
        return 1 / (1 + Math.pow(Math.E, value));
    }

    public void trainSingle(double[] x, int t) {
        Integer o = perception(x);
        if (o == null) {
            return;
        }
        for (int i = 0; i < weight.length; ++i) {
            weight[i] = yIta * (t - o) * x[i];
        }
    }

    public void load() {

    }

    @Override
    public void setWeight(double[] weight) {
        this.weight = weight;
    }

    @Override
    public void setyIta(double yIta) {
        this.yIta = yIta;
    }

    @Override
    public Integer count(double[] x) {
        return null;
    }

    @Override
    public void train(double[] x, int t) {

    }
}
