package cn.graydove.weblog.controller;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.pojo.Blog;
import cn.graydove.weblog.pojo.Comments;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.BlogService;
import cn.graydove.weblog.service.CommentsService;
import cn.graydove.weblog.service.FansService;
import cn.graydove.weblog.service.LikesService;
import cn.graydove.weblog.uitls.ReturnUtil;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class MessageController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentsService commentsService;

    @Resource
    private FansService fansService;

    @Resource
    private LikesService likesService;

    @GetMapping("/blog")
    public String blog(int userId){
        if(userId<=0)
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        List<Blog> blogs = blogService.selBlogsByUserId(userId);

        return ReturnUtil.retJson(blogs);
    }

    @GetMapping("/comment")
    public String getComment(int blogId){
        List<Comments> list = commentsService.selCommentsByBlogId(blogId);

        return ReturnUtil.retJson(list);
    }

    @GetMapping("/like")
    public String getLike(int blogId){
        List<User> users = likesService.selAllLikedUserByBlogId(blogId);
        if(users==null){
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        }
        for(User u:users){
            u.setPassword(null);
        }
        return ReturnUtil.retJson(users);
    }

    @GetMapping("/fans")
    public String getFans(int userId){
        List<User> users = fansService.selFansList(userId);
        for(User u:users){
            u.setPassword(null);
        }
        return ReturnUtil.retJson(users);
    }

    @GetMapping("/focus")
    public String getFocus(int userId){
        List<User> users = fansService.selFocusList(userId);
        for(User u:users){
            u.setPassword(null);
        }

        return ReturnUtil.retJson(users);
    }
}
