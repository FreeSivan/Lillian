package machineLearning.xmm.hmm.build.dict;

import machineLearning.xmm.hmm.build.AbstractBuild;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public abstract class AbstractDict extends AbstractBuild {
    protected List<String> dealEachLine(String str) {
        List<String> lstStr = new ArrayList<String>();
        char[] charArr = str.toCharArray();
        int[] stack = new int[charArr.length];
        int top = -1;
        for (int i = 0; i < charArr.length; ++i) {
            if (charArr[i] == '（' || charArr[i] == '(') {
                stack[++top] = i;
            }
            else if (charArr[i] == '）'|| charArr[i] == ')') {
                String tmp = str.substring(stack[top--]+1,i);
                lstStr.add(tmp.trim());
            }
            else {
                // doNothing
            }
        }
        for (String item : lstStr) {
            System.out.println("item : " + item);
        }
        return lstStr;
    }

    protected abstract void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException;
}
