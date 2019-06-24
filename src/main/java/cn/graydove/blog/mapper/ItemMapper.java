package cn.graydove.blog.mapper;

import cn.graydove.blog.pojo.Folder;
import cn.graydove.blog.pojo.Items;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {


    @Select("select * from items where folder_id=#{0}")
    List<Items> selItemByFolderId(int FolderId);

    @Insert("insert into items values(default,#{uuid},#{name},#{format},now(),#{folderId})")
    int insItem(Items item);

    @Delete("delete from items where items_id=#{0}")
    int delItemByItemId(int itemId);
}
