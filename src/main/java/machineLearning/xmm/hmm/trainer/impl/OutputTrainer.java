package machineLearning.xmm.hmm.trainer.impl;

import machineLearning.xmm.hmm.commonbean.HmmData;
import machineLearning.xmm.hmm.trainer.AbstractTrainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class OutputTrainer extends AbstractTrainer{

    private Map<Integer, Map<String, Double>> rateMap = new HashMap<Integer, Map<String, Double>>();

    private Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

    private Map<Integer, Map<String, Integer>> singleMap = new HashMap<Integer, Map<String, Integer>>();

    @Override
    protected void trainsAll() {
        for (Integer firstKey : countMap.keySet()) {
            Integer allCount = countMap.get(firstKey);
            Map<String, Integer> curMap = singleMap.get(firstKey);
            if (allCount == null || curMap == null) {
                continue;
            }
            if (!rateMap.containsKey(firstKey)) {
                Map<String, Double> tmpMap = new HashMap<String, Double>();
                rateMap.put(firstKey, tmpMap);
            }
            Map<String, Double> curRateMap = rateMap.get(firstKey);
            for (String secondKey : curMap.keySet()) {
                Integer value = curMap.get(secondKey);
                Double rate = (double)value/(double)allCount;
                curRateMap.put(secondKey, rate);
            }
        }
    }

    @Override
    protected void dealEachLine(String line) {
        String[] itemArr = line.split(" ");
        if (itemArr.length != 2) {
            return;
        }
        Integer val = Integer.parseInt(itemArr[0]);
        if (val == null || itemArr[1].isEmpty()) {
            return;
        }
        if (!countMap.containsKey(val)) {
            countMap.put(val, 0);
        }
        int tmpCount = countMap.get(val) + 1;
        countMap.put(val, tmpCount);
        if (!singleMap.containsKey(val)) {
            Map<String, Integer> tmpMap = new HashMap<String, Integer>();
            singleMap.put(val, tmpMap);
        }
        Map<String, Integer> curMap = singleMap.get(val);
        String strKey = itemArr[1];
        if (!curMap.containsKey(strKey)) {
            curMap.put(strKey, 0);
        }
        int curCount = curMap.get(strKey);
        curMap.put(strKey, ++curCount);
    }

    @Override
    public void loadDate(HmmData data) {
        data.outputMap = rateMap;
    }

    @Override
    public void display() {
        for (Integer firstKey : rateMap.keySet()) {
            System.out.println("key = " + firstKey);
            Map<String, Double> curMap = rateMap.get(firstKey);
            for (String secondKey : curMap.keySet()) {
                System.out.println("   key2 = " + secondKey + "  value = " + curMap.get(secondKey));
            }
        }
    }
}
