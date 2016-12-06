package lillian.neural.build.buildData.invertedImpl;

import lillian.neural.build.buildBean.impl.ConBean;
import lillian.neural.build.buildBean.impl.ReqBean;
import lillian.neural.build.buildBean.impl.SecBean;
import lillian.neural.build.buildData.ITableBuild;
import lillian.neural.parse.wordsplit.ISplit;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class BuildSimpleInverted implements ITableBuild {

    /**
     * 分词器
     */
    private ISplit splitWord;

    @Override
    public void buildTable(ConBean data, String dumpPath) {
        Map<String, List<Integer>> inverted= new HashMap<String, List<Integer>>();
        List<SecBean> secList = data.getSections();
        for (SecBean section : secList) {
            ReqBean reqBean = section.getReqBean();
            String request = reqBean.getRequest();
            String[] words = splitWord.split(request);
            for (String item : words) {
                String str = item.trim();
                if (str.isEmpty()) {
                    continue;
                }
                if (inverted.containsKey(item)) {
                    List<Integer> lst = inverted.get(item);
                    lst.add(section.getDocId());
                }
                else {
                    List<Integer> lst = new ArrayList<Integer>();
                    lst.add(section.getDocId());
                    inverted.put(item, lst);
                }
            }
        }
        dump(dumpPath, inverted);
    }

    /**
     *
     * @param path
     * @param inverted
     */
    private void dump(String path, Map<String, List<Integer>> inverted) {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(writer);
            for (Map.Entry entry : inverted.entrySet()) {
                String word = (String)entry.getKey();
                List<Integer> lst = (List<Integer>)entry.getValue();
                String line = "";
                line += word;
                line += ":";
                for (Integer integer : lst) {
                    line += integer;
                    line += "|";
                }
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSplitWord(ISplit splitWord) {
        this.splitWord = splitWord;
    }
}
