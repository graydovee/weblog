package cn.graydove.weblog.mapper;

import cn.graydove.weblog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikesMapper {
    @Insert("insert into likes value(default,#{weblog},#{user})")
    int insLike(@Param("weblog") int blogId, @Param("user") int UserId);

    @Insert("delete from likes where blog_id=#{weblog} and user_id=#{user}")
    int delLike(@Param("weblog") int blogId,@Param("user") int UserId);

    @Select("select count(*) from likes where blog_id=#{0}")
    int selLikeNumberByBlogId(int blogId);

    @Select("select * from user where user_id in (select user_id from likes where blog_id=#{0})")
    List<User> selAllLikedUserByBlogId(int blogId);
}
