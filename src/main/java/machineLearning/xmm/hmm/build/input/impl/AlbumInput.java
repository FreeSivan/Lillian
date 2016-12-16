package machineLearning.xmm.hmm.build.input.impl;



import machineLearning.xmm.hmm.build.input.AbstractInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/13.
 */
public class AlbumInput extends AbstractInput {

    @Override
    protected List<String> dealEachLine(String str) {
        List<String> result = new ArrayList<String>();
        String[] strArr = str.split("\\t");
        if (strArr.length < 3) {
            return null;
        }
        result.add(strArr[2]);
        return result;
    }

    public static void main(String[] args) {
        String orgPath = "D:\\code\\myself\\lillian\\data\\hmm\\input\\album.name";
        String dstPath = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\2.txt";
        AlbumInput dealSongName = new AlbumInput();
        dealSongName.change(orgPath, dstPath);
    }
}
