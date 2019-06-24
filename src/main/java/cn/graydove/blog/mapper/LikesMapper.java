package cn.graydove.blog.mapper;

import cn.graydove.blog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikesMapper {
    @Insert("insert into likes value(default,#{blog},#{user})")
    int insLike(@Param("blog") int blogId, @Param("user") int UserId);

    @Insert("delete from likes where blog_id=#{blog} and user_id=#{user}")
    int delLike(@Param("blog") int blogId,@Param("user") int UserId);

    @Select("select count(*) from likes where blog_id=#{0}")
    int selLikeNumberByBlogId(int blogId);

    @Select("select * from user where user_id in (select user_id from likes where blog_id=#{0})")
    List<User> selAllLikedUserByBlogId(int blogId);
}
