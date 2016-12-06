package lillian.neural.service.serviceMatcher.impl;

import lillian.neural.service.serviceBean.ReqMeta;
import lillian.neural.service.serviceMatcher.IMatcher;

/**
 * Created by xiwen.yxw on 2016/11/25.
 */
public class SimpleMatcher implements IMatcher{
    @Override
    public Integer isMatch(ReqMeta[] reqMetaArr, String query) {
        if (reqMetaArr.length == 0) {
            return null;
        }
        for (ReqMeta reqMeta : reqMetaArr) {
            double val = similarity(reqMeta.getQuery(), query);
            System.out.println("val = " + val);
            if (val > 0.8) {
                return reqMeta.getDocId();
            }
        }
        return null;
    }


    private int ld(String s, String t) {
        int d[][];
        int sLen = s.length();
        int tLen = t.length();
        int si;
        int ti;
        char ch1;
        char ch2;
        int cost;
        if(sLen == 0) {
            return tLen;
        }
        if(tLen == 0) {
            return sLen;
        }
        d = new int[sLen+1][tLen+1];
        for(si=0; si<=sLen; si++) {
            d[si][0] = si;
        }
        for(ti=0; ti<=tLen; ti++) {
            d[0][ti] = ti;
        }
        for(si=1; si<=sLen; si++) {
            ch1 = s.charAt(si-1);
            for(ti=1; ti<=tLen; ti++) {
                ch2 = t.charAt(ti-1);
                if(ch1 == ch2) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                d[si][ti] = Math.min(Math.min(d[si-1][ti]+1, d[si][ti-1]+1),d[si-1][ti-1]+cost);
            }
        }
        return d[sLen][tLen];
    }

    public double similarity(String src, String tar) {
        int ld = ld(src, tar);
        return 1 - (double) ld / Math.max(src.length(), tar.length());
    }

}
