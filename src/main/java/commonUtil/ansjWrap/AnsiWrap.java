package commonUtil.ansjWrap;

import lillian.common.spring.BaseTest;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/12.
 */
public class AnsiWrap {
    private static final String DIC_SONG = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\1.txt";
    private static final String DIC_ARTIST = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\0.txt";
    private static final String DIC_ALBUM = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\2.txt";
    private static final String DIC_STYLE = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\3.txt";
    private static final String DIC_TAG = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\4.txt";

    private Forest forest_song;
    private Forest forest_artist;
    private Forest forest_album;
    private Forest forest_style;
    private Forest forest_tag;


    public void initDict() {
        try {
            forest_song = Library.makeForest(DIC_SONG);
            forest_artist = Library.makeForest(DIC_ARTIST);
            forest_album = Library.makeForest(DIC_ALBUM);
            forest_style = Library.makeForest(DIC_STYLE);
            forest_tag = Library.makeForest(DIC_TAG);

            UserDefineLibrary.loadLibrary(forest_album, DIC_ALBUM);
            UserDefineLibrary.loadLibrary(forest_song, DIC_SONG);
            UserDefineLibrary.loadLibrary(forest_artist, DIC_ARTIST);
            UserDefineLibrary.loadLibrary(forest_style, DIC_STYLE);
            UserDefineLibrary.loadLibrary(forest_tag, DIC_TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] split(String text) {
        List<String> lst = new ArrayList<String>();
        List<org.ansj.domain.Term> terms = ToAnalysis.parse(text, forest_song, forest_artist, forest_album, forest_style, forest_tag).getTerms();
        for (org.ansj.domain.Term term : terms) {
            lst.add(term.getRealName());
        }
        return lst.toArray(new String[lst.size()]);
    }

    public static void main(String[] args) {
        AnsiWrap ansiWrap = (AnsiWrap)BaseTest.getAc().getBean("ansiWrap");
        String[] result = ansiWrap.split("梁静茹的心里藏着你百万全纪录 新歌+精选");
        for (String str : result) {
            System.out.println("str = " + str);
        }
    }
}
