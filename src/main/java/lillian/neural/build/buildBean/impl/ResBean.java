package lillian.neural.build.buildBean.impl;

import lillian.neural.build.buildBean.IBaseBean;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class ResBean implements IBaseBean{
    /**
     * 标识response的类型
     */
    private Style style;

    /**
     * 单一回复的回复内容
     */
    private String singleRes;

    /**
     * 随机回复的回复内容
     */
    private List<String> randomRes;

    /**
     * 函数调用的结构
     */
    private String functionName;

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getSingleRes() {
        return singleRes;
    }

    public void setSingleRes(String singleRes) {
        this.singleRes = singleRes;
    }

    public List<String> getRandomRes() {
        return randomRes;
    }

    public void setRandomRes(List<String> randomRes) {
        this.randomRes = randomRes;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public void showSelf() {
        if (style == Style.RANDOM) {
            System.out.println("random response = ");
            for (String res : randomRes) {
                System.out.println("   " + res);
            }
        }
        else if (style == Style.FUNCTION) {
            System.out.println("function response = ");
            System.out.println("    function name = " + getFunctionName());
        }
        else {
            System.out.println("single response = " + getSingleRes());
        }
    }

    public static enum Style {
        SINGLE, RANDOM, FUNCTION
    }
}
