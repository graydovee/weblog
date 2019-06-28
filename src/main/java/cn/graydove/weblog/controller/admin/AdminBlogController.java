package cn.graydove.weblog.controller.admin;

import cn.graydove.weblog.enums.ServerStatus;
import cn.graydove.weblog.exceptions.NotFoundException;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Blog;
import cn.graydove.weblog.pojo.Comments;
import cn.graydove.weblog.service.BlogService;
import cn.graydove.weblog.service.CommentsService;
import cn.graydove.weblog.uitls.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminBlogController {
    @Resource
    private BlogService blogService;

    @Resource
    private CommentsService commentsService;

    @PostMapping("/blog")
    public String addBlog(Blog blog, int userId){
        if(blog==null || blog.getContent()==null)
            return ReturnUtil.retJson(ServerStatus.NULL_PARAM);
        blog.setUserId(userId);
        int c = 0;
        try {
            c = blogService.insBlog(blog);
        } catch (ParamException e) {
            log.error(e.toString(),e);
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }
        if(c>0)
            return ReturnUtil.retJson(ServerStatus.OK);

        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }


    @DeleteMapping("/blog")
    public String delBlog(int blogId,int userId){
        Blog blog = blogService.selBlogByBlogId(blogId);

        if(blog.getUserId()!=userId){
            return ReturnUtil.retJson(ServerStatus.FORBIDDEN);
        }

        int c = blogService.delBlogByBlogId(blogId);
        if(c>0){
            List<Comments> list = commentsService.selCommentsByBlogId(blogId);
            if(list!=null){
                for(Comments com:list){
                    commentsService.delByCommentsId(com.getCommentsId());
                }
            }
            return ReturnUtil.retJson(ServerStatus.OK);
        }
        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }


    @PutMapping("/blog")
    public String updBlog(String content,int blogId,int userId){
        Blog blog = blogService.selBlogByBlogId(blogId);

        if(blog.getUserId()!=userId){
            return ReturnUtil.retJson(ServerStatus.FORBIDDEN);
        }

        blog.setContent(content);
        try {
            int c = blogService.updateBlog(blog);
            if(c>0)
                return ReturnUtil.retJson(ServerStatus.OK);
        } catch (Exception e) {
            log.error(e.toString(),e);
            return ReturnUtil.retJson(ServerStatus.PARAM_ERROR);
        }

        return ReturnUtil.retJson(ServerStatus.SERVER_ERROR);
    }
}
