package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.mapper.CommentMapper;
import cn.graydove.weblog.pojo.Comments;
import cn.graydove.weblog.service.CommentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
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
    public Comments selCommentsByCommentsId(int commentsId) {
        return commentMapper.selCommentsByCommentsId(commentsId);
    }

    @Override
    public int delByCommentsId(int commentsId) {
        return commentMapper.delByCommentsId(commentsId);
    }
}
