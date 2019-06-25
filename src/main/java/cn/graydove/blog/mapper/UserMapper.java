package cn.graydove.blog.mapper;

import cn.graydove.blog.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user values(default,#{username},#{password},#{nickname},null,null,null)")
    int insUser(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User selUserByUsernameAndPassword(User user);

    @Select("select * from user where user_id=#{0}")
    User selUserByUserId(int userId);

    @Update("update user set password=#{password} where user_id=#{userId}")
    int updatePassword(User user);

    @Update("update user set nickname=#{nickname},sex=#{sex},birth=#{birth},sign=#{sign} where user_id=#{userId}")
    int updateMessage(User user);

    @Delete("delete from user where user_id=#{0}")
    int delUserByUserId(int id);

    //粉丝操作

    @Select("select * from user where user_id in (select focuser from fans where focused=#{0})")
    List<User> getFansList(int id);

    @Select("select count(*) from fans where focused=#{0}")
    Integer getFansNumber(int id);

    @Select("select * from user where user_id in (select focused from fans where focuser=#{0})")
    List<User> getFocusList(int id);

    @Select("select count(*) from fans where focuser=#{0}")
    int getFocusNumber(int id);

    @Insert("insert into fans values(default,#{focuser},#{focused})")
    int focus(@Param("focuser") int focuser, @Param("focused")int focused);

    @Delete("delete from fans where focuser=#{focuser} and focused=#{focused}")
    int defocus(@Param("focuser") int focuser, @Param("focused")int focused);

}
