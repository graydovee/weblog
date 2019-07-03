package cn.graydove.weblog.mapper;

import cn.graydove.weblog.pojo.Items;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {


    @Select("select * from items where user_id=#{0}")
    List<Items> selItemByUserId(int userId);

    @Select("select * from items where items_id=#{0}")
    Items selItemByItemsId(int itemsId);

    @Insert("insert into items values(default,#{uuid},#{name},#{format},now(),#{userId})")
    int insItem(Items item);

    @Delete("delete from items where items_id=#{0}")
    int delItemByItemId(int itemId);
}
