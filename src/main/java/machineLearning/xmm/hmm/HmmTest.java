package machineLearning.xmm.hmm;

import machineLearning.xmm.hmm.analysis.Analysis;
import machineLearning.xmm.hmm.changer.IChanger;
import machineLearning.xmm.hmm.changer.impl.InitialChanger;
import machineLearning.xmm.hmm.changer.impl.OutputChanger;
import machineLearning.xmm.hmm.changer.impl.TransitionChanger;
import machineLearning.xmm.hmm.dictbuild.InvertedList;
import machineLearning.xmm.hmm.trainer.ITrainer;
import machineLearning.xmm.hmm.trainer.impl.InitialTrainer;
import machineLearning.xmm.hmm.trainer.impl.OutputTrainer;
import machineLearning.xmm.hmm.trainer.impl.TransitionTrainer;

/**
 * Created by xiwen.yxw on 2016/11/28.
 */
public class HmmTest {
    public static void main(String[] args) {
        IChanger changer = new InitialChanger();
        IChanger changer1 = new OutputChanger();
        IChanger changer2 = new TransitionChanger();
        String hmmTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\hmm.txt";
        String initTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\initial.txt";
        String outputTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\output.txt";
        String tranTextPath = "D:\\code\\myself\\lillian\\data\\hmm\\build\\transition.txt";
        String dictPath = "D:\\code\\myself\\lillian\\data\\hmm\\dict";
        changer.changer(hmmTextPath, initTextPath);
        changer1.changer(hmmTextPath, outputTextPath);
        changer2.changer(hmmTextPath, tranTextPath);

        ITrainer trainer = new InitialTrainer();
        ITrainer trainer1 = new OutputTrainer();
        ITrainer trainer2 = new TransitionTrainer();
        InvertedList invertedList = new InvertedList();

        Analysis analysis = new Analysis();
        analysis.setInitTrainer(trainer);
        analysis.setOutputTrainer(trainer1);
        analysis.setTransitionTrainer(trainer2);
        analysis.setInverted(invertedList);
        analysis.load(initTextPath, outputTextPath, tranTextPath, dictPath);

        String[] strArr = analysis.viterbi(new String[] {
                "王菲",
                "唱",
                "的",
                "周杰伦"
        });

        for (String str : strArr) {
            System.out.println(str);
        }

        System.out.println("============================================");
        strArr = analysis.viterbi(new String[] {
                "周杰伦",
                "唱",
                "的",
                "七里香"
        });

        for (String str : strArr) {
            System.out.println(str);
        }

        System.out.println("============================================");
        strArr = analysis.viterbi(new String[] {
                "周杰伦",
                "在",
                "星期天",
                "演唱会",
                "唱",
                "的",
                "王菲"
        });

        for (String str : strArr) {
            System.out.println(str);
        }
        System.out.println("============================================");
        strArr = analysis.viterbi(new String[] {
                "王菲",
                "陈奕迅",
                "合唱",
                "的",
                "因为爱情"
        });

        for (String str : strArr) {
            System.out.println(str);
        }
    }
}
