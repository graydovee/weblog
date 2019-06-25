package cn.graydove.blog.service;

import cn.graydove.blog.pojo.User;

import java.util.List;

public interface LikesService {
    int insLike(int blogId, int userId);

    int delLike(int blogId, int userId);

    int selLikeNumberByBlogId(int blogId);

    List<User> selAllLikedUserByBlogId(int blogId);
}
