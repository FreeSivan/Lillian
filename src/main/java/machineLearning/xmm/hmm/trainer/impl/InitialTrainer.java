package machineLearning.xmm.hmm.trainer.impl;

import machineLearning.xmm.hmm.commonbean.HmmData;
import machineLearning.xmm.hmm.trainer.AbstractTrainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class InitialTrainer extends AbstractTrainer{

    private Map<Integer, Double> initMap = new HashMap<Integer, Double>();

    private Integer count = 0;

    private Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();


    @Override
    protected void trainsAll() {
        if (count == 0 || countMap.size() == 0) {
            return;
        }
        for (Map.Entry entry : countMap.entrySet()) {
            entry.getKey();
            Double value = (double)((Integer)entry.getValue())/(double)count;
            initMap.put((Integer)entry.getKey(), value);
        }
    }

    @Override
    protected void dealEachLine(String line) {
        Integer val = Integer.parseInt(line);
        if (val == null) {
            return;
        }
        count ++;
        if (!countMap.containsKey(val)) {
            countMap.put(val, 0);
        }
        Integer tmpVal = countMap.get(val);
        countMap.put(val, ++tmpVal);
    }

    @Override
    public void loadDate(HmmData data) {
        data.initMap = initMap;
    }

    @Override
    public void display() {
        for (Integer key : initMap.keySet()) {
            System.out.println("key = " + key + "  value = " + initMap.get(key));
        }
    }
}
