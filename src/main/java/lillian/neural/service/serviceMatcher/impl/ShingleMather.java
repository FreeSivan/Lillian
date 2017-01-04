package lillian.neural.service.serviceMatcher.impl;

import lillian.neural.service.serviceBean.ReqMeta;
import lillian.neural.service.serviceMatcher.IMatcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by xiwen.yxw on 2016/12/19.
 */
public class ShingleMather implements IMatcher{
    @Override
    public Integer isMatch(ReqMeta[] postMetaArr, String query) {
        double max = 0;
        ReqMeta meta = null;
        for (ReqMeta reqMeta : postMetaArr) {
            String dstStr = reqMeta.getQuery();
            double sim = similar(query, dstStr);
            if (sim > max) {
                max = sim;
                meta = reqMeta;
            }
        }
        if (max < 0.8) {
            return null;
        }
        return meta.getDocId();
    }

    private double similar(String query, String dstStr) {
        Set<String> strSet = new HashSet<String>();
        char[] words = query.toCharArray();
        int count = 0;
        for (int i = 1; i < words.length; ++i) {
            String tmp = String.valueOf(words[i-1]);
            String tmp2 = String.valueOf(words[i]);
            String str = tmp + tmp2;
            if (strSet.contains(str)) {
                continue;
            }
            strSet.add(str);
        }
        char[] wordsDst = dstStr.toCharArray();
        for (int i = 1; i < wordsDst.length; ++i) {
            String tmp = String.valueOf(wordsDst[i-1]);
            String tmp2 = String.valueOf(wordsDst[i]);
            String str = tmp + tmp2;
            if (strSet.contains(str)) {
                count ++;
                continue;
            }
            strSet.add(str);
        }
        if (strSet.size() == 0) {
            return 0;
        }
        return (double)count / (double)strSet.size();
    }

    public static void main(String[] args) {
        ShingleMather shingleMather = new ShingleMather();
        double result = shingleMather.similar("今天早上天气很好","今天早上天气好");
        System.out.println("result = " + result);
    }
}
