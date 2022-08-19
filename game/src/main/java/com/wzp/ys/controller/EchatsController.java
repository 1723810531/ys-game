package com.wzp.ys.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzp.ys.model.RoleBasic;
import com.wzp.ys.service.EchatsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "echats图表分析")
@RestController
@CrossOrigin
@RequestMapping("/Rest/echats")
public class EchatsController {
    @Resource
    private EchatsService echatsService;

    @PostMapping("echatsEm")
    public JSONObject echatsEm(HttpServletResponse response) throws Exception {
        try {
            JSONObject addData = echatsService.echatsEm();
            return addData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
