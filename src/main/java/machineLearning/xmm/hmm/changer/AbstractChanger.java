package machineLearning.xmm.hmm.changer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public abstract class AbstractChanger implements IChanger{

    @Override
    public void changer(String orgPath, String dstPath) {
        try {
            FileReader reader = new FileReader(orgPath);
            BufferedReader br = new BufferedReader(reader);
            FileWriter writer = new FileWriter(dstPath);
            BufferedWriter bw = new BufferedWriter(writer);
            String str;
            while ((str = br.readLine()) != null) {
                List<String> lstStr = dealEachLine(str);
                writeToFile(bw, lstStr);
            }
            br.close();
            bw.close();
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> dealEachLine(String str) {
        List<String> lstStr = new ArrayList<String>();
        char[] charArr = str.toCharArray();
        int[] stack = new int[charArr.length];
        int top = -1;
        for (int i = 0; i < charArr.length; ++i) {
            if (charArr[i] == '（') {
                stack[++top] = i;
            }
            else if (charArr[i] == '）') {
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
