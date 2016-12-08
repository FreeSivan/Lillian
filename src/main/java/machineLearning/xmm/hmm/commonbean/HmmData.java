package machineLearning.xmm.hmm.commonbean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class HmmData {

    public Map<Integer, Double> initMap = new HashMap<Integer, Double>();

    public Map<Integer, Map<String, Double>> outputMap = new HashMap<Integer, Map<String, Double>>();

    public Map<Integer, Map<Integer, Double>> transitionMap = new HashMap<Integer, Map<Integer, Double>>();

}
