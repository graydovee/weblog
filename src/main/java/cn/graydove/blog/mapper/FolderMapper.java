package cn.graydove.blog.mapper;

import cn.graydove.blog.pojo.Folder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FolderMapper {
    @Select("select * from folder where user_id=#{0}")
    List<Folder> selFolderByUserId(int UserId);

    @Select("select * from folder where user_id=#{0} and type<>1")
    List<Folder> selNotPrivateFolderByUserId(int UserId);

    @Select("select * from folder where folder_id=#{0}")
    Folder selFolderByFolderId(int folderId);

    @Insert("insert into folder values(default,#{userId},#{name},#{type},#{password},now())")
    int insFloder(Folder folder);

    @Delete("delete from folder where folder_id=#{0}")
    int delFolderByFolderId(int folderId);

    @Update("update folder set name=#{name} where folder_id=#{folderId}")
    int updFolderName(Folder folder);

}
