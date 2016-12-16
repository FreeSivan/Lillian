package machineLearning.xmm.hmm.build.dict.impl;

import machineLearning.xmm.hmm.build.dict.AbstractDict;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class OutputDict extends AbstractDict {

    @Override
    protected void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException {
        if (lstStr.size() == 0) {
            return;
        }
        for (String item : lstStr) {
            String[] itemArr = item.split("\\|");
            if (itemArr.length < 2) {
                continue;
            }
            bw.write(itemArr[1]);
            bw.write(" ");
            bw.write(itemArr[0]);
            bw.newLine();
        }
    }
}
