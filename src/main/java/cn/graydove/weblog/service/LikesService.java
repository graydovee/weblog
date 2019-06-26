package cn.graydove.weblog.service;

import cn.graydove.weblog.pojo.User;

import java.util.List;

public interface LikesService {
    int insLike(int blogId, int userId);

    int delLike(int blogId, int userId);

    int selLikeNumberByBlogId(int blogId);

    List<User> selAllLikedUserByBlogId(int blogId);
}
