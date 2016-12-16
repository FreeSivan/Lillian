package machineLearning.xmm.hmm.build.dict.impl;

import machineLearning.xmm.hmm.build.dict.AbstractDict;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class InitialDict extends AbstractDict {

    @Override
    protected void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException {
        if (lstStr.size() == 0) {
            return;
        }
        String str = lstStr.get(0);
        String[] termArr = str.split("\\|");
        if (termArr.length != 2) {
            return;
        }
        bw.write(termArr[1]);
        bw.newLine();
    }
}
