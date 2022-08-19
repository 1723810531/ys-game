package com.wzp.ys.controller;

import com.wzp.ys.model.RoleBasic;
import com.wzp.ys.service.InjuryExpectationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "伤害期望")
@RestController
@CrossOrigin
@RequestMapping("/Rest/expectation")
public class InjuryExpectationController {
    @Resource
    private InjuryExpectationService injuryExpectationService;

    @PostMapping("selectInjury")
    public String injury(HttpServletResponse response, @RequestBody RoleBasic data) throws Exception {
        try {
            String addData = injuryExpectationService.injury(data);
            return addData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
