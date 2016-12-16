package machineLearning.xmm.hmm.build.input.impl;

import machineLearning.xmm.hmm.build.input.AbstractInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/13.
 */
public class ArtistInput extends AbstractInput {

    @Override
    protected List<String> dealEachLine(String str) {
        List<String> result = new ArrayList<String>();
        String[] strArr = str.split("\\t");
        if (strArr.length < 3) {
            return null;
        }
        String artistName = strArr[2];
        result.add(artistName.trim());
        if (strArr.length == 4) {
            String addStr = strArr[3];
            String[] addArr = addStr.split("\\/");
            for (String item : addArr) {
                result.add(item.trim());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String orgPath = "D:\\code\\myself\\lillian\\data\\hmm\\input\\artist.name";
        String dstPath = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\0.txt";
        ArtistInput dealSongName = new ArtistInput();
        dealSongName.change(orgPath, dstPath);
    }
}
