package cn.graydove.weblog.controller.admin;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.UserService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    @Resource
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth")
    public String updateMessage(User user, String userId){
        if(user==null)
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        log.info(user.toString());

        user.setUserId(Integer.parseInt(userId));
        int c;
        try {
            c = userService.updMsg(user);
        } catch (ParamException e) {
            log.error(e.toString(), e);
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.OK);
    }

    @PutMapping("/auth")
    public String updatePassword(String password,String userId){
        if(password==null)
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        password = bCryptPasswordEncoder.encode(password);
        int c = userService.updPwd(Integer.parseInt(userId),password);
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }

    @GetMapping("/auth")
    public String getPersonalInfo(String userId){
        User user = userService.selUserByUserId(Integer.parseInt(userId));
        if(user==null){
            ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        }
        return ReturnUtil.retJson(user);
    }
}
