package machineLearning.xmm.hmm.build.input;

import machineLearning.xmm.hmm.build.AbstractBuild;

import java.io.*;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/13.
 */
public abstract class AbstractInput extends AbstractBuild {
    protected void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException {
        if (lstStr == null) {
            return;
        }
        for (String str : lstStr) {
            bw.write(str);
            bw.newLine();
        }
    }

    protected abstract List<String> dealEachLine(String str);
}
