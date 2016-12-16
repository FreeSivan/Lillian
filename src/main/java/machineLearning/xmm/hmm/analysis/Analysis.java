package machineLearning.xmm.hmm.analysis;

import commonUtil.ansjWrap.AnsiWrap;
import machineLearning.xmm.hmm.commonbean.HmmData;
import machineLearning.xmm.hmm.inverted.InvertedList;
import machineLearning.xmm.hmm.trainer.ITrainer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class Analysis {

    private final int INIT_STATE = -1;

    private final int SKIP_STATE = -2;

    private final int BREAK_STATE = -3;

    private Map<Integer, Double> initMap = new HashMap<Integer, Double>();

    private Map<Integer, Map<String, Double>> outputMap = new HashMap<Integer, Map<String, Double>>();

    private Map<Integer, Map<Integer, Double>> transitionMap = new HashMap<Integer, Map<Integer, Double>>();

    private Map<String, List<Integer>> invertedList = new HashMap<String, List<Integer>>();

    private ITrainer initTrainer;

    private ITrainer outputTrainer;

    private ITrainer transitionTrainer;

    private InvertedList inverted;

    private AnsiWrap ansiWrap;


    public String[] viterbi(String text) {
        String[] words = ansiWrap.split(text);
        return viterbi(words);
    }

    public String[] viterbi(String[] words) {
        if (words.length == 0) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        int pro = INIT_STATE;
        MetaBean metaBean = new MetaBean();
        metaBean.state = INIT_STATE;
        Map<Integer, Double> rateMap = null;
        for (String item : words) {
            item = item.trim();
            if (metaBean.state == INIT_STATE) {
                metaBean = dealInit(item);
            }
            else {
                metaBean = dealNormal(rateMap, item);
            }
            if (metaBean.state == BREAK_STATE) {
                return null;
            }
            if (metaBean.state == SKIP_STATE) {
                result.add(item);
                continue;
            }
            String single = "("+item+"|"+metaBean.state+")";
            result.add(single);
            rateMap = metaBean.rateMap;
        }
        return result.toArray(new String[result.size()]);
    }

    private MetaBean dealNormal(Map<Integer, Double> rateMap, String item) {
        MetaBean metaBean = new MetaBean();
        // 如果词典中没有这个词，则跳过这个词执行下一个
        if (!invertedList.containsKey(item)) {
            metaBean.state = SKIP_STATE;
            return metaBean;
        }
        //取出所有的当前可选状态
        List<Integer> styleLst = invertedList.get(item);
        Map<Integer, Double> newRateMap = new HashMap<Integer, Double>();
        double max = 0.0;
        // 取出每一个前序状态，及对应的概率
        for (Integer proState : rateMap.keySet()) {
            // 取出选中的前一个状态存在的概率
            double proRate = rateMap.get(proState);
            // 如果前一个状态没有可转移的状态，则跳过该前序状态
            if (!transitionMap.containsKey(proState)) {
                continue;
            }
            // 取出该前序状态的状态转移表
            Map<Integer, Double> tranLst = transitionMap.get(proState);
            // 迭代每一个当前可选状态
            for (Integer cur : styleLst) {
                // 取出前一状态到当前状态的转移概率
                // 如果转移概率为0，则跳过这个状态
                if (!tranLst.containsKey(cur)) {
                    continue;
                }
                // 取出当前前序状态到当前状态的转换概率
                double tranRate = tranLst.get(cur);
                // 如果当前状态无输出概率，跳过
                if (!outputMap.containsKey(cur)) {
                    continue;
                }
                // 取出当前概率的输出概率表
                Map<String, Double> outLst = outputMap.get(cur);
                // 设置保底输出概率
                double singleRate = 0.00001;
                // 如果当前状态到当前单词的输出概率为0，跳过
                if (outLst.containsKey(item)) {
                    singleRate += outLst.get(item);
                }
                // 取出输出概率
                double val = proRate*tranRate*singleRate;
                // 判断该状态是否有保存当前概率
                if (newRateMap.containsKey(cur)) {
                    // 如果之前已保存，则和当前比较，取大的
                    double curRate = newRateMap.get(cur);
                    if (val > curRate) {
                        newRateMap.put(cur, val);
                    }
                }
                else {
                    // 如果之前没保存，则保存当前的概率
                    newRateMap.put(cur, val);
                }
                // 判断当前style的概率是否是可选状态
                if (val > max) {
                    max = val;
                    metaBean.state = cur;
                }
            }
        }
        if (max == 0) {
            metaBean.state = SKIP_STATE;
            return metaBean;
        }
        metaBean.rateMap = newRateMap;
        return metaBean;
    }

    private MetaBean dealInit(String item) {
        MetaBean metaBean = new MetaBean();
        // 如果初始词没有任何状态，跳出
        if (!invertedList.containsKey(item)) {
            metaBean.state = BREAK_STATE;
            return metaBean;
        }
        // 该map保存item作为首词的所有状态的概率
        Map<Integer, Double> rateMap = new HashMap<Integer, Double>();
        // 查倒排表，从styleLst中取出item的所有状态
        List<Integer> styleLst = invertedList.get(item);
        double max = 0;
        // 迭代item的所有状态
        for (Integer style : styleLst) {
            // 当前状态style没有初始概率
            if (!initMap.containsKey(style)) {
                continue;
            }
            // 取出当前style的初始概率
            double initRate = initMap.get(style);
            // 如果当前styple没有输出概率，跳过
            if (!outputMap.containsKey(style)) {
                continue;
            }
            // 取出当前style的输出概率表
            Map<String, Double> singleOut = outputMap.get(style);
            // 如果当前style到item没有输出概率，有个保底概率
            double singleRate = 0.0000001;
            if (singleOut.containsKey(item)) {
                singleRate += singleOut.get(item);
            }
            // 计算当前style的概率
            double val = initRate * singleRate;
            // 保存当前style的概率,供后续判断使用
            rateMap.put(style, val);
            // 判断当前style的概率是否是可选状态
            if (val > max) {
                max = val;
                metaBean.state = style;
            }
        }
        metaBean.rateMap = rateMap;
        if (max == 0) {
            metaBean.state = BREAK_STATE;
            return metaBean;
        }
        return metaBean;
    }

    /**
     *
     * @param orgPath
     * @param dstPath
     */
    public void sample(String orgPath, String dstPath) {
        try {
            FileReader reader = new FileReader(orgPath);
            BufferedReader br = new BufferedReader(reader);
            FileWriter writer = new FileWriter(dstPath);
            BufferedWriter bw = new BufferedWriter(writer);
            String str;
            while ((str = br.readLine()) != null) {
                // 对str进行分词
                String[] words = ansiWrap.split(str);
                if (words == null) {
                    continue;
                }
                String line = "";
                for (String item : words) {
                    if (!invertedList.containsKey(item)) {
                        line += (item + " ");
                        continue;
                    }
                    List<Integer> lst = invertedList.get(item);
                    if (lst.size() > 1) {
                        line += "("+item+"|";
                        for (Integer val : lst) {
                            line += val+",";
                        }
                        line = line.substring(0, line.length()-1);
                        line += ") ";
                        continue;
                    }
                    Integer value = lst.get(0);
                    line += ("("+item+"|"+value+")");
                }
                if (!"".equals(line)) {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String path1, String path2, String path3, String path4) {
        initTrainer.trainer(path1);
        outputTrainer.trainer(path2);
        transitionTrainer.trainer(path3);
        HmmData hmmData = new HmmData();
        initTrainer.loadDate(hmmData);
        outputTrainer.loadDate(hmmData);
        transitionTrainer.loadDate(hmmData);
        inverted.load(path4);
        initMap = hmmData.initMap;
        outputMap = hmmData.outputMap;
        transitionMap = hmmData.transitionMap;
        invertedList = inverted.getInvertMap();
    }

    public ITrainer getInitTrainer() {
        return initTrainer;
    }

    public void setInitTrainer(ITrainer initTrainer) {
        this.initTrainer = initTrainer;
    }

    public ITrainer getOutputTrainer() {
        return outputTrainer;
    }

    public void setOutputTrainer(ITrainer outputTrainer) {
        this.outputTrainer = outputTrainer;
    }

    public ITrainer getTransitionTrainer() {
        return transitionTrainer;
    }

    public void setTransitionTrainer(ITrainer transitionTrainer) {
        this.transitionTrainer = transitionTrainer;
    }

    public void setInverted(InvertedList inverted) {
        this.inverted = inverted;
    }

    public Map<String, List<Integer>> getInvertedList() {
        return this.invertedList;
    }

    public void setAnsiWrap(AnsiWrap ansiWrap) {
        this.ansiWrap = ansiWrap;
    }

    public AnsiWrap getAnsiWrap() {
        return ansiWrap;
    }

    private static class MetaBean {
        public int state;
        public Map<Integer, Double> rateMap;
    }

}
