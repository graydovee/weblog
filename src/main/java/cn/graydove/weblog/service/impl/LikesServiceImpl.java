package cn.graydove.weblog.service.impl;

import cn.graydove.weblog.mapper.LikesMapper;
import cn.graydove.weblog.pojo.User;
import cn.graydove.weblog.service.LikesService;

import javax.annotation.Resource;
import java.util.List;

public class LikesServiceImpl implements LikesService {

    @Resource
    LikesMapper likesMapper;

    @Override
    public int insLike(int blogId, int userId) {
        return likesMapper.insLike(blogId,userId);
    }

    @Override
    public int delLike(int blogId, int userId) {
        return likesMapper.delLike(blogId, userId);
    }

    @Override
    public int selLikeNumberByBlogId(int blogId) {
        return likesMapper.selLikeNumberByBlogId(blogId);
    }

    @Override
    public List<User> selAllLikedUserByBlogId(int blogId) {
        return likesMapper.selAllLikedUserByBlogId(blogId);
    }
}
