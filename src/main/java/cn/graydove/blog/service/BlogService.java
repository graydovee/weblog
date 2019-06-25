package cn.graydove.blog.service;

import cn.graydove.blog.exceptions.NotFoundException;
import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.pojo.Blog;

import java.util.List;

public interface BlogService {
    int insBlog(Blog blog) throws ParamException;

    Blog selBlogByBlogId(int blogId);

    List<Blog> selBlogsByUserId(int userId);

    int delBlogByBlogId(int blogId);

    int updateBlog(Blog blog) throws ParamException, NotFoundException;
}
