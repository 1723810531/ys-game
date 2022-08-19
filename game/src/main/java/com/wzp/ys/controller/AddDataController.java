package com.wzp.ys.controller;

import com.alibaba.fastjson.JSONArray;
import com.wzp.ys.model.Relics;
import com.wzp.ys.service.AddDataService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api(tags = "基础数据导入，查询")
@RestController
@CrossOrigin
@RequestMapping("/Rest/addData")
public class AddDataController {
    @Resource
    private AddDataService addDataService;

    @PostMapping("selectRelicsName")
    public JSONArray selectRelicsName(HttpServletResponse response) throws Exception {
        try {
            JSONArray addData = addDataService.selectRelicsName();
            return addData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //圣遗物信息展示
    @PostMapping("selectRelics")
    public List<Relics> selectRelics(HttpServletResponse response, @RequestBody Relics data) throws Exception {
        try {
            List<Relics> addData = addDataService.selectRelics(data);
            return addData;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }

    //圣遗物信息展示 分页
    @PostMapping("selectRelicsFy")
    public Map<String ,Object> selectRelicsFy(HttpServletResponse response, @RequestBody Relics data) throws Exception {
        try {
            Map<String ,Object> addData = addDataService.selectRelicsFy(data);
            return addData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //导入圣遗物信息
    @PostMapping("relicsInfo")
    public String relicsInfo(HttpServletResponse response, @RequestBody JSONArray data) throws Exception {
        try {
            String addData = addDataService.relicsInfo(data);
            return addData;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
}
