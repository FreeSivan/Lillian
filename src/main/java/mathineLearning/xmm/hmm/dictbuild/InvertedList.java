package mathineLearning.xmm.hmm.dictbuild;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/29.
 */
public class InvertedList {

    private Map<String, List<Integer>> invertMap = new HashMap<String, List<Integer>>();

    public void load(String dirPath) {
        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        for (File file : files) {
            try{
                String name = file.getName();
                name = name.substring(0, name.indexOf("."));
                Integer style = Integer.parseInt(name);
                FileReader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
                String str;
                while ((str = br.readLine()) != null) {
                    if (!invertMap.containsKey(str)) {
                        List<Integer> lst = new ArrayList<Integer>();
                        invertMap.put(str, lst);
                    }
                    System.out.println("item = " + str + "  style = " + style);
                    List<Integer> lst = invertMap.get(str);
                    lst.add(style);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void display () {
        for (String str : invertMap.keySet()) {
            System.out.print("" + str + ": ");
            List<Integer> lst = invertMap.get(str);
            for (Integer val : lst) {
                System.out.print("" + val + " ");
            }
            System.out.println("");
        }
    }

    public Map<String, List<Integer>> getInvertMap() {
        return invertMap;
    }

    public void setInvertMap(Map<String, List<Integer>> invertMap) {
        this.invertMap = invertMap;
    }
}
