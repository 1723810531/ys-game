package com.wzp.ys.controller;

import com.wzp.ys.model.RoleBasic;
import com.wzp.ys.model.User;
import com.wzp.ys.service.InjuryExpectationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import com.wzp.ys.service.UserServiceImpl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户登录")
@RestController
@CrossOrigin
@RequestMapping("/Rest/user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @PostMapping("userLogin")
    public String userLogin(HttpServletResponse response, @RequestBody User data) throws Exception {
        try {
            String user = userServiceImpl.userLogin(data);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return "错误";
        }
    }

}
