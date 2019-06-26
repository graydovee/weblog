package cn.graydove.weblog.controller;

import cn.graydove.weblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @GetMapping("/tst")
    public String tst(int userId){
        return "Hello!!!"+userId;
    }
}
