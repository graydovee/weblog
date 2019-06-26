package cn.graydove.weblog.controller;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
