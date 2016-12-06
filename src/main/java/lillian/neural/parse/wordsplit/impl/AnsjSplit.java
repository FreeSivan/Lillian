package lillian.neural.parse.wordsplit.impl;

import lillian.neural.parse.wordsplit.ISplit;
import org.ansj.splitWord.analysis.DicAnalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/25.
 */
public class AnsjSplit implements ISplit{
    @Override
    public String[] split(String text) {
        List<String> lst = new ArrayList<String>();
        List<org.ansj.domain.Term> terms = DicAnalysis.parse(text).getTerms();
        for (org.ansj.domain.Term term : terms) {
            lst.add(term.getRealName());
        }
        return lst.toArray(new String[lst.size()]);
    }
}
