package lillian.neural.service;

import lillian.neural.parse.wordsplit.ISplit;
import lillian.neural.service.serviceBean.ReqMeta;
import lillian.neural.service.serviceBean.ResMeta;
import lillian.neural.service.serviceChoicer.IChoicer;
import lillian.neural.service.serviceInverted.IInverted;
import lillian.neural.service.serviceMatcher.IMatcher;
import lillian.neural.service.servicePositive.IPositive;
import lillian.neural.service.serviceQparse.IParse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public class Service {

    // 请求解析器
    private IParse parse;

    // 正排表
    private IPositive positive;

    // 倒排表
    private IInverted inverted;

    // 筛选器
    private IMatcher matcher;

    // 结果选择器
    private IChoicer randomChoicer;

    // 结果选择器
    private IChoicer funcChoicer;

    // 分词器
    private ISplit splitWord;

    public Service() {
    }

    public IPositive getPositive() {
        return positive;
    }

    public void setPositive(IPositive positive) {
        this.positive = positive;
    }

    public IInverted getInverted() {
        return inverted;
    }

    public void setInverted(IInverted inverted) {
        this.inverted = inverted;
    }

    public IMatcher getMatcher() {
        return matcher;
    }

    public void setMatcher(IMatcher matcher) {
        this.matcher = matcher;
    }

    public IChoicer getRandomChoicer() {
        return randomChoicer;
    }

    public void setRandomChoicer(IChoicer randomChoicer) {
        this.randomChoicer = randomChoicer;
    }

    public ISplit getSplitWord() {
        return splitWord;
    }

    public void setParse(IParse parse) {
        this.parse = parse;
    }

    public IChoicer getFuncChoicer() {
        return funcChoicer;
    }

    public void setFuncChoicer(IChoicer funcChoicer) {
        this.funcChoicer = funcChoicer;
    }

    public void setSplitWord(ISplit splitWord) {
        this.splitWord = splitWord;
    }

    public void load(String invertedPath, String positivePath) {
        inverted.load(invertedPath);
        positive.load(positivePath);
    }

    public String query(String query) {
        Map<String, String> context = new HashMap<String, String>();

        query = parse.parseQuery(query, context);

        // 分词
        String[] wordArr = splitWord.split(query);
        // 查倒排
        Integer[] docIdArr = inverted.searchDoc(wordArr);
        // 查正排
        ReqMeta[] reqMetaArr = positive.searchReq(docIdArr);
        if (reqMetaArr.length == 0) {
            System.out.println("length = 0");
            return null;
        }
        // 从一群正排中查询返回结果
        Integer docId = matcher.isMatch(reqMetaArr, query);
        if (docId == null) {
            System.out.println("docId = null");
            return null;
        }
        ResMeta resp = positive.searchResp(docId);
        if (resp.getStyle() == 0) {
            return resp.getSingleResponse();
        }
        else if (resp.getStyle() == 1) {
            return randomChoicer.choiceResp(resp, context);
        }
        else if (resp.getStyle() == 2) {
            return funcChoicer.choiceResp(resp, context);
        }
        return null;
    }



}
