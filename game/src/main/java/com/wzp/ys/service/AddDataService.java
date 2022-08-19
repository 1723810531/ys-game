package com.wzp.ys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzp.ys.dao.AddDataDao;
import com.wzp.ys.model.Relics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddDataService {

    @Resource
    private AddDataDao addDataDao;

    public JSONArray selectRelicsName() throws Exception{
        List<Relics> list =  addDataDao.selectRelicsName();
        JSONArray nameList = new JSONArray();
        for (int i = 0;i<list.size();i++){
            JSONObject nameMap = new JSONObject();
            Relics relics = list.get(i);
            nameMap.put("label",relics.getName());
            nameMap.put("value",relics.getRelicsNum());
            nameList.add(nameMap);
        }
        return nameList;
    }

    //圣遗物信息展示
    public List<Relics> selectRelics(Relics data) throws Exception{
        List<Relics> list =  addDataDao.selectRelics(data);
        return list;
    }

    //圣遗物信息展示 分页
    public Map<String ,Object> selectRelicsFy(Relics data) throws Exception{
        Map<String ,Object> map = new HashMap<>();
        List<Relics> list =  selectRelics(data);
        int total = list.size();
        int start = (data.getPage()-1) * data.getPageSize();
        int end = (data.getPage()) * data.getPageSize();
        List<Relics> list2 = new ArrayList<>();
        for(int i = start;i<end;i++){
            if(i<total){
                Relics object = list.get(i);
                list2.add(object);
            }else{
                break;
            }
        }
        map.put("rows", list2);
        map.put("total", total);
        return map;
    }

    //导入各省数据
    public String relicsInfo(JSONArray data) {
        List<Relics> list = new ArrayList<>();
        List<Relics> nameList =  addDataDao.selectRelicsName();
        Map<String,String> nameMap = new HashMap<>();
        for(int i =0;i<nameList.size();i++){
            Relics relics = nameList.get(i);
            nameMap.put(relics.getName(),relics.getRelicsNum());
        }
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> map = (Map<String, String>) data.get(i);
            Relics relics = new Relics();
            String name = nameMap.get(map.get("name"));
            relics.setRelicsNum(name);
            String parts = map.get("parts");
            if(parts.equals("生之花")){
                parts = "1";
            }else if(parts.equals("死之羽")){
                parts = "2";
            }else if(parts.equals("时之沙")){
                parts = "3";
            }else if(parts.equals("空之杯")){
                parts = "4";
            }else if(parts.equals("理之冠")){
                parts = "5";
            }
            relics.setPartsNum(parts);
            relics.setAtk(map.get("atk"));
            relics.setDef(map.get("def"));
            relics.setHp(map.get("hp"));
            relics.setAtks(map.get("atks"));
            relics.setDefs(map.get("defs"));
            relics.setHps(map.get("hps"));
            relics.setEm(map.get("em"));
            relics.setEc(map.get("ec"));
            relics.setHit(map.get("hit"));
            relics.setFatk(map.get("fatk"));
            relics.setAps(map.get("aps"));
            relics.setAds(map.get("ads"));
            relics.setTmt(map.get("tmt"));
            list.add(relics);
        }
        addDataDao.insertRelics(list);
        String res = "上传成功";
        return res;
    }
}
