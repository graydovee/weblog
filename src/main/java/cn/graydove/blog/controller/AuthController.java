package cn.graydove.blog.controller;

import cn.graydove.blog.enums.ServerStatus;
import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.pojo.User;
import cn.graydove.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    // 为了减少篇幅就不写service接口了
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public String registerUser(User user){
        log.info("register:"+user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int c;
        try {
            c = userService.insUser(user);
        } catch (ParamException e) {
            e.printStackTrace();
            return ServerStatus.PARAM_ERROR.toString();
        }
        if(c==1)
            return ServerStatus.OK.toString();
        return ServerStatus.SERVER_ERROR.toString();
    }
}
