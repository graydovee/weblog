package cn.graydove.weblog.controller.admin;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.service.FansService;
import cn.graydove.weblog.service.LikesService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("admin")
public class AdminFansController {

    @Resource
    private FansService fansService;

    @Resource
    private LikesService likesService;
    
    @PostMapping("/like")
    public String like(int blogId,int userId){
        int c = likesService.insLike(blogId,userId);
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }
    
    @PostMapping("/focus")
    public String focus(int id,int userId){
        int c = fansService.insfocus(userId, id);
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }

    @DeleteMapping("/focus")
    public String defocus(int id,int userId){
        int c = fansService.delfocus(userId, id);
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }
    
}
