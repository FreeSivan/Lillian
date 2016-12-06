package lillian.neural.service.serviceInverted;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public interface IInverted {
    /**
     *
     * @param words the array of query split words
     * @return the array of docId
     */
    public Integer[] searchDoc(String[] words);

    /**
     *
     * @param path
     */
    public void load(String path);
}
