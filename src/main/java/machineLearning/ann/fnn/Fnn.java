package machineLearning.ann.fnn;

import machineLearning.ann.fnn.frame.FnnLayer;
import machineLearning.ann.fnn.frame.FnnNet;
import machineLearning.ann.fnn.frame.FnnNode;
import machineLearning.ann.fnn.train.Train;
import machineLearning.ann.neuron.IThreshold;
import machineLearning.ann.neuron.impl.SigmoidThreshold;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/9.
 */
public class Fnn {
    public static void main(String[] args) {

        IThreshold threshold = new SigmoidThreshold();
        FnnNode mid1 = new FnnNode();
        List<Double> weight1 = new ArrayList<Double>();
        weight1.add(0.09);
        weight1.add(0.04);
        weight1.add(-0.03);
        weight1.add(-0.06);
        weight1.add(-0.05);
        weight1.add(0.07);
        weight1.add(0.03);
        weight1.add(0.04);
        mid1.setWeightLst(weight1);
        mid1.setThrFunc(threshold);
        FnnNode mid2 = new FnnNode();
        List<Double> weight2 = new ArrayList<Double>();
        weight2.add(0.05);
        weight2.add(0.04);
        weight2.add(-0.07);
        weight2.add(-0.06);
        weight2.add(-0.02);
        weight2.add(0.08);
        weight2.add(0.08);
        weight2.add(-0.03);
        mid2.setWeightLst(weight2);
        mid2.setThrFunc(threshold);
        FnnNode mid3 = new FnnNode();
        List<Double> weight3 = new ArrayList<Double>();
        weight3.add(0.04);
        weight3.add(0.05);
        weight3.add(-0.07);
        weight3.add(-0.01);
        weight3.add(-0.04);
        weight3.add(0.02);
        weight3.add(0.04);
        weight3.add(-0.05);
        mid3.setWeightLst(weight3);
        mid3.setThrFunc(threshold);
        List<FnnNode> lstMid = new ArrayList<FnnNode>();
        lstMid.add(mid1);
        lstMid.add(mid2);
        lstMid.add(mid3);
        FnnLayer midLayer = new FnnLayer();
        midLayer.setNodeList(lstMid);

        FnnNode out1 = new FnnNode();
        List<Double> o1 = new ArrayList<Double>();
        o1.add(0.07);
        o1.add(0.03);
        o1.add(-0.03);
        out1.setWeightLst(o1);
        out1.setThrFunc(threshold);

        FnnNode out2 = new FnnNode();
        List<Double> o2 = new ArrayList<Double>();
        o2.add(0.02);
        o2.add(0.05);
        o2.add(-0.06);
        out2.setWeightLst(o2);
        out2.setThrFunc(threshold);

        FnnNode out3 = new FnnNode();
        List<Double> o3 = new ArrayList<Double>();
        o3.add(-0.05);
        o3.add(0.07);
        o3.add(-0.05);
        out3.setWeightLst(o3);
        out3.setThrFunc(threshold);

        FnnNode out4 = new FnnNode();
        List<Double> o4 = new ArrayList<Double>();
        o4.add(0.06);
        o4.add(0.08);
        o4.add(-0.03);
        out4.setWeightLst(o4);
        out4.setThrFunc(threshold);

        FnnNode out5 = new FnnNode();
        List<Double> o5 = new ArrayList<Double>();
        o5.add(-0.03);
        o5.add(0.04);
        o5.add(-0.07);
        out5.setWeightLst(o5);
        out5.setThrFunc(threshold);

        FnnNode out6 = new FnnNode();
        List<Double> o6 = new ArrayList<Double>();
        o6.add(-0.04);
        o6.add(0.03);
        o6.add(-0.08);
        out6.setWeightLst(o6);
        out6.setThrFunc(threshold);

        FnnNode out7 = new FnnNode();
        List<Double> o7 = new ArrayList<Double>();
        o7.add(0.04);
        o7.add(0.03);
        o7.add(0.04);
        out7.setWeightLst(o7);
        out7.setThrFunc(threshold);

        FnnNode out8 = new FnnNode();
        List<Double> o8 = new ArrayList<Double>();
        o8.add(-0.05);
        o8.add(0.07);
        o8.add(0.02);
        out8.setWeightLst(o8);
        out8.setThrFunc(threshold);

        List<FnnNode> lstOut = new ArrayList<FnnNode>();
        lstOut.add(out1);
        lstOut.add(out2);
        lstOut.add(out3);
        lstOut.add(out4);
        lstOut.add(out5);
        lstOut.add(out6);
        lstOut.add(out7);
        lstOut.add(out8);
        FnnLayer outLayer = new FnnLayer();
        outLayer.setNodeList(lstOut);

        List<FnnLayer> layLst = new ArrayList<FnnLayer>();
        layLst.add(midLayer);
        layLst.add(outLayer);
        FnnNet fnnNet = new FnnNet();
        fnnNet.setLayerList(layLst);

        Train train = new Train();
        train.setFnnNet(fnnNet);
        train.setTiny(0.3);

        String fnnTextPath = "D:\\code\\myself\\lillian\\data\\fnn\\fnn.txt";
        try {
            FileReader reader = new FileReader(fnnTextPath);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            List<double[]> inputLst = new ArrayList<double[]>();
            List<double[]> outputLst = new ArrayList<double[]>();
            while ((str = br.readLine()) != null) {
                String[] strArr = str.split("\\|");
                if (strArr.length != 2) {
                    continue;
                }
                String[] inputs = strArr[0].split(" ");
                String[] outputs = strArr[1].trim().split(" ");
                double[] input = new double[inputs.length];
                double[] output = new double[outputs.length];
                for (int i = 0; i < input.length; ++i) {
                    input[i] = Double.parseDouble(inputs[i].trim());
                }
                for (int i = 0; i < output.length; ++i) {
                    output[i] = Double.parseDouble(outputs[i].trim());
                }
                inputLst.add(input);
                outputLst.add(output);
            }
            int i = 0;
            while (i < 500000) {
                int j = i % inputLst.size();
                double[] input = inputLst.get(j);
                double[] output = outputLst.get(j);
                List<Double> newInputLst = new ArrayList<Double>();
                for (int k = 0; k < input.length; ++k) {
                    newInputLst.add(input[k]);
                }
                List<Double> newOutputLst = new ArrayList<Double>();
                for (int k = 0; k < output.length; ++k) {
                    newOutputLst.add(output[k]);
                }
                System.out.println("i = " + i);
                train.trainSingle(newInputLst, newOutputLst);
                ++i;
            }
            List<Double> realInput  = new ArrayList<Double>();
            realInput.add(0.0);
            realInput.add(0.0);
            realInput.add(0.0);
            realInput.add(1.0);
            realInput.add(0.0);
            realInput.add(0.0);
            realInput.add(0.0);
            realInput.add(0.0);
            fnnNet.inputToOutput(realInput);


            System.out.println("val1 = " + fnnNet.getLayerList().get(0).getNodeList().get(0).getCurOutput());
            System.out.println("val2 = " + fnnNet.getLayerList().get(0).getNodeList().get(1).getCurOutput());
            System.out.println("val3 = " + fnnNet.getLayerList().get(0).getNodeList().get(2).getCurOutput());

            System.out.println("===========================");

            System.out.println("val1 = " + fnnNet.getLayerList().get(1).getNodeList().get(0).getCurOutput());
            System.out.println("val2 = " + fnnNet.getLayerList().get(1).getNodeList().get(1).getCurOutput());
            System.out.println("val3 = " + fnnNet.getLayerList().get(1).getNodeList().get(2).getCurOutput());
            System.out.println("val4 = " + fnnNet.getLayerList().get(1).getNodeList().get(3).getCurOutput());
            System.out.println("val5 = " + fnnNet.getLayerList().get(1).getNodeList().get(4).getCurOutput());
            System.out.println("val6 = " + fnnNet.getLayerList().get(1).getNodeList().get(5).getCurOutput());
            System.out.println("val7 = " + fnnNet.getLayerList().get(1).getNodeList().get(6).getCurOutput());
            System.out.println("val8 = " + fnnNet.getLayerList().get(1).getNodeList().get(7).getCurOutput());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
