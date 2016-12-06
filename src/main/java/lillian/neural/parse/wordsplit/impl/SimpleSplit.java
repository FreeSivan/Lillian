package lillian.neural.parse.wordsplit.impl;

import lillian.common.unit.StringUtil;
import lillian.neural.parse.wordsplit.ISplit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/18.
 */
public class SimpleSplit implements ISplit{
    @Override
    public String[] split(String text) {
        // 1. Check null.
        if (text == null) {
            return new String[0];
        }

        // 2.Split.
        List<String> list = new ArrayList<String>();
        int flag = -1; // chinese(2)\number(0)\letter(1)
        String temp = "";
        char[] array = text.toCharArray();
        for (char c : array) {
            if (StringUtil.isNumeric(c)) {
                if (flag != 0 && temp.length() > 0) {
                    list.add(temp);
                    temp = "";
                }
                flag = 0;
                temp += c;
            } else if (StringUtil.isLetter(c) || c == 60 || c == 62 || c == 47 || c == 91 || c == 93) {
                if (flag != 1 && temp.length() > 0) {
                    list.add(temp);
                    temp = "";
                    temp += c;
                    flag = 1;
                } else if (flag == 1 && temp.length() > 0 && (c == 62 || c == 93)) {
                    list.add(temp + c);
                    temp = "";
                } else {
                    flag = 1;
                    temp += c;
                }
            } else {
                if (temp.length() > 0) {
                    list.add(temp);
                    temp = "";
                }
                list.add(c + "");
                flag = 2;
            }
        }
        if (flag < 2) {
            list.add(temp);
        }
        String[] ret = new String[list.size()];
        return list.toArray(ret);
    }
}
