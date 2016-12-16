package machineLearning.xmm.hmm;

import commonUtil.ansjWrap.AnsiWrap;
import lillian.common.spring.BaseTest;
import machineLearning.xmm.hmm.analysis.Analysis;
import machineLearning.xmm.hmm.build.IBuild;
import machineLearning.xmm.hmm.build.dict.impl.InitialDict;
import machineLearning.xmm.hmm.build.dict.impl.OutputDict;
import machineLearning.xmm.hmm.build.dict.impl.TransitionDict;
import machineLearning.xmm.hmm.inverted.InvertedList;
import machineLearning.xmm.hmm.trainer.ITrainer;
import machineLearning.xmm.hmm.trainer.impl.InitialTrainer;
import machineLearning.xmm.hmm.trainer.impl.OutputTrainer;
import machineLearning.xmm.hmm.trainer.impl.TransitionTrainer;

import java.io.*;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class HmmTest {
    public static void main(String[] args) {
        IBuild changer = new InitialDict();
        IBuild changer1 = new OutputDict();
        IBuild changer2 = new TransitionDict();
        String hmmTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\sample.txt";
        String initTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\initial.txt";
        String outputTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\output.txt";
        String tranTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\transition.txt";
        String dictPath = "D:\\code\\myself\\lillian\\data\\hmm\\dict";
        String originPath = "D:\\code\\myself\\lillian\\data\\hmm\\sample\\origin.txt";
        String samplePath = "D:\\code\\myself\\lillian\\data\\hmm\\sample\\sample.txt";
        changer.change(hmmTextPath, initTextPath);
        changer1.change(hmmTextPath, outputTextPath);
        changer2.change(hmmTextPath, tranTextPath);

        ITrainer trainer = new InitialTrainer();
        ITrainer trainer1 = new OutputTrainer();
        ITrainer trainer2 = new TransitionTrainer();
        InvertedList invertedList = new InvertedList();

        Analysis analysis = new Analysis();
        analysis.setInitTrainer(trainer);
        analysis.setOutputTrainer(trainer1);
        analysis.setTransitionTrainer(trainer2);
        analysis.setInverted(invertedList);
        analysis.setAnsiWrap((AnsiWrap) BaseTest.getAc().getBean("ansiWrap"));
        analysis.load(initTextPath, outputTextPath, tranTextPath, dictPath);

        //analysis.sample(originPath, samplePath);

        String result = "D:\\code\\myself\\lillian\\data\\hmm\\result";
        String destiny = "D:\\code\\myself\\lillian\\data\\hmm\\destiny.txt";
        try {
            FileReader reader = new FileReader(result);
            BufferedReader br = new BufferedReader(reader);
            FileWriter writer = new FileWriter(destiny);
            BufferedWriter bw = new BufferedWriter(writer);

            String str;
            while ((str = br.readLine()) != null) {
                String[] words = analysis.viterbi(str);
                if (words == null) {
                    continue;
                }
                String value = "";
                for (String word : words) {
                    value += word;
                }
                bw.write(value);
                bw.newLine();
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
}
