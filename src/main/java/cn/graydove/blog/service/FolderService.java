package cn.graydove.blog.service;

import cn.graydove.blog.exceptions.NotFoundException;
import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.pojo.Folder;

import java.util.List;

public interface FolderService {
    List<Folder> selFolderByUserId(int userId);

    List<Folder> selNotPrivateFolderByUserId(int userId);

    int insFloder(Folder folder) throws ParamException;

    int delFolderByFolderId(int folderId);

    int updFolderName(Folder folder) throws NotFoundException, ParamException;
}
