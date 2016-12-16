package machineLearning.xmm.hmm.build;

import java.io.*;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/15.
 */
public abstract class AbstractBuild implements IBuild{
    @Override
    public void change(String orgPath, String dstPath) {
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
    protected abstract List<String> dealEachLine(String str);
    protected abstract void writeToFile(BufferedWriter bw, List<String> lstStr) throws IOException;
}
