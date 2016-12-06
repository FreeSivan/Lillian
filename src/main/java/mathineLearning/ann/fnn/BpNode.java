package mathineLearning.ann.fnn;

/**
 * Created by xiwen.yxw on 2016/11/26.
 */
public class BpNode {

    public static final int TYPE_INPUT = 0;
    public static final int TYPE_HIDDEN = 1;
    public static final int TYPE_OUTPUT = 2;

    private int type;

    private float mForwardInputValue;
    private float mForwardOutputValue;

    private float mBackwardInputValue;
    private float mBackwardOutputValue;

    public BpNode() {}

    public BpNode(int type) {
        this.type = type;
    }

    /**
     * tan-sigmoid函数
     *
     * @param in
     * @return
     */
    private float tanSigmoid(float in) {
        return (float) ((Math.exp(in) - Math.exp(-in)) / (Math.exp(in) + Math.exp(-in)));
    }
    /**
     * sigmoid函数，这里用tan-sigmoid，经测试其效果比log-sigmoid好！
     *
     * @param in
     * @return
     */
    private float forwardSigmoid(float in) {
        switch (type) {
            case TYPE_INPUT:
                return in;
            case TYPE_HIDDEN:
            case TYPE_OUTPUT:
                return tanSigmoid(in);
            default:
                return 0;
        }
    }
    /**
     * tan-sigmoid函数的导数
     *
     * @param in
     * @return
     */
    private float tanhSDerivative(float in) {
        return (float) ((1 - Math.pow(mForwardOutputValue, 2)) * in);
    }

    /**
     * 误差反向传播时，激活函数的导数
     *
     * @param in
     * @return
     */
    private float backwardPropagate(float in) {
        switch (type) {
            case TYPE_INPUT:
                return in;
            case TYPE_HIDDEN:
            case TYPE_OUTPUT:
                return tanhSDerivative(in);
            default:
                return 0;
        }
    }

    public float getForwardInputValue() {
        return mForwardInputValue;
    }

    public float getForwardOutputValue() {
        return mForwardOutputValue;
    }

    public void setForwardInputValue(float mInputValue) {
        this.mForwardInputValue = mInputValue;
        setForwardOutputValue(mInputValue);
    }

    private void setForwardOutputValue(float mInputValue) {
        this.mForwardOutputValue = forwardSigmoid(mInputValue);
    }

    public float getBackwardInputValue() {
        return mBackwardInputValue;
    }

    public void setBackwardInputValue(float mBackwardInputValue) {
        this.mBackwardInputValue = mBackwardInputValue;
        setBackwardOutputValue(mBackwardInputValue);
    }

    private void setBackwardOutputValue(float input) {
        this.mBackwardOutputValue = backwardPropagate(input);
    }

    public float getBackwardOutputValue() {
        return mBackwardOutputValue;
    }
}
