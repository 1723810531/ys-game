package com.wzp.ys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzp.ys.dao.AddDataDao;
import com.wzp.ys.model.Echats;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EchatsService {

    @Resource
    private AddDataDao addDataDao;

    public JSONObject echatsEm() throws Exception{
        JSONObject jsonObject = new JSONObject();
        List<Echats> emPowerList = addDataDao.selectEm();
        JSONArray emList = new JSONArray();
        JSONArray ind = new JSONArray();
        JSONArray inc = new JSONArray();
        JSONArray fn = new JSONArray();
        JSONArray fnDo = new JSONArray();
        JSONArray fnDc = new JSONArray();
        JSONArray cl = new JSONArray();
        JSONArray clDo = new JSONArray();
        for(int i = 0;i< emPowerList.size();i++){
            Echats echats = emPowerList.get(i);
            String increaseData = echats.getIncreaseData();
            String increaseCount = echats.getIncreaseCount();
            String fusion = echats.getFusion();
            String fusionDio = echats.getFusionDio();
            String fusionDic = echats.getFusionDic();
            String crystal = echats.getCrystal();
            String crystalDio = echats.getCrystalDio();
            emList.add(echats.getEm());
            ind.add(increaseData);
            inc.add(increaseCount);
            fn.add(fusion);
            fnDo.add(fusionDio);
            fnDc.add(fusionDic);
            cl.add(crystal);
            clDo.add(crystalDio);
        }
        JSONObject emPowerObject = new JSONObject();
        emPowerObject.put("ind",ind);
        emPowerObject.put("inc",inc);
        emPowerObject.put("fn",fn);
        emPowerObject.put("fnDo",fnDo);
        emPowerObject.put("fnDc",fnDc);
        emPowerObject.put("cl",cl);
        emPowerObject.put("clDo",clDo);

        String [] yName = {"增幅提升","增幅提升拟合","聚变提升","聚变/实际增幅","聚变/拟合增幅","结晶提升","结晶/实际增幅"};
        String [] yData = {"ind","inc","fn","fnDo","fnDc","cl","clDo"};
        JSONArray seriesList = new JSONArray();
        for(int i = 0;i<yData.length;i++){
            JSONObject seriesObj = new JSONObject();
            seriesObj.put("name",yName[i]);
            seriesObj.put("type","line");
            seriesObj.put("symbol","none");
            seriesObj.put("data",emPowerObject.get(yData[i]));
            seriesList.add(seriesObj);
        }
        jsonObject.put("emList",emList);
        jsonObject.put("yName",yName);
        jsonObject.put("seriesList",seriesList);
        return jsonObject;
    }
}
