package cn.graydove.weblog.service;

import cn.graydove.weblog.exceptions.NotFoundException;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Blog;

import java.util.List;

public interface BlogService {
    int insBlog(Blog blog) throws ParamException;

    Blog selBlogByBlogId(int blogId);

    List<Blog> selBlogsByUserId(int userId);

    int delBlogByBlogId(int blogId);

    int updateBlog(Blog blog) throws ParamException, NotFoundException;
}
