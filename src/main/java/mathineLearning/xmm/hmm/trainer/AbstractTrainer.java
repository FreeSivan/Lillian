package mathineLearning.xmm.hmm.trainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public abstract class AbstractTrainer implements ITrainer{
    @Override
    public void trainer(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                dealEachLine(line);
            }
            br.close();
            reader.close();
            trainsAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void trainsAll();

    protected abstract void dealEachLine(String line);
}
