package cn.graydove.weblog.controller.admin;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Blog;
import cn.graydove.weblog.pojo.Comments;
import cn.graydove.weblog.service.BlogService;
import cn.graydove.weblog.service.CommentsService;
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
@RequestMapping("/admin")
public class AdminCommentsController {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentsService commentsService;


    @PostMapping("/comment")
    public String uploadComment(Comments comments, int userId){
        if(comments==null || comments.getContent()==null){
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        }
        Blog blog = blogService.selBlogByBlogId(comments.getBlogId());
        if(blog==null){
            return ReturnUtil.retJson(ServerStatus.RESOURCE_NOT_FOUND);
        }
        comments.setUserId(userId);
        try {
            int c = commentsService.insComment(comments);
            if(c==0)
                return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
        } catch (ParamException e) {
            log.error(e.toString(),e);
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }
        return ReturnUtil.retJson(ServerStatus.OK);
    }

    @DeleteMapping("/comment")
    public String delComment(int commentId,int userId){
        Comments comments = commentsService.selCommentsByCommentsId(commentId);

        if(comments==null){
            return ReturnUtil.retJson(ServerStatus.RESOURCE_NOT_FOUND);
        }

        if(comments.getUserId()!=userId){
            Blog blog = blogService.selBlogByBlogId(comments.getBlogId());
            if(blog==null){
                return ReturnUtil.retJson(ServerStatus.RESOURCE_NOT_FOUND);
            }
            if(blog.getUserId()!=userId){
                return ReturnUtil.retJson(ServerStatus.FORBIDDEN);
            }
        }

        int c = commentsService.delByCommentsId(commentId);

        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);

        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }
}

