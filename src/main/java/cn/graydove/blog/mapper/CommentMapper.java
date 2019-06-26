package cn.graydove.blog.mapper;

import cn.graydove.blog.pojo.Comments;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comments values(default,#{content},now(),#{userId},#{blogId})")
    int insComment(Comments comments);

    @Select("select * from comments where blog_id=#{0}")
    List<Comments> selCommentsByBlogId(int blogId);

    @Select("select * from comments where user_id=#{0}")
    List<Comments> selCommentsByUserId(int userId);

    @Delete("delete from comments where comments_id=#{0}")
    int delByCommentsId(int commentsId);
}