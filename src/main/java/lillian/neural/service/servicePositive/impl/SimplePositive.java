package lillian.neural.service.servicePositive.impl;

import lillian.neural.parse.wordsplit.ISplit;
import lillian.neural.service.serviceBean.ReqMeta;
import lillian.neural.service.serviceBean.ResMeta;
import lillian.neural.service.servicePositive.IPositive;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * Created by xiwen.yxw on 2016/11/25.
 */
public class SimplePositive implements IPositive {

    private Map<Integer, MetaData> postTable = new HashMap<Integer, MetaData>();

    @Override
    public ReqMeta[] searchReq(Integer[] docId) {
        List<ReqMeta> reqLst = new ArrayList<ReqMeta>();
        for (Integer val : docId) {
            MetaData metaData = postTable.get(val);
            if (metaData == null) {
                continue;
            }
            reqLst.add(metaData.reqMeta);
        }
        return reqLst.toArray(new ReqMeta[reqLst.size()]);
    }

    @Override
    public ResMeta searchResp(Integer docId) {
        return postTable.get(docId).resMeta;
    }

    @Override
    public void load(String path) {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String str;
            while ((str = br.readLine()) != null) {
                String[] strArr = str.split("\\|");
                if (strArr.length != 3) {
                    continue;
                }
                Integer docId = Integer.parseInt(strArr[0]);
                MetaData metaData = new MetaData();
                metaData.reqMeta.setQuery(strArr[1]);
                metaData.reqMeta.setDocId(docId);
                setResMeta(metaData.resMeta, strArr[2]);
                postTable.put(docId, metaData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setResMeta(ResMeta resMeta, String s) {
        String[] strArr = s.split("#");
        if (strArr.length != 2) {
            return;
        }
        resMeta.setStyle(Integer.parseInt(strArr[0]));
        if (resMeta.getStyle() == 0) {
            resMeta.setSingleResponse(strArr[1]);
        }
        else if (resMeta.getStyle() == 1) {
            String[] resArr = strArr[1].split("\\^");
            List<String> strLst = new ArrayList<String>();
            for (String str : resArr) {
                if (str.trim().isEmpty()) {
                    continue;
                }
                strLst.add(str.trim());
            }
            resMeta.setRandomResponse(strLst);
        }
        else if (resMeta.getStyle() == 2){
            resMeta.setFunctionName(strArr[1]);
        }
    }

    public static class MetaData {
        public ReqMeta reqMeta;
        public ResMeta resMeta;

        public MetaData() {
            reqMeta = new ReqMeta();
            resMeta = new ResMeta();
        }
    }

}
