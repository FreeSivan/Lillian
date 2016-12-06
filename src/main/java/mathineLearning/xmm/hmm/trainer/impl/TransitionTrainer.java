package mathineLearning.xmm.hmm.trainer.impl;

import mathineLearning.xmm.hmm.commonbean.HmmData;
import mathineLearning.xmm.hmm.trainer.AbstractTrainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class TransitionTrainer extends AbstractTrainer{

    private Map<Integer, Map<Integer, Double>> rateMap = new HashMap<Integer, Map<Integer, Double>>();

    private Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

    private Map<Integer, Map<Integer, Integer>> singleMap = new HashMap<Integer, Map<Integer, Integer>>();

    @Override
    protected void trainsAll() {
        for (Integer firstKey : countMap.keySet()) {
            Integer curCount = countMap.get(firstKey);
            if (!rateMap.containsKey(firstKey)) {
                Map<Integer, Double> tmp = new HashMap<Integer, Double>();
                rateMap.put(firstKey, tmp);
            }
            Map<Integer, Double> curRate = rateMap.get(firstKey);
            Map<Integer, Integer> curMap = singleMap.get(firstKey);
            for (Integer secondKey : curMap.keySet()) {
                Integer singleCount = curMap.get(secondKey);
                Double rate = (double)singleCount/(double)curCount;
                curRate.put(secondKey, rate);
            }
        }
    }

    @Override
    protected void dealEachLine(String line) {
        String[] itemArr = line.split(" ");
        if (itemArr.length != 2) {
            return;
        }
        Integer firstKey = Integer.parseInt(itemArr[0]);
        if (!countMap.containsKey(firstKey)) {
            countMap.put(firstKey, 0);
        }
        int curCount = countMap.get(firstKey);
        countMap.put(firstKey, ++curCount);
        if (!singleMap.containsKey(firstKey)) {
            Map<Integer, Integer> tmp = new HashMap<Integer, Integer>();
            singleMap.put(firstKey, tmp);
        }
        Map<Integer, Integer> curMap = singleMap.get(firstKey);
        Integer secondKey = Integer.parseInt(itemArr[1]);
        if (!curMap.containsKey(secondKey)) {
            curMap.put(secondKey, 0);
        }
        int singleCount = curMap.get(secondKey);
        curMap.put(secondKey, ++singleCount);
    }

    @Override
    public void loadDate(HmmData data) {
        data.transitionMap = rateMap;
    }

    @Override
    public void display() {
        for (Integer firstKey : rateMap.keySet()) {
            System.out.println("Transition key = " + firstKey);
            Map<Integer, Double> curMap = rateMap.get(firstKey);
            for (Integer secondKey : curMap.keySet()) {
                System.out.println("   Transition key2 = " + secondKey + "  value = " + curMap.get(secondKey));
            }
        }
    }
}
