package cn.graydove.blog.service.impl;

import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.mapper.CommentMapper;
import cn.graydove.blog.pojo.Comments;
import cn.graydove.blog.service.CommentsService;

import javax.annotation.Resource;
import java.util.List;

public class CommentsServiceImpl implements CommentsService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public int insComment(Comments comments) throws ParamException {
        if(comments==null)
            throw new ParamException("comments is null");
        return commentMapper.insComment(comments);
    }

    @Override
    public List<Comments> selCommentsByBlogId(int blogId) {
        return commentMapper.selCommentsByBlogId(blogId);
    }

    @Override
    public List<Comments> selCommentsByUserId(int userId) {
        return commentMapper.selCommentsByUserId(userId);
    }

    @Override
    public int delByCommentsId(int commentsId) {
        return commentMapper.delByCommentsId(commentsId);
    }
}
