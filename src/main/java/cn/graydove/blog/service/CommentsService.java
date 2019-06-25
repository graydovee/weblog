package cn.graydove.blog.service;

import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.pojo.Comments;

import java.util.List;

public interface CommentsService {
    int insComment(Comments comments) throws ParamException;

    List<Comments> selCommentsByBlogId(int blogId);

    List<Comments> selCommentsByUserId(int userId);

    int delByCommentsId(int commentsId);
}
