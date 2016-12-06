package mathineLearning.xmm.hmm.trainer;

import mathineLearning.xmm.hmm.commonbean.HmmData;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public interface ITrainer {

    public void trainer(String filePath);

    public void loadDate(HmmData data);

    public void display();

}
