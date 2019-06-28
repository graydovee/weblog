package cn.graydove.weblog.service;

import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Comments;

import java.util.List;

public interface CommentsService {
    int insComment(Comments comments) throws ParamException;

    List<Comments> selCommentsByBlogId(int blogId);

    List<Comments> selCommentsByUserId(int userId);

    Comments selCommentsByCommentsId(int commentsId);

    int delByCommentsId(int commentsId);
}
