package cn.graydove.weblog.mapper;

import cn.graydove.weblog.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Insert("insert into blog values(default,#{content},now(),#{userId})")
    int insBlog(Blog blog);

    @Select("select * from blog where blog_id=#{0}")
    Blog selBlogByBlogId(int blogId);

    @Select("select * from blog where user_id=#{0}")
    List<Blog> selBlogsByUserId(int userId);

    @Delete("delete from blog where blog_id=#{0}")
    int delBlogByBlogId(int blogId);

    @Update("update blog set content=#{content} where blog_id=#{blogId}")
    int updateBlog(Blog blog);

}
