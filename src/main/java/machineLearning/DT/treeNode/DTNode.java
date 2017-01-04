package machineLearning.DT.treeNode;

import machineLearning.DT.sampleNode.Sample;

import java.util.*;

/**
 * Created by xiwen.yxw on 2016/12/21.
 */
public class DTNode {

    /**
     * 当前节点所属的样本集合
     */
    private List<Sample> samples;

    /**
     * 当前阶段所属的属性集合
     */
    private List<String> attributes;

    /**
     *
     */
    private Map<Integer, DTNode> sonNode;

    /**
     *
     */
    private String attr;

    /**
     *
     */
    private Integer result;

    public void train() {
        // 先判断当前训练样本是否是同一归类
        boolean isEnd = judgeSample();
        // 是同一归类
        if (isEnd) {
            result = samples.get(0).getResult();
            return;
        }
        // 获取分类能力最强的属性
        attr = getFlagAttr();
        // 分类
        Map<Integer, List<Sample>> classify = classification();
        // 构造子节点
        buildSonNode(classify);
    }

    /**
     * 根据分类好的训练样本，构造子节点
     * @param classify
     */
    private void buildSonNode(Map<Integer, List<Sample>> classify) {
        if (sonNode == null) {
            sonNode = new HashMap<Integer, DTNode>();
        }
        sonNode.clear();
        attributes.remove(attr);
        for (Integer key : classify.keySet()) {
            DTNode son = new DTNode();
            son.setAttributes(attributes);
            son.setSamples(classify.get(key));
            sonNode.put(key, son);
        }
    }

    private Map<Integer, List<Sample>> classification() {
        Map<Integer, List<Sample>> classify = new HashMap<Integer, List<Sample>>();
        for (Sample sample : samples) {
            Integer result = sample.getResult();
            List<Sample> samLst = classify.get(result);
            if (samLst == null) {
                samLst = new ArrayList<Sample>();
            }
            samLst.add(sample);
        }
        return classify;
    }

    private String getFlagAttr() {
        return null;
    }

    private boolean judgeSample() {
        Set<Integer> samSet = new HashSet<Integer>();
        for (Sample sample : samples) {
            if (samSet.size() == 0) {
                samSet.add(sample.getResult());
                continue;
            }
            if (!samSet.contains(sample.getResult())) {
                return false;
            }
        }
        return true;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
