package cn.graydove.blog.service.impl;

import cn.graydove.blog.exceptions.NotFoundException;
import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.mapper.BlogMapper;
import cn.graydove.blog.pojo.Blog;
import cn.graydove.blog.service.BlogService;

import javax.annotation.Resource;
import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Override
    public int insBlog(Blog blog) throws ParamException {
        if(blog==null)
            throw new ParamException("blog is null");

        if(blog.getContent()==null)
            throw new ParamException("blog's content is null");
        return blogMapper.insBlog(blog);
    }

    @Override
    public Blog selBlogByBlogId(int blogId) {
        return blogMapper.selBlogByBlogId(blogId);
    }

    @Override
    public List<Blog> selBlogsByUserId(int userId) {
        return blogMapper.selBlogsByUserId(userId);
    }

    @Override
    public int delBlogByBlogId(int blogId) {
        return blogMapper.delBlogByBlogId(blogId);
    }

    @Override
    public int updateBlog(Blog blog) throws ParamException, NotFoundException {
        if(blog==null)
            throw new ParamException("blog is null");

        Blog b = blogMapper.selBlogByBlogId(blog.getBlogId());
        if(b==null)
            throw new NotFoundException("blog is not exist");

        if(blog.getContent()==null)
            throw new ParamException("blog's content is null");

        b.setContent(blog.getContent());

        return blogMapper.updateBlog(blog);
    }
}
