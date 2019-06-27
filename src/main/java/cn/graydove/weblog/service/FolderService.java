package cn.graydove.weblog.service;

import cn.graydove.weblog.exceptions.NotFoundException;
import cn.graydove.weblog.exceptions.ParamException;
import cn.graydove.weblog.pojo.Folder;

import java.util.List;

public interface FolderService {
    List<Folder> selFolderByUserId(int userId);

    List<Folder> selNotPrivateFolderByUserId(int userId);

    Folder selFolderByFolderId(int FolderId);

    int insFloder(Folder folder) throws ParamException;

    int delFolderByFolderId(int folderId);

    int updFolderName(Folder folder) throws NotFoundException, ParamException;
}
