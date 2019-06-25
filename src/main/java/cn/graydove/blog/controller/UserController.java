package cn.graydove.blog.controller;

import cn.graydove.blog.pojo.User;
import cn.graydove.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    public int register(User user){
        int c = userService.insUser(user);

        if(c==1){
            return 200;
        }

        return 500;
    }
}
