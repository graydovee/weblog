package cn.graydove.weblog.controller;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.JwtUser;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.UserService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth")
    public String registerUser(User user){
        log.info("register:"+user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int c;
        try {
            c = userService.insUser(user);
        } catch (ParamException e) {
            e.printStackTrace();
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }
        if(c==1)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }


    @GetMapping("/auth")
    public String checkUsername(String username){
        if(username==null || username.equals("")){
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        }
        if(((JwtUser)userDetailsService.loadUserByUsername(username)).getUser()==null){
            return ReturnUtil.retJson(ServerStatus.OK);
        }
        return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
    }
}
