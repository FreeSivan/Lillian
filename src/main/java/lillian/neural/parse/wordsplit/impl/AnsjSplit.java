package lillian.neural.parse.wordsplit.impl;

import commonUtil.ansjWrap.AnsiWrap;
import lillian.neural.parse.wordsplit.ISplit;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/25.
 */
public class AnsjSplit implements ISplit{
    private AnsiWrap ansiWrap;
    @Override
    public String[] split(String text) {
        return ansiWrap.split(text);
    }

    public AnsiWrap getAnsiWrap() {
        return ansiWrap;
    }

    public void setAnsiWrap(AnsiWrap ansiWrap) {
        this.ansiWrap = ansiWrap;
    }
}
