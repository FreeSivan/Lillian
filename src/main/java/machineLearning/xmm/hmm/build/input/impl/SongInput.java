package machineLearning.xmm.hmm.build.input.impl;

import machineLearning.xmm.hmm.build.input.AbstractInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiwen.yxw on 2016/12/13.
 */
public class SongInput extends AbstractInput {

    @Override
    protected List<String> dealEachLine(String str) {
        String[] strArr = str.split("\\t");
        if (strArr.length < 3) {
            return null;
        }
        String songName = strArr[2];
        if (songName.contains("(")) {
            songName = songName.substring(0, songName.indexOf("("));
        }
        if (songName.contains("（")) {
            songName = songName.substring(0, songName.indexOf("（"));
        }
        //if (songName.contains("-")) {
        //    songName = songName.substring(0, songName.indexOf("-"));
        //}
        if (songName.contains("_")) {
            songName = songName.substring(0, songName.indexOf("_"));
        }
        if (songName.contains("【")) {
            return null;
        }
        if (songName.contains("《")) {
            return null;
        }
        songName = songName.trim();
        if (songName.length() > 15) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        result.add(songName);
        return result;
    }

    public static void main(String[] args) {
        String orgPath = "D:\\code\\myself\\lillian\\data\\hmm\\input\\song.name";
        String dstPath = "D:\\code\\myself\\lillian\\data\\hmm\\dict\\1.txt";
        SongInput dealSongName = new SongInput();
        dealSongName.change(orgPath, dstPath);
    }
}
