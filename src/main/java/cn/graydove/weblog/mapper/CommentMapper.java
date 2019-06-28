package cn.graydove.weblog.mapper;

import cn.graydove.weblog.pojo.Comments;
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

    @Select("select * from comments where comments_id=#{0}")
    Comments selCommentsByCommentsId(int commentsId);

    @Delete("delete from comments where comments_id=#{0}")
    int delByCommentsId(int commentsId);
}
