package lillian.neural.service.serviceBean;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public class ReqMeta {

    private Integer docId;

    private String query;

    private String[] words;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getWords() {
        return words;
    }

    public void setWords(String[] words) {
        this.words = words;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }
}
