package lillian.neural.service.serviceInverted.impl;

import lillian.neural.service.serviceInverted.IInverted;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/19.
 */
public class SimpleInverted implements IInverted{

    private Map<String, List<Integer>> inverted= new HashMap<String, List<Integer>>();

    @Override
    public Integer[] searchDoc(String[] words) {
        ContextState contextState = new ContextState();
        contextState.curHits = 0;
        contextState.curDocId = -1;
        contextState.curHold = 0;
        contextState.wordPos = -1;
        Integer[] docPos = new Integer[words.length];
        for (int i = 0; i < docPos.length; ++i) {
            docPos[i] = -1;
        }
        contextState.docPos = docPos;
        contextState.words = words;
        contextState.result = new ArrayList<Integer>();
        scheduleState(contextState);
        int size = contextState.result.size();
        return contextState.result.toArray(new Integer[size]);
    }


    @Override
    public void load(String path) {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String str;
            while ((str = br.readLine()) != null) {
                String[] itemArr = str.split(":");
                if (itemArr.length != 2) {
                    continue;
                }
                if (!inverted.containsKey(itemArr[0])) {
                    List<Integer> lst = new ArrayList<Integer>();
                    inverted.put(itemArr[0], lst);
                }
                List<Integer> lst = inverted.get(itemArr[0]);
                for (String val : itemArr[1].split("\\|")) {
                    val = val.trim();
                    if (val.isEmpty()) {
                        continue;
                    }
                    Integer newValue = new Integer(val);
                    if (lst.contains(newValue)) {
                        continue;
                    }
                    lst.add(new Integer(val));
                }
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 每处理完一个单词就进入该调度状态，由该状态判断下一个状态，该状态只循环改变word
     *  1. 某个单词的倒排项不存在，则直接返回
     *  2. 如果curHits为0，则说明第一次进入，下一状态为初始状态
     *  3. 如果curHits为words.length，说明所有单词都有该倒排项，进入选择状态
     *  4. 其余情况进入普通处理状态
     * @param context 状态机上下文
     */
    private void scheduleState(ContextState context) {
        context.wordPos = (context.wordPos+1)%context.words.length;
        List<Integer> val = inverted.get(context.words[context.wordPos]);
        if (val == null) {
            return;
        }
        context.curHold = val.size();
        if (context.curHits == 0) {
            initState(context);
        }
        else if (context.curHits == context.words.length) {
            selectedState(context);
        }
        else {
            ordinaryState(context);
        }
    }

    /**
     * 初始状态，处理第一次选择的特殊情况
     * @param context
     */
    private void initState(ContextState context) {
        context.docPos[context.wordPos] ++;
        context.curHits = 1;
        int curPos = context.docPos[context.wordPos];
        if (curPos >= context.curHold) {
            return;
        }
        List<Integer> val = inverted.get(context.words[context.wordPos]);
        context.curDocId = val.get(context.docPos[context.wordPos]);
        scheduleState(context);
    }

    /**
     *
     * @param context
     */
    private void ordinaryState(ContextState context) {
        context.docPos[context.wordPos] ++;
        int curPos = context.docPos[context.wordPos];
        if (curPos >= context.curHold) {
            return;
        }
        List<Integer> val = inverted.get(context.words[context.wordPos]);
        while (context.docPos[context.wordPos] < context.curHold) {
            Integer curDocId = val.get(curPos);
            if (curDocId.intValue() == context.curDocId.intValue()) {
                context.curHits ++;
                break;
            }
            else if (curDocId.intValue() > context.curDocId.intValue()) {
                context.curHits = 1;
                context.curDocId = val.get(curPos);
                break;
            }
            else {
                context.docPos[context.wordPos]++;
            }
        }
        scheduleState(context);
    }

    /**
     *
     * @param context
     */
    private void selectedState(ContextState context) {
        context.result.add(context.curDocId);
        context.docPos[context.wordPos] ++;
        int curPos = context.docPos[context.wordPos];
        if (curPos >= context.curHold.intValue()) {
            return;
        }
        List<Integer> val = inverted.get(context.words[context.wordPos]);
        context.curDocId = val.get(curPos);
        context.curHits = 1;
        scheduleState(context);
    }

    public void display() {
        for (String word : inverted.keySet()) {
            System.out.print("key = " + word);
            System.out.print(" : ");
            List<Integer> lst = inverted.get(word);
            for (Integer val : lst) {
                System.out.print(val);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }


    private class ContextState {
        public Integer curHits;
        public Integer curDocId;
        public Integer[] docPos;
        public Integer curHold;
        public Integer wordPos;
        public String[] words;
        public List<Integer> result;
    }

}
