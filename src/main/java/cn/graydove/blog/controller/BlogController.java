package cn.graydove.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BlogController {

    @GetMapping("/blog")
    public String blog(String userId){
        return "test!!!!!!"+userId;
    }
}
