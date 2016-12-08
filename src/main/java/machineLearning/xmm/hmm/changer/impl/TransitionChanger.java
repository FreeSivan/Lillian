package machineLearning.xmm.hmm.changer.impl;

import machineLearning.xmm.hmm.changer.AbstractChanger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class TransitionChanger extends AbstractChanger{

    @Override
    protected void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException {
        if (lstStr.size() < 2) {
            return;
        }
        for (int i = 0; i < lstStr.size() - 1; ++i) {
            String term1 = lstStr.get(i);
            String term2 = lstStr.get(i+1);
            String[] termArr1 = term1.split("\\|");
            String[] termArr2 = term2.split("\\|");
            if (termArr1.length != 2 || termArr2.length != 2) {
                continue;
            }
            bw.write(termArr1[1]);
            bw.write(" ");
            bw.write(termArr2[1]);
            bw.newLine();
        }
    }
}
