package cn.graydove.blog.service.impl;

import cn.graydove.blog.exceptions.NotFoundException;
import cn.graydove.blog.exceptions.ParamException;
import cn.graydove.blog.mapper.FolderMapper;
import cn.graydove.blog.pojo.Folder;
import cn.graydove.blog.service.FolderService;

import javax.annotation.Resource;
import java.util.List;

public class FolderServiceImpl implements FolderService {

    @Resource
    FolderMapper folderMapper;

    @Override
    public List<Folder> selFolderByUserId(int userId) {
        return folderMapper.selFolderByUserId(userId);
    }

    @Override
    public List<Folder> selNotPrivateFolderByUserId(int userId) {
        return folderMapper.selNotPrivateFolderByUserId(userId);
    }

    @Override
    public int insFloder(Folder folder) throws ParamException {
        if(folder==null)
            throw new ParamException("folder is null");
        return folderMapper.insFloder(folder);
    }

    @Override
    public int delFolderByFolderId(int folderId) {
        return folderMapper.delFolderByFolderId(folderId);
    }

    @Override
    public int updFolderName(Folder folder) throws ParamException, NotFoundException {
        if(folder==null)
            throw new ParamException("folder is null");
        Folder f = folderMapper.selFolderByFolderId(folder.getFolderId());

        if(f==null)
            throw new NotFoundException("folder is not exist");
        return folderMapper.updFolderName(folder);
    }
}
