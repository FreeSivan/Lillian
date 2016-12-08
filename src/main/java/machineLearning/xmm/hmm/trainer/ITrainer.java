package machineLearning.xmm.hmm.trainer;

import machineLearning.xmm.hmm.commonbean.HmmData;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public interface ITrainer {

    public void trainer(String filePath);

    public void loadDate(HmmData data);

    public void display();

}
