package com.wzp.ys.service;

import com.wzp.ys.dao.UserMapper;
import com.wzp.ys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.security.MessageDigest;

import static com.wzp.utlis.EncryptionTool.md5Encode;


@Service
public class UserServiceImpl {
    @Resource
    private UserMapper userMapper;

    public String userLogin(User data) throws Exception{

        String passWord1 = data.getPassWord();
        String passWord2 = userMapper.userPassword(data.getUserName());

        String passWordMD5 = md5Encode(passWord2);
        String txt = "false";
        if(passWord1.equals(passWordMD5)){
            txt = "true";
        }

        return txt;
    }


}
